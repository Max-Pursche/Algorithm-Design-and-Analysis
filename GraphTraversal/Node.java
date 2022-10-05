// CS224 Fall 2022

import java.util.ArrayList;

public class Node {
  ArrayList<Node> adjlist;
  int name;

  public Node(int name) {
    this.name = name;
    this.adjlist = new ArrayList<Node>();
  }

  public void addEdge(Node otherNode) {
    // make sure that this edge doesn't already exist
    for (Node n: this.adjlist) {
      if (n == otherNode) {
        System.out.println("ERROR: there is already an edge from " + this.name + " to " + otherNode.name);
        return;
      }
    }
    for (Node n: otherNode.adjlist) {
      if (n == this) {
        System.out.println("ERROR: there is already an edge from " + this.name + " to " + otherNode.name);
        return;
      }
    }

    // add edge from this to edge.tail
    this.adjlist.add(otherNode);
    // and vice versa
    otherNode.adjlist.add(this);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;

    if ( ! (o instanceof Node) )
      return false;

    Node otherNode = (Node) o;
    if (otherNode.name == this.name)
      return true;

    return false;
  }

  @Override
  public String toString() {
    String s = "" + this.name;
    return s;
  }
}
