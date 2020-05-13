package com.bulumutka.polyconstr.models.graphlib.graphlib;

import com.bulumutka.polyconstr.models.graphlib.graphlib.base.DfsVisitor;

public class ConnectedVisitor<E, V> implements DfsVisitor<E, V> {
    private final int vertexNumber;
    private int visitedVertices = 0;

    public ConnectedVisitor(int vertexNumber) {
        this.vertexNumber = vertexNumber;
    }

    @Override
    public void discoverVertex(V vertex) {
        visitedVertices++;
    }

    public boolean isConnected() {
        System.out.println(visitedVertices);
        return visitedVertices == vertexNumber;
    }
}
