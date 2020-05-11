package com.bulumutka.polyconstr.models;

import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {
    private int vertexNumber = 0;
    private int currentEdgeId = 0;
    private int root = 0;
    private List<GraphEdge> edges = new ArrayList<>();

    public GraphBuilder() {
    }

    public GraphBuilder(int vertexNumber) {
        addVertex(vertexNumber);
    }

    public void addEdge(int source, int target, double time) {
        System.out.println("Graph builder: " + "add edge " + source + " " + target + " " + time);
        edges.add(new GraphEdge(currentEdgeId++, source, target, time));
        edges.add(new GraphEdge(currentEdgeId++, target, source, time));
    }

    public void addVertex(int number) {
        for (var i = 0; i != number; ++i) {
            addVertex();
        }
    }

    public void addVertex() {
        System.out.println("Graph builder: " + "add vertex " + vertexNumber);
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

    public void setRoot(int vertex) {
        root = vertex;
    }

    public MetricGraph build() {
        for (int i = 0; i != edges.size(); ++i) {
            edges.get(i).id = i;
        }
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(vertexNumber);
        for (var i = 0; i != vertexNumber; ++i) {
            adjacencyList.add(new ArrayList<>());
        }
        for (var edge : edges) {
            adjacencyList.get(edge.source).add(edge.id);
        }
        return new MetricGraph(edges, adjacencyList, root);
    }

    public void reset() {
        currentEdgeId = 0;
        vertexNumber = 0;
        edges.clear();
    }
}
