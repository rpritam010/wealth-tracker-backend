package com.wealthManagement.wealthManagement.joiner.service;

import com.wealthManagement.wealthManagement.pojo.requestBody.TransactionDataRequest;

import java.util.List;

public interface TransactionService {
    List<TransactionDataRequest> addTransactions(List<TransactionDataRequest> transactions);
}
