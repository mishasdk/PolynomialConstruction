package com.bulumutka.polyconstr.models;

public class ConnectedVisitor<Vertex, Edge> implements DfsVisitor<Vertex, Edge> {
    private final int vertexNumber;
    private int visitedVertices = 0;

    public ConnectedVisitor(int vertexNumber) {
        this.vertexNumber = vertexNumber;
    }

    @Override
    public void discoverVertex(Vertex vertex) {
        visitedVertices++;
    }

    public boolean isConnected() {
        System.out.println(visitedVertices);
        return visitedVertices == vertexNumber;
    }
}
