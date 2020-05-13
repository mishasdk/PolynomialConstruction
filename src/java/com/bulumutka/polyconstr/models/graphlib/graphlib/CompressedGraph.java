package com.bulumutka.polyconstr.models.graphlib.graphlib;

import com.bulumutka.polyconstr.exceptions.GraphParameterException;
import com.bulumutka.polyconstr.models.graphlib.graphlib.base.ForAllVertices;
import com.sun.javafx.scene.traversal.Algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Class that mapping graph to vector of numbers,
 * encapsulates all data needed for calculation function
 * of number endpoints of finite graph.
 */
public class CompressedGraph {
    private final MetricGraph graph;
    private List<Double> vector = null;

    public CompressedGraph(MetricGraph g) {
        graph = g;
    }

    public List<Double> getVector() throws GraphParameterException {
        if (vector == null) {
            buildCompression();
        }
        return vector;
    }

    private void buildCompression() throws GraphParameterException {
        SubGraphBruteForce brute = new SubGraphBruteForce(graph);
        var g = brute.next();
        while (g != null) {
            if (Algorithms.isConnected(g, g.getRoot())) {
                proceedGraph(g);
            }
            g = brute.next();
        }
    }

    private Integer sumOfDegree = 0;

    private void proceedGraph(SubGraph g) {
        var bridges = Algorithms.findBridges(g, g.getRoot());
        Set<Integer> bridgesEnds = new HashSet<>();
        for (var edge : bridges) {
            bridgesEnds.add(edge.getSource());
            bridgesEnds.add(edge.getTarget());
        }

        List<Double> args1 = new ArrayList<>();
        List<Double> args2 = new ArrayList<>();
        sumOfDegree = 0;
        Algorithms.depthFirstSearch(g, g.getRoot(), (ForAllVertices<GraphEdge, Integer>) vertex -> {
            var sub = graph.outgoingEdges(vertex).size() - g.outgoingEdges(vertex).size();
            sumOfDegree += sub;
            var marks = Algorithms.findMarks(g, g.getRoot(), vertex);
            var argument1 = 0.0;
            for (var edge : marks) {
                argument1 += edge.time * 2;
            }
            args1.add(argument1);

            if (bridgesEnds.contains(vertex)) {
                var argument2 = 0.0;
                for (var edge : marks) {
                    for (var j : g.getEdges()) {
                        if (edge.id / 2 != j.id / 2) {
                            argument2 += edge.time - j.time;
                        }
                    }
                }
            }
        });
    }
}
