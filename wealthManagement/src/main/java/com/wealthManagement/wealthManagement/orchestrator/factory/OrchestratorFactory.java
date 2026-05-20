package com.wealthManagement.wealthManagement.orchestrator.factory;


import com.wealthManagement.wealthManagement.orchestrator.asset.AssetOrchestrator;
import com.wealthManagement.wealthManagement.orchestrator.user.UserOrchestrator;
import org.springframework.stereotype.Component;

@Component
public class OrchestratorFactory {

    private final AssetOrchestrator assetOrchestrator;
    private final UserOrchestrator userOrchestrator;

    public OrchestratorFactory(AssetOrchestrator assetOrchestrator, UserOrchestrator userOrchestrator) {
        this.assetOrchestrator = assetOrchestrator;
        this.userOrchestrator = userOrchestrator;
    }

    public AssetOrchestrator getAssetOrchestrator() {
        return assetOrchestrator;
    }

    public UserOrchestrator getUserOrchestrator() {
        return userOrchestrator;
    }
}