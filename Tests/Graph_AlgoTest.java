package Tests;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.node;
import org.junit.Test;

import static org.junit.Assert.*;

public class Graph_AlgoTest {
    Graph_Algo ga=new Graph_Algo();

    public void main(){
        DGraph gra=new DGraph();
        for(int i=1;i<5;i++){
            gra.addNode(new node(i));
        }
            gra.connect(1,2,0);
            gra.connect(2,1,0);
            gra.connect(2,3,0);
            gra.connect(3,4,0);
            gra.connect(3,2,0);
            gra.connect(4,3,0);
            ga.init(gra);
        System.out.println(ga.isConnected());
        }




    @Test
    public void init() {



    }

    @Test
    public void testInit() {
    }

    @Test
    public void save() {
    }

    @Test
    public void isConnected() {
        DGraph gra=new DGraph();
        for(int i=1;i<6;i++){
            gra.addNode(new node(i));
        }
        gra.connect(1,2,0);
        gra.connect(2,3,0);
        gra.connect(3,4,0);
        gra.connect(4,5,0);
        gra.connect(5,4,0);
        gra.connect(4,3,0);
        gra.connect(3,2,0);
        gra.connect(2,1,0);
        ga.init(gra);
        assertTrue(ga.isConnected());
        gra.removeEdge(3,4);
        assertFalse(ga.isConnected());
        gra.connect(3,4,0);
        gra.removeNode(3);
        assertFalse(ga.isConnected());
    }


    @Test
    public void shortestPathDist() {
    }

    @Test
    public void shortestPath() {
    }

    @Test
    public void TSP() {
    }

    @Test
    public void copy() {
    }
}