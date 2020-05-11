package com.bulumutka.polyconstr.models;

import java.util.ArrayList;
import java.util.List;

public class MetricGraph extends AbstractGraph<GraphEdge, Integer> {
    private final List<GraphEdge> edges;
    private final ArrayList<ArrayList<Integer>> adjacencyList;
    private final int rootVertex;

    public MetricGraph(List<GraphEdge> edges, ArrayList<ArrayList<Integer>> list, int root) {
        this.edges = edges;
        adjacencyList = list;
        rootVertex = root;
    }

    @Override
    public List<GraphEdge> outgoingEdges(Integer vertex) {
        List<GraphEdge> edges = new ArrayList<>();
        for (var id : adjacencyList.get(vertex)) {
            edges.add(this.edges.get(id));
        }
        return edges;
    }

    @Override
    public Integer getTarget(GraphEdge edge) {
        return edge.target;
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
