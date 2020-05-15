package com.bulumutka.polyconstr.models.graphlib;

import com.bulumutka.polyconstr.models.graphlib.base.DfsVisitor;
import com.bulumutka.polyconstr.models.graphlib.base.Edge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EdgesListVisitor<E extends Edge<V>, V> implements DfsVisitor<E, V> {
    private final Map<V, V> parent = new HashMap<>();
    private final Set<V> black = new HashSet<>();
    private final Set<E> edges = new HashSet<>();

    @Override
    public void goEdge(E e) {
        edges.add(e);
        parent.put(e.getTarget(), e.getSource());
    }

    @Override
    public void returnEdge(E e) {
        black.add(e.getTarget());
    }

    @Override
    public void backEdge(E e) {
        if (black.contains(e.getTarget()) || parent.get(e.getSource()) == null ||
                e.getTarget().equals(parent.get(e.getSource()))) {
            return;
        }
        edges.add(e);
    }

    public Set<E> getEdges() {
        return edges;
    }
}
