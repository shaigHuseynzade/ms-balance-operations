package az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostpaidAzfBalanceDto {
    private BigDecimal countryWideBonusAzn;
    private LocalDateTime expireDateCountryWideBonusAzn;
    private BigDecimal bonusAzn;
    private LocalDateTime expireDateBonusAzn;
    private BigDecimal smsAzn;
    private LocalDateTime expireDateSmsAzn;

    //individual
    private BigDecimal outstandingIndDebt;
    private BigDecimal unBilledIndividualBalance;
    private BigDecimal advancePayment;
    private BigDecimal currentBalance;   //adv.payment - unBilledIndividualBalance
    private BigDecimal availableIndvCreditAmount;
    private List<BonusBalance> bonusBalanceList;
    private BigDecimal totalBalanceForIndividual;
    private PaymentLimitUsageDto paymentLimitUsage;
}
