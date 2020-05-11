package com.bulumutka.polyconstr.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

    @Test
    public void connectedTest1() {
        GraphBuilder builder = new GraphBuilder(4);
        builder.addEdge(0, 1, 0);
        builder.addEdge(1, 2, 0);
        builder.addEdge(1, 3, 0);
        MetricGraph g = builder.build();

        assertTrue(Algorithms.isConnected(g, 0));

        builder.addVertex();
        g = builder.build();
        assertFalse(Algorithms.isConnected(g, 0));
    }

    @Test
    public void connectedTest2() {
        var builder = new GraphBuilder(6);
        builder.addEdge(1, 2, 1);
        builder.addEdge(5, 4, 3);
        builder.addEdge(3, 4, 1);
        builder.addEdge(0, 4, 1);
        builder.addEdge(2, 3, 1);
        builder.setRoot(1);
        MetricGraph g = builder.build();

        assertEquals(6, g.getVertexNumber());
        assertTrue(Algorithms.isConnected(g, 0));
    }

    @Test
    public void connectedTest3() {
        var builder = new GraphBuilder(2);
        builder.setRoot(1);
        MetricGraph g = builder.build();

        assertEquals(2, g.getVertexNumber());
        assertTrue(!Algorithms.isConnected(g, 0));
    }

    @Test
    public void bridgesTest1() {
        var builder = new GraphBuilder(6);
        builder.addEdge(1, 2, 1);
        builder.addEdge(5, 4, 3);
        builder.addEdge(3, 4, 1);
        builder.addEdge(0, 4, 1);
        builder.addEdge(2, 3, 1);
        builder.setRoot(1);
        MetricGraph g = builder.build();
        var b = Algorithms.findBridges(g, 0);
        assertEquals(5, b.size());
    }

    @Test
    public void bridgesTest2() {
        var builder = new GraphBuilder(15);
        var edges = new int[][]{new int[]{0, 1}, new int[]{0, 2}, new int[]{1, 3}, new int[]{2, 4},
                new int[]{2, 5}, new int[]{3, 6}, new int[]{3, 7}, new int[]{4, 8}, new int[]{5, 9},
                new int[]{5, 10}, new int[]{6, 11}, new int[]{6, 12}, new int[]{7, 13},
                new int[]{7, 14}, new int[]{1, 6}, new int[]{3, 14}, new int[]{0, 8},
                new int[]{2, 10},};
        for (var p : edges) {
            builder.addEdge(p[0], p[1], 0);
        }
        var g = builder.build();

        var b = Algorithms.findBridges(g, 0);
        assertEquals(5, b.size());
    }

    @Test
    public void marksTest1() {
        var builder = new GraphBuilder(3);
        builder.addEdge(0, 1, 0);
        builder.addEdge(0, 2, 0);
        var g = builder.build();
        var m = Algorithms.findMarks(g, 0, 1);

        assertEquals(1, m.size());
        assertEquals(0, m.iterator().next().id);
    }

    @Test
    public void marksTest2() {
        var builder = new GraphBuilder(5);
        builder.addEdge(0, 1, 0);
        builder.addEdge(0, 2, 0);
        builder.addEdge(1, 3, 0);
        builder.addEdge(3, 4, 0);
        var g = builder.build();
        var m = Algorithms.findMarks(g, 0, 2);
        assertEquals(1, m.size());

        m = Algorithms.findMarks(g, 0, 4);
        assertEquals(3, m.size());

        m = Algorithms.findMarks(g, 0, 0);
        assertEquals(0, m.size());

        builder.addEdge(4, 1, 0);
        g = builder.build();
        m = Algorithms.findMarks(g, 0, 4);
        assertEquals(2, m.size());

        builder.addEdge(4, 0, 0);
        g = builder.build();
        m = Algorithms.findMarks(g, 0, 4);

        for (var e : m) {
            System.out.println(String.format("E: s = %d, t = %d", e.source, e.target));
        }

        assertEquals(4, m.size());
    }
}
