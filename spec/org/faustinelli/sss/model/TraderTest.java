package org.faustinelli.sss.model;

import junit.framework.TestCase;

import java.time.ZonedDateTime;
import java.util.*;

public class TraderTest extends TestCase {

    private TreeSet<Trade> trades;

    private Trader trader;

    public void setUp() throws Exception {
        trades = new TreeSet<Trade>(new Comparator<Trade>() {
            @Override
            public int compare(Trade t1, Trade t2) {
                return t2.timestamp().compareTo(t1.timestamp());
            }
        });

        trader = Trader.instance(this.trades, new TradingClock() {
            @Override
            public ZonedDateTime time() {
                ZonedDateTime now = ZonedDateTime.now();
                System.out.println(now);
                return now;
            }
        });
    }

    public void testTrade() throws Exception {
        Trade result = trader.trade(
                Stock.instance("XYZ", Stock.Type.PREFERRED),
                Trade.Indicator.BUY,
                Amount.instance(55),
                12);
        assertEquals("XYZ - PREFERRED", result.stock().toString());
        assertEquals("55 PENNY", result.price().toString());
        assertEquals(new Integer(12), result.quantity());
        assertEquals(1, this.trades.size());
    }


    public void testTickerPrice() throws Exception {
        Stock xyz = Stock.instance("XYZ", Stock.Type.PREFERRED);
        Stock abc = Stock.instance("ABC", Stock.Type.COMMON);
        trader.trade(abc, Trade.Indicator.BUY, Amount.instance(55), 13);
        Thread.sleep(10);
        trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(85), 14);
        Thread.sleep(10);
        trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(65), 15);
        Thread.sleep(10);
        trader.trade(abc, Trade.Indicator.SELL, Amount.instance(45), 16);
        Thread.sleep(10);
        trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(25), 17);
        Thread.sleep(10);
        trader.trade(xyz, Trade.Indicator.SELL, Amount.instance(35), 18);

        assertEquals(Amount.instance(35), trader.tickerPrice(xyz));
        assertEquals(Amount.instance(45), trader.tickerPrice(abc));

    }

    /*
      TODO - succeeds only when executed alone. Make execution of all tests sequential.
     */
    public void testStockPrice() throws Exception {
        Stock xyz = Stock.instance("XYZ", Stock.Type.PREFERRED);
        Stock abc = Stock.instance("ABC", Stock.Type.PREFERRED);

        trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(20), 2);
        assertEquals(Amount.instance(20), trader.stockPrice(xyz));
        Thread.sleep(10);

        trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(10), 1);
        assertEquals(Amount.instance(17), trader.stockPrice(xyz));
        Thread.sleep(10);

        trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(10), 1);
        assertEquals(Amount.instance(15), trader.stockPrice(xyz));
        Thread.sleep(10);

        trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(15), 2);
        assertEquals(Amount.instance(15), trader.stockPrice(xyz));
        Thread.sleep(10);

        trader.trade(xyz, Trade.Indicator.BUY, Amount.instance(20), 3);
        assertEquals(Amount.instance(17), trader.stockPrice(xyz));
    }

    public void testOrderedSet() throws Exception {
        Set orderedSet = new TreeSet<ZonedDateTime>(new Comparator<ZonedDateTime>() {
            @Override
            public int compare(ZonedDateTime o1, ZonedDateTime o2) {
                return o2.compareTo(o1);
            }
        });

        Calendar cal = Calendar.getInstance();
        ZonedDateTime oggi = ZonedDateTime.now();
        System.out.println("oggi=" + oggi);
        ZonedDateTime ieri = oggi.minusDays(1);
        System.out.println("ieri=" + ieri);
        ZonedDateTime domani = oggi.plusDays(1);
        System.out.println("domani=" + domani);

        orderedSet.add(oggi);
        orderedSet.add(ieri);
        orderedSet.add(domani);

        ZonedDateTime first = (ZonedDateTime) orderedSet.stream().findFirst().get();

        assertEquals(domani, first);
    }
}