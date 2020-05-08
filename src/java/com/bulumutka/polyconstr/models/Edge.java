package com.bulumutka.polyconstr.models;

public class Edge {
    int id;
    int source;
    int target;
    int time;

    public Edge(int id, int source, int target, int time) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.time = time;
    }
}
