package az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentLimitUsageDto {
    private BigDecimal amount;
    private BigDecimal usedAmount;
    private String limitType;
}
