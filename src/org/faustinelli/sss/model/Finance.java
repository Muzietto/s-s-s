package org.faustinelli.sss.model;

import java.util.HashSet;
import java.util.Set;

public class Finance {
    private final Set<Stock.Dividend> lastDividends;

    public Finance(Set<Stock.Dividend> theLastDividends) {
        lastDividends = theLastDividends;
    }

    public static Finance instance(Set<Stock.Dividend> lastDividends) {
        return new Finance(lastDividends);
    }

    public static Finance instance() {
        return new Finance(new HashSet<Stock.Dividend>());
    }

    public Stock.Dividend recordDividend(Stock.Dividend dividend) {
        lastDividends.add(dividend);
        return dividend;
    }

    public static Stock.DividendYield instance(Stock stock, Integer value) {
        return stock.dividendYield(new Double(value));
    }

    public static Stock.DividendYield instance(Stock stock, Double value) {
        return stock.dividendYield(value);
    }

}
