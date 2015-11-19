package org.faustinelli.sss.model;

import junit.framework.TestCase;
import org.faustinelli.sss.util.Amount;

// NB: use different shares in each test!
public class StockMarketTest extends TestCase {

    public void testTickerPrice() throws Exception {

        StockMarket gbce = StockMarket.GBCE();

        gbce.trade(Stock.instance("ABC", Stock.Type.COMMON), Trade.Indicator.SELL, Amount.instance(10), 100);
        gbce.trade(Stock.instance("XYZ", Stock.Type.PREFERRED), Trade.Indicator.BUY, Amount.instance(20), 50);
        gbce.trade(Stock.instance("JKL", Stock.Type.PREFERRED), Trade.Indicator.SELL, Amount.instance(30), 30);

        assertEquals(Amount.instance(10), gbce.tickerPrice(Stock.instance("ABC", Stock.Type.COMMON)));
        assertEquals(Amount.instance(20), gbce.tickerPrice(Stock.instance("XYZ", Stock.Type.PREFERRED)));
        assertEquals(Amount.instance(30), gbce.tickerPrice(Stock.instance("jkl", Stock.Type.PREFERRED)));
    }

    public void testDividendYield() throws Exception {
        StockMarket gbce = StockMarket.GBCE();

        Stock common = Stock.common("co1", Amount.instance(100));
        Stock preferred = Stock.preferred("pr1", Amount.instance(5), new Integer(3));

        gbce.trade(common, Trade.Indicator.SELL, Amount.instance(10), new Integer(100));
        gbce.trade(preferred, Trade.Indicator.SELL, Amount.instance(20), new Integer(10));

        gbce.recordDividend(common, Amount.instance(7));
        gbce.recordDividend(preferred, Amount.instance(8));

        assertEquals(common.dividendYield(new Double(0.7)), gbce.dividendYield(common));
        assertEquals(preferred.dividendYield(new Double(0.75)), gbce.dividendYield(preferred));
    }
}
