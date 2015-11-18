package org.faustinelli.sss.model;

import org.faustinelli.sss.util.Amount;

import java.text.NumberFormat;
import java.util.Optional;

public class Stock {

    public static Dividend NULL_DIVIDEND = dividend(Stock.common("___", Amount.ZERO_PENNIES), Amount.ZERO_PENNIES);

    private final String symbol;
    private final Stock.Type type;
    private final Amount parValue;
    private final Integer fixedDividendPc;

    private Stock(String aName, Stock.Type aType, Amount aParValue, Integer aFixedDividendPc) {
        symbol = aName.substring(0, 3).toUpperCase();
        type = aType;
        parValue = aParValue;
        fixedDividendPc = aFixedDividendPc;
    }

    public static Stock instance(String name, Stock.Type type) {
        return new Stock(name, type, Amount.instance(100), new Integer(2));
    }

    public static Stock common(String name, Amount parValue) {
        return new Stock(name, Type.COMMON, parValue, null);
    }

    public static Stock preferred(String name, Amount parValue, Integer fixedDividendPc) {
        return new Stock(name, Type.PREFERRED, parValue, fixedDividendPc);
    }

    public static Dividend dividend(Stock aStock, Amount aDividend) {
        return aStock.new Dividend(aStock, aDividend);
    }

    public Optional<Dividend> dividend() {
        return this.type.dividend(this).flatMap(amount -> Optional.of(new Dividend(this, amount)));
    }

    public Amount parValue() {
        return Amount.instance(parValue.value());
    }

    public Integer fixedDividendPc() {
        return new Integer(fixedDividendPc);
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

    @Override
    public int hashCode() {
        return symbol.hashCode() + type.hashCode();
    }

    public enum Type {
        COMMON {
            @Override
            Optional<Amount> dividend(Stock stock) {
                return Optional.empty();
            }
        },
        PREFERRED {
            @Override
            Optional<Amount> dividend(Stock stock) {
                return Optional.of(Amount.instance(stock.parValue().value() * stock.fixedDividendPc()));
            }
        };

        abstract Optional<Amount> dividend(Stock stock);
    }

    public class Dividend {
        private Stock stock;
        private Amount dividend;

        private Dividend(Stock aStock, Amount aDividend) {
            dividend = aDividend;
            stock = aStock;
        }

        public Stock stock() {
            return Stock.instance(stock.symbol, stock.type);
        }

        public Amount amount() {
            return Amount.instance(dividend.value());
        }

        @Override
        public String toString() {
            return dividend.toString() + " : " + stock.toString();
        }

        @Override
        public boolean equals(Object obj) {
            try {
                Dividend other = (Dividend) obj;
                return this.stock.equals(other.stock) && this.dividend.equals(other.dividend);
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return stock.hashCode() + dividend.hashCode();
        }
    }

    public class DividendYield {
        private final Stock stock;
        private final Double value;
        private NumberFormat nf;

        protected DividendYield(Stock aStock, Integer intValue) {
            this(aStock, (double) intValue);
        }

        protected DividendYield(Stock aStock, Double dbValue) {
            stock = aStock;
            value = dbValue;
            nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(1);
        }

        public Stock stock() {
            return Stock.instance(stock.symbol, stock.type);
        }

        @Override
        public String toString() {
            return nf.format(value);
        }
    }

}