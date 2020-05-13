package com.bulumutka.polyconstr.models.graphlib.graphlib.base;

import java.util.List;

public interface Graph<E extends Edge<V>, V> {
    V getRoot();

    List<E> outgoingEdges(V vertex);

    Integer getVertexNumber();

    Integer getEdgesNumber();
}
