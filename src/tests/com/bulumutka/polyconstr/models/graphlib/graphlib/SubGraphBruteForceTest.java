package com.bulumutka.polyconstr.models.graphlib.graphlib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubGraphBruteForceTest {
    @Test
    public void nextTest() {
        GraphBuilder builder = new GraphBuilder(3);
        builder.addEdge(0, 1, 0);
        builder.addEdge(1, 2, 0);
        assertEquals(1 << 2, bruteTestCase(builder.build()));

        builder.addEdge(0, 2, 0);
        assertEquals(1 << 3, bruteTestCase(builder.build()));

        builder.addVertex(3);
        builder.addEdge(0, 3, 0);
        builder.addEdge(0, 4, 0);
        builder.addEdge(0, 5, 0);
        assertEquals(1 << 6, bruteTestCase(builder.build()));

        builder.addEdge(3, 1, 0);
        builder.addEdge(2, 3, 0);
        builder.addEdge(5, 2, 0);
        builder.addEdge(4, 5, 0);
        assertEquals(1 << 10, bruteTestCase(builder.build()));
    }

    @Test
    public void showSubGraphsTest1() {
        GraphBuilder builder = new GraphBuilder(3);
        builder.addEdge(0, 1, 0);
        builder.addEdge(1, 2, 0);
        builder.setRoot(0);
        SubGraphBruteForce b = new SubGraphBruteForce(builder.build());
        var subGraph = b.next();
        assertEquals(0, subGraph.getEdgesNumber());
        assertEquals(0, subGraph.outgoingEdges(1).size());

        subGraph = b.next();
        assertEquals(1, subGraph.getEdgesNumber());
        assertEquals(1, subGraph.outgoingEdges(1).size());
        b.next();
        subGraph = b.next();
        assertEquals(2, subGraph.getEdgesNumber());
        assertEquals(2, subGraph.outgoingEdges(1).size());
    }

    @Test
    public void showSubGraphsTest2() {
        GraphBuilder builder = new GraphBuilder(5);
        builder.addEdge(0, 1, 0);
        builder.addEdge(0, 2, 0);
        builder.addEdge(0, 3, 0);
        builder.addEdge(0, 4, 0);
        builder.setRoot(0);
        var g = builder.build();
        var b = new SubGraphBruteForce(g);
        var subGraph = b.next();
        while (subGraph != null) {
            System.out.println(subGraph);
            subGraph = b.next();
        }
    }

    @Test
    public void showSubGraphsTest3() {
        GraphBuilder builder = new GraphBuilder(2);
        builder.addEdge(0, 1, 0);
        builder.setRoot(0);
        var b = new SubGraphBruteForce(builder.build());


        var subGraph = b.next();
        while (subGraph != null) {
            System.out.println(subGraph);
            subGraph = b.next();
        }
    }

    private int bruteTestCase(MetricGraph g) {
        var brute = new SubGraphBruteForce(g);

        int count = 0;
        while (brute.next() != null) {
            count++;
        }
        return count;
    }
}
