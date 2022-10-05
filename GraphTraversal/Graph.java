// CS224 Fall 2022

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {
  ArrayList<Node> nodes;

  public Graph() {
    nodes = new ArrayList<Node>();
  }

  public void addNode(Node node) {
    for (Node n: this.nodes) {
      if (n == node) {
        System.out.println("ERROR: graph already has a node " + n.name);
        assert false;
      }
    }
    this.nodes.add(node);
  }

  public void addEdge(Node n1, Node n2) {
    // outgoing edge
    int idx1 = this.nodes.indexOf(n1);
    if (idx1 < 0) {
      System.out.println("ERROR: node " + n1.name + " not found in graph");
      assert false;
    }

    int idx2 = this.nodes.indexOf(n2);
    if (idx2 < 0) {
      System.out.println("ERROR: node " + n2.name + " not found in graph");
      assert false;
    }

    n1.addEdge(n2);
  }

  /*
    Addntl Notes:
  To build the DFS tree, we can use an additional array
  parent, and set parent[v] = u when we add node v
  to the stack due to edge (u,v) (i.e., when we discover
  node v)

  And when we mark a node u as Explored, we can add
  the edge (u, parent[u]) to the tree T.

  Adding or deleting a node from the stack takes BigO(1) time.

  So, we need to find how many times a node gets added to
  the stack.

  A node will be added to the stack every time one of its
  adjacent nodes is explored, and so at most the total
  number of times a node will be added to the stack is 2m
  (the sum of the degrees of the graph).

  And so DFS is also BigO(m + n) (assuming an adj list representation)

  DFS(s)
    Initialize S to be a stack with one element s
    While S is not empty
      Take a node u from S
      If Explored[u] = false then
        Set Explored[u] = true
        For Each edge (u,v) incident to u
          add v to the stack S
        EndFor
      EndIf
    EndWhile
    Put elements of stack into arraylist
    return arraylist
   EndDFS

  */
  public ArrayList<Node> DFS(Node s) {
    // implement this
    System.out.println("DFS("+s.name+")");
    ArrayList<Node> connected = new ArrayList<>();
    boolean[] visited = new boolean[nodes.size()+1];
    Stack<Node> S = new Stack<Node>();
    S.push(s);
    while(!S.isEmpty()) {
      //take a node u from S
      Node u = S.peek();
      if(!visited[u.name]) {
        visited[u.name] = true;
        S.pop();
        for (Node v: u.adjlist) {
          S.push(v);
        }
        connected.add(u);
      }
      else{
        S.pop();
      }
    }
    return connected;
  } // DFS()

  public void findConnectedComponents() {
    // implement this
    boolean[] visited = new boolean[nodes.size()+1];
    ArrayList<Node> connectedComponents;
    for (Node u: nodes){
      //check if the node has been visited yet
      if(!visited[u.name]){
        visited[u.name] = true;
        connectedComponents = DFS(u);
        int i =0;
        System.out.print("[");
        for(Node v: connectedComponents) {
          visited[v.name] = true;
          if(i < connectedComponents.size()-1){
            System.out.print(v.toString()+", ");
          }
          else{
            System.out.print(v.toString());
          }
          i++;
        }
        System.out.print("]\n");
      }
    }
  } // findConnectedComponents()
} // class Graph
