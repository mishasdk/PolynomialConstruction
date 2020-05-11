package com.bulumutka.polyconstr.models;

public class GraphEdge implements Edge<Integer> {
    int id;
    int source;
    int target;
    double time;

    public GraphEdge(int id, int source, int target, double time) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.time = time;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        var other = (GraphEdge) obj;
        return source == other.source && target == other.target;
    }

    @Override
    public Integer getTarget() {
        return target;
    }

    @Override
    public Integer getSource() {
        return source;
    }
}
