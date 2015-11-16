package org.faustinelli.sss.util;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class GeometricMean {
    public static Integer value(List<Integer> aList) {
        Integer product = aList.stream()
                .reduce(1, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer acc, Integer curr) {
                        return acc * curr;
                    }
                });

        return (int) Math.round(Math.pow((double) product.intValue(), 1.0 / aList.size()));
    }
}
