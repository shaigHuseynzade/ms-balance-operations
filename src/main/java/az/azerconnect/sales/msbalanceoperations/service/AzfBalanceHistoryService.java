package az.azerconnect.sales.msbalanceoperations.service;

import az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model.SubscriberAzfBalanceResponse;
import az.azerconnect.sales.msbalanceoperations.dto.request.RechargeLogRequest;
import az.azerconnect.sales.msbalanceoperations.dto.response.BalanceHistoryResponseDto;
import org.springframework.data.domain.Pageable;

public interface AzfBalanceHistoryService {

    BalanceHistoryResponseDto getAzfBalanceInfo(RechargeLogRequest rechargeLogVM, Pageable pageable, String trackId);
}
