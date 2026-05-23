package com.wealthManagement.wealthManagement.orchestrator.factory;


import com.wealthManagement.wealthManagement.orchestrator.asset.AssetOrchestrator;
import com.wealthManagement.wealthManagement.orchestrator.transaction.TransactionOrchestrator;
import com.wealthManagement.wealthManagement.orchestrator.user.UserOrchestrator;
import org.springframework.stereotype.Component;

@Component
public class OrchestratorFactory {

    private final AssetOrchestrator assetOrchestrator;
    private final UserOrchestrator userOrchestrator;
    private final TransactionOrchestrator transactionOrchestrator;

    public OrchestratorFactory(AssetOrchestrator assetOrchestrator, UserOrchestrator userOrchestrator, TransactionOrchestrator transactionOrchestrator) {
        this.assetOrchestrator = assetOrchestrator;
        this.userOrchestrator = userOrchestrator;
        this.transactionOrchestrator = transactionOrchestrator;
    }

    public AssetOrchestrator getAssetOrchestrator() {
        return assetOrchestrator;
    }

    public UserOrchestrator getUserOrchestrator() {
        return userOrchestrator;
    }

    public TransactionOrchestrator getTransactionOrchestrator() {
        return transactionOrchestrator;
    }
}