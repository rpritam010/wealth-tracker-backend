package com.wealthManagement.wealthManagement.mybatis.mapper;

import com.wealthManagement.wealthManagement.pojo.dto.AssetDto;
import com.wealthManagement.wealthManagement.pojo.responseBody.AssetDetailMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AssetMapper {

    List<AssetDetailMapper> selectOne();

    List<AssetDetailMapper> selectAssetsByUserId(@Param("userId") Long userId);

    void insertAsset(AssetDto assetDto);

    /** Old method kept for backward compatibility */
    AssetDto getAssetByFolio(
            @Param("folioNumber") String folioNumber,
            @Param("userId") Long userId
    );

    /** New — keyed by folio + isin + user so each scheme gets its own asset row */
    AssetDto getAssetByFolioAndIsin(
            @Param("folioNumber") String folioNumber,
            @Param("isin") String isin,
            @Param("userId") Long userId
    );
}