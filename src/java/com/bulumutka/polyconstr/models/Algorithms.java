package com.bulumutka.polyconstr.models;

import java.util.HashSet;
import java.util.Set;

public class Algorithms {
    private Algorithms() {
    }

    public static <E extends Edge<V>, V> boolean isConnected(Graph<E, V> g, V originVertex) {
        ConnectedVisitor<V, E> visitor = new ConnectedVisitor<>(g.getVertexNumber());
        depthFirstSearch(g, originVertex, visitor);
        return visitor.isConnected();
    }

    public static <E extends Edge<V>, V> Set<E> findBridges(Graph<E, V> g, V originVertex) {
        BridgesVisitor<V, E> visitor = new BridgesVisitor<>();
        depthFirstSearch(g, originVertex, visitor);
        return visitor.getBridges();
    }

    public static <E extends Edge<V>, V> Set<E> findMarks(Graph<E, V> g, V originVertex,
                                                          V targetVertex) {
        MarksVisitor<V, E> visitor = new MarksVisitor<>(targetVertex);
        depthFirstSearch(g, originVertex, visitor);
        return visitor.getMarks();
    }

    public static <E extends Edge<V>, V> void depthFirstSearch(Graph<E, V> g, V v,
                                                               DfsVisitor<V, E> visitor) {
        Set<V> set = new HashSet<>();
        dfs(g, v, visitor, set);
    }

    private static <E extends Edge<V>, V> void dfs(Graph<E, V> g, V vertex,
                                                   DfsVisitor<V, E> visitor, Set<V> used) {
        used.add(vertex);
        visitor.discoverVertex(vertex);
        for (var edge : g.outgoingEdges(vertex)) {
            var target = edge.getTarget();
            visitor.examineEdge(edge);

            if (!used.contains(target)) {
                visitor.goEdge(edge);
                dfs(g, target, visitor, used);
                visitor.returnEdge(edge);
            } else {
                visitor.backEdge(edge);
            }
        }
    }
}
