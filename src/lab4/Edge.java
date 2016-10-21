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
public class Edge implements Comparable {

    private Vertex one, two;

    public Edge(Vertex one, Vertex two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public int compareTo(Object s) {
        Edge other = (Edge) s;
        //Edge(a,b) compared to Edge(a,b)
        if (this.one == other.getOne() && this.two == other.getTwo()) {
            return 0;
        }
        
        //Edge(a,b) compared to Edgeb, a)
        if(this.one == other.getTwo() && this.two == other.getOne()){
            return 0;
        }
        
        return 1;
    }
    
    public Vertex getOtherVertex(Vertex curr){
        if(one.equals(curr)){
            return two;
        }else
            return one;
    }

    public Vertex getOne() {
        return one;
    }

    public Vertex getTwo() {
        return two;
    }
    
    

}
