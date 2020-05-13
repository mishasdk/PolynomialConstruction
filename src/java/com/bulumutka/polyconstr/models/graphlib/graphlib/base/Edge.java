package com.bulumutka.polyconstr.models.graphlib.graphlib.base;

public interface Edge<Vertex> {
    Vertex getTarget();

    Vertex getSource();
}
