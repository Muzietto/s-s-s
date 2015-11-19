package org.faustinelli.sss.model;

import junit.framework.TestCase;
import org.faustinelli.sss.util.Amount;

public class AnalystTest extends TestCase {


    public void testInstanceRecordDividend() throws Exception {
        Analyst analyst = org.faustinelli.sss.model.Analyst.instance();
        Stock com = Stock.common("com", Amount.instance(100));

        Stock.Dividend returnDivv = analyst.recordDividend(com, Amount.instance(10));
        assertEquals(returnDivv, com.dividend(Amount.instance(10)));

        returnDivv = analyst.recordDividend(com, Amount.instance(11));
        assertEquals(returnDivv, com.dividend(Amount.instance(11)));
    }

    public void testCalculateDividendYield() throws Exception {
        Analyst analyst = org.faustinelli.sss.model.Analyst.instance();
        Stock com = Stock.common("com", Amount.instance(100));
        Stock pre = Stock.preferred("com", Amount.instance(5), new Integer(3));
        Trader trader = Trader.instance();

        trader.trade(com, Trade.Indicator.SELL, Amount.instance(10), new Integer(100));
        trader.trade(pre, Trade.Indicator.BUY, Amount.instance(20), new Integer(10));
        analyst.recordDividend(com, Amount.instance(7));
        analyst.recordDividend(pre, Amount.instance(8));

        assertEquals(com.dividendYield(new Double(0.7)), analyst.dividendYield(com, trader));
        assertEquals(pre.dividendYield(new Double(0.75)), analyst.dividendYield(pre, trader));
    }

}