package az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon;

import az.azerconnect.sales.msbalanceoperations.client.dto.GenericResponse;
import az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model.SubscriberAzfBalanceResponse;
import az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model.SubscriberAzfPrimaryOfferResponse;
import az.azerconnect.sales.msbalanceoperations.configuration.NgbssClientConfiguration;
import az.azerconnect.sales.msbalanceoperations.constants.HeaderName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url ="http://digitalsales-03.azerconnect.az:8084",name = "FUCK", configuration = NgbssClientConfiguration.class)
public interface NgbssAzerfonClient {
    @GetMapping("/api/v2/subscribers/msisdn/{msisdn}")
    GenericResponse<SubscriberAzfPrimaryOfferResponse> checkSubscriberByMsisdnV2(@RequestHeader(value = HeaderName.TRACK_ID) String trackId, @PathVariable String msisdn);

    @GetMapping("/api/v2/subscribers/{msisdn}/balance")
    GenericResponse<SubscriberAzfBalanceResponse> getAzfBalance(@PathVariable String msisdn);
}
