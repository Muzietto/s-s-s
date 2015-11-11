package org.faustinelli.sss.model;

import java.util.Set;

public class StockMarket {

    private static final StockMarket INSTANCE = null;

    private Set<Stock> stocks;
    // TODO have a Finance handle this
    private Set<Stock.Dividend> lastDividends;
    private Trader trader;

    public static StockMarket THE_MARKET() {
        return INSTANCE;
    }

    public Trade trade(Stock aStock, Trade.Indicator anIndicator, Amount aPrice, Integer numShares) {
        stocks.add(aStock);
        return trader.trade(aStock, anIndicator, aPrice, numShares);
    }

    public Amount tickerPrice(Stock stock) {
        return trader.tickerPrice(stock);
    }
}
