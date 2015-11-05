package org.faustinelli.sss.model;

import java.security.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class StockMarket {

    private static final StockMarket INSTANCE = null;

    private Set<Stock> stocks;
    // TODO have a Finance handle this
    private Set<Stock.Dividend> lastDividends;
    private Trader trader;

    public static StockMarket THE_MARKET() {
        return INSTANCE;
    }

    public Trade trade(Stock aStock, Trade.Indicator anIndicator, Amount aPrice) {
        stocks.add(aStock);
        return trader.trade(aStock, anIndicator, aPrice);
    }

    public Amount tickerPrice(Stock stock) {
        return trader.tickerPrice(stock);
    }
}
