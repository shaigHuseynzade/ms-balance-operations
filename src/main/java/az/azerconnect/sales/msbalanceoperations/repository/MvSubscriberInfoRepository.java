package az.azerconnect.sales.msbalanceoperations.repository;

import az.azerconnect.sales.msbalanceoperations.entity.MvSubscriberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MvSubscriberInfoRepository extends JpaRepository<MvSubscriberInfo, String> {


    @Query(value = "select * " +
            "from PRINT_INQUIRY.MV_SUBSCRIBER_INFO t " +
            "where RN_LAST = 1" +
            "and MSISDN = :msisdn " +
            "and CRM_ACCT_ID = :crmAccountId", nativeQuery = true)
    MvSubscriberInfo getCbsAccIdByMsisdnAndCrmActId(String msisdn, String crmAccountId);
}
