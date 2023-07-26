package ug.co.absa.microsrvc.gateway.domain.enumeration;

/**
 * The ErrorCodes enumeration.
 */
public enum ErrorCodes {
    SUCCESS("00"),
    PENDING("01"),
    UNKNOWN("99"),
    FAILED("101"),
    INVALIDACCOUNT("105"),
    INVALIDBILLERCODE("106"),
    INVALIDINVALIDAMOUNT("107"),
    INVALIDPAYMENTTYPE("108"),
    INVALIDPAYMENTMETHOD("109"),
    INVALID_DEVICE_ID("110");

    private final String value;

    ErrorCodes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
