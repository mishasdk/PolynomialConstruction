package com.bulumutka.polyconstr.models;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<Edge> edges;
    private final ArrayList<ArrayList<Integer>> adjacencyList;
    private final int startVertex;

    public Graph(List<Edge> edges, ArrayList<ArrayList<Integer>> list, int startVertex) {
        this.edges = edges;
        adjacencyList = list;
        this.startVertex = startVertex;
    }
}
