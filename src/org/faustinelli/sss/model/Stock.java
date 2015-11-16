package org.faustinelli.sss.model;

public class Stock {

    private final String symbol;
    private final Stock.Type type;
    private final Amount parValue;

    private Stock(String aName, Stock.Type aType, Amount aParValue) {
        symbol = aName.substring(0, 3).toUpperCase();
        type = aType;
        parValue = aParValue;
    }

    private Stock(String aName, Stock.Type aType) {
        this(aName, aType, Amount.instance(100));
    }

    public static Stock instance(String name, Stock.Type type) {
        return new Stock(name, type);
    }

    public static Dividend dividend(Stock aStock, Amount aDividend) {
        return aStock.new Dividend(aStock, aDividend);
    }

    public Amount parValue() {
        return Amount.instance(parValue.value());
    }

    @Override
    public String toString() {
        return symbol + " - " + type;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Stock other = (Stock) obj;
            return this.symbol.equals(other.symbol) && this.type.equals(other.type);
        } catch (Exception e) {
            return false;
        }
    }

    // TODO add enum method for fixed dividend
    public enum Type {
        COMMON, PREFERRED
    }

    public class Dividend {
        Stock stock;
        private Amount dividend;

        private Dividend(Stock aStock, Amount aDividend) {
            dividend = aDividend;
            stock = aStock;
        }

        @Override
        public String toString() {
            return dividend.toString() + " : " + stock.toString();
        }
    }
}