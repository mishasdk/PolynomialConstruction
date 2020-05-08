package com.bulumutka.polyconstr.models;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Edge> edges;
    private ArrayList<ArrayList<Integer>> adjacencyList;

    public Graph(List<Edge> edges, ArrayList<ArrayList<Integer>> list) {
        this.edges = edges;
        adjacencyList = list;
    }
}
