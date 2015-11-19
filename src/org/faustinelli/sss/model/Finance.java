package org.faustinelli.sss.model;

import org.faustinelli.sss.util.Amount;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Finance {
    private final Map<Stock, Stock.Dividend> lastDividends;

    public Finance(Map<Stock, Stock.Dividend> theLastDividends) {
        lastDividends = theLastDividends;
    }

    public static Finance instance(Map<Stock, Stock.Dividend> lastDividends) {
        return new Finance(lastDividends);
    }

    public static Finance instance() {
        return new Finance(new ConcurrentHashMap<Stock, Stock.Dividend>());
    }

    public Stock.Dividend recordDividend(Stock stock, Amount value) {
        lastDividends.put(stock, stock.dividend(value));
        return lastDividends.get(stock);
    }

    public static Stock.DividendYield instance(Stock stock, Integer value) {
        return stock.dividendYield(new Double(value));
    }

    public static Stock.DividendYield instance(Stock stock, Double value) {
        return stock.dividendYield(value);
    }

}
