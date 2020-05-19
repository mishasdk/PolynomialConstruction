package com.bulumutka.polyconstr.models.graphlib;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.bulumutka.polyconstr.models.graphlib.TestHelper.generateCycle;
import static com.bulumutka.polyconstr.models.graphlib.TestHelper.generateDenseGraph;

class CompressedGraphTest {
    @Test
    public void getVectorTest1() {
        var builder = new GraphBuilder(3);
        builder.addEdge(0, 1, 1);
        builder.addEdge(1, 2, 1);
        builder.setRoot(0);
        var cg = new CompressedGraph(builder.build());
        System.out.println(cg.getVector());
    }

    @Test
    public void getVectorTest2() {
        var builder = new GraphBuilder(15);
        var edges = new int[][]{new int[]{0, 1}, new int[]{0, 2}, new int[]{1, 3}, new int[]{2, 4},
                new int[]{2, 5}, new int[]{3, 6}, new int[]{3, 7}, new int[]{4, 8}, new int[]{5, 9},
                new int[]{5, 10}, new int[]{6, 11}, new int[]{6, 12}, new int[]{7, 13},
                new int[]{7, 14}, new int[]{1, 6}, new int[]{3, 14}, new int[]{0, 8},
                new int[]{2, 10},};
        for (var p : edges) {
            builder.addEdge(p[0], p[1], 0);
        }
        var cg = new CompressedGraph(builder.build());
        System.out.println(cg.getVector());
    }

    @Test
    public void getVectorTest3() {
        var cg = new CompressedGraph(generateDenseGraph(2));
        System.out.println(cg.getVector());
    }

    @Test
    public void generateCompressedTest1() {
        var c = new CompressedGraph(generateDenseGraph(3));
        var v = c.getVector();
        writeVector(v, "data/" + "K3_graph_vector.txt");
        c = new CompressedGraph(generateDenseGraph(4));
        v = c.getVector();
        writeVector(v, "data/" + "K4_graph_vector.txt");

        c = new CompressedGraph(generateDenseGraph(5));
        v = c.getVector();
        writeVector(v, "data/" + "K5_graph_vector.txt");

        c = new CompressedGraph(generateDenseGraph(6));
        v = c.getVector();
        writeVector(v, "data/" + "K6_graph_vector.txt");
    }

    @Test
    public void generateK3Test() {
        var c = new CompressedGraph(generateDenseGraph(3));
        var v = c.getVector();
        writeVector(v, "data/" + "K3_graph_vector.txt");
    }

    @Test
    public void genHGraph() {
        var b = new GraphBuilder(6);
        b.addEdge(0, 2, 1.4142135623730951);
        b.addEdge(0, 3, 1.7320508075688772);
        b.addEdge(0, 1, 2.23606797749979);
        b.addEdge(1, 4, 2.6457513110645907);
        b.addEdge(1, 5, 3.605551275463989);
        b.setRoot(0);
        var c = new CompressedGraph(b.build());
        writeVector(c.getVector(), "data/" + "H_graph_vector.txt");
    }

    @Test
    public void generateCyclicTest1() {
        var c = new CompressedGraph(generateCycle(3));
        var v = c.getVector();
        writeVector(v, "data/" + "cycle_3_vector.txt");

        c = new CompressedGraph(generateCycle(4));
        v = c.getVector();
        writeVector(v, "data/" + "cycle_4_vector.txt");

        c = new CompressedGraph(generateCycle(5));
        v = c.getVector();
        writeVector(v, "data/" + "cycle_5_vector.txt");

        c = new CompressedGraph(generateCycle(6));
        v = c.getVector();
        writeVector(v, "data/" + "cycle_6_vector.txt");

        c = new CompressedGraph(generateCycle(7));
        v = c.getVector();
        writeVector(v, "data/" + "cycle_7_vector.txt");

        c = new CompressedGraph(generateCycle(8));
        v = c.getVector();
        writeVector(v, "data/" + "cycle_8_vector.txt");

        c = new CompressedGraph(generateCycle(10));
        v = c.getVector();
        writeVector(v, "data/" + "cycle_10_vector.txt");

        c = new CompressedGraph(generateCycle(16));
        v = c.getVector();
        writeVector(v, "data/" + "cycle_16_vector.txt");

        c = new CompressedGraph(generateCycle(24));
        v = c.getVector();
        writeVector(v, "data/" + "cycle_24_vector.txt");
    }

    private static void writeVector(List<Double> vector, String filePath) {
        try {
            var writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < vector.size(); ++i) {
                writer.write(String.valueOf(vector.get(i)));
                writer.newLine();
            }
            writer.close();
        } catch (IOException exception) {
        }
    }
}
