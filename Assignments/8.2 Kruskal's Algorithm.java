package weblab;

import java.util.*;

class Solution {

    /**
     * Builds a Minimum Spanning Tree (MST) using
     * Kruskal's Algorithm (as learned in class).
     * Nodes are numbered from 0 to n - 1.
     *
     * @param n the amount of nodes in the graph
     * @param edges the edges that comprise the graph
     * @return the list of edges that should be included in the MST
     */
    public static List<Edge> buildMST(int n, List<Edge> edges) {
        UnionFind clusters = new UnionFind(n);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        List<Edge> res = new LinkedList<>();

        for (Edge e : edges) {
            pq.add(e);
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (clusters.union(edge.getFrom(), edge.getTo())) {
                res.add(edge);
            }
        }
        return res;
    }
}