package org.faustinelli.sss.util;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilTest extends TestCase {

    public void testGeometricMean() {

        assertEquals(new Integer(2), GeometricMean.value(Arrays.asList(1, 2, 3, 2, 1)));

        List<Integer> list = new ArrayList<Integer>();
        Integer gazillion = new Integer(100000);
//        Integer gazillion = new Integer(1000000000);
        Integer times = 10;
        for (int i = 0; i < times; i++) {
            list.add(gazillion);
        }
        assertEquals(gazillion, GeometricMean.value(list));
    }

    public void testSimpleFraction() {
        assertEquals(new Integer(12), new Fraction<Integer, Integer>(12, 13).numerator());
        assertEquals(new Integer(13), new Fraction<Integer, Amount>(12, Amount.instance(13)).denominator().value());
    }

}