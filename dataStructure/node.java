package dataStructure;

import utils.Point3D;

import java.util.HashSet;

public class node implements node_data{
    private HashSet<edge> edges;
    private int key;
    private Point3D location;
    private double weight;
    private String info;
    private int tag;

    public node(){
        edges=new HashSet<edge>();
        key=0;
        location=new Point3D(0,0,0);
        weight=0;
        info=null;
        tag=0;
    }
    public node(int k){
        edges=new HashSet<edge>();
        key=k;
        location=new Point3D(0,0,0);
        weight=0;
        info=null;
        tag=0;
    }

    public node(int k,Point3D p,double w){
        edges=new HashSet<edge>();
        key=k;
        location=new Point3D(p);
        weight=w;
        info=null;
        tag=0;
    }
    public node(node n){
        edges=new HashSet<edge>(n.getEdges());
        key=n.getKey();
        location=n.getLocation();
        weight=n.getWeight();
        info=n.getInfo();
        tag=n.getTag();
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

    public HashSet<edge> getEdges() {
        return edges;
    }
    public void addEdge(edge e){
        edges.add(e);
    }

}
