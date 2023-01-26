package weblab;

import java.util.*;

class Solution {

  /**
   * @param village Adjacency map representing the village.
   * @param coatiHouses The IDs of the coati houses that should be connected.
   * @return The roads on which the traps should be dismantled.
   */
  public static Set<Road> regroupTheCoatis(List<Map<Integer, Integer>> village, Set<Integer> coatiHouses) {
    // Create set of all roads in village except the ones connected to an illegal house.
    Set<Road> roadsToClear = new HashSet<>();
    Set<Road> roads = new HashSet<>();
    for (int i = 0; i < village.size(); i++) {
        for (Integer j : village.get(i).keySet()) {
          if (coatiHouses.contains(i) && coatiHouses.contains(j)) {
            roads.add(new Road(i, j, village.get(i).get(j)));
          }
        }
    }


    // Add shortest path to currently built MST (roadsToClear) iff (at least 1 of the houses isn't connected yet)
    Map<Integer, Set<Integer>> connected = computeConnections(roadsToClear, village.size());
    Road shorty = findShortestRoad(roads, connected);

    while (shorty != null) {
        roadsToClear.add(shorty);
        connected = computeConnections(roadsToClear, village.size());
        roads.remove(shorty);
        shorty = findShortestRoad(roads, connected);
    }


    // Check if everything has been connected, else return null
    Set<Integer> finalConnected = new HashSet<>();
    for (Road road : roadsToClear) {
        finalConnected.add(road.getTo());
        finalConnected.add(road.getFrom());
    }
    if (!finalConnected.equals(coatiHouses)) {
        return null;
    }

    return roadsToClear;
  }

  private static Map<Integer, Set<Integer>> computeConnections(Set<Road> roadsToClear, int vilSize) {
    Map<Integer, Set<Integer>> connected = new HashMap<>();
    for (int i=0; i < vilSize; i++) {
        connected.put(i, new HashSet<Integer>());
    }
    int to, from;
    for (Road road : roadsToClear) {
        to = road.getTo();
        from = road.getFrom();
        connected.get(from).add(to);
        connected.get(to).add(from);
        for (Integer i : connected.get(from)) {
            connected.get(to).add(i);
        }
        for (Integer i : connected.get(to)) {
            connected.get(from).add(i);
        }
        for (Integer i : connected.get(from)) {
            for (Integer j : connected.get(from)) {
                connected.get(i).add(j);
                connected.get(j).add(i);
            }
        }
    }
    return connected;
  }

  private static Road findShortestRoad(Set<Road> roads, Map<Integer, Set<Integer>> connected) {
    Road shortest = new Road(0,0,Integer.MAX_VALUE);
    Road check = shortest;

    for (Road road : roads) {
        if (road.getTraps() < shortest.getTraps() &&
                !connected.get(road.getFrom()).contains(road.getTo()) &&
                !connected.get(road.getTo()).contains(road.getFrom())) {

            shortest = road;
        }
    }

    if (shortest == check) {return null;}
    return shortest;
  }
}
