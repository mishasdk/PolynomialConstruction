package com.bulumutka.polyconstr.models;

import java.util.List;

public interface Graph<Edge, Vertex> {
    Vertex getRoot();

    List<Edge> outgoingEdges(Vertex vertex);

    Vertex getTarget(Edge edge);

    Integer getVertexNumber();

    Integer getEdgesNumber();
}
