package com.bulumutka.polyconstr.models.graphlib;

import com.bulumutka.polyconstr.models.graphlib.graphlib.Algorithms;
import com.bulumutka.polyconstr.models.graphlib.graphlib.GraphBuilder;
import com.bulumutka.polyconstr.models.graphlib.graphlib.MetricGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BridgesVisitorTest {
    @Test
    public void bridgesTest1() {
        var builder = new GraphBuilder(6);
        builder.addEdge(1, 2, 1);
        builder.addEdge(5, 4, 3);
        builder.addEdge(3, 4, 1);
        builder.addEdge(0, 4, 1);
        builder.addEdge(2, 3, 1);
        builder.setRoot(1);
        MetricGraph g = builder.build();
        var b = Algorithms.findBridges(g, 0);
        assertEquals(5, b.size());
    }

    @Test
    public void bridgesTest2() {
        var builder = new GraphBuilder(15);
        var edges = new int[][]{new int[]{0, 1}, new int[]{0, 2}, new int[]{1, 3}, new int[]{2, 4},
                new int[]{2, 5}, new int[]{3, 6}, new int[]{3, 7}, new int[]{4, 8}, new int[]{5, 9},
                new int[]{5, 10}, new int[]{6, 11}, new int[]{6, 12}, new int[]{7, 13},
                new int[]{7, 14}, new int[]{1, 6}, new int[]{3, 14}, new int[]{0, 8},
                new int[]{2, 10},};
        for (var p : edges) {
            builder.addEdge(p[0], p[1], 0);
        }
        var g = builder.build();

        var b = Algorithms.findBridges(g, 0);
        assertEquals(5, b.size());
    }

    @Test
    public void bridgesTest3() {
        var g = generateDenseGraph(5);
        assertEquals(10, g.getEdgesNumber());
        var b = Algorithms.findBridges(g, 0);
        assertEquals(0, b.size());

        g = generateDenseGraph(10);
        assertEquals(45, g.getEdgesNumber());
        b = Algorithms.findBridges(g, 0);
        assertEquals(0, b.size());
    }

    private static MetricGraph generateDenseGraph(int size) {
        var builder = new GraphBuilder(size);
        for (int i = 0; i != size; ++i) {
            for (int j = i + 1; j != size; ++j) {
                builder.addEdge(i, j, 0);
            }
        }
        builder.setRoot(0);
        return builder.build();
    }
}