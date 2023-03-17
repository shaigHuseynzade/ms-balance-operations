package az.azerconnect.sales.msbalanceoperations.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AzfBalanceInfoResponseDto {
    String subscriberType;
    BigDecimal balance;
}
