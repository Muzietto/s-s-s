package org.faustinelli.sss.model;

import junit.framework.TestCase;

import java.util.Comparator;
import java.util.TreeSet;

public class TraderTest extends TestCase {

    private TreeSet<Trade> trades;

    private Trader trader;

    public void setUp() throws Exception {
        this.trades = new TreeSet<Trade>(new Comparator<Trade>() {
            @Override
            public int compare(Trade t1, Trade t2) {
                return t1.timestamp().compareTo(t2.timestamp());
            }
        });

        this.trader = Trader.instance(this.trades);
    }

    public void testTrade() throws Exception {

        Trade result = trader.trade(Stock.instance("XYZ", Stock.Type.PREFERRED), Trade.Indicator.BUY, Amount.instance(55));
        assertEquals("XYZ - PREFERRED", result.stock().toString());
        assertEquals(1, this.trades.size());
    }

    public void testTickerPrice() throws Exception {
        Stock xyz = Stock.instance("XYZ", Stock.Type.PREFERRED);
        Stock abc = Stock.instance("ABC", Stock.Type.COMMON);
        this.trader.trade(abc, Trade.Indicator.BUY, Amount.instance(55));
        this.trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(85));
        this.trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(65));
        this.trader.trade(abc, Trade.Indicator.SELL, Amount.instance(45));
        this.trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(25));
        this.trader.trade(xyz, Trade.Indicator.SELL, Amount.instance(35));

        //assertEquals("35 PENNY",trader.tickerPrice(xyz));
        //assertEquals("45 PENNY",trader.tickerPrice(abc));

    }

    public void testStockPrice() throws Exception {
        Stock xyz = Stock.instance("XYZ", Stock.Type.PREFERRED);
        this.trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(55));
        this.trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(85));
        this.trader.trade(xyz, Trade.Indicator.SELL, Amount.instance(35));

        assertEquals(null,trader.stockPrice(xyz));

    }
}