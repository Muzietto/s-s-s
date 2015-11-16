package org.faustinelli.sss.util;

import junit.framework.TestCase;
import org.faustinelli.sss.model.Amount;

import java.util.Arrays;

public class UtilTest extends TestCase {

    public void testGeometricMean() {
        assertEquals(new Integer(2), GeometricMean.value(Arrays.asList(1, 2, 3, 2, 1)));
    }

    public void testSimpleFraction() {
        assertEquals(new Integer(12), new Fraction<Integer, Integer>(12, 13).numerator());
        assertEquals(new Integer(13), new Fraction<Integer, Amount>(12, Amount.instance(13)).denominator().value());
    }

}