package az.azerconnect.sales.msbalanceoperations.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AzfBalanceRechargeResponseDto {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rechargeDate;
    private String paymentType;
    private String rechargeAmount;
    private String channelName;
}
