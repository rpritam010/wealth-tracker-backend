package com.wealthManagement.wealthManagement.pojo.requestBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Maps to JSON: "summary": { "accounts": { "demat", "insurance", "mutual_funds", "nps" }, "total_value" }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {

    @JsonProperty("accounts")
    private AccountsSummary accounts;

    @JsonProperty("total_value")
    private BigDecimal totalValue;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccountsSummary {

        @JsonProperty("demat")
        private AccountCount demat;

        @JsonProperty("insurance")
        private AccountCount insurance;

        @JsonProperty("mutual_funds")
        private AccountCount mutualFunds;

        @JsonProperty("nps")
        private AccountCount nps;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccountCount {

        @JsonProperty("count")
        private Integer count;

        @JsonProperty("total_value")
        private BigDecimal totalValue;
    }
}
