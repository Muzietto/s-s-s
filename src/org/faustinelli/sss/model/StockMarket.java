package org.faustinelli.sss.model;

import org.faustinelli.sss.util.Amount;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BinaryOperator;

public class StockMarket {

    private static final StockMarket GBCE = new StockMarket();

    private Set<Stock> stocks;
    private Analyst analyst;
    private Trader trader;

    private StockMarket() {
        stocks = new HashSet<Stock>();
        trader = Trader.instance();
        analyst = Analyst.instance();
    }

    public static StockMarket GBCE() {
        return GBCE;
    }

    public Trade trade(Stock aStock, Trade.Indicator anIndicator, Amount aPrice, Integer numShares) {
        stocks.add(aStock);
        return trader.trade(aStock, anIndicator, aPrice, numShares);
    }

    public Amount tickerPrice(Stock stock) {
        return trader.stockPrice(stock);
    }

    public Integer gbceAllSharesIndex() {

        Integer product = stocks
                .stream()
                .map(stock -> trader.stockPrice(stock).value())
                .reduce(1, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer acc, Integer curr) {
                        return acc * curr;
                    }
                });

        return (int) Math.round(Math.pow((double) product.intValue(), 1.0 / stocks.size()));
    }

    public Stock.Dividend recordDividend(Stock stock, Amount value) {
        return analyst.recordDividend(stock, value);

    }

    public Stock.DividendYield dividendYield(Stock stock) {
        return analyst.dividendYield(stock, trader);
    }
}
