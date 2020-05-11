package com.bulumutka.polyconstr.models;

import java.util.*;

public class BridgesVisitor<V, E extends Edge<V>> implements DfsVisitor<V, E> {
    private int time = 0;
    private final Set<E> bridges = new HashSet<>();
    private final Map<V, V> parent = new HashMap<>();
    private final Map<V, Integer> timeIn = new HashMap<>();
    private final Map<V, Integer> fup = new HashMap<>();

    @Override
    public void examineVertex(V vertex) {
        timeIn.put(vertex, time);
        fup.put(vertex, time);
        ++time;
    }

    @Override
    public void examineEdge(E edge) {
        if (edge.getTarget().equals(parent.get(edge.getSource()))) {
            return;
        }
        boolean targetUsed = timeIn.containsKey(edge.getTarget());
        if (targetUsed) {
            fup.put(edge.getSource(), minTime(edge.getSource(), edge.getTarget()));
        }
    }

    @Override
    public void returnEdge(E edge) {
        fup.put(edge.getSource(), Math.min(fup.get(edge.getSource()), fup.get(edge.getTarget())));
        if (fup.get(edge.getTarget()) > timeIn.get(edge.getSource())) {
            bridges.add(edge);
        }
    }

    @Override
    public void goEdge(E e) {
        parent.put(e.getTarget(), e.getSource());
    }

    public Set<E> getBridges() {
        return bridges;
    }

    private Integer minTime(V f, V s) {
        var first = fup.get(f);
        var second = timeIn.get(s);
        return Math.min(first, second);
    }
}
