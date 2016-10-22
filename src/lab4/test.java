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
        Graph g = new Graph(20,40);
    
        System.out.println("Graph generated");
        g.breathFirstSearch(g.getVertex(0));
        
        System.out.println("From 0 to 5: ");
        g.printPath(0, 5);
    }
    
}
