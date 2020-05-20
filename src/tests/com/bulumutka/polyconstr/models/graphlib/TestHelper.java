package com.bulumutka.polyconstr.models.graphlib;

import java.util.ArrayList;
import java.util.List;

public class TestHelper {
    private TestHelper() {
    }

    public static MetricGraph generateDenseGraph(int size) {
        var builder = new GraphBuilder(size);
        int edges = size * (size - 1) / 2;
        var sample = getIndependentSample(edges);
        var num = 0;
        for (int i = 0; i != size; ++i) {
            for (int j = i + 1; j != size; ++j) {
                builder.addEdge(i, j, "sqrt(" + sample.get(num) + ")");
                ++num;
            }
        }
        builder.setRoot(0);
        return builder.build();
    }

    public static MetricGraph generateCycle(int size) {
        var builder = new GraphBuilder(size);
        var sample = getIndependentSample(size);
        for (int i = 0; i < size; ++i) {
            builder.addEdge(i, (i + 1) % size, "sqrt(" + sample.get(i) + ")");
        }
        builder.setRoot(0);
        return builder.build();
    }

    public static List<Integer> getIndependentSample(Integer size) {
        List<Integer> primes = new ArrayList<>(size);
        for (int i = 2; i < 10000; ++i) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; ++j) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                primes.add(i);
                if (primes.size() == size) {
                    break;
                }
            }
        }
        return primes;
    }
}
