package com.wealthManagement.wealthManagement.pojo.dto;

import lombok.*;
import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class PortfolioSnapshotDto {
    private Long       id;
    private Long       portfolioId;
    private String     snapshotDate;
    private BigDecimal investedAmount;
    private BigDecimal currentValue;
    private BigDecimal totalProfitLoss;
    private BigDecimal xirr;        // null — computed separately
    private BigDecimal cagr;        // null — computed separately
}