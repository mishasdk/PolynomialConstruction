package com.bulumutka.polyconstr.models;

public interface DfsVisitor<Vertex, Edge> {
    default void discoverVertex(Vertex vertex) {}

    default void examineVertex(Vertex vertex) {}

    default void examineEdge(Edge edge) {}

    default void goEdge(Edge edge) {}

    default void returnEdge(Edge edge) {}
}
