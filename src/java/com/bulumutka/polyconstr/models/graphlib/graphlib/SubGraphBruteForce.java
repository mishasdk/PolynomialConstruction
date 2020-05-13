package com.bulumutka.polyconstr.models.graphlib.graphlib;

import com.bulumutka.polyconstr.exceptions.GraphParameterException;

public class SubGraphBruteForce {
    private static final int MAX_EDGE_NUMBER = 63;
    private long mask = -1;
    private final MetricGraph graph;
    private final long maxMask;

    public SubGraphBruteForce(MetricGraph graph) throws GraphParameterException {
        if (graph.getEdgesNumber() > MAX_EDGE_NUMBER) {
            throw new GraphParameterException("Graph should not has more than 63 edges.");
        }
        this.graph = graph;
        maxMask = 1L << graph.getEdgesNumber();
    }

    public SubGraph next() {
        if (mask == maxMask - 1) {
            return null;
        }
        ++mask;
        System.out.println("Mask: " + mask);
        return new SubGraph(graph, edge -> {
            int bit = edge.id / 2;
            return (mask & 1L << bit) != 0;
        });
    }
}
