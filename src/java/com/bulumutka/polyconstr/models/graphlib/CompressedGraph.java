package com.bulumutka.polyconstr.models.graphlib;

import com.bulumutka.polyconstr.models.graphlib.base.ForAllVertices;

import java.util.*;

/**
 * Class that mapping graph to vector of numbers,
 * encapsulates all data needed for calculation function
 * of number endpoints of finite graph.
 */
public class CompressedGraph {
    private final MetricGraph graph;
    private List<Double> vector = null;
    private final List<Double> firstTerm = new ArrayList<>();
    private final List<Double> secondTerm = new ArrayList<>();

    public CompressedGraph(MetricGraph g) {
        graph = g;
    }

    public List<Double> getVector() {
        if (vector == null) {
            buildCompression();
        }
        vector = new ArrayList<>();
        vector.add(Double.valueOf(graph.getVertexNumber()));
        vector.add(Double.valueOf(graph.getEdgesNumber()));
        Collections.reverse(firstTerm);
        Collections.reverse(secondTerm);
        vector.addAll(firstTerm);
        vector.addAll(secondTerm);
        firstTerm.clear();
        secondTerm.clear();
        return vector;
    }

    private void buildCompression() {
        SubGraphBruteForce brute = new SubGraphBruteForce(graph);
        var g = brute.next();
        int graphNumber = 0;
        while (g != null) {
            if (Algorithms.isConnected(g, g.getRoot())) {
                ++graphNumber;
                proceedGraph(g);
            }
            g = brute.next();
        }
        firstTerm.add((double) graphNumber);
        secondTerm.add((double) graphNumber);
    }

    private void proceedGraph(SubGraph g) {
        var bridges = Algorithms.findBridges(g, g.getRoot());
        Set<Integer> bridgesEnds = new HashSet<>();
        for (var edge : bridges) {
            bridgesEnds.add(edge.getSource());
            bridgesEnds.add(edge.getTarget());
        }
        var allEdges = List.copyOf(Algorithms.findEdges(g));
        var reverseEdges = List.copyOf(Algorithms.findReverseEdges(g, g.getRoot()));
        vertexNumberTerm2 = 0;
        Algorithms.depthFirstSearch(g, g.getRoot(), (ForAllVertices<GraphEdge, Integer>) vertex -> {
            proceedGraphAndVertex(g, vertex, reverseEdges, bridgesEnds, allEdges);
        });
        firstTerm.add(Double.valueOf(g.getVertexNumber()));
        secondTerm.add((double) vertexNumberTerm2);
        List<Double> weights = new ArrayList<>();
        for (var edge : allEdges) {
            weights.add(edge.time);
        }
        firstTerm.addAll(weights);
        secondTerm.addAll(weights);
        firstTerm.add((double) allEdges.size());
        secondTerm.add((double) allEdges.size());
    }

    private int vertexNumberTerm2 = 0;

    private void proceedGraphAndVertex(SubGraph g, Integer vertex, List<GraphEdge> reverseEdges,
                                       Set<Integer> bridgesEnds, List<GraphEdge> allEdges) {
        // First term.
        var vertexDegree = g.outgoingEdges(vertex).size();
        var sub = graph.outgoingEdges(vertex).size() - vertexDegree;
        List<Double> args1 = new ArrayList<>();
        List<Double> args2 = new ArrayList<>();
        if (bridgesEnds.contains(vertex) && vertexDegree > 1) {
            ++vertexNumberTerm2;
            // First and second term
            for (var set : findAllMarksSet(g, reverseEdges, vertex)) {
                var marksSum1 = 0.0;
                var marksSum2 = 0.0;
                for (var markedEdge : set) {
                    marksSum1 += markedEdge.time;
                    for (var j : allEdges) {
                        if (j.id != markedEdge.id) {
                            marksSum2 += markedEdge.time - j.time;
                        }
                    }
                }
                args1.add(marksSum1);
                args2.add(marksSum2);
            }
            secondTerm.addAll(args2);
            secondTerm.add((double) args2.size());
        } else {
            // Only first term
            for (var set : findAllMarksSet(g, reverseEdges, vertex)) {
                var marksSum = 0.0;
                for (var markedEdge : set) {
                    marksSum += markedEdge.time;
                }
                args1.add(marksSum);
            }
        }

        firstTerm.addAll(args1);
        firstTerm.add((double) args1.size());
        firstTerm.add((double) sub);
    }

    private List<Set<GraphEdge>> findAllMarksSet(SubGraph g, List<GraphEdge> edges,
                                                 Integer targetVertex) {
        var list = new ArrayList<Set<GraphEdge>>();
        var bruteForce = new EdgesBruteForce(g, edges);
        var subGraph = bruteForce.next();
        while (subGraph != null) {
            list.add(Algorithms.findMarks(subGraph, subGraph.getRoot(), targetVertex));
            subGraph = bruteForce.next();
        }
        return list;
    }
}
