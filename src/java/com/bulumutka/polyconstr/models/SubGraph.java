package com.bulumutka.polyconstr.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class SubGraph implements Graph<GraphEdge, Integer> {
    private final MetricGraph otherGraph;
    private final Predicate<GraphEdge> predicate;
    private int vertexNumber = -1;

    public SubGraph(MetricGraph otherGraph, Predicate<GraphEdge> predicate) {
        this.otherGraph = otherGraph;
        this.predicate = predicate;
    }

    @Override
    public List<GraphEdge> outgoingEdges(Integer vertex) {
        List<GraphEdge> edges = otherGraph.outgoingEdges(vertex);
        List<GraphEdge> filteredEdges = new ArrayList<>();
        for (var edge : edges) {
            if (predicate.test(edge)) {
                filteredEdges.add(edge);
            }
        }
        return filteredEdges;
    }

    @Override
    public Integer getTarget(GraphEdge edge) {
        return otherGraph.getTarget(edge);
    }

    @Override
    public Integer getVertexNumber() {
        if (vertexNumber != -1) {
            return vertexNumber;
        }
        Set<Integer> set = new HashSet<>();
        for (var edge : otherGraph.getEdges()) {
            if (predicate.test(edge)) {
                set.add(edge.source);
                set.add(edge.target);
            }
        }
        vertexNumber = set.size();
        return vertexNumber;
    }

    public Integer getStartVertex() {
        return otherGraph.getStartVertex();
    }
}
