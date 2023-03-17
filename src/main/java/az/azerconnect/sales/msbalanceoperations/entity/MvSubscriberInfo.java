package az.azerconnect.sales.msbalanceoperations.entity;

import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "MV_SUBSCRIBER_INFO", schema = "PRINT_INQUIRY")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class MvSubscriberInfo {

    @Id
    String msisdn;

    @Column(name = "CBS_ACCT_ID")
    String cbsAcctId;

    public String getCbsAcctId() {
        return cbsAcctId;
    }
}
