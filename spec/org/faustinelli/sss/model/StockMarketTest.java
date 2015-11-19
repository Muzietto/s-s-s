package org.faustinelli.sss.model;

import junit.framework.TestCase;
import org.faustinelli.sss.util.Amount;

public class StockMarketTest extends TestCase {

    public void testTickerPrice() throws Exception {

        StockMarket gbce = StockMarket.GBCE();

        gbce.trade(Stock.instance("ABC", Stock.Type.COMMON), Trade.Indicator.SELL, Amount.instance(10), 100);
        gbce.trade(Stock.instance("XYZ", Stock.Type.PREFERRED), Trade.Indicator.BUY, Amount.instance(20), 50);
        gbce.trade(Stock.instance("JKL", Stock.Type.PREFERRED), Trade.Indicator.SELL, Amount.instance(30), 30);

        assertEquals(Amount.instance(10), gbce.tickerPrice(Stock.instance("ABC", Stock.Type.COMMON)));
        assertEquals(Amount.instance(20), gbce.tickerPrice(Stock.instance("XYZ", Stock.Type.PREFERRED)));
        assertEquals(Amount.instance(30), gbce.tickerPrice(Stock.instance("XYZ", Stock.Type.PREFERRED)));
    }
    }
}
