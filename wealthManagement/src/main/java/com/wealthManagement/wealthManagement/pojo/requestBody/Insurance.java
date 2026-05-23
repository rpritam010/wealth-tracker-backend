package com.wealthManagement.wealthManagement.pojo.requestBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Maps to JSON: "insurance": { "life_insurance_policies": [] }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Insurance {

    @JsonProperty("life_insurance_policies")
    private List<Object> lifeInsurancePolicies;
}
