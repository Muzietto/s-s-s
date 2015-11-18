package org.faustinelli.sss.model;

import junit.framework.TestCase;
import org.faustinelli.sss.util.Amount;

public class FinanceTest extends TestCase {


    public void testInstanceRecordDividend() throws Exception {
        Finance fff = Finance.instance();
        Stock com = Stock.common("com", Amount.instance(100));

        Stock.Dividend div1 = com.dividend(Amount.instance(10));
        Stock.Dividend returnDivv = fff.recordDividend(div1);
        assertEquals(returnDivv, div1);

        Stock.Dividend div2 = com.dividend(Amount.instance(11));
        returnDivv = fff.recordDividend(div2);
        assertEquals(returnDivv, div2);
    }

    public void testInstanceDividendYield() throws Exception {

    }

}