package com.bulumutka.polyconstr.models.graphlib.graphlib;

import com.bulumutka.polyconstr.exceptions.GraphParameterException;
import org.junit.jupiter.api.Test;

import java.util.List;

class EdgesBruteForceTest {
    @Test
    public void bruteForceTest1() throws GraphParameterException {
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
        var sg = new SubGraph(builder.build());
        var set = Algorithms.findReverseEdges(builder.build(), 0);
        EdgesBruteForce b = new EdgesBruteForce(sg, List.copyOf(set));
        var g = b.next();
        while (g != null) {
            System.out.println(g);
            g = b.next();
        }
    }
}
