package org.faustinelli.sss.model;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

public class Trader {

    private TradingClock clock;
    private final Set<Trade> trades;

    private Trader(TradingClock aClock) {
        this(new TreeSet<Trade>(new Comparator<Trade>() {
            @Override
            public int compare(Trade t1, Trade t2) {
                // LIFO
                return t2.timestamp().compareTo(t1.timestamp());
            }
        }), aClock);
    }

    private Trader(Set someTrades, TradingClock aClock) {
        trades = someTrades;
        clock = aClock;
    }

    public static Trader instance(TradingClock clock) {
        return new Trader(clock);
    }

    public static Trader instance(Set someTrades, TradingClock aClock) {
        return new Trader(someTrades, aClock);
    }

    private static Fraction<Amount, Amount> stockPriceInstance(Amount numerator, Integer denominator) {
        return new Fraction(numerator, denominator);
    }

    public Trade trade(Stock aStock, Trade.Indicator anIndicator, Amount aPrice, Integer numShares) {
        Trade trade = Trade.instance(aStock, anIndicator, aPrice, numShares, clock.time());
        trades.add(trade);
        return trade;
    }

    public Amount tickerPrice(Stock stock) {
        return trades.stream()
                .filter(trade -> trade.stock().equals(stock))
                .findFirst()
                .get()
                .price();
    }

    public Amount stockPrice(Stock stock) {
        return null;
        /*
        return trades.stream()
                .filter(trade -> trade.stock().equals(stock))
                .map((Trade t) -> t.priceQuantity())
                .reduce(Trade.priceQuantity(Amount.ZERO_PENNIES, 0),
                        (acc, curr) -> Trade.priceQuantity());
*/
    }
}