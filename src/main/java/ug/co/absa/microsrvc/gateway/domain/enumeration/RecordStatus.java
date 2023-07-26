package ug.co.absa.microsrvc.gateway.domain.enumeration;

/**
 * The RecordStatus enumeration.
 */
public enum RecordStatus {
    ACTIVE("active"),
    INACTIVE("inactive"),
    PENDING("pending"),
    INITIATED("initiated"),
    APPROVED("approved");

    private final String value;

    RecordStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
