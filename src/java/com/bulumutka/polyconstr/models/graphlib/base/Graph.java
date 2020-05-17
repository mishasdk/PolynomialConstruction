package com.bulumutka.polyconstr.models.graphlib.base;

public interface Graph<E extends Edge<V>, V> {
    V getRoot();

    Iterable<E> outgoingEdges(V vertex);

    Integer getVertexNumber();

    Integer getEdgesNumber();
}
