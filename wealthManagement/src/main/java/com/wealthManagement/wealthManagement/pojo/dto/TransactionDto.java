package com.wealthManagement.wealthManagement.pojo.dto;

import lombok.*;
import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class TransactionDto {

    private Long       id;                  // populated by MyBatis useGeneratedKeys

    private BigDecimal portfolioId;
    private BigDecimal assetId;
    private BigDecimal userId;

    private String     folioNumber;
    private String     transactionType;     // PURCHASE | PURCHASE_SIP | STAMP_DUTY_TAX | REDEMPTION
    private String     transactionDate;     // yyyy-MM-dd

    private BigDecimal totalAmount;
    private BigDecimal quantity;            // units
    private BigDecimal pricePerUnit;
    private BigDecimal navValue;
    private BigDecimal unitBalance;         // running unit balance after txn (null for STAMP_DUTY_TAX)

    private BigDecimal charges;
    private BigDecimal stampDuty;
    private BigDecimal interestRate;

    private String     transactionReference;
    private String     transactionSource;

    private String     premiumDueDate;
    private String     maturityDate;

    private String     notes;
    private String     createdAt;
}