package com.wealthManagement.wealthManagement.joiner.serviceImpl;

import com.wealthManagement.wealthManagement.joiner.service.AssetService;
import com.wealthManagement.wealthManagement.mybatis.mapper.AssetMapper;
import com.wealthManagement.wealthManagement.pojo.responseBody.AssetDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetMapper assetMapper;

    public AssetServiceImpl(AssetMapper assetMapper) {
        this.assetMapper = assetMapper;
    }

    @Override
    public List<AssetDetailMapper> getAssetsByUserId(Long userId) {
        return assetMapper.selectAssetsByUserId(userId);
    }
}