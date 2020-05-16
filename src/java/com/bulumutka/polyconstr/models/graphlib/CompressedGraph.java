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
        Collections.reverse(firstTerm);
        Collections.reverse(secondTerm);
    }

    private int vertexNumberTerm2 = 0;

    private void proceedGraph(SubGraph g) {
        Map<Integer, GraphEdge> bridgesEnds = new HashMap<>();
        for (var edge : Algorithms.findBridges(g, g.getRoot())) {
            bridgesEnds.put(edge.getTarget(), edge);
        }
        var reverseEdges = List.copyOf(Algorithms.findReverseEdges(g, g.getRoot()));

        vertexNumberTerm2 = 0;
        Algorithms.depthFirstSearch(g, g.getRoot(), (ForAllVertices<GraphEdge, Integer>) vertex -> {
            if (g.outgoingEdges(vertex).size() > 1 && bridgesEnds.containsKey(vertex)) {
                proceedFirstAndSecondTerm(g, vertex, findAllMarksSet(g, reverseEdges, vertex),
                        bridgesEnds.get(vertex));
                ++vertexNumberTerm2;
            } else {
                proceedFirstTerm(g, vertex, findAllMarksSet(g, reverseEdges, vertex));
            }
        });

        firstTerm.add(Double.valueOf(g.getVertexNumber()));
        secondTerm.add((double) vertexNumberTerm2);
        var allEdges = Algorithms.findEdges(g);
        List<Double> weights = new ArrayList<>();
        for (var edge : allEdges) {
            weights.add(edge.time);
        }
        firstTerm.addAll(weights);
        secondTerm.addAll(weights);
        firstTerm.add((double) allEdges.size());
        secondTerm.add((double) allEdges.size());
    }

    private void proceedFirstTerm(SubGraph g, Integer vertex, List<Set<GraphEdge>> allMarks) {
        // Only first term
        var sub = graph.outgoingEdges(vertex).size() - g.outgoingEdges(vertex).size();
        for (var set : allMarks) {
            var marksSum = 0.0;
            for (var markedEdge : set) {
                marksSum += markedEdge.time;
            }
            firstTerm.add(marksSum);
        }
        firstTerm.add((double) allMarks.size());
        firstTerm.add((double) sub);
    }

    private void proceedFirstAndSecondTerm(SubGraph g, Integer vertex,
                                           List<Set<GraphEdge>> allMarks, GraphEdge edge) {
        var sub = graph.outgoingEdges(vertex).size() - g.outgoingEdges(vertex).size();
        // First and second term
        for (var set : allMarks) {
            var marksSum1 = 0.0;
            var marksSum2 = 0.0;
            for (var markedEdge : set) {
                marksSum1 += markedEdge.time;
                if (markedEdge.id != edge.id) {
                    marksSum2 += markedEdge.time - edge.time;
                }
            }
            firstTerm.add(marksSum1);
            secondTerm.add(marksSum2);
        }
        secondTerm.add((double) allMarks.size());
        secondTerm.add(edge.time);
        firstTerm.add((double) allMarks.size());
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
