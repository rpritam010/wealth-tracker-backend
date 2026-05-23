package com.wealthManagement.wealthManagement.pojo.dto;

import lombok.*;
import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class HoldingsSnapshotDto {
    private Long       id;
    private Long       portfolioId;
    private Long       assetId;
    private String     snapshotDate;
    private BigDecimal totalQuantity;
    private BigDecimal investedAmount;
    private BigDecimal currentPrice;
    private BigDecimal currentValue;
    private BigDecimal profitLoss;
    private BigDecimal xirr;        // null — computed separately
    private BigDecimal cagr;        // null — computed separately
}