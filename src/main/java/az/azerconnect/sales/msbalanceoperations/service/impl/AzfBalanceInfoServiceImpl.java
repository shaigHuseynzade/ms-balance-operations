package az.azerconnect.sales.msbalanceoperations.service.impl;

import az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.NgbssAzerfonClient;
import az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model.PrimaryOfferingAzf;
import az.azerconnect.sales.msbalanceoperations.client.ngbss.azerfon.model.SubscriberAzfBalanceResponse;
import az.azerconnect.sales.msbalanceoperations.constants.SubscriberType;
import az.azerconnect.sales.msbalanceoperations.dto.request.RechargeLogRequest;
import az.azerconnect.sales.msbalanceoperations.dto.response.AzfBalanceInfoResponseDto;
import az.azerconnect.sales.msbalanceoperations.dto.response.AzfBalanceRechargeResponseDto;
import az.azerconnect.sales.msbalanceoperations.entity.MvSubscriberInfo;
import az.azerconnect.sales.msbalanceoperations.repository.MvSubscriberInfoRepository;
import az.azerconnect.sales.msbalanceoperations.service.AzfBalanceInfoService;
import az.azerconnect.sales.msbalanceoperations.utils.AzcUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AzfBalanceInfoServiceImpl implements AzfBalanceInfoService {

    private final NgbssAzerfonClient ngbssAzerfonClient;
    private final MvSubscriberInfoRepository mvSubscriberInfoRepository;
    @Autowired
    private JdbcTemplate ngbssCdrJdbcTemplate;


    @Override
    public AzfBalanceInfoResponseDto getAzfBalanceInfo(String msisdn) {
        SubscriberAzfBalanceResponse subscriberAzfBalanceResponse = ngbssAzerfonClient.getAzfBalance(msisdn).getData();

        AzfBalanceInfoResponseDto azfBalanceInfoResponseDto = new AzfBalanceInfoResponseDto();

        azfBalanceInfoResponseDto.setSubscriberType(subscriberAzfBalanceResponse.getSubscriberType().getName());

        if (subscriberAzfBalanceResponse.getSubscriberType().equals(SubscriberType.PREPAID)) {
            azfBalanceInfoResponseDto.setBalance(
                    BigDecimal.valueOf(subscriberAzfBalanceResponse.getPrepaidBalance().getMainBalance())
            );
        } else if (subscriberAzfBalanceResponse.getSubscriberType().equals(SubscriberType.POSTPAID)) {
            azfBalanceInfoResponseDto.setBalance(
                    subscriberAzfBalanceResponse.getPostpaidBalance().getCurrentBalance()
            );
        }

        return azfBalanceInfoResponseDto;
    }

    @Override
    public PrimaryOfferingAzf getPrimaryOfferOfMsisdn(String trackId, String msisdn) {
        return ngbssAzerfonClient.checkSubscriberByMsisdnV2(trackId, msisdn)
                .getData()
                .getPrimaryOffering();
    }

    @Override
    public String getCbsAccountId(String msisdn, String crmAccountId) {
        if (msisdn.startsWith("994")) {
            msisdn = msisdn.substring(3);
        }
        return mvSubscriberInfoRepository.getCbsAccIdByMsisdnAndCrmActId(msisdn, crmAccountId).getCbsAcctId();
    }

    @Override
    public Page<AzfBalanceRechargeResponseDto> getBalanceRechargeLogList(RechargeLogRequest rechargeLogVM, Pageable pageable, String trackId) {

        String accountId = ngbssAzerfonClient.checkSubscriberByMsisdnV2(trackId, rechargeLogVM.getMsisdn()).getData().getAccountId();
        rechargeLogVM.setAccountId(getCbsAccountId(rechargeLogVM.getMsisdn(), accountId));

        int startIndex = pageable.getPageNumber() * pageable.getPageSize();
        int endIndex = startIndex + pageable.getPageSize();
        rechargeLogVM.setStartDate(rechargeLogVM.getStartDate().toLocalDate().atStartOfDay());
        rechargeLogVM.setEndDate(rechargeLogVM.getEndDate().toLocalDate().atTime(23, 59, 59));

        String sql = "select ENTRY_DATE, RECHARGE_TYPE, paymentchannelname, " +
                " (case when t.recharge_type = '3' or t.Status = 'R' then - to_number (t.RECHARGE_AMT) else to_number(t.RECHARGE_AMT) end) /100000 as AMOUNT" +
                " from NGBSS_CDR.CDR_RECHARGE t " +
                " left outer join ngbss_adhoc.dim_paymentchannel ch on ch.paymentchannelid  = t.CHANNEL_ID AND ch.language='en_US' " +
                " where " + createCdrWhereQuery(rechargeLogVM);

        String dataSql = "select * from (" +
                " select ROWNUM as row_num, a.* from (" +
                " " + sql + " order by ENTRY_DATE desc" +
                "    ) a where ROWNUM <=" + endIndex +
                ") where row_num > " + startIndex;

        log.debug("Get RechargeLog Sql: {}", dataSql);

        List<AzfBalanceRechargeResponseDto> cdrRechargeList = this.ngbssCdrJdbcTemplate.query(dataSql, (rs, rowNum) -> getCdrRechargeListFromRS(rs));
        int total = getCdrListCount(rechargeLogVM);

        Page<AzfBalanceRechargeResponseDto> cdrRechargePage = new PageImpl<AzfBalanceRechargeResponseDto>(cdrRechargeList, pageable, total);

        return cdrRechargePage;
    }

    private AzfBalanceRechargeResponseDto getCdrRechargeListFromRS(ResultSet resultSet) {
        AzfBalanceRechargeResponseDto cdrRecharge = new AzfBalanceRechargeResponseDto();
        try {
            LocalDateTime startDate = resultSet.getTimestamp("ENTRY_DATE").toLocalDateTime();
            cdrRecharge.setRechargeDate(startDate);
            cdrRecharge.setRechargeAmount(resultSet.getString("AMOUNT"));
            cdrRecharge.setPaymentType(AzcUtils.getPaymentDetailByType(resultSet.getString("RECHARGE_TYPE")));
            cdrRecharge.setChannelName(resultSet.getString("paymentchannelname"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cdrRecharge;
    }

    private String createCdrWhereQuery(RechargeLogRequest rechargeLogVM) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String startDate = rechargeLogVM.getStartDate().format(dateFormat);
        String endDate = rechargeLogVM.getEndDate().format(dateFormat);

        String where = " ENTRY_DATE >= trunc(add_months (sysdate,- 12)) " +
                " AND ENTRY_DATE BETWEEN " +
                " TO_DATE('" + startDate + "', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('" + endDate + "', 'YYYY-MM-DD HH24:MI:SS') " +
                " AND PRI_IDENTITY = '" + AzcUtils.validateMsisdn(rechargeLogVM.getMsisdn()) + "' AND ACCT_ID = '" + rechargeLogVM.getAccountId() + "'";

        return where;
    }


    private int getCdrListCount(RechargeLogRequest rechargeLogVM) {
        String sql = "SELECT count(*) total FROM NGBSS_CDR.CDR_RECHARGE where " + createCdrWhereQuery(rechargeLogVM);

        int total = this.ngbssCdrJdbcTemplate.queryForObject(sql, Integer.class);
        return total;
    }
}
