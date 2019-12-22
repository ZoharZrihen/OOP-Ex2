package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph, Serializable {
	private static int numOfEdgesG=0;
	private HashMap<Integer, node_data> vertices;
    private int numOfVertices;
	private int ModeCount;

	public DGraph(){
		numOfVertices=0;
		vertices=new HashMap<Integer, node_data>();
	}
	public DGraph(DGraph gr){
		numOfVertices=gr.nodeSize();
		vertices=new HashMap<>(gr.getVertices());
		ModeCount=0;
	}

	@Override
	public node_data getNode(int key) {
		if(vertices.containsKey(key)){
			return vertices.get(key);
		}
		else return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if(vertices.containsKey(src)&& vertices.containsKey(dest)){
		    node_data n =vertices.get(src);
			return ((node)n).getEdge(dest);
		}
		else return null;
	}

	@Override
	public void addNode(node_data n) {
	    node n1= (node) n;
		if(!vertices.containsKey(n1.getKey())) {
			vertices.put(n1.getKey(),n1);
			numOfVertices++;
			ModeCount++;
		}
	}

	@Override
	public void connect(int src, int dest, double w) {
		try {
		    //node_data n=vertices.get(src);
            ((node)vertices.get(src)).getEdges().put(dest, new edge(vertices.get(src), vertices.get(dest), w));
			ModeCount++;
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

	@Override
	public Collection<node_data> getV() {
		return vertices.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
	    node_data n=vertices.get(node_id);
	    return ((node)n).getEdges().values();
	}

	@Override
	public node_data removeNode(int key) {
        try {
            node_data n = vertices.remove(key); //go over all the hash and check if dest removed
            numOfVertices--;
            ModeCount++;
            Object[] arr = vertices.keySet().toArray();
            for (int i = 0; i < arr.length; i++) {
                if (((node) vertices.get(arr[i])).getEdges().get(key) != null) {
                    ((node) vertices.get(arr[i])).getEdges().remove(key);
                    ModeCount++;
                }
            }
            return n;
        } catch(Exception e){
            return null;
        }
    }
	@Override
	public edge_data removeEdge(int src, int dest) {
		try {
			return ((node)vertices.get(src)).getEdges().remove(dest);
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public int nodeSize() {
		return numOfVertices;
	}

	@Override
	public int edgeSize() {
	    getNumOfEdgesG();
		return numOfEdgesG;
	}

	@Override
	public int getMC() {
		return ModeCount;
	}


	public HashMap<Integer,node_data> getVertices(){
		return vertices;
	}
    public int getNumOfEdgesG(){
	    numOfEdgesG=0;
	    Object[] arr=vertices.keySet().toArray();
	    for(int i=0;i<arr.length;i++){
            numOfEdgesG+=((node)vertices.get(arr[i])).getEdges().size();
        }
	    return numOfEdgesG;
    }
    public String toString(){
        String t="";
	    Object[] arr=vertices.keySet().toArray();
        for(int i=0;i<arr.length;i++){
            t+=vertices.get(arr[i]).getKey() + " -> " ;
        }
        return t;
    }
}
