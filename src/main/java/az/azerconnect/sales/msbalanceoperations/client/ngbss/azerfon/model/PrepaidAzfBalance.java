package az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrepaidAzfBalance {
    Float mainBalance;
    LocalDateTime expireDateMainBalance;
    Float countryWideBonusAzn;
    LocalDateTime expireDateCountryWideBonusAzn;
    Float bonusAzn;
    LocalDateTime expireDateBonusAzn;
    Float smsAzn;
    LocalDateTime expireDateSmsAzn;
    List<BonusBalance> bonusBalanceList;
}
