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
        Queue<Vertex> q = new LinkedList();
        DefaultMutableTreeNode tree = null;

        //setup list for marking of visted vertices
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
            if (tree == null) {
                tree = new DefaultMutableTreeNode(startPoint);
            }else{
                tree = tree.getNextNode();
            }
            
            for (Edge eachEdge : current.getNeighborhood()) {
                //visit a neighbouring vertex
                Vertex adjacentVertex = eachEdge.getOtherVertex(current);
                if (!vistedVertex[adjacentVertex.getName()]) {
                    vistedVertex[adjacentVertex.getName()] = true;  //mark it as visted
                    q.add(adjacentVertex);//add it to the queue
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(adjacentVertex);
                    tree.add(child);
                }
            }
        }

        for (Enumeration<DefaultMutableTreeNode> e = ((DefaultMutableTreeNode)tree.getRoot()).preorderEnumeration(); e.hasMoreElements();) {
            DefaultMutableTreeNode treenode = e.nextElement();
            Vertex a = (Vertex) treenode.getUserObject();
            System.out.println(a.getName() + " " + treenode.getLevel());
        }

    }

    public Vertex getVertex(int ID) {
        return vertices.get(ID);
    }

}
