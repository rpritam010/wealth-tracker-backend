package com.wealthManagement.wealthManagement.joiner.service;

import com.wealthManagement.wealthManagement.pojo.responseBody.AssetDetailMapper;

import java.util.List;

public interface AssetService {
    List<AssetDetailMapper> getAssetsByUserId(Long userId);
}
