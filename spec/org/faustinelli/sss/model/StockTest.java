package org.faustinelli.sss.model;

import junit.framework.Assert.*;
import junit.framework.TestCase;

public class StockTest extends TestCase {

    public void testCreateStock() throws Exception {

        Stock fiat = Stock.instance("Fiat", Stock.Type.PREFERRED);
        assertNotNull(fiat);
        assertEquals("FIA - PREFERRED", fiat.toString());
        assertEquals(Amount.instance(100), fiat.parValue());

        assertEquals(Stock.instance("Fiat", Stock.Type.PREFERRED), fiat);
        assertFalse(Stock.instance("Fiat", Stock.Type.COMMON).equals(fiat));
        assertFalse(Stock.instance("Volkswagen", Stock.Type.PREFERRED).equals(fiat));
    }

    public void testAmountDividend() throws Exception {

        Amount amm = Amount.instance(12);
        assertEquals("12 PENNY", amm.toString());

        Stock abc = Stock.instance("abc", Stock.Type.COMMON);
        Stock.Dividend divv = Stock.dividend(abc, amm);
        assertEquals("12 PENNY : ABC - COMMON", divv.toString());

    }



    // TODO make test for dividends in preferred stock
}