package com.bulumutka.polyconstr.models;

public interface Edge<Vertex> {
    Vertex getTarget();

    Vertex getSource();
}
