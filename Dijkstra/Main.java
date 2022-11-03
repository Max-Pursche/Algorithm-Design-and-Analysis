// CS224 Fall 2022

public class Main {
  public static void main(String args[]) {
    testOne();
    testTwo();
  }

  // graph on p. 139 (but as an undirected graph)
  public static void testOne() {
    System.out.println("*** testOne() ***");
    Node n1 = new Node(1); // node s
    Node n2 = new Node(2); // node u
    Node n3 = new Node(3); // node v
    Node n4 = new Node(4); // node y
    Node n5 = new Node(5); // node x
    Node n6 = new Node(6); // node z

    Graph G = new Graph();
    G.addNode(n1);
    G.addNode(n2);
    G.addNode(n3);
    G.addNode(n4);
    G.addNode(n5);
    G.addNode(n6);

    G.addEdge(n1, n2, 1);
    G.addEdge(n1, n3, 2);
    G.addEdge(n1, n5, 4);
    G.addEdge(n2, n4, 3);
    G.addEdge(n2, n5, 1);
    G.addEdge(n3, n5, 2);
    G.addEdge(n3, n6, 3);
    G.addEdge(n5, n4, 1);
    G.addEdge(n5, n6, 2);

    int distances[] = G.shortestPath(n1);
    for (int i=1; i<distances.length; ++i) {
      System.out.println("distance from node #1 to node #" + i + " = " + distances[i]);
    }
  } // testOne()

  // from my Ch. 4 Sec. 4 lecture notes
  public static void testTwo() {
    System.out.println("*** testTwo() ***");
    // A is node 8
    // B is node 9
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(7);
    Node n8 = new Node(8);
    Node n9 = new Node(9);

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

    G.addEdge(n8, n1, 22);
    G.addEdge(n8, n2, 64);
    G.addEdge(n8, n3, 23);
    G.addEdge(n8, n5, 52);
    G.addEdge(n1, n2, 41);
    G.addEdge(n2, n4, 36);
    G.addEdge(n2, n9, 130);
    G.addEdge(n3, n2, 39);
    G.addEdge(n3, n4, 64);
    G.addEdge(n3, n6, 42);
    G.addEdge(n4, n9, 74);
    G.addEdge(n5, n6, 37);
    G.addEdge(n5, n7, 126);
    G.addEdge(n6, n4, 21);
    G.addEdge(n6, n7, 43);
    G.addEdge(n6, n9, 132);
    G.addEdge(n7, n9, 52);

    G.print();

    int distances[] = G.shortestPath(n8);
    for (int i=1; i<distances.length; ++i) {
      System.out.println("distance from node #8 to node #" + i + " = " + distances[i]);
    }
  } // testTwo()
}
