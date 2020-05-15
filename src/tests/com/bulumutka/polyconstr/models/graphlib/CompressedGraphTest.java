package com.bulumutka.polyconstr.models.graphlib;

import org.junit.jupiter.api.Test;

class CompressedGraphTest {
    @Test
    public void getVectorTest1() {
        var builder = new GraphBuilder(3);
        builder.addEdge(0, 1, 1);
        builder.addEdge(1, 2, 1);
        builder.setRoot(0);
        var cg = new CompressedGraph(builder.build());
        System.out.println(cg.getVector());
    }

    @Test
    public void getVectorTest2() {
        var builder = new GraphBuilder(15);
        var edges = new int[][]{new int[]{0, 1}, new int[]{0, 2}, new int[]{1, 3}, new int[]{2, 4},
                new int[]{2, 5}, new int[]{3, 6}, new int[]{3, 7}, new int[]{4, 8}, new int[]{5, 9},
                new int[]{5, 10}, new int[]{6, 11}, new int[]{6, 12}, new int[]{7, 13},
                new int[]{7, 14}, new int[]{1, 6}, new int[]{3, 14}, new int[]{0, 8},
                new int[]{2, 10},};
        for (var p : edges) {
            builder.addEdge(p[0], p[1], 0);
        }
        var cg = new CompressedGraph(builder.build());
        System.out.println(cg.getVector());
    }

    @Test
    public void getVectorTest3() {
        var cg = new CompressedGraph(generateDenseGraph(2));
        System.out.println(cg.getVector());
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
