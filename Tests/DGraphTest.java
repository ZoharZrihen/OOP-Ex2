package Tests;

import dataStructure.DGraph;
import dataStructure.node;
import org.junit.rules.Stopwatch;

import static org.junit.Assert.*;

public class DGraphTest {
    DGraph D=new DGraph();
    @org.junit.Test
    public void getNode() {
    }

    @org.junit.Test
    public void getEdge() {
    }

    @org.junit.Test
    public void addNode() {
        for(int i=1;i<5;i++) {
            D.addNode(new node(i));

        }
    }

    @org.junit.Test
    public void connect() {
    }

    @org.junit.Test
    public void getV() {
    }

    @org.junit.Test
    public void getE() {

    }

    @org.junit.Test
    public void nodeSize() {
        for (int i = 1; i < 4; i++) {
            D.addNode(new node(i));
        }
        assertEquals(3,D.nodeSize());
        D.removeNode(1);
        assertEquals(2,D.nodeSize());
        D.addNode(new node(4));
        D.addNode(new node(5));
        D.addNode(new node(2)); //aleardy used key
        assertEquals(4,D.nodeSize());
    }

    @org.junit.Test
    public void edgeSize() {
        D.addNode(new node(1));
        D.addNode(new node(2));
        D.addNode(new node(3));
        D.connect(1,2,0);
        D.connect(2,3,0);
        assertEquals(2,D.edgeSize());
        D.removeNode(2);
        assertEquals(0,D.edgeSize());
        D.addNode(new node(2));
        D.connect(1,2,0);
        D.connect(2,3,0);
        D.removeEdge(2,3);
        assertEquals(1,D.edgeSize());
    }

    @org.junit.Test
    public void getMC() {
    }
}