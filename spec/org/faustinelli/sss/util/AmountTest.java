package org.faustinelli.sss.util;

import junit.framework.TestCase;
import org.faustinelli.sss.util.Amount;

public class AmountTest extends TestCase {

    public void testAmountInstance() throws Exception {
        Amount two = Amount.instance(2);
        Amount twopennies = Amount.instance(2, Amount.Currency.PENNY);

        assertTrue(two.equals(twopennies));
        assertEquals(two.hashCode(), twopennies.hashCode());
        assertEquals("2 PENNY", two.toString());
    }
}