package com.bulumutka.polyconstr.models.graphlib.base;

@FunctionalInterface
public interface ForAllVertices<E, V> extends DfsVisitor<E, V> {
    @Override
    void discoverVertex(V vertex);
}
