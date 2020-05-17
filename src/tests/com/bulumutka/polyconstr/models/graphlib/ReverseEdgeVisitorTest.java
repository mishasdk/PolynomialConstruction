package com.bulumutka.polyconstr.models.graphlib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseEdgeVisitorTest {
    @Test
    public void findReverseEdgeTest1() {
        var builder = new GraphBuilder(12);
        builder.addEdge(0, 1, 0);
        builder.addEdge(0, 2, 0);
        builder.addEdge(0, 3, 0);
        builder.addEdge(1, 3, 0);
        builder.addEdge(1, 4, 0);
        builder.addEdge(1, 7, 0);
        builder.addEdge(4, 7, 0);
        builder.addEdge(4, 8, 0);
        builder.addEdge(2, 5, 0);
        builder.addEdge(2, 6, 0);
        builder.addEdge(9, 6, 0);
        builder.addEdge(9, 10, 0);
        builder.addEdge(9, 11, 0);
        builder.addEdge(6, 11, 0);
        builder.setRoot(0);
        var set = Algorithms.findReverseEdges(builder.build(), 0);
        assertEquals(3, set.size());
    }

    @Test
    public void findReverseEdgeTest2() {
        var builder = new GraphBuilder(3);
        builder.addEdge(0, 1, 0);
        builder.addEdge(1, 2, 0);
        builder.addEdge(2, 0, 0);
        builder.setRoot(0);
        var set = Algorithms.findReverseEdges(builder.build(), 0);
        assertEquals(1, set.size());

    }
}
