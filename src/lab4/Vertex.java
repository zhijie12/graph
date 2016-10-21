/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author Mavric
 */
public class Vertex {

    private int name;
    private ArrayList<Edge> neighborhood;

    public Vertex(int name) {
        this.name = name;
        neighborhood = new ArrayList();
    }

    public int getName() {
        return name;
    }

    public ArrayList<Edge> getNeighborhood() {
        return neighborhood;
    }

    public void addEdge(Vertex other) {
        neighborhood.add(new Edge(this, other));
    }

    public boolean hasNeighbour(Vertex other) {
        for (Edge e : neighborhood) {
            if (e.compareTo(new Edge(this, other)) == 0) {
                return true;
            }
        }
        return false;
    }

    public void printNeighbours() {
        String output = this.getName() + " : ";
        if(!this.hasNeighbours()){
            System.out.println(output + " has no neighbours");
            return;
        }
        
        for (Edge e : neighborhood) {
            if (e.getOne().equals(this)) {
                output+=e.getTwo().getName()+", ";
            }else{
                output+=e.getOne().getName()+", ";
            }
        }
        System.out.println(output);
    }
    
    public boolean hasNeighbours(){
        return !neighborhood.isEmpty();
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex other = (Vertex) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
}
