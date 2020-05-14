package com.bulumutka.polyconstr.models.graphlib.graphlib.base;

@FunctionalInterface
public interface ForAllVertices<E, V> extends DfsVisitor<E, V> {
    @Override
    void discoverVertex(V vertex);
}
