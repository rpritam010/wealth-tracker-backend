package com.wealthManagement.wealthManagement.pojo.requestBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Maps to each element in JSON: "mutual_funds": [ { "amc", "folio_number", "schemes", ... } ]
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MutualFund {

    @JsonProperty("amc")
    private String amc;

    @JsonProperty("folio_number")
    private String folioNumber;

    @JsonProperty("registrar")
    private String registrar;

    @JsonProperty("value")
    private BigDecimal value;

    @JsonProperty("additional_info")
    private FolioAdditionalInfo additionalInfo;

    @JsonProperty("linked_holders")
    private List<Holder> linkedHolders;

    @JsonProperty("schemes")
    private List<Scheme> schemes;

    // ── Nested: additional_info at folio level ──────────────────────────

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FolioAdditionalInfo {

        @JsonProperty("kyc")
        private String kyc;

        @JsonProperty("name")
        private String name;

        @JsonProperty("pan")
        private String pan;

        @JsonProperty("pankyc")
        private String pankyc;
    }

    // ── Nested: linked_holders ──────────────────────────────────────────

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Holder {

        @JsonProperty("name")
        private String name;

        @JsonProperty("pan")
        private String pan;
    }

    // ── Nested: scheme inside a folio ──────────────────────────────────

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Scheme {

        @JsonProperty("isin")
        private String isin;

        @JsonProperty("name")
        private String name;

        @JsonProperty("type")
        private String type;                // e.g. "EQUITY", "DEBT", "N/A"

        @JsonProperty("nav")
        private BigDecimal nav;

        @JsonProperty("units")
        private BigDecimal units;

        @JsonProperty("cost")
        private BigDecimal cost;

        @JsonProperty("value")
        private BigDecimal value;

        @JsonProperty("nominees")
        private List<String> nominees;

        @JsonProperty("gain")
        private Gain gain;

        @JsonProperty("additional_info")
        private SchemeAdditionalInfo additionalInfo;

        @JsonProperty("transactions")
        private List<TransactionEntry> transactions;
    }

    // ── Nested: gain ───────────────────────────────────────────────────

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Gain {

        @JsonProperty("absolute")
        private BigDecimal absolute;

        @JsonProperty("percentage")
        private BigDecimal percentage;
    }

    // ── Nested: additional_info at scheme level ─────────────────────────

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SchemeAdditionalInfo {

        @JsonProperty("advisor")
        private String advisor;

        @JsonProperty("amfi")
        private String amfi;

        @JsonProperty("rta")
        private String rta;

        @JsonProperty("rta_code")
        private String rtaCode;

        @JsonProperty("open_units")
        private BigDecimal openUnits;

        @JsonProperty("close_units")
        private BigDecimal closeUnits;
    }
}
