package az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model;


import az.azerconnect.sales.msbalanceoperations.constants.SubscriberType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriberAzfBalanceResponse {
    SubscriberType subscriberType;
    PrepaidAzfBalance prepaidBalance;
    PostpaidAzfBalanceDto postpaidBalance;
}
