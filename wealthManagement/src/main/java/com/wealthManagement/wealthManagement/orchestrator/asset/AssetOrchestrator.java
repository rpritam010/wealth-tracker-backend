package com.wealthManagement.wealthManagement.orchestrator.asset;

import com.wealthManagement.wealthManagement.joiner.service.AssetService;
import com.wealthManagement.wealthManagement.orchestrator.base.BaseOrchestrator;
import com.wealthManagement.wealthManagement.pojo.responseBody.AssetDetailMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssetOrchestrator extends BaseOrchestrator {

    private final AssetService assetService;


    public AssetOrchestrator(AssetService assetService) {
        this.assetService = assetService;
    }

    public List<AssetDetailMapper> getAssets(Long userId) {

        logRequest("GET_ASSETS");

        validateUserId(userId);

        return assetService.getAssetsByUserId(userId);
    }

}