package org.faustinelli.sss.model;

import junit.framework.TestCase;

public class AmountTest extends TestCase {

    public void testInstance() throws Exception {
        Amount two = Amount.instance(2);
        Amount twopennies = Amount.instance(2, Amount.Currency.PENNY);

        assertTrue(two.equals(twopennies));
        assertEquals("2 PENNY", two.toString());
    }
}