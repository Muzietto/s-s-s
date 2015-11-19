package org.faustinelli.sss.model;

import junit.framework.TestCase;
import org.faustinelli.sss.util.Amount;

public class FinanceTest extends TestCase {


    public void testInstanceRecordDividend() throws Exception {
        Finance fff = Finance.instance();
        Stock com = Stock.common("com", Amount.instance(100));

        Stock.Dividend returnDivv = fff.recordDividend(com, Amount.instance(10));
        assertEquals(returnDivv, com.dividend(Amount.instance(10)));

        returnDivv = fff.recordDividend(com, Amount.instance(11));
        assertEquals(returnDivv, com.dividend(Amount.instance(11)));
    }

    public void testInstanceDividendYield() throws Exception {

    }

}