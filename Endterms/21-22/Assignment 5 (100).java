package weblab;

import java.util.*;

class Street {

    // Use the fields below to store the heads and tails of the even and odd side of the street
    // Do NOT change the names of the fields or how they are initialised in the constructor!
    // You may NOT add more fields to the class.
    Node headEven;

    Node tailEven;

    Node headOdd;

    Node tailOdd;

    /**
     * Make an empty street.
     */
    public Street() {
        headEven = new Node();
        tailEven = new Node();
        headEven.setNext(tailEven);
        tailEven.setPrev(headEven);
        headOdd = new Node();
        tailOdd = new Node();
        headOdd.setNext(tailOdd);
        tailOdd.setPrev(headOdd);
    }

    /**
     * Removes a house from the street.
     * If the house does not exist, the street should be unchanged and the method should return null.
     *
     * This method must run in O(n + m) time.
     * Here is n the number of even houses, and m the number of odd houses.
     *
     * @param houseNumber number of the house to be removed from this street, if present
     * @return if present, the removed house, otherwise null
     */
    public House removeHouse(int houseNumber) {
        // Get head node of corresponding house side
        Node headNode = houseNumber % 2 == 0 ? headEven : headOdd;
        Node tailNode = houseNumber % 2 == 0 ? tailEven : tailOdd;
        Node oppoHead = houseNumber % 2 == 1 ? headEven : headOdd;
        Node oppoTail = houseNumber % 2 == 1 ? tailEven : tailOdd;

        // Get the house's node
        Node walkNode = headNode.getNext();
        Node houseNode = null;
        while (walkNode != tailNode) {
            if (walkNode.getHouse().getHouseNumber() == houseNumber) {
                houseNode = walkNode;
            }
            walkNode = walkNode.getNext();
        }

        // If houseNode == null, we didn't find the house, thus return null;
        if (houseNode == null) {return null;}

        // If we reach this, houseNode is the node containing the house to be deleted


        // Update opposing houses of opposing nodes
        House remHouse = houseNode.getHouse();
        
            // Loop through all houses on the otherside and try to remove our house
            // from their opposing houses list
        Node oppoWalk = oppoHead.getNext();
        while (oppoWalk != oppoTail) {
            oppoWalk.getOpposingHouses().remove(remHouse);
            oppoWalk = oppoWalk.getNext();
        }

        // Update other nodes around us
        Node next = houseNode.getNext();
        Node prev = houseNode.getPrev();
        prev.setNext(next);
        next.setPrev(prev);
        
        return remHouse;
    }

    /**
     * Puts all the houses in the street in a list in increasing order of house number.
     *
     * This method must run in O(n+m) time.
     * Here n is the number of even houses, and m is the number of odd houses.
     *
     * @return a list of all the houses in the street in increasing order of house number
     */
    public List<House> collectAllHouses() {
        // This is very similar to merging sorted lists.
        List<House> houses = new ArrayList<>();
        Node walkerEven = headEven.getNext();
        Node walkerOdd = headOdd.getNext();
        while (walkerEven != tailEven && walkerOdd != tailOdd) {
            if (walkerEven.getHouse().getHouseNumber() < walkerOdd.getHouse().getHouseNumber()) {
                houses.add(walkerEven.getHouse());
                walkerEven = walkerEven.getNext();
            } else {
                houses.add(walkerOdd.getHouse());
                walkerOdd = walkerOdd.getNext();
            }
        }
        while (walkerEven != tailEven) {
            houses.add(walkerEven.getHouse());
            walkerEven = walkerEven.getNext();
        }
        while (walkerOdd != tailOdd) {
            houses.add(walkerOdd.getHouse());
            walkerOdd = walkerOdd.getNext();
        }
        return houses;
    }

    /**
     * Gets the number of even numbered houses in the street.
     *
     * @return the number of houses with an even house number
     */
    public int getNumEven() {
        Node walkerEven = headEven;
        int n = 0;
        while (walkerEven != tailEven) {
            n++;
            walkerEven = walkerEven.getNext();
        }
        return n-1;
    }
}
