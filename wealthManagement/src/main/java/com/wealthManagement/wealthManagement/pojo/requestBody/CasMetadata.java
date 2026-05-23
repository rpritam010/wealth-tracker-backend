package com.wealthManagement.wealthManagement.pojo.requestBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Maps to JSON: "meta": { "cas_type", "generated_at", "statement_period": { "from", "to" } }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CasMetadata {

    @JsonProperty("cas_type")
    private String casType;

    @JsonProperty("generated_at")
    private String generatedAt;             // e.g. "2026-05-21T21:07:24"

    @JsonProperty("statement_period")
    private StatementPeriod statementPeriod;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StatementPeriod {

        @JsonProperty("from")
        private String from;                // e.g. "2020-01-01"

        @JsonProperty("to")
        private String to;
    }
}
