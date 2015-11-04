package org.faustinelli.sss.model;

public class Amount {
    private final Currency currency;
    private final Integer value;
    private Integer digits;

    private Amount(Integer aValue, Amount.Currency aCurrency) {
        value = aValue;
        currency = aCurrency;
    }

    private Amount(Integer aValue) {
        value = aValue;
        currency = Currency.PENNY;
    }

    public Integer value() {
        return new Integer(value);
    }

    public Currency currency() {
        return currency;
    }

    public static Amount instance(Integer aValue) {
        return new Amount(aValue);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Amount
                && ((Amount) obj).currency().equals(currency)
                && ((Amount) obj).value().equals(value);
    }

    public enum Currency {
        PENNY
    }
}
