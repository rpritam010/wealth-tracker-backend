package com.wealthManagement.wealthManagement.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssetDto {

    private Long id;

    // =========================================
    // ASSET BASIC DETAILS
    // =========================================

    private String assetName;

    private String assetCode;

    private Long assetTypeId;

    private Long sectorId;

    private Long geographyId;

    private String currencyCode;

    private String issuerName;

    private String assetSubType;

    // =========================================
    // FOLIO
    // =========================================

    private String folioNumber;

    // =========================================
    // USER
    // =========================================

    private Long userId;

    // =========================================
    // OPTIONAL
    // =========================================

    private String remarks;

    // =========================================
    // AUDIT
    // =========================================

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}