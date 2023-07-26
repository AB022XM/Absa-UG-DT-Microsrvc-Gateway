package ug.co.absa.microsrvc.gateway.domain.enumeration;

/**
 * The ErrorMessage enumeration.
 */
public enum ErrorMessage {
    TRANSACTIONSUCCESS("(DTransaction suceessfully processed"),
    TRANSACTIONFAILED("DTransaction failed "),
    TRANSACTIONPENDING("DTransaction pending "),
    TRANSACTIONINITIATED("DTransaction initiated "),
    TRANSACTIONAPPROVED("DTransaction approved "),
    TRANSACTIONUNKNOWN("DTransaction unknown !"),
    TRANSACTIONINVALIDACCOUNT("Invalid account !"),
    TRANSACTIONINVALIDBILLERCODE("Invalid biller code !"),
    TRANSACTIONINVALIDINVALIDAMOUNT("Invalid invalid amount !"),
    TRANSACTIONINVALIDPAYMENTTYPE("Invalid payment type !"),
    TRANSACTIONINVALIDPAYMENTMETHOD("Invalid payment method !"),
    TRANSACTIONINVALIDDEVICEID("Invalid device id !"),
    TRANSACTIONUNKNOWNERROR("Unknown error");

    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
