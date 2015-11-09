package org.faustinelli.sss.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Trade {

    private final Stock stock;
    private final Indicator indicator;
    private final Amount price;
    private final ZonedDateTime timestamp;

    private Trade(Stock aStock, Indicator anIndicator, Amount aPrice, ZonedDateTime aTimestamp) {
        stock = aStock;
        indicator = anIndicator;
        price = aPrice;
        timestamp = aTimestamp;
    }

    public Stock stock() {
        return stock;
    }

    public Amount price() {
        return price;
    }

    public ZonedDateTime timestamp() {
        return timestamp;
    }

    public static Trade instance(Stock aStock, Indicator anIndicator, Amount aPrice, ZonedDateTime aTimestamp) {
        return new Trade(aStock, anIndicator, aPrice, aTimestamp);
    }

    public enum Indicator {
        BUY, SELL
    }
}

