package org.faustinelli.sss.model;

import junit.framework.TestCase;
import org.faustinelli.sss.util.Amount;

public class StockTest extends TestCase {

    public void testCreateStock() throws Exception {

        Stock fiat = Stock.instance("Fiat", Stock.Type.PREFERRED);
        assertNotNull(fiat);
        assertEquals("FIA - PREFERRED", fiat.toString());
        assertEquals(Amount.instance(100), fiat.parValue());

        assertEquals(Stock.instance("Fiat", Stock.Type.PREFERRED), fiat);
        assertEquals(Stock.instance("Fiat", Stock.Type.PREFERRED).hashCode(), fiat.hashCode());

        assertFalse(Stock.instance("Fiat", Stock.Type.COMMON).equals(fiat));
        assertFalse(Stock.instance("Fiat", Stock.Type.COMMON).hashCode() == fiat.hashCode());

        assertFalse(Stock.instance("Volkswagen", Stock.Type.PREFERRED).equals(fiat));
        assertFalse(Stock.instance("Volkswagen", Stock.Type.PREFERRED).hashCode() == fiat.hashCode());
    }

    public void testAmountDividend() throws Exception {

        Amount amm = Amount.instance(12);
        assertEquals("12 PENNY", amm.toString());

        Stock abc = Stock.common("abc", Amount.instance(100));
        Stock.Dividend divv = Stock.dividend(abc, amm);
        assertEquals("12 PENNY : ABC - COMMON", divv.toString());

        assertEquals(abc, divv.stock());
        assertEquals(new Integer(12), divv.amount().value());

        Stock.Dividend abc100 = Stock.dividend(abc, Amount.instance(100));
        Stock.Dividend abc100_2 = Stock.dividend(abc, Amount.instance(100));
        Stock.Dividend abc101 = Stock.dividend(abc, Amount.instance(101));

        assertEquals(abc100, abc100_2);
        assertEquals(abc100.hashCode(), abc100_2.hashCode());

        assertFalse(abc100.equals(abc101));
        assertFalse(abc100.hashCode() == abc101.hashCode());
    }

    public void testDividendPreferredStock() throws Exception {
        Stock pre = Stock.preferred("pre", Amount.instance(250), new Integer(3));
        Stock com = Stock.common("com", Amount.instance(250));
        assertEquals(Stock.dividend(pre, Amount.instance(750)), pre.dividend().orElse(Stock.NULL_DIVIDEND));
        assertEquals(Stock.NULL_DIVIDEND, com.dividend().orElse(Stock.NULL_DIVIDEND));

    }
}