package com.wealthManagement.wealthManagement.mybatis.mapper;

import com.wealthManagement.wealthManagement.pojo.responseBody.AssetDetailMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AssetDetail {

    // SQL for this method is defined in src/main/resources/mapper/SampleMapper.xml
    AssetDetailMapper selectOne();

    // Returns all assets for a given user id
    List<AssetDetailMapper> selectAssetsByUserId(@Param("userId") Long userId);
}
