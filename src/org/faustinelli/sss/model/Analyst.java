package org.faustinelli.sss.model;

import org.faustinelli.sss.util.Amount;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Analyst {
    private final Map<Stock, Stock.Dividend> lastDividends;

    public Analyst(Map<Stock, Stock.Dividend> theLastDividends) {
        lastDividends = theLastDividends;
    }

    public static Analyst instance(Map<Stock, Stock.Dividend> lastDividends) {
        return new Analyst(lastDividends);
    }

    public static Analyst instance() {
        return new Analyst(new ConcurrentHashMap<Stock, Stock.Dividend>());
    }

    public Stock.Dividend recordDividend(Stock stock, Amount value) {
        lastDividends.put(stock, stock.dividend(value));
        return lastDividends.get(stock);
    }

    public Stock.DividendYield dividendYield(Stock stock, Trader trader) {
        Stock.Dividend lastDividend = lastDividends.get(stock);
        Amount tickerPrice = trader.stockPrice(stock);
        return stock.dividendYield(lastDividend.amount().value()/new Double(tickerPrice.value()));
    }
}
