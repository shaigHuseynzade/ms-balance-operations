package az.azerconnect.sales.msbalanceoperations.service;

import az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model.PrimaryOfferingAzf;
import az.azerconnect.sales.msbalanceoperations.dto.request.RechargeLogRequest;
import az.azerconnect.sales.msbalanceoperations.dto.response.AzfBalanceInfoResponseDto;
import az.azerconnect.sales.msbalanceoperations.dto.response.AzfBalanceRechargeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AzfBalanceInfoService {
    AzfBalanceInfoResponseDto getAzfBalanceInfo(String msisdn);

    PrimaryOfferingAzf getPrimaryOfferOfMsisdn(String trackId, String msisdn);

    String getCbsAccountId(String msisdn, String crmAccountId);


    Page<AzfBalanceRechargeResponseDto> getBalanceRechargeLogList(RechargeLogRequest rechargeLogVM, Pageable pageable, String trackId);
}
