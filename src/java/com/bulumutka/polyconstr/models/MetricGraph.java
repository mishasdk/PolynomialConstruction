package com.bulumutka.polyconstr.models;

import java.util.ArrayList;
import java.util.List;

public class MetricGraph implements Graph<GraphEdge, Integer> {
    private final List<GraphEdge> edges;
    private final ArrayList<ArrayList<Integer>> adjacencyList;
    private final int startVertex;

    public MetricGraph(List<GraphEdge> edges, ArrayList<ArrayList<Integer>> list, int startVertex) {
        this.edges = edges;
        adjacencyList = list;
        this.startVertex = startVertex;
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

    public int getStartVertex() {
        return startVertex;
    }

    public List<GraphEdge> getEdges() {
        return edges;
    }
}
