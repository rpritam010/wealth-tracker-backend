package com.wealthManagement.wealthManagement.pojo.responseBody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class AssetDetailMapper {

    private String assetName;
    private String assetCode;
    private String issuerName;
    private String assetSubType;
    private String folioNumber;
    private String assetTypeCode;

}
