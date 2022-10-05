// CS224 Fall 2022

public class Main {
  public static void main(String args[]) {
    testOne();
    //testTwo();
  }

  public static void testOne() {
    // this is the graph in Fig. 3.2
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(7);
    Node n8 = new Node(8);
    Node n9 = new Node(9);
    Node n10 = new Node(10);
    Node n11 = new Node(11);
    Node n12 = new Node(12);
    Node n13 = new Node(13);

    Graph G = new Graph();
    G.addNode(n1);
    G.addNode(n2);
    G.addNode(n3);
    G.addNode(n4);
    G.addNode(n5);
    G.addNode(n6);
    G.addNode(n7);
    G.addNode(n8);
    G.addNode(n9);
    G.addNode(n10);
    G.addNode(n11);
    G.addNode(n12);
    G.addNode(n13);

    G.addEdge(n1, n2);
    G.addEdge(n1, n3);
    G.addEdge(n2, n4);
    G.addEdge(n2, n5);
    G.addEdge(n3, n7);
    G.addEdge(n3, n8);
    G.addEdge(n4, n5);
    G.addEdge(n5, n6);
    G.addEdge(n7, n8);
    G.addEdge(n9, n10);
    G.addEdge(n11, n12);
    G.addEdge(n12, n13);

    G.findConnectedComponents();
  } // testOne()

  public static void testTwo() {
    //a graph having no edges
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);

    Graph G = new Graph();
    G.addNode(n1);
    G.addNode(n2);
    G.addNode(n3);
    G.addNode(n4);
    G.addNode(n5);
    G.addNode(n6);

    G.findConnectedComponents();
  } // testTwo()
}
