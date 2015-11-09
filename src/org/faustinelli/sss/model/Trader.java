package org.faustinelli.sss.model;

import java.time.Clock;
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

    public Trade trade(Stock aStock, Trade.Indicator anIndicator, Amount aPrice) {
        Trade trade = Trade.instance(aStock, anIndicator, aPrice, clock.time());
        trades.add(trade);
        return trade;
    }

    public Amount tickerPrice(Stock stock) {
        return trades.stream().filter(new Predicate<Trade>() {
            @Override
            public boolean test(Trade trade) {
                return trade.stock().equals(stock);
            }
        }).findFirst().get().price();
    }

    public Amount stockPrice(Stock stock) {
        return null;
    }
}