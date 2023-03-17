package az.azerconnect.sales.msbalanceoperations.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    INTERNAL_ERROR_OCCURRED(0, "Internal error occurred"),
    TOKEN_IS_INVALID_OR_INCORRECT(1, "Token is incorrect or invalid"),
    ACCESS_TOKEN_EXPIRED(2, "Access token is expired"),
    TOKEN_DOESNT_EXIST(3, "Token does not exist"),
    MSISDN_ALREADY_RESERVED(142, "Msisdn already reserved"),
    STOCK_DOES_NOT_BELONG(150, "This stock does not belong to tis user"),

    NOT_IS_BAKCELL_CUSTOMER(151, "Not is bakcell customer"),
    CIRCUIT_CARD_NOT_FOUND(152, "Circuit card not found"),
    TRANSACTION_ALREADY_EXPIRED(153, "Transaction already expired"),
    OTP_EXPIRED(204, "Otp expired"),
    OTP_ATTEMPT(205, "Otp attempt"),
    OTP_INCORRECT(206, "Otp is incorrect");


    int code;
    String message;
}
