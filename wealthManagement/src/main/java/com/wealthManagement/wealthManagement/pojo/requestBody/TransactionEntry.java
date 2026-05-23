package com.wealthManagement.wealthManagement.pojo.requestBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Maps to each element in JSON: scheme.transactions[]
 * Types seen: PURCHASE, PURCHASE_SIP, STAMP_DUTY_TAX, REDEMPTION, SWITCH_IN, SWITCH_OUT, DIVIDEND_PAYOUT
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionEntry {

    @JsonProperty("date")
    private String date;                    // e.g. "2024-05-16"

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private String type;                    // PURCHASE | PURCHASE_SIP | STAMP_DUTY_TAX | REDEMPTION etc.

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("units")
    private BigDecimal units;              // nullable

    @JsonProperty("nav")
    private BigDecimal nav;                // nullable

    @JsonProperty("balance")
    private BigDecimal balance;            // running unit balance after txn; nullable for STAMP_DUTY_TAX

    @JsonProperty("dividend_rate")
    private BigDecimal dividendRate;       // nullable

    @JsonProperty("additional_info")
    private Map<String, Object> additionalInfo;   // always {} in current data; kept flexible
}
