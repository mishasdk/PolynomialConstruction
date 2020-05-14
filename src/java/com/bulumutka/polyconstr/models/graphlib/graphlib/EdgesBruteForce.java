package com.bulumutka.polyconstr.models.graphlib.graphlib;

import com.bulumutka.polyconstr.exceptions.GraphParameterException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgesBruteForce {
    private static final int MAX_EDGE_NUMBER = 63;
    private long mask = -1;
    private final SubGraph graph;
    private final long maxMask;
    private final Map<Integer, Integer> permutation = new HashMap<>();

    public EdgesBruteForce(SubGraph g, List<GraphEdge> edges) throws GraphParameterException {
        if (edges.size() > MAX_EDGE_NUMBER) {
            throw new GraphParameterException("Graph should not has more than 63 edges.");
        }
        graph = g;
        maxMask = 1L << edges.size();
        for (var i = 0; i != edges.size(); ++i) {
            permutation.put(edges.get(i).id / 2, i);
        }
    }

    public SubGraph next() {
        if (mask == maxMask - 1) {
            return null;
        }
        ++mask;
        return graph.addFilter(edge -> {
            if (permutation.containsKey(edge.id / 2)) {
                return (mask & 1L << permutation.get(edge.id / 2)) != 0;
            }
            return true;
        });
    }
}
