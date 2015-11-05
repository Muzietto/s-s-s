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
        try {
            Amount amm = ((Amount) obj);
            return amm.currency().equals(currency)
                    && amm.value().equals(value);

        } catch (Exception exc) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value + " " + currency;
    }

    public enum Currency {
        PENNY
    }
}
