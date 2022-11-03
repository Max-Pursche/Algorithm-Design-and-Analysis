// CS224 Fall 2022

import java.util.ArrayList;
import java.util.Queue;

public class Graph {
  ArrayList<Node> nodes;

  public Graph() {
    this.nodes = new ArrayList<Node>();
  }

  public void addNode(Node newNode) {
    for (Node n: this.nodes) {
      if (n == newNode) {
        System.out.println("ERROR: graph already has a node " + n.name);
        assert false;
      }
    }
    nodes.add(newNode);
  }

  public void addEdge(Node n1, Node n2, int weight) {
    // make sure edge does not already exist
    int idx1 = this.nodes.indexOf(n1);
    if (idx1 >= 0) {
      for (Link link: this.nodes.get(idx1).adjlist) {
        if (link.n2 == n2) {
          System.out.println("ERROR: there is already an edge from " + n1.name + " to " + n2.name);
          return;
        }
      }
      this.nodes.get(idx1).addEdge(n2, weight);
    } else {
      System.out.println("ERROR: node " + n1.name + " not found in graph");
    }

    int idx2 = this.nodes.indexOf(n2);
    if (idx2 >= 0) {
      this.nodes.get(idx2).addEdge(n1, weight);
    } else {
      System.out.println("ERROR: node " + n2.name + " not found in graph");
    }
  } // addEdge()

  public void print() {
    for (Node n1: this.nodes) {
      System.out.print(n1 + ":");
      for (Link link: n1.adjlist) {
        System.out.print(" " + link.n2.name + " (d=" + link.weight + ")");
      }
      System.out.print("|");
    }
  }
/*
Notes:
• choose a starting node s
• maintain a set S of vertices u for which we have determined a shortest-path distance d(u) from s:
  this is the "explored" part of the graph
• d(u) is the shortest path to the node u from the starting node s through nodes in S
• initially, S = {s}, and d(s) = 0
• now, for each node v ∈ V − S, we determine the shortest path from s that can be constructed by
traveling along a path through the explored part S to some u ∈ S, followed by the single edge (u, v)
• then, choose the node v ∈ V − S for which this quantity is minimized(The greedy part)
• and add v to S
Aside:
• l(Subscript: e) is the length of an edge or the weight

Dijkstras(Node n)
  Let S be the set of explored nodes
    Foreach u ∈ S, we store a distance d(u)
  Initially S = {s} and d(s) = 0
  While S != V ()
    Select a node v !∈ S with at least one edge from S for which
    (Translation: find the node not in S that has an edge to a node in S and has the smallest distance through S to the starting node s)
      d'(v) = min(Subscript:e=(u,v):u ∈ S) d(u) + l(Subscript: e) is as small as possible
      (Translation: use the smallest value from the depth you are at and look for the smallest value link, add them and add them to the array)
    Add v to S and define d(v) = d'(v)
  EndWhile
 */
  public int[] shortestPath(Node s) {
    boolean[] visited = new boolean[nodes.size()+1];
    ArrayList<Node> S = new ArrayList<Node>();
    int[] distances = new int[nodes.size()+1];
    //Initially S = {s} and d(s) = 0
    S.add(s);
    distances[s.name] = 0;
    visited[s.name] = true;
    //Foreach u ∈ S, we store a distance d(u)
    for (Node u: S) {
      for (Link l: u.adjlist) {
        distances[l.n2.name] = l.weight;
      }
    }
    //While checking is S != V (While S doesn't have every Node)
    while (S.size() != nodes.size()) {
      //Select a node v !∈ S with at least one edge from S for which
      //d'(v) = min(Subscript:e=(u,v):u ∈ S) d(u) + l(Subscript: e) is as small as possible
      //Translation: Find the smallest value from the depth you are at and look for the smallest value link, add them for the weight from s
      Node smNode = null;
      int minWeight = 0;
      for (Node v : S) {
        int weightP = 0;
        for (Link l: v.adjlist){
          int weight = 0;
          if(visited[l.n2.name]){
            weightP = distances[v.name];
          }
          //if statement for "if node has been visited"
          if(!visited[l.n2.name]){
            //Current weight from s
            weight = weightP + l.weight;
            //Checking if the weight is smaller than the previous
            if((weight > 0 && minWeight == 0) && !visited[l.n2.name]) {
              minWeight = weight;
              smNode = l.n2;
            }
            else if (minWeight > weight && !visited[l.n2.name]) {
              minWeight = weight;
              smNode = l.n2;
            }
            System.out.println(v.name+" -> "+l.n2.name+" :"+ weight);//This is correct output
          }
        }
      }
      S.add(smNode);
      visited[smNode.name]=true;
      distances[smNode.name]=minWeight;
      System.out.println("Found a Node "+smNode.name+": distance is "+minWeight);//This is correct output
    }
    return distances;
  } // shortestPath()
}
