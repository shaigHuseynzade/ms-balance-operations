package az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrimaryOfferingAzf {
    String offeringId;
    String offeringName;
    String status;
    LocalDateTime effectiveDate;
    LocalDateTime expiredDate;
}
