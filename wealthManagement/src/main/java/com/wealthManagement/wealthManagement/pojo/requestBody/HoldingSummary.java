package com.wealthManagement.wealthManagement.pojo.requestBody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoldingSummary {

    private BigDecimal currentNav;

    private String currentNavDate;

    private BigDecimal marketValue;
}
