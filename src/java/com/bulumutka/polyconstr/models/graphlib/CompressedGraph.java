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
    private List<Object> vector = null;
    private final List<Object> firstTerm = new ArrayList<>();
    private final List<Object> secondTerm = new ArrayList<>();

    public CompressedGraph(MetricGraph g) {
        graph = g;
    }

    public List<Object> getVector() {
        if (vector == null) {
            buildCompression();
        }
        vector = new ArrayList<>();
        vector.add(graph.getVertexNumber());
        vector.add(graph.getEdgesNumber());
        vector.addAll(firstTerm);
        vector.addAll(secondTerm);
        firstTerm.clear();
        secondTerm.clear();
        System.out.println("Vector built");
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
        firstTerm.add(graphNumber);
        secondTerm.add(graphNumber);
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

        var allEdges = Algorithms.findEdges(g);
        if (vertexNumberTerm2 != 0) {
            for (var edge : allEdges) {
                firstTerm.add(edge.time);
                secondTerm.add(edge.time);
            }
            secondTerm.add(allEdges.size());
        } else {
            for (var edge : allEdges) {
                firstTerm.add(edge.time);
            }
        }
        firstTerm.add(allEdges.size());
        firstTerm.add(g.getVertexNumber());
        secondTerm.add(vertexNumberTerm2);
    }

    private void proceedFirstTerm(SubGraph g, Integer vertex, List<Set<GraphEdge>> allMarks) {
        // Only first term
        var sub = graph.outgoingEdges(vertex).size() - g.outgoingEdges(vertex).size();
        if (sub == 0) {
            firstTerm.add(sub);
            return;
        }
        for (var set : allMarks) {
            for (var markedEdge : set) {
                firstTerm.add(markedEdge.time);
            }
            firstTerm.add(set.size());
        }
        firstTerm.add(allMarks.size());
        firstTerm.add(sub);
    }

    private void proceedSecondTerm(List<Set<GraphEdge>> allMarks, GraphEdge edge) {
        // Second term
        for (var set : allMarks) {
            int count = 0;
            for (var markedEdge : set) {
                if (markedEdge.id / 2 != edge.id / 2) {
                    ++count;
                    secondTerm.add(markedEdge.time);
                }
            }
            secondTerm.add(count);
        }
        secondTerm.add(allMarks.size());
        secondTerm.add(edge.time);
    }

    private void proceedFirstAndSecondTerm(SubGraph g, Integer vertex,
                                           List<Set<GraphEdge>> allMarks, GraphEdge edge) {
        var sub = graph.outgoingEdges(vertex).size() - g.outgoingEdges(vertex).size();
        if (sub == 0) {
            firstTerm.add(0);
            proceedSecondTerm(allMarks, edge);
            return;
        }
        // First and second term
        for (var set : allMarks) {
            int count = 0;
            for (var markedEdge : set) {
                if (markedEdge.id / 2 != edge.id / 2) {
                    ++count;
                    secondTerm.add(markedEdge.time);
                }
                firstTerm.add(markedEdge.time);
            }
            firstTerm.add(set.size());
            secondTerm.add(count);
        }
        secondTerm.add(allMarks.size());
        secondTerm.add(edge.time);
        firstTerm.add(allMarks.size());
        firstTerm.add(sub);
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
