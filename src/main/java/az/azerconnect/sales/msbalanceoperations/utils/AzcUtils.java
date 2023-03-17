package az.azerconnect.sales.msbalanceoperations.utils;

public final class AzcUtils {
    public static String validateMsisdn(String msisdn) {
        if (msisdn.length() == 10) {
            return "994" + msisdn.substring(1);
        } else if (msisdn.length() == 9) {
            return "994" + msisdn;
        } else {
            return msisdn;
        }
    }

    public static String shortenMsisdn(String msisdn) {
        if (msisdn == null) {
            return "";
        } else if (msisdn.length() == 12) {
            return msisdn.substring(3);
        } else {
            return msisdn;
        }

    }

    public static String getPaymentDetailByType(String rechargeType) {
        switch (rechargeType) {
            case "0": //Voucher
                return "Recharge Card";
            case "1": //Cash
            case "2": //E-voucher
            case "3": //Cash Recharge Reversal
                return "Cash";
        }
        return null;
    }
}
