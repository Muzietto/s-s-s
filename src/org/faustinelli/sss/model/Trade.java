package org.faustinelli.sss.model;

import java.time.LocalDateTime;

public class Trade {

    private final Stock stock;
    private final Indicator indicator;
    private final Amount price;
    private final LocalDateTime timestamp;

    private Trade(Stock aStock, Indicator anIndicator, Amount aPrice) {
        stock = aStock;
        indicator = anIndicator;
        price = aPrice;
        timestamp = LocalDateTime.now();
    }

    public Stock stock() {
        return stock;
    }

    public Amount price() {
        return price;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    public static Trade instance(Stock aStock, Indicator anIndicator, Amount aPrice) {
        return new Trade(aStock, anIndicator, aPrice);
    }


    public enum Indicator {
        BUY, SELL
    }
}

