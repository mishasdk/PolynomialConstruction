package com.bulumutka.polyconstr.models.graphlib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgesListVisitorTest {
    @Test
    public void getEdgesTest() {
        assertEquals(10, Algorithms.findEdges(TestHelper.generateDenseGraph(5)).size());
        assertEquals(15, Algorithms.findEdges(TestHelper.generateDenseGraph(6)).size());
        assertEquals(21, Algorithms.findEdges(TestHelper.generateDenseGraph(7)).size());
        assertEquals(0, Algorithms.findEdges(TestHelper.generateDenseGraph(1)).size());
    }
}