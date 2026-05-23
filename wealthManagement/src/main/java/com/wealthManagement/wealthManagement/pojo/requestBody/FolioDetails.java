package com.wealthManagement.wealthManagement.pojo.requestBody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FolioDetails {

    private String folioNumber;

    private String amcName;

    private String schemeCode;

    private String schemeName;

    private String schemeType;

    private String schemeOption;

    private String investmentMode;

    private BigDecimal openingUnitBalance;
}