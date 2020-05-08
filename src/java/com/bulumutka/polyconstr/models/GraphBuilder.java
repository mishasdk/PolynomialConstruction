package com.bulumutka.polyconstr.models;

import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {
    private int vertexNumber = 0;
    private int currentEdgeId = 0;
    private List<Edge> edges = new ArrayList<>();

    public void addEdge(int source, int target, int time) {
        edges.add(new Edge(currentEdgeId++, source, target, time));
    }

    public void addVertex() {
        vertexNumber++;
    }

    public void removeEdge(int source, int target) {
        edges.removeIf(edge -> (edge.target == target && edge.source == source ||
                edge.source == target && edge.target == source));
    }

    public void removeVertex(int vertex) {
        edges.removeIf(edge -> (edge.target == vertex || edge.source == vertex));
        for (var edge : edges) {
            if (edge.source > vertex) {
                --edge.source;
            }
            if (edge.target > vertex) {
                --edge.target;
            }
        }
        vertexNumber--;
    }

    public Graph build() {
        for (int i = 0; i != edges.size(); ++i) {
            edges.get(i).id = i;
        }
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(vertexNumber);
        for (var i = 0; i != vertexNumber; ++i) {
            adjacencyList.add(new ArrayList<>());
        }
        for (var edge : edges) {
            adjacencyList.get(edge.source).add(edge.target);
            adjacencyList.get(edge.target).add(edge.source);
        }
        return new Graph(edges, adjacencyList);
    }

    public void reset() {
        currentEdgeId = 0;
        vertexNumber = 0;
        edges.clear();
    }
}
