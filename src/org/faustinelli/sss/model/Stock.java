package org.faustinelli.sss.model;

public class Stock {

    private String symbol;
    private Stock.Type type;
    private Amount parValue;

    private Stock(String aName, Stock.Type aType, Amount aParValue) {
        symbol = aName.substring(0,3).toUpperCase();
        type = aType;
        parValue = aParValue;
    }

    private Stock(String aName, Stock.Type aType) {
        this(aName, aType, Amount.instance(100));
    }

    public static Stock instance(String name, Stock.Type type) {
        return new Stock(name, type);
    }

    public Amount parValue() {
        return Amount.instance(parValue.value());
    }

    @Override
    public String toString() {
        return symbol + " - " + type;
    }

    public enum Type {
        COMMON, PREFERRED
    }
}
