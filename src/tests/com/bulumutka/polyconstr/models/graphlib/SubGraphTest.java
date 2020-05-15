package com.bulumutka.polyconstr.models.graphlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubGraphTest {
    @Test
    public void outgoingEdgesTest() {
        var builder = new GraphBuilder(6);
        builder.addEdge(0, 1, 0);
        builder.addEdge(1, 2, 1);
        builder.addEdge(1, 4, 0);
        builder.addEdge(5, 4, 3);
        builder.addEdge(3, 4, 1);
        builder.setRoot(1);

        MetricGraph g = builder.build();
        System.out.println(g);
        var sg = new SubGraph(g, edge -> edge.time != 0);

        System.out.println(sg);
        assertEquals(5, sg.getVertexNumber());
        assertEquals(2, sg.outgoingEdges(4).size());
        assertEquals(1, sg.outgoingEdges(5).size());
        assertEquals(0, sg.outgoingEdges(0).size());
    }

    @Test
    public void connectedTest1() {
        var builder = new GraphBuilder(6);
        builder.addEdge(0, 1, 0);
        builder.addEdge(1, 2, 1);
        builder.addEdge(1, 4, 0);
        builder.addEdge(5, 4, 3);
        builder.addEdge(3, 4, 1);
        builder.setRoot(1);

        MetricGraph g = builder.build();
        var sg = new SubGraph(g, edge -> edge.time != 0);

        Assertions.assertFalse(Algorithms.isConnected(sg, sg.getRoot()));
    }

    @Test
    public void connectedTest2() {
        var builder = new GraphBuilder(6);
        builder.addEdge(0, 1, 0);
        builder.addEdge(1, 2, 1);
        builder.addEdge(1, 4, 0);
        builder.addEdge(5, 4, 3);
        builder.addEdge(3, 4, 1);
        builder.addEdge(5, 3, 0);
        builder.addEdge(0, 4, 1);
        builder.addEdge(2, 3, 1);
        builder.setRoot(1);

        MetricGraph g = builder.build();
        var sg = new SubGraph(g, edge -> edge.time != 0);

        assertEquals(6, sg.getVertexNumber());
        assertTrue(Algorithms.isConnected(sg, sg.getRoot()));
    }

    @Test
    public void showSubGraphTest() {
        GraphBuilder builder = new GraphBuilder(5);
        builder.addEdge(0, 1, 0);
        builder.addEdge(0, 2, 0);
        builder.addEdge(0, 3, 1);
        builder.addEdge(0, 4, 1);
        builder.setRoot(0);
        var g = new SubGraph(builder.build(), edge -> edge.time != 0);
        assertEquals(2, g.outgoingEdges(0).size());
        System.out.println(g);

        g = new SubGraph(builder.build(), edge -> edge.time == 0);
        assertEquals(2, g.outgoingEdges(0).size());
        System.out.println(g);
    }
}
