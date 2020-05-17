package com.bulumutka.polyconstr.models.graphlib;

import com.bulumutka.polyconstr.models.graphlib.base.AbstractGraph;

import java.util.ArrayList;
import java.util.List;

public class MetricGraph extends AbstractGraph<GraphEdge, Integer> {
    private final List<GraphEdge> edges;
    private final ArrayList<ArrayList<GraphEdge>> adjacencyList;
    private final int rootVertex;

    public MetricGraph(List<GraphEdge> edges, ArrayList<ArrayList<GraphEdge>> list, int root) {
        this.edges = edges;
        adjacencyList = list;
        rootVertex = root;
    }

    @Override
    public List<GraphEdge> outgoingEdges(Integer vertex) {
        return adjacencyList.get(vertex);
    }

    @Override
    public Integer getVertexNumber() {
        return adjacencyList.size();
    }

    @Override
    public Integer getEdgesNumber() {
        return edges.size() / 2;
    }

    public Integer getRoot() {
        return rootVertex;
    }

    public List<GraphEdge> getEdges() {
        return edges;
    }
}
