package org.faustinelli.sss.model;

public class Stock {

    private String name;
    private Stock.Type type;

    private Stock(String aName, Stock.Type aType) {
        name = aName;
        type = aType;
    }

    public static Stock instance(String name, Stock.Type type) {
        return new Stock(name, type);
    }

    @Override
    public String toString() {
        return name + " - " + type;
    }

    public enum Type {
        COMMON, PREFERRED
    }
}
