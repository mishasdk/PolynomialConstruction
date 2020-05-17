package com.bulumutka.polyconstr.models.graphlib.base;

public interface DfsVisitor<E, V> {

    default void discoverVertex(V vertex) {
    }

    default void examineEdge(E edge) {
    }

    default void goEdge(E edge) {
    }

    default void returnEdge(E edge) {
    }

    default void backEdge(E edge) {
    }
}
