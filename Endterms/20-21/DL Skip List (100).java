package weblab;

class CoatiList {

  private CoatiNode head;
  private CoatiNode tail;
  
  public CoatiNode getHead() {
    return head;
  }

  public CoatiNode getTail() {
    return tail;
  }

  /**
   * Adds value v in a new node to the head of the list.
   *
   * @param v Value to add.
   */
  public void addFirst(int v) {
    CoatiNode newNode = new CoatiNode(v);
    newNode.setNext(head);
    if (head != null && head.getNext() != null && head.getNext().getNext() != null) {
      newNode.setSkipAhead(head.getNext().getNext());
      head.getNext().getNext().setSkipBack(newNode);
    }
    if (head == null) {
      this.tail = newNode;
    } else {
      this.head.setPrevious(newNode);
    }
    this.head = newNode;
  }

  /**
   * Remove the first node in the list and return its value.
   *
   * @return The value of the head node.
   * @throws EmptyListException If the list is empty.
   */
  public int removeFirst() {
    if (head == null) {throw new EmptyListException();}

    // Head cannot be null anymore
    CoatiNode returnNode = head;
    this.head = head.getNext(); // Might set head to null again
    
    if (head == null) {tail = null;} // If head is now null, list is empty
    else {head.setPrevious(null);} // Else head needs to have nothing before

    if (returnNode.getSkipAhead() != null) { // Remove other old references
      returnNode.getSkipAhead().setSkipBack(null);
    }
    return returnNode.getValue();
  }

  /**
   * Returns the value of node at the given position.
   * @param pos Position to look for.
   * @return The value of the node at the given position.
   * @throws IllegalIndexException If there is no node at this index.
   */
  public int getValueAtPosition(int pos) {
    if (head == null || pos < 0) {throw new IllegalIndexException();}
    int skips = pos / 3;
    int nexts = pos % 3;
    CoatiNode walkNode = head;
    for (int i = 0; i < skips; i++) {
      walkNode = walkNode.getSkipAhead();
      if (walkNode == null) {throw new IllegalIndexException();}
    }
    for (int i = 0; i < nexts; i++) {
      walkNode = walkNode.getNext();
      if (walkNode == null) {throw new IllegalIndexException();}
    }

    return walkNode.getValue();
  }
}
