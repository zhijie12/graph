/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

/**
 *
 * @author Mavric
 */
public class test {
    
    public static void main(String[] args) {
        
        Graph g = new Graph(10,20); //NumOfVertices, NumOfEdges
    
        System.out.println("----------");
        g.breathFirstSearch(g.getVertex(0));
        System.out.println("----------");
        g.breathFirstSearch(new Vertex(1));
        
        /*
        Graph g1 = new Graph(5000, 1000);
        Graph g2 = new Graph(5000, 5000);
        Graph g3 = new Graph(5000, 10000);
        Graph g4 = new Graph(5000, 50000);
        Graph g5 = new Graph(5000, 100000);

        Graph g6 = new Graph(10000, 1000);
        Graph g7 = new Graph(10000, 5000);
        Graph g8 = new Graph(10000, 10000);
        Graph g9 = new Graph(10000, 50000);
        Graph g10 = new Graph(10000, 100000);        
        */
    }
    
}
