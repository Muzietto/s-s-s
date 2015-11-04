package org.faustinelli.sss.model;

import junit.framework.Assert.*;
import junit.framework.TestCase;

public class StockTest extends TestCase {

    public void testCreateStock() throws Exception {

        Stock fiat = Stock.instance("FIAT", Stock.Type.PREFERRED);
        assertNotNull(fiat);
        assertEquals("FIAT - PREFERRED", fiat.toString());

    }

}