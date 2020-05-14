package com.bulumutka.polyconstr.models.graphlib.graphlib;

public class SubGraphBruteForce {
    private static final int MAX_EDGE_NUMBER = 63;
    private long mask = -1;
    private final MetricGraph graph;
    private final long maxMask;

    public SubGraphBruteForce(MetricGraph g) {
        if (g.getEdgesNumber() > MAX_EDGE_NUMBER) {
            throw new IllegalArgumentException("Graph should not has more than 63 edges.");
        }
        this.graph = g;
        maxMask = 1L << g.getEdgesNumber();
    }

    public SubGraph next() {
        if (mask == maxMask - 1) {
            return null;
        }
        ++mask;
        return new SubGraph(graph, edge -> {
            int bit = edge.id / 2;
            return (mask & 1L << bit) != 0;
        });
    }
}
