package org.faustinelli.sss.util;

public class Amount {
    public static final Amount ZERO_PENNIES = Amount.instance(0);
    private final Currency currency;
    private final Integer value;

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

    public static Amount instance(Integer aValue, Currency aCurrency) {
        return new Amount(aValue, aCurrency);
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
    public int hashCode() {
        return value.intValue() + currency.hashCode();
    }

    @Override
    public String toString() {
        return value + " " + currency;
    }

    public enum Currency {
        PENNY
    }
}
