package com.bulumutka.polyconstr.models.graphlib.graphlib;

import com.bulumutka.polyconstr.models.graphlib.graphlib.base.DfsVisitor;
import com.bulumutka.polyconstr.models.graphlib.graphlib.base.Edge;
import com.bulumutka.polyconstr.models.graphlib.graphlib.base.Graph;

import java.util.HashSet;
import java.util.Set;

public class Algorithms {
    private Algorithms() {
    }

    public static <E extends Edge<V>, V> boolean isConnected(Graph<E, V> g, V originVertex) {
        var visitor = new ConnectedVisitor<E, V>(g.getVertexNumber());
        depthFirstSearch(g, originVertex, visitor);
        return visitor.isConnected();
    }

    public static <E extends Edge<V>, V> Set<E> findBridges(Graph<E, V> g, V originVertex) {
        var visitor = new BridgesVisitor<E, V>();
        depthFirstSearch(g, originVertex, visitor);
        return visitor.getBridges();
    }

    public static <E extends Edge<V>, V> Set<E> findMarks(Graph<E, V> g, V originVertex,
                                                          V targetVertex) {
        var visitor = new MarksVisitor<E, V>(targetVertex);
        depthFirstSearch(g, originVertex, visitor);
        return visitor.getMarks();
    }

    public static <E extends Edge<V>, V> void depthFirstSearch(Graph<E, V> g, V v,
                                                               DfsVisitor<E, V> visitor) {
        Set<V> set = new HashSet<>();
        dfs(g, v, visitor, set);
    }

    private static <E extends Edge<V>, V> void dfs(Graph<E, V> g, V vertex,
                                                   DfsVisitor<E, V> visitor, Set<V> used) {
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
