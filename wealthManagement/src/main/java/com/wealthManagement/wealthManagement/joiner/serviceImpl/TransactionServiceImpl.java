package com.wealthManagement.wealthManagement.joiner.serviceImpl;

import com.wealthManagement.wealthManagement.joiner.service.TransactionService;
import com.wealthManagement.wealthManagement.mybatis.mapper.*;
import com.wealthManagement.wealthManagement.pojo.dto.*;
import com.wealthManagement.wealthManagement.pojo.requestBody.MutualFund;
import com.wealthManagement.wealthManagement.pojo.requestBody.MutualFund.Scheme;
import com.wealthManagement.wealthManagement.pojo.requestBody.TransactionDataRequest;
import com.wealthManagement.wealthManagement.pojo.requestBody.TransactionEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionMapper       transactionMapper;
    private final UserMapper              userMapper;
    private final PortfolioMapper         portfolioMapper;
    private final AssetMapper             assetMapper;
    private final AssetPriceHistoryMapper assetPriceHistoryMapper;
    private final HoldingsSnapshotMapper  holdingsSnapshotMapper;
    private final PortfolioSnapshotMapper portfolioSnapshotMapper;

    private static final DateTimeFormatter DATE_FMT            = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final long              MUTUAL_FUND_TYPE_ID = 1L;

    @Override
    public List<TransactionDataRequest> addTransactions(List<TransactionDataRequest> requests) {
        if (CollectionUtils.isEmpty(requests)) {
            log.warn("Empty transaction request list received");
            return List.of();
        }
        List<TransactionDataRequest> saved = new ArrayList<>();
        for (TransactionDataRequest request : requests) {
            try {
                processCasStatement(request, saved);
            } catch (Exception ex) {
                log.error("Failed to process CAS statement", ex);
            }
        }
        return saved;
    }

    private void processCasStatement(
            TransactionDataRequest request,
            List<TransactionDataRequest> saved
    ) {
        if (request.getInvestor() == null) {
            log.warn("Investor missing, skipping CAS statement");
            return;
        }
        if (CollectionUtils.isEmpty(request.getMutualFunds())) {
            log.warn("No mutual_funds in CAS, skipping");
            return;
        }

        UserDto user = createOrFetchUser(request);

        PortfolioDto portfolio = createOrFetchPortfolio(user);

        for (MutualFund fund : request.getMutualFunds()) {
            if (CollectionUtils.isEmpty(fund.getSchemes())) {
                log.warn("Folio {} has no schemes, skipping", fund.getFolioNumber());
                continue;
            }
            for (Scheme scheme : fund.getSchemes()) {

                AssetDto asset = createOrFetchAsset(fund, scheme, user);

                if (!CollectionUtils.isEmpty(scheme.getTransactions())) {
                    for (TransactionEntry entry : scheme.getTransactions()) {
                        try {
                            insertTransactionIfNew(fund, scheme, entry, user, portfolio, asset);
                        } catch (Exception ex) {
                            log.error("Failed inserting transaction date={} type={}",
                                    entry.getDate(), entry.getType(), ex);
                        }
                    }
                }

                insertAssetPriceHistory(scheme, asset);

                log.info("Inserted transaction scheme={} portfolio={} asset={}",scheme,portfolio,asset);

                insertHoldingsSnapshot(scheme, portfolio, asset);
            }
        }

        if (request.getSummary() != null) {
            insertPortfolioSnapshot(request, portfolio);
        }

        saved.add(request);
        log.info("CAS statement processed: investor={}", request.getInvestor().getName());
    }

    private UserDto createOrFetchUser(TransactionDataRequest request) {
        String pan    = request.getInvestor().getPan();
        String email  = request.getInvestor().getEmail();

        UserDto existing = null;
        if (pan   != null) existing = userMapper.selectUserByPan(pan);
        if (existing == null && email != null) existing = userMapper.selectUserByEmail(email);
        if (existing != null) {
            log.info("User found: id={}", existing.getId());
            return existing;
        }

        UserDto user = UserDto.builder()
                .fullName(request.getInvestor().getName())
                .email(email)
                .mobileNumber(request.getInvestor().getMobile())
                .pan(pan)
                .build();
        userMapper.insertUser(user);
        log.info("User created: id={}", user.getId());
        return user;
    }

    private PortfolioDto createOrFetchPortfolio(UserDto user) {
        PortfolioDto existing = portfolioMapper.selectPortfolioByUserId(user.getId());
        if (existing != null) {
            log.info("Portfolio found: id={}", existing.getId());
            return existing;
        }
        PortfolioDto portfolio = PortfolioDto.builder()
                .userId(user.getId())
                .portfolioName("Mutual Fund Portfolio")
                .portfolioDescription("CAS Imported Portfolio")
                .build();
        portfolioMapper.insertPortfolio(portfolio);
        log.info("Portfolio created: id={}", portfolio.getId());
        return portfolio;
    }

    private AssetDto createOrFetchAsset(MutualFund fund, Scheme scheme, UserDto user) {
        String folio = fund.getFolioNumber();
        String isin  = scheme.getIsin();

        AssetDto existing = assetMapper.getAssetByFolioAndIsin(folio, isin, user.getId());
        if (existing != null) {
            log.info("Asset found: id={} folio={} isin={}", existing.getId(), folio, isin);
            return existing;
        }

        String advisor = scheme.getAdditionalInfo() != null
                ? scheme.getAdditionalInfo().getAdvisor() : null;
        String rta = scheme.getAdditionalInfo() != null
                ? scheme.getAdditionalInfo().getRta() : null;

        AssetDto asset = AssetDto.builder()
                .assetName(scheme.getName())
                .assetCode(isin)
                .folioNumber(folio)
                .assetTypeId(MUTUAL_FUND_TYPE_ID)
                .assetSubType(resolveAssetSubType(scheme.getType()))
                .issuerName(fund.getAmc())
                .userId(user.getId())
                .remarks("Advisor=" + advisor + " | RTA=" + rta
                        + " | Registrar=" + fund.getRegistrar())
                .build();

        assetMapper.insertAsset(asset);
        log.info("Asset created: id={} folio={} isin={}", asset.getId(), folio, isin);
        return asset;
    }

    private void insertTransactionIfNew(
            MutualFund fund, Scheme scheme, TransactionEntry entry,
            UserDto user, PortfolioDto portfolio, AssetDto asset
    ) {
        boolean isStampDuty = "STAMP_DUTY_TAX".equalsIgnoreCase(entry.getType());
        BigDecimal amount   = nvl(entry.getAmount());
        BigDecimal units    = nvl(entry.getUnits());
        BigDecimal nav      = nvl(entry.getNav());
        String     txnRef   = buildTxnRef(fund, scheme, entry);

        Integer count = transactionMapper.checkTransactionExists(
                fund.getFolioNumber(), entry.getDate(),
                entry.getType(), amount, units);
        if (count != null && count > 0) {
            log.info("Duplicate skipped: {}", txnRef);
            return;
        }

        TransactionDto dto = TransactionDto.builder()
                .portfolioId(BigDecimal.valueOf(portfolio.getId()))
                .assetId(BigDecimal.valueOf(asset.getId()))
                .userId(BigDecimal.valueOf(user.getId()))
                .folioNumber(fund.getFolioNumber())
                .transactionType(entry.getType())
                .transactionDate(entry.getDate())
                .totalAmount(amount)
                .quantity(units)
                .pricePerUnit(nav)
                .navValue(nav)
                .unitBalance(entry.getBalance())
                .charges(isStampDuty ? amount : BigDecimal.ZERO)
                .stampDuty(isStampDuty ? amount : BigDecimal.ZERO)
                .interestRate(BigDecimal.ZERO)
                .transactionReference(txnRef)
                .transactionSource("CAS_IMPORT")
                .notes(buildTxnNotes(fund, scheme, entry, user))
                .createdAt(LocalDateTime.now().toString())
                .build();

        transactionMapper.insertTransaction(dto);
        log.info("Transaction inserted: {}", txnRef);
    }

    private void insertAssetPriceHistory(Scheme scheme, AssetDto asset) {
        if (scheme.getNav() == null) return;

        String today = LocalDate.now().format(DATE_FMT);
        Integer exists = assetPriceHistoryMapper.countByAssetAndDate(asset.getId(), today);
        if (exists != null && exists > 0) {
            log.info("Price history exists: assetId={} date={}", asset.getId(), today);
            return;
        }

        assetPriceHistoryMapper.insert(AssetPriceHistoryDto.builder()
                .assetId(asset.getId())
                .priceDate(today)
                .price(scheme.getNav())
                .build());
        log.info("Price history inserted: assetId={} nav={}", asset.getId(), scheme.getNav());
    }

    private void insertHoldingsSnapshot(Scheme scheme, PortfolioDto portfolio, AssetDto asset) {
        if (scheme.getUnits() == null || scheme.getNav() == null) return;

        BigDecimal units       = scheme.getUnits();
        BigDecimal nav         = scheme.getNav();
        BigDecimal curValue    = scheme.getValue() != null
                ? scheme.getValue() : units.multiply(nav);
        BigDecimal invested    = scheme.getCost() != null
                ? scheme.getCost() : BigDecimal.ZERO;

        holdingsSnapshotMapper.upsert(HoldingsSnapshotDto.builder()
                .portfolioId(portfolio.getId())
                .assetId(asset.getId())
                .snapshotDate(LocalDate.now().format(DATE_FMT))
                .totalQuantity(units)
                .investedAmount(invested)
                .currentPrice(nav)
                .currentValue(curValue)
                .profitLoss(curValue.subtract(invested))
                .build());
        log.info("Holdings snapshot upserted: portfolioId={} assetId={}", portfolio.getId(), asset.getId());
    }

    private void insertPortfolioSnapshot(TransactionDataRequest request, PortfolioDto portfolio) {
        BigDecimal totalValue = request.getSummary().getTotalValue() != null
                ? request.getSummary().getTotalValue() : BigDecimal.ZERO;

        BigDecimal totalInvested = request.getMutualFunds().stream()
                .filter(f -> !CollectionUtils.isEmpty(f.getSchemes()))
                .flatMap(f -> f.getSchemes().stream())
                .filter(s -> s.getCost() != null)
                .map(s -> s.getCost())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        portfolioSnapshotMapper.upsert(PortfolioSnapshotDto.builder()
                .portfolioId(portfolio.getId())
                .snapshotDate(LocalDate.now().format(DATE_FMT))
                .investedAmount(totalInvested)
                .currentValue(totalValue)
                .totalProfitLoss(totalValue.subtract(totalInvested))
                .build());
        log.info("Portfolio snapshot upserted: portfolioId={} value={}", portfolio.getId(), totalValue);
    }

    private String buildTxnRef(MutualFund fund, Scheme scheme, TransactionEntry entry) {
        return fund.getFolioNumber()
                + "_" + scheme.getIsin()
                + "_" + entry.getDate()
                + "_" + entry.getType();
    }

    private String buildTxnNotes(MutualFund fund, Scheme scheme, TransactionEntry entry, UserDto user) {
        return "AMC="         + fund.getAmc()
                + " | Scheme="   + scheme.getName()
                + " | ISIN="     + scheme.getIsin()
                + " | Desc="     + entry.getDescription()
                + " | Investor=" + user.getFullName()
                + " | PAN="      + user.getPan();
    }

    private String resolveAssetSubType(String schemeType) {
        if (schemeType == null) return "MUTUAL_FUND";
        return switch (schemeType.toUpperCase()) {
            case "EQUITY"  -> "MUTUAL_FUND_EQUITY";
            case "DEBT"    -> "MUTUAL_FUND_DEBT";
            case "HYBRID"  -> "MUTUAL_FUND_HYBRID";
            case "ELSS"    -> "MUTUAL_FUND_ELSS";
            default        -> "MUTUAL_FUND";
        };
    }

    private BigDecimal nvl(BigDecimal val) {
        return val != null ? val : BigDecimal.ZERO;
    }
}