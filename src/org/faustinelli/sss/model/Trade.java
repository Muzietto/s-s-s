package org.faustinelli.sss.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.AbstractMap;

public class Trade {

    private final Stock stock;
    private final Indicator indicator;
    private final PriceQuantity priceQuantity;
    private final ZonedDateTime timestamp;

    private Trade(Stock aStock, Indicator anIndicator, Amount aPrice, Integer numShares, ZonedDateTime aTimestamp) {
        stock = aStock;
        indicator = anIndicator;
        priceQuantity = new PriceQuantity(aPrice, numShares);
        timestamp = aTimestamp;
    }

    public Stock stock() {
        return stock;
    }

    public PriceQuantity priceQuantity() {
        return priceQuantity;
    }

    public Amount price() {
        return (Amount) priceQuantity.getPrice();
    }

    public Integer quantity() {
        return (Integer) priceQuantity.getQuantity();
    }

    public ZonedDateTime timestamp() {
        return timestamp;
    }

    public static Trade instance(Stock aStock, Indicator anIndicator, Amount aPrice, Integer numShares, ZonedDateTime aTimestamp) {
        return new Trade(aStock, anIndicator, aPrice, numShares, aTimestamp);
    }

    public static PriceQuantity priceQuantity(Amount aPrice, Integer numShares) {
        return new PriceQuantity(aPrice, numShares);
    }

    public enum Indicator {
        BUY, SELL
    }

    public static class PriceQuantity extends AbstractMap.SimpleImmutableEntry<Amount, Integer> {

        private PriceQuantity(Amount aPrice, Integer numShares) {
            super(aPrice, numShares);
        }

        public Amount getPrice() {
            return (Amount) getKey();
        }

        public Integer getQuantity() {
            return (Integer) getValue();
        }
    }
}

