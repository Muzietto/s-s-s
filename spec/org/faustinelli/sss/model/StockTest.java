package org.faustinelli.sss.model;

import junit.framework.Assert.*;
import junit.framework.TestCase;

public class StockTest extends TestCase {

    public void testCreateStock() throws Exception {

        Stock fiat = Stock.instance("Fiat", Stock.Type.PREFERRED);
        assertNotNull(fiat);
        assertEquals("FIA - PREFERRED", fiat.toString());
        assertEquals(Amount.instance(100),fiat.parValue());

    }

}