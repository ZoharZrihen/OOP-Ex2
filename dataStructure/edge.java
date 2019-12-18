package dataStructure;

public class edge implements edge_data {
    private node source;
    private node dest;
    private int tag;
    private String info;
    private double weight;

    public edge(node s){
        source=new node(s);
        dest=null;
        tag=0;
        info=null;
        weight=0;
    }

    public edge(node s, node d, double w){
        source=s;
        dest= d;
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
}
