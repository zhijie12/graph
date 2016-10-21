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
        Graph g = new Graph(10,10);
    
        System.out.println("a");
        g.breathFirstSearch(g.getVertex(0));
      //  g.breathFirstSearch(new Vertex(1));
        
    }
    
}
