package com.wealthManagement.wealthManagement.mybatis.mapper;

import com.wealthManagement.wealthManagement.pojo.responseBody.AssetDetailMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AssetMapper {

    /**
     * Fetch all assets for a given user
     */
    List<AssetDetailMapper> selectAssetsByUserId(
            @Param("userId") Long userId
    );

    /**
     * Fetch single asset
     */
    AssetDetailMapper selectAssetById(
            @Param("assetId") Long assetId
    );

}