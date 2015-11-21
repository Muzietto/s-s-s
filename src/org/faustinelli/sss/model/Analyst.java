package org.faustinelli.sss.model;

import org.faustinelli.sss.util.Amount;

import java.time.ZonedDateTime;
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

    public Stock.DividendYield dividendYield(Stock stock, Trader trader, ZonedDateTime now) {
        Stock.Dividend lastDividend = lastDividends.get(stock);
        Amount tickerPrice = trader.stockPrice(stock, now);

        if (tickerPrice.value() < Double.MIN_VALUE * 1000) {
            return Stock.NULL_DIVIDEND_YIELD;
        }
        return stock.dividendYield(lastDividend.amount().value()/new Double(tickerPrice.value()));
    }

    public Stock.DividendYield dividendYield(Stock stock, Trader trader) {
        return dividendYield(stock, trader, ZonedDateTime.now());
    }

    public Stock.PERatio peRatio(Stock stock, Trader trader, ZonedDateTime now) {
        Stock.Dividend lastDividend = lastDividends.get(stock);
        Amount tickerPrice = trader.stockPrice(stock, now);

        if (lastDividend == null || lastDividend.amount().value() == 0) {
            return Stock.NULL_PE_RATIO;
        }
        return stock.peRatio((int) Math.round(new Double(tickerPrice.value())/lastDividend.amount().value()));
    }

    public Stock.PERatio peRatio(Stock stock, Trader trader) {
        return peRatio(stock, trader, ZonedDateTime.now());
    }
}
