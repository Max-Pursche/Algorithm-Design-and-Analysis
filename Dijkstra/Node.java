// CS224 Fall 2022

import java.util.ArrayList;
import java.lang.Comparable;

public class Node {
  ArrayList<Link> adjlist;
  int name;

  public Node(int name) {
    this.name = name;
    this.adjlist = new ArrayList<Link>();
  }

  public void addEdge(Node otherNode, int weight) {
    // make sure that this edge doesn't already exist
    for (Link link: this.adjlist) {
      if (link.n2 == otherNode) {
        System.out.println("ERROR: there is already an edge from " + this.name + " to " + otherNode.name);
        return;
      }
    }

    // add edge from this to edge.tail
    this.adjlist.add(new Link(otherNode, weight));
  }

  public String toString() {
    String s = "N " + this.name;
    return s;
  }
}
