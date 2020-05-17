package com.bulumutka.polyconstr.models.graphlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarksVisitorTest {
    @Test
    public void marksTest1() {
        var builder = new GraphBuilder(3);
        builder.addEdge(0, 1, 0);
        builder.addEdge(0, 2, 0);
        var g = builder.build();
        var m = Algorithms.findMarks(g, 0, 1);

        assertEquals(1, m.size());
        Assertions.assertEquals(0, m.iterator().next().id);
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
            System.out.println(String.format("E: s = %d, t = %d", e.getSource(), e.getTarget()));
        }

        assertEquals(4, m.size());
    }
}
