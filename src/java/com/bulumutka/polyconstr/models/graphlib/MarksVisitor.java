package com.bulumutka.polyconstr.models.graphlib;

import com.bulumutka.polyconstr.models.graphlib.base.Edge;
import com.bulumutka.polyconstr.models.graphlib.base.DfsVisitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MarksVisitor<E extends Edge<V>, V> implements DfsVisitor<E, V> {
    private final Map<V, E> parent = new HashMap<>();
    private final Set<V> found = new HashSet<>();
    private final Set<E> marks = new HashSet<>();
    private final Set<V> black = new HashSet<>();
    private final V target;

    public MarksVisitor(V target) {
        this.target = target;
    }

    public Set<E> getMarks() {
        return marks;
    }

    @Override
    public void discoverVertex(V v) {
        if (v.equals(target)) {
            found.add(v);
        }
    }

    @Override
    public void goEdge(E e) {
        parent.put(e.getTarget(), e);
        markEdge(e);
    }

    @Override
    public void returnEdge(E e) {
        black.add(e.getTarget());
        if (found.contains(e.getTarget())) {
            found.add(e.getSource());
        } else {
            markEdge(e);
        }
    }

    @Override
    public void backEdge(E e) {
        if (black.contains(e.getTarget()) || parent.get(e.getSource()) == null ||
                e.getTarget().equals(parent.get(e.getSource()).getSource())) {
            return;
        }
        markEdge(e);
        var current = parent.get(e.getSource());
        while (current != null && !current.getTarget().equals(e.getTarget())) {
            markEdge(current);
            current = parent.get(current.getSource());
        }
    }

    private void markEdge(E edge) {
        if (!marks.remove(edge)) {
            marks.add(edge);
        }
    }
}
