package dataStructure;

import utils.Point3D;

import java.util.ArrayList;
import java.util.HashMap;

public class node implements node_data{
    private int key;
    private Point3D location;
    private double weight;
    private String info;
    private int tag;
    private HashMap<Integer,edge_data> edges;
    private static int numOfEdges;

    public node(){
        edges=new HashMap<>();
        key=0;
        location=new Point3D(0,0,0);
        weight=0;
        info=null;
        tag=0;
        numOfEdges=edges.size();

    }
    public node(int k){
        edges=new HashMap<>();
        key=k;
        location=new Point3D(0,0,0);
        weight=0;
        info=null;
        tag=0;
        numOfEdges=edges.size();
    }

    public node(int k,Point3D p,double w){
        edges=new HashMap<>();
        key=k;
        location=new Point3D(p);
        weight=w;
        info=null;
        tag=0;
        numOfEdges=edges.size();
    }
    public node(node n){
        edges=new HashMap(n.getEdges());
        key=n.getKey();
        location=n.getLocation();
        weight=n.getWeight();
        info=n.getInfo();
        tag=n.getTag();
        numOfEdges=edges.size();
    }
    @Override
    public int getKey() {
        return key;
    }

    @Override
    public Point3D getLocation() {
        return location;
    }

    @Override
    public void setLocation(Point3D p) {
        location=new Point3D(p);
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double w) {
        weight=w;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String s) {
        info=s;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        tag=t;
    }

    public HashMap<Integer,edge_data> getEdges() {
        return edges;
    }

    public edge_data getEdge(int dest){
        return this.edges.get(dest);
    }

    public int getNumOfEdges(){
        return numOfEdges;
    }

}
