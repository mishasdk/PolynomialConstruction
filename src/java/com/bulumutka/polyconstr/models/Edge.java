package com.bulumutka.polyconstr.models;

public class Edge {
    int id;
    int source;
    int target;
    double time;

    public Edge(int id, int source, int target, double time) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.time = time;
    }
}
