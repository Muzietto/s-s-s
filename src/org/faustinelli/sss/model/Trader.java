package org.faustinelli.sss.model;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

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

        Fraction<Amount, Integer> result = trades.stream()
                .filter(trade -> trade.stock().equals(stock))
                .map((Trade t) -> t.priceQuantity())
                .reduce(new Fraction(Amount.ZERO_PENNIES, 0),
                        new BiFunction<Fraction<Amount, Integer>, Trade.PriceQuantity, Fraction<Amount, Integer>>() {
                            @Override
                            public Fraction apply(Fraction<Amount, Integer> acc, Trade.PriceQuantity curr) {
                                return new Fraction<Amount, Integer>(
                                        Amount.instance(
                                                curr.getPrice().value() * curr.getQuantity() + acc.numerator().value(),
                                                acc.numerator().currency()
                                        ),
                                        acc.denominator() + curr.getQuantity()
                                );
                            }
                        },
                        new BinaryOperator<Fraction<Amount, Integer>>() {
                            @Override
                            public Fraction<Amount, Integer> apply(Fraction<Amount, Integer> fr1, Fraction<Amount, Integer> fr2) {
                                return new Fraction<Amount, Integer>(
                                        Amount.instance(fr1.numerator().value() + fr2.numerator().value(),
                                                fr1.numerator().currency()), fr1.denominator() + fr2.denominator()
                                );
                            }
                        }
                );

        try {

            int priceQtys = result.numerator().value();
            double qtys = (double)result.denominator();

            return Amount.instance((int) Math.round(priceQtys / qtys), result.numerator().currency());
        } catch (Exception exc) {
            return Amount.instance(0, result.numerator().currency());
        }
    }
}