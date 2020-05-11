package com.bulumutka.polyconstr.models;

import com.bulumutka.polyconstr.exceptions.GraphParameterException;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;

import static org.junit.jupiter.api.Assertions.*;

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
    public void showSubGraphsTest() {
        GraphBuilder builder = new GraphBuilder(5);
        builder.addEdge(0, 1, 0);
        builder.addEdge(0, 2, 0);
        builder.addEdge(0, 3, 0);
        builder.addEdge(0, 4, 0);
        builder.setRoot(0);
        var g = builder.build();
        SubGraphBruteForce b;
        try {
            b = new SubGraphBruteForce(g);
        } catch (GraphParameterException e) {
            throw new TestAbortedException(e.getMessage());
        }
        var subGraph = b.next();
        while (subGraph != null) {
            System.out.println(subGraph);
            subGraph = b.next();
        }
    }

    private int bruteTestCase(MetricGraph g) {
        SubGraphBruteForce brute;
        try {
            brute = new SubGraphBruteForce(g);
        } catch (GraphParameterException e) {
            throw new TestAbortedException(e.getMessage());
        }
        int count = 0;
        while (brute.next() != null) {
            count++;
        }
        return count;
    }
}
