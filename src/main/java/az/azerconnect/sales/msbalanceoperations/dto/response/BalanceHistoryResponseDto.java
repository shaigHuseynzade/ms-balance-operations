package az.azerconnect.sales.msbalanceoperations.dto.response;

import az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model.PrimaryOfferingAzf;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BalanceHistoryResponseDto {
    AzfBalanceInfoResponseDto azfBalanceInfoResponseDto;
    PrimaryOfferingAzf primaryOfferingAzf;
    Page<AzfBalanceRechargeResponseDto> azfBalanceRechargeResponseDto;
}
