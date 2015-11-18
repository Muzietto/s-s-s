package org.faustinelli.sss.model;

import junit.framework.TestCase;
import org.faustinelli.sss.util.Amount;

public class FinanceTest extends TestCase {


    public void testInstance() throws Exception {
        Finance fff = Finance.instance();
        Stock com = Stock.common("com", Amount.instance(100));
        fff.recordDividend(Stock.dividend(com, Amount.instance(10)));

//        assertEquals(null, fff.dividendYield(com));

    }
}