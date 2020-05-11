package com.bulumutka.polyconstr.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetricGraphTest {
    @Test
    public void createTest() {
        GraphBuilder builder = new GraphBuilder(4);
        builder.addEdge(0, 1, 0);
        builder.addEdge(1, 2, 0);
        builder.addEdge(1, 3, 0);
        var g = builder.build();

        assertEquals(4, g.getVertexNumber());

        builder.addEdge(2, 3, 0);
        g = builder.build();
        assertEquals(4, g.getVertexNumber());

        builder.addVertex();
        g = builder.build();
        assertEquals(5, g.getVertexNumber());

        assertEquals(3, g.outgoingEdges(1).size());
        assertEquals(1, g.outgoingEdges(0).size());
        assertEquals(0, g.outgoingEdges(4).size());
    }

    @Test
    public void showGraph() {
        GraphBuilder builder = new GraphBuilder(4);
        builder.addEdge(0, 1, 0);
        builder.addEdge(1, 2, 0);
        builder.addEdge(1, 3, 0);
        var g = builder.build();
        System.out.println(g);
    }
}
