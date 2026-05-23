package com.wealthManagement.wealthManagement.pojo.dto;

import lombok.*;
import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
public class AssetPriceHistoryDto {
    private Long       id;
    private Long       assetId;
    private String     priceDate;   // yyyy-MM-dd
    private BigDecimal price;       // NAV
}