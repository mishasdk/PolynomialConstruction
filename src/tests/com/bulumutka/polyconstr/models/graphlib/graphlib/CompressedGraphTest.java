package com.bulumutka.polyconstr.models.graphlib.graphlib;

import org.junit.jupiter.api.Test;

class CompressedGraphTest {
    @Test
    public void getVectorTest() {
        var builder = new GraphBuilder(3);
        builder.addEdge(0, 1, 1);
        builder.addEdge(1, 2, 1);
        builder.setRoot(0);
        var cg = new CompressedGraph(builder.build());
        System.out.println(cg.getVector());
    }
}
