package weblab;

import java.util.*;

class GraphChecker {

    /**
     * Traverses the given graph depth first and returns the IDs of visited vertices in order, starting from the given vertex.
     * In case multiple choices are possible for a next vertex, they are visited in increasing order of ID.
     * Runs in O(V+E) time. Does not modify the input graph.
     * @param g Graph to traverse.
     * @param v Vertex to start the traversal from.
     * @return List of IDs of vertices found using DFS traversal.
     */
    public List<Integer> traverseDepthFirst(Graph g, Vertex v) {
        List<Integer> ret = new ArrayList<>();
        Set<Vertex> vs = new HashSet<>();
        Stack<Vertex> s = new Stack<>();
        s.push(v);

        while (!s.isEmpty()) {
            v = s.pop();
            if (vs.contains(v)) {continue;}
            for (int i = v.getNeighbours().size()-1; i >= 0; i--) {
                Vertex x = v.getNeighbours().get(i);

                if (!vs.contains(x)) {
                    s.push(x);
                }
            }
            ret.add(v.getId());
            vs.add(v);
        }
        return ret;
    }

    /**
     * Returns the transpose of the given graph, i.e. a graph with all edges reversed.
     * Runs in O(V+E) time. Does not modify the input graph.
     * @param g Graph to create the transpose of.
     * @return Transpose of the given graph.
     */
    public Graph transpose(Graph g) {
        Graph tg = new Graph();

        // Create all new vertices for the tg
        for (Vertex v : g.getAllVertices()) {
            tg.addVertex(new Vertex(v.getId()));
        }

        // Add neighbours for the tg
        for (Vertex v : g.getAllVertices()) {
            for (Vertex n : v.getNeighbours()) {
                tg.getVertex(n.getId()).addNeighbour(tg.getVertex(v.getId()));
            }
        }

        return tg;
    }

    /**
     * Returns true if the given graph is strongly connected, false otherwise.
     * Runs in O(V+E) time. Does not modify the input graph.
     * @param g Graph to check for strong connectedness.
     * @return True if the graph is strongly connected, false otherwise.
     */
    public boolean isStronglyConnected(Graph g) {
        Graph tg = transpose(g);
        Vertex v1 = g.getAllVertices().get(0);
        Vertex v2 = tg.getAllVertices().get(0);
        return (traverseDepthFirst(g, v1).size() == traverseDepthFirst(tg, v2).size())
                && traverseDepthFirst(g, v1).size() == g.getAllVertices().size();
    }
}
