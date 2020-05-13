package com.bulumutka.polyconstr.models.graphlib.graphlib.base;

import com.bulumutka.polyconstr.models.graphlib.graphlib.Algorithms;

public abstract class AbstractGraph<E extends Edge<V>, V> implements Graph<E, V> {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph:\n");
        Algorithms.depthFirstSearch(this, getRoot(), new DfsVisitor<>() {
            @Override
            public void discoverVertex(V vertex) {
                sb.append(vertex).append(" -> ");
                for (var edge : outgoingEdges(vertex)) {
                    sb.append(edge.getTarget()).append(" ");
                }
                sb.append("\n");
            }
        });
        return sb.toString();
    }
}
