package com.wealthManagement.wealthManagement.orchestrator.transaction;

import com.wealthManagement.wealthManagement.joiner.service.TransactionService;
import com.wealthManagement.wealthManagement.orchestrator.base.BaseOrchestrator;
import com.wealthManagement.wealthManagement.pojo.requestBody.TransactionDataRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionOrchestrator extends BaseOrchestrator {

    private final TransactionService transactionService;

    public TransactionOrchestrator(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    public List<TransactionDataRequest> addTransaction(List<TransactionDataRequest> transactions) {

        logRequest("Transaction");

        return transactionService.addTransactions(transactions);
    }

}
