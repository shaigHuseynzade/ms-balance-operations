package az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriberAzfPrimaryOfferResponse {
    String accountId;
    PrimaryOfferingAzf primaryOffering;
}
