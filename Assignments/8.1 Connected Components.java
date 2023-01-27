package weblab;

import java.util.*;

class Solution {

    public static int numberOfConnectedComponents(Graph g) {
        if (g.getAllVertices().size() == 0) {return 0;}

        Collection<Vertex> unexplored = new HashSet<Vertex>();
        unexplored.addAll(g.getAllVertices());
        int comps = 0;

        while (!unexplored.isEmpty()) {
            Vertex node = unexplored.iterator().next();
            unexplored.remove(node);
            explore(unexplored, node, g);
            comps++;
        }

        return comps;
    }

    private static void explore(Collection<Vertex> unexplored, Vertex v, Graph g) {
        for (Vertex neighbour : g.getNeighbours(v)) {
            if (unexplored.contains(neighbour)) {
                unexplored.remove(neighbour);
                explore(unexplored, neighbour, g);
            }
        }
    }
}
