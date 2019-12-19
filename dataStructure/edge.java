package dataStructure;

import java.io.Serializable;

public class edge implements edge_data, Serializable {
    private node source;
    private node dest;
    private int tag;
    private String info;
    private double weight;

    public edge(node_data s, node_data d, double w){
        source=(node)s;
        dest= (node)d;
    }

    public edge(edge e){
        source=new node(e.source);
        dest=new node(e.dest);
        tag=e.getTag();
        info=e.getInfo();
        weight=e.getWeight();
    }

    @Override
    public int getSrc() {
        return source.getKey();
    }

    @Override
    public int getDest() {
        return dest.getKey();
    }

    @Override
    public double getWeight() {
        return weight;
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
    public node getSource(){
        return source;
    }
    public node getDestination(){
        return dest;
    }
}
