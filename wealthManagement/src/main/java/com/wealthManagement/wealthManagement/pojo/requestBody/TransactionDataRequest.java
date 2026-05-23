package com.wealthManagement.wealthManagement.pojo.requestBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Root object — the API receives a List<TransactionDataRequest>
 * matching the top-level JSON array structure.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDataRequest {

    @JsonProperty("demat_accounts")
    private List<Object> dematAccounts;

    @JsonProperty("insurance")
    private Insurance insurance;

    @JsonProperty("investor")
    private InvestorDetails investor;

    @JsonProperty("meta")
    private CasMetadata meta;

    @JsonProperty("mutual_funds")
    private List<MutualFund> mutualFunds;

    @JsonProperty("nps")
    private List<Object> nps;

    @JsonProperty("summary")
    private Summary summary;
}
