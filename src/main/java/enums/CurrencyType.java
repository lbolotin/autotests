package enums;

public enum CurrencyType {

    RUR("₽", "RUR"),
    EUR("€", "EUR"),
    USD("$", "USD");

    private String sign;
    private String value;

    CurrencyType(String sign, String value) {
        this.sign = sign;
        this.value = value;
    }

    public String getSign() {
        return sign;
    }

    public String getValue() {
        return value;
    }
}
