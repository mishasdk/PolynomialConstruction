package com.bulumutka.polyconstr.models.graphlib;

public class TestHelper {
    private TestHelper() {
    }

    public static MetricGraph generateDenseGraph(int size) {
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
