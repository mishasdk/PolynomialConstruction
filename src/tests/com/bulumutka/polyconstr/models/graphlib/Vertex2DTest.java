package com.bulumutka.polyconstr.models.graphlib;

import com.bulumutka.polyconstr.ui.Vertex2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vertex2DTest {

    @Test
    public void containsTest() {
        Vertex2D<Integer> vertex = new Vertex2D<>(0, 0, 0);
        assertTrue(vertex.contains(0, 0));
        assertTrue(vertex.contains(1, 1));
        assertTrue(vertex.contains(-3, 3));
        assertFalse(vertex.contains(100, 100));
        assertFalse(vertex.contains(-10, -10));
    }

}
