package org.faustinelli.sss.model;

import java.util.AbstractMap;
import java.util.Map;

public class Fraction<N, D> extends AbstractMap.SimpleImmutableEntry<N, D> {

    public Fraction(N numerator, D denominator) {
        super(numerator, denominator);
    }

    public N numerator() {
        return getKey();
    }

    public D denominator() {
        return getValue();
    }
}
