package com.wealthManagement.wealthManagement.pojo.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SipPlanDto {

    private Long id;

    private Long portfolioId;

    private Long assetId;

    private BigDecimal sipAmount;

    private String sipFrequency;

    private Integer sipDay;

    private String startDate;

    private Boolean isActive;
}