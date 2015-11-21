package org.faustinelli.sss.model;

import org.faustinelli.sss.util.Amount;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BinaryOperator;

public class StockMarket {

    private static final StockMarket GBCE = new StockMarket();

    private Set<Stock> stocks;
    private Analyst analyst;
    private Trader trader;

    private StockMarket() {
        this(Trader.instance());
    }

    private StockMarket(Trader aTrader) {
        stocks = new HashSet<Stock>();
        trader = aTrader;
        analyst = Analyst.instance();
    }

    public static StockMarket GBCE() {
        return GBCE;
    }

    public static StockMarket instance(Trader trader) {
        return new StockMarket(trader);
    }

    public Trade trade(Stock aStock, Trade.Indicator anIndicator, Amount aPrice, Integer numShares) {
        stocks.add(aStock);
        return trader.trade(aStock, anIndicator, aPrice, numShares);
    }

    public Amount tickerPrice(Stock stock) {
        return trader.stockPrice(stock);
    }

    public Amount tickerPrice(Stock stock, ZonedDateTime now) {
        return trader.stockPrice(stock, now);
    }

    public Integer gbceAllSharesIndex() {
        return gbceAllSharesIndex(ZonedDateTime.now());
    }

    public Integer gbceAllSharesIndex(ZonedDateTime now) {

        Double product = stocks
                .stream()
                .map(stock -> new Double(trader.stockPrice(stock, now).value()))
                .reduce(1.0, new BinaryOperator<Double>() {
                    @Override
                    public Double apply(Double acc, Double curr) {
                        return acc * curr;
                    }
                });

        int result = (int) Math.round(Math.pow(product, 1.0 / stocks.size()));
        return result;
    }

    public Stock.Dividend recordDividend(Stock stock, Amount value) {
        return analyst.recordDividend(stock, value);

    }

    public Stock.DividendYield dividendYield(Stock stock) {
        return analyst.dividendYield(stock, trader);
    }

    public Stock.DividendYield dividendYield(Stock stock, ZonedDateTime now) {
        return analyst.dividendYield(stock, trader, now);
    }

    public Stock.PERatio peRatio(Stock stock) {
        return analyst.peRatio(stock, trader);
    }

    public Stock.PERatio peRatio(Stock stock, ZonedDateTime now) {
        return analyst.peRatio(stock, trader, now);
    }
}
