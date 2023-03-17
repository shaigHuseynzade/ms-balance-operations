package az.azerconnect.sales.msbalanceoperations.controller;

import az.azerconnect.sales.msbalanceoperations.dto.request.RechargeLogRequest;
import az.azerconnect.sales.msbalanceoperations.dto.response.BalanceHistoryResponseDto;
import az.azerconnect.sales.msbalanceoperations.service.AzfBalanceHistoryService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/balance")
public class BalanceHistoryController {

    private final AzfBalanceHistoryService azfBalanceHistoryService;

    @GetMapping("/history")
    public BalanceHistoryResponseDto getMsisdnDetails(@RequestBody RechargeLogRequest rechargeLogRequest,
                                                      @ApiParam Pageable pageable,
                                                      @RequestParam String trackId) {
        return azfBalanceHistoryService.getAzfBalanceInfo(rechargeLogRequest, pageable, trackId);
    }

}
