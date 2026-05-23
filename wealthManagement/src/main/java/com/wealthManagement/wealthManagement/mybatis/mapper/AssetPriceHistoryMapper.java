package com.wealthManagement.wealthManagement.mybatis.mapper;

import com.wealthManagement.wealthManagement.pojo.dto.AssetPriceHistoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AssetPriceHistoryMapper {

    void insert(AssetPriceHistoryDto dto);

    Integer countByAssetAndDate(
            @Param("assetId") Long assetId,
            @Param("priceDate") String priceDate
    );
}