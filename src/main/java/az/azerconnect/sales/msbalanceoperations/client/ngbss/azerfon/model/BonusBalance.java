package az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BonusBalance {
    private String type;
    private Float amount;
    private LocalDateTime expirationDate;
}
