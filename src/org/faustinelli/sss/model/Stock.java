package org.faustinelli.sss.model;

import org.faustinelli.sss.util.Amount;

import java.text.NumberFormat;
import java.util.Optional;

public class Stock {

    public static Dividend NULL_DIVIDEND =
            Stock.common("___", Amount.ZERO_PENNIES).dividend(Amount.ZERO_PENNIES);
    public static final DividendYield NULL_DIVIDEND_YIELD =
            Stock.common("___", Amount.ZERO_PENNIES).dividendYield(new Double(0));

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

    // just a straight factory - no domain knowledge whatsoever
    public DividendYield dividendYield(Double value) {
        return new DividendYield(this, value);
    }

    // just a straight factory - no domain knowledge whatsoever
    public PERatio peRatio(Integer value) {
        return new PERatio(this, value);
    }

    public PERatio peRatio(DividendYield yield) {
        if (yield.value() < Double.MIN_VALUE * 1000) {
            return Stock.NULL_PE_RATIO;
        }
        return new PERatio(yield.stock, new Integer((int) Math.round(1/yield.value)));
    }

    public Optional<Dividend> fixedDividend() {
        return this.type.fixedDividend(this).flatMap(amount -> Optional.of(new Dividend(this, amount)));
    }

    public Dividend dividend(Amount value) {
        return this.fixedDividend().orElse(this.new Dividend(this, value));
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
            Optional<Amount> fixedDividend(Stock stock) {
                return Optional.empty();
            }
        },
        PREFERRED {
            @Override
            Optional<Amount> fixedDividend(Stock stock) {
                return Optional.of(Amount.instance(stock.parValue().value() * stock.fixedDividendPc()));
            }
        };

        abstract Optional<Amount> fixedDividend(Stock stock);
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

        private DividendYield(Stock aStock, Integer intValue) {
            this(aStock, (double) intValue);
        }

        private DividendYield(Stock aStock, Double dbValue) {
            stock = aStock;
            value = dbValue;
            nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(1);
        }

        public Stock stock() {
            return Stock.instance(stock.symbol, stock.type);
        }

        public Double value() {
            return new Double(value);
        }

        @Override
        public String toString() {
            return nf.format(value);
        }

        @Override
        public boolean equals(Object obj) {
            try {
                DividendYield other = (DividendYield) obj;
                return this.stock.equals(other.stock) && this.value.equals(other.value);
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return stock.hashCode() + value.hashCode();
        }
    }

    public class PERatio {
        private final Stock stock;
        private final Integer value;
        private NumberFormat nf;

        private PERatio(Stock aStock, Integer intValue) {
            stock = aStock;
            value = intValue;
            nf = NumberFormat.getNumberInstance();
        }

        public Stock stock() {
            return Stock.instance(stock.symbol, stock.type);
        }

        public Integer value() {
            return new Integer(value);
        }

        @Override
        public String toString() {
            return nf.format(value);
        }

        @Override
        public boolean equals(Object obj) {
            try {
                PERatio other = (PERatio) obj;
                return this.stock.equals(other.stock) && this.value.equals(other.value);
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return stock.hashCode() + value.hashCode();
        }
    }
}