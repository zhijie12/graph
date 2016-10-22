/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.tree.DefaultMutableTreeNode;
import sun.reflect.generics.tree.Tree;

/**
 *
 * @author Mavric
 */
public class Graph {

    private HashMap<Integer, Vertex> vertices;
    private ArrayList<Vertex>[][] pathResults;
    private int numOfVertices, numOfEdges;

    public Graph(int numOfVertices, int numOfEdges) {
        vertices = new HashMap();

        // initalise the path matrix
        pathResults = new ArrayList[numOfVertices][numOfVertices];

        this.numOfVertices = numOfVertices;
        this.numOfEdges = numOfEdges;

        populateGraph();
    }

    private void populateGraph() {

        for (int i = 0; i < numOfVertices; i++) {
            for (int j = 0; j < numOfVertices; j++) {
                pathResults[i][j] = new ArrayList();
            }
        }

        //generate vertices
        for (int i = 0; i < numOfVertices; i++) {
            vertices.put(i, new Vertex(i));
        }

        //randomly assign edges to each vertice
        for (int i = 0; i < numOfEdges; i++) {

            Vertex vertexX;
            Vertex vertexY;

            //check to make sure no parrallel edges or self loop
            do {
                int x = (int) (Math.random() * this.numOfVertices);
                int y = (int) (Math.random() * this.numOfVertices);

                //get verticeX and verticeY
                vertexX = vertices.get(x);
                vertexY = vertices.get(y);

            } while (vertexX.hasNeighbour(vertexY) || vertexX.equals(vertexY));  //regenerate if the two vertices are the same e.g prevents self loop

            //add edge to both vertexX and vertexY
            vertexX.addEdge(vertexY);
            vertexY.addEdge(vertexX);

        }
        for (int j = 0; j < numOfVertices; j++) {
            printNeighbours(vertices.get(j));
        }
    }

    public void printNeighbours(Vertex v) {
        v.printNeighbours();
    }

    public void printNeighbours(int index) {
        Vertex v = vertices.get(index);
        v.printNeighbours();
    }

    public void breathFirstSearch(Vertex startPoint) {

        //datastructure to process nodes in a FIFO fashion
        Queue<Vertex> q = new LinkedList();
        //tree structure to store our BFS 
        DefaultMutableTreeNode tree = null;

        //setup list for marking of visted vertices
        //set all to false (not visted)
        boolean[] vistedVertex = new boolean[numOfVertices];
        for (int i = 0; i < numOfVertices; i++) {
            vistedVertex[i] = false;
        }

        //set up tree to store result for BFS
        q.add(startPoint);
        vistedVertex[startPoint.getName()] = true;

        while (!q.isEmpty()) {

            //get a vertex from the queue
            Vertex current = q.remove();
            //special case for root (first node)
            if (tree == null) {
                tree = new DefaultMutableTreeNode(startPoint);
            } else {
                //do level by level travesal for node so that we can match our vertex queue
                Enumeration<DefaultMutableTreeNode> e = ((DefaultMutableTreeNode)tree.getRoot()).breadthFirstEnumeration();
                while (e.hasMoreElements()) {
                    DefaultMutableTreeNode curr = e.nextElement();
                    if(curr.equals(tree)){
                        break;
                    }
                }
                tree = e.nextElement();
            }

            for (Edge eachEdge : current.getNeighborhood()) {
                //visit a neighbouring vertex
                Vertex adjacentVertex = eachEdge.getOtherVertex(current);

                //make sure its not a vertex that is marked
                if (!vistedVertex[adjacentVertex.getName()]) {
                    //mark it as visted
                    vistedVertex[adjacentVertex.getName()] = true;

                    //add it to the queue
                    q.add(adjacentVertex);

                    //make a tree node and add it into our tree
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(adjacentVertex);
                    tree.add(child);
                }
            }
        }

        //store results of BFS tree into 3D array
        storeBFSTree((DefaultMutableTreeNode) tree.getRoot());

    }

    public void storeBFSTree(DefaultMutableTreeNode BFSRoot) {
        ArrayList<Vertex> path = new ArrayList();
        int previousLevel = 0;
        Vertex startPoint = (Vertex) BFSRoot.getUserObject();
        //map BFS tree into 3D array
        for (Enumeration<DefaultMutableTreeNode> e = BFSRoot.preorderEnumeration(); e.hasMoreElements();) {

            //retrieve both the treenode and its vertex object
            DefaultMutableTreeNode currTreeNode = e.nextElement();
            Vertex currVertex = (Vertex) currTreeNode.getUserObject();

            //special case for root
            if (currTreeNode.getLevel() == 0) {
                path.add(currVertex);
                previousLevel = currTreeNode.getLevel();

            } //siblings (same level on the BFS tree) remove previous node from path and add 
            else if (currTreeNode.getLevel() == previousLevel) {
                path.remove(path.size() - 1);
                path.add(currVertex);

            } //previous node is parent of curr node add curr node to the path
            else if (currTreeNode.getLevel() > previousLevel) {
                path.add(currVertex);
                previousLevel = currTreeNode.getLevel();
            } //traversing from leaf to another subtree remove the difference in level from path
            else if (currTreeNode.getLevel() < previousLevel) {
                for (int i = 0; i <= (previousLevel - currTreeNode.getLevel()); i++) {
                    path.remove(path.size() - 1);
                }
                path.add(currVertex);
                previousLevel = currTreeNode.getLevel();
            }

            //store it into an 3D array
            pathResults[startPoint.getName()][currVertex.getName()] = new ArrayList(path.subList(0, path.size()));

            for (Vertex v : path) {
                System.out.print(v.getName() + " -> ");
            }
            System.out.println("");
        }
    }

    public void printPath(Vertex start, Vertex end) {

        for (Vertex v : pathResults[start.getName()][end.getName()]) {
            System.out.print(v.getName() + " -> ");
        }
    }

    public void printPath(int startID, int endID) {
        Vertex start = getVertex(startID);
        Vertex end = getVertex(endID);

        printPath(start, end);
    }

    public Vertex getVertex(int ID) {
        return vertices.get(ID);
    }

}
