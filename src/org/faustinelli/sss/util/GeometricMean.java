package org.faustinelli.sss.util;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class GeometricMean {
    public static Integer value(List<Integer> aList) {
        try {
            Double sumOfLogs = aList.stream()
                    .map(item -> {
                        if (item == 0) {
                            throw new RuntimeException();
                        }
                        return new Double(Math.log((double) item));
                    })
                    .reduce(new Double(0), (curr, acc) -> curr + acc );

            // return exp(sumOfLogs/listSize)
            return (int) Math.round(Math.exp(sumOfLogs / aList.size()));
        } catch (RuntimeException ex) {
            return 0;
        }
    }
}
