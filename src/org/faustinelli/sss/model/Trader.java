package org.faustinelli.sss.model;

import java.util.*;
import java.util.function.Predicate;

public class Trader {

    private final Set<Trade> trades;

    private Trader() {
        this(new TreeSet<Trade>(new Comparator<Trade>() {
            @Override
            public int compare(Trade t1, Trade t2) {
                return t1.timestamp().compareTo(t2.timestamp());
            }
        }));
    }

    private Trader(Set someTrades) {
        trades = someTrades;
    }

    public static Trader instance() {
        return new Trader();
    }

    public static Trader instance(Set someTrades) {
        return new Trader(someTrades);
    }

    public Trade trade(Stock aStock, Trade.Indicator anIndicator, Amount aPrice) {
        Trade trade = Trade.instance(aStock, anIndicator, aPrice);
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