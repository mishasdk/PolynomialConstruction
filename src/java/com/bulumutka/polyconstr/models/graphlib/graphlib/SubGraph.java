package com.bulumutka.polyconstr.models.graphlib.graphlib;

import com.bulumutka.polyconstr.models.graphlib.graphlib.base.AbstractGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class SubGraph extends AbstractGraph<GraphEdge, Integer> {
    private final MetricGraph otherGraph;
    private final Predicate<GraphEdge> predicate;
    private int vertexNumber = -1;
    private int edgesNumber = -1;
    private List<GraphEdge> edges = null;

    public SubGraph(MetricGraph otherGraph) {
        this.otherGraph = otherGraph;
        this.predicate = edge -> true;
    }

    public SubGraph(MetricGraph otherGraph, Predicate<GraphEdge> predicate) {
        this.otherGraph = otherGraph;
        this.predicate = predicate;
    }

    @Override
    public Integer getRoot() {
        return otherGraph.getRoot();
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
    public Integer getVertexNumber() {
        if (vertexNumber != -1) {
            return vertexNumber;
        }
        Set<Integer> set = new HashSet<>();
        for (var edge : getEdges()) {
            set.add(edge.source);
            set.add(edge.target);
        }
        vertexNumber = set.size();
        return vertexNumber;
    }

    @Override
    public Integer getEdgesNumber() {
        if (edgesNumber != -1) {
            return edgesNumber;
        }
        edgesNumber = 0;
        for (var edge : getEdges()) {
            ++edgesNumber;
        }
        edgesNumber /= 2;
        return edgesNumber;
    }

    public SubGraph addFilter(Predicate<GraphEdge> filter) {
        return new SubGraph(otherGraph, predicate.and(filter));
    }

    public List<GraphEdge> getEdges() {
        if (edges != null) {
            return edges;
        }
        edges = new ArrayList<>();
        for (var edge : otherGraph.getEdges()) {
            if (predicate.test(edge)) {
                edges.add(edge);
            }
        }
        return edges;
    }
}
