package com.wealthManagement.wealthManagement.orchestrator.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseOrchestrator {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void logRequest(String apiName) {
        logger.info("Executing API : {}", apiName);
    }

    protected void validateUserId(Long userId) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid User Id");
        }
    }
}