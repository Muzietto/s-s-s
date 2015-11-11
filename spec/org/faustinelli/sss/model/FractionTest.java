package org.faustinelli.sss.model;

import junit.framework.TestCase;

public class FractionTest extends TestCase {

    public void testNumerator() throws Exception {

        Fraction fff = new Fraction(12, 23);
        assertEquals(12, fff.numerator());
        assertEquals(23, fff.denominator());

    }
}