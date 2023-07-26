package ug.co.absa.microsrvc.gateway.domain.enumeration;

/**
 * The Currency enumeration.
 */
public enum Currency {
    UGX("ugx"),
    USD("usd"),
    EUR("eur"),
    GBP("gbp"),
    AUD("aud"),
    CNY("cny"),
    JPY("jpy"),
    CHF("chf"),
    CZK("czk");

    private final String value;

    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
