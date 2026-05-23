package com.wealthManagement.wealthManagement.pojo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PortfolioDto {

    private Long id;

    private Long userId;

    private String portfolioName;

    private String portfolioDescription;
}