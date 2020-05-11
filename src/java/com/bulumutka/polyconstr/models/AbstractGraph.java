package com.bulumutka.polyconstr.models;

public abstract class AbstractGraph<Edge, Vertex> implements Graph<Edge, Vertex> {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph:\n");
        Algorithms.depthFirstSearch(this, getRoot(), new DfsVisitor<>() {
            @Override
            public void discoverVertex(Vertex vertex) {
                sb.append(vertex).append(" -> ");
                for (var edge : outgoingEdges(vertex)) {
                    sb.append(getTarget(edge)).append(" ");
                }
                sb.append("\n");
            }
        });
        return sb.toString();
    }
}
