package az.azerconnect.sales.msbalanceoperations.service.impl;

import az.azerconnect.sales.msbalanceoperations.dto.request.RechargeLogRequest;
import az.azerconnect.sales.msbalanceoperations.dto.response.BalanceHistoryResponseDto;
import az.azerconnect.sales.msbalanceoperations.repository.MvSubscriberInfoRepository;
import az.azerconnect.sales.msbalanceoperations.service.AzfBalanceHistoryService;
import az.azerconnect.sales.msbalanceoperations.service.AzfBalanceInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class AzfBalanceHistoryServiceImpl implements AzfBalanceHistoryService {


    private final AzfBalanceInfoService azfBalanceInfoService;

    @Override
    public BalanceHistoryResponseDto getAzfBalanceInfo(RechargeLogRequest rechargeLogVM, Pageable pageable, String trackId) {
        BalanceHistoryResponseDto balanceHistoryResponseDto = new BalanceHistoryResponseDto();
        balanceHistoryResponseDto.setAzfBalanceInfoResponseDto(azfBalanceInfoService.getAzfBalanceInfo(rechargeLogVM.getMsisdn()));
        balanceHistoryResponseDto.setPrimaryOfferingAzf(azfBalanceInfoService.getPrimaryOfferOfMsisdn(trackId, rechargeLogVM.getMsisdn()));
        balanceHistoryResponseDto.setAzfBalanceRechargeResponseDto(azfBalanceInfoService.getBalanceRechargeLogList(rechargeLogVM, pageable, trackId));
        return balanceHistoryResponseDto;
    }

}
