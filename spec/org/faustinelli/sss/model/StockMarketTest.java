package org.faustinelli.sss.model;

import junit.framework.TestCase;
import org.faustinelli.sss.util.Amount;

public class StockMarketTest extends TestCase {

    public void testMarket() throws Exception {

        StockMarket gbce = StockMarket.GBCE();

        gbce.trade(Stock.instance("ABC", Stock.Type.COMMON), Trade.Indicator.SELL, Amount.instance(10), 100);
        gbce.trade(Stock.instance("ZYZ", Stock.Type.PREFERRED), Trade.Indicator.BUY, Amount.instance(20), 50);
        gbce.trade(Stock.instance("JKL", Stock.Type.PREFERRED), Trade.Indicator.SELL, Amount.instance(30), 30);

        assertEquals(new Integer(18), gbce.gbceAllSharesIndex());
        assertEquals(Amount.instance(10), gbce.tickerPrice(Stock.instance("ABC", Stock.Type.COMMON)));
    }
}
