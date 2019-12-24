package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph, Serializable {
	private static int numOfEdgesG=0;
	private HashMap<Integer, node_data> vertices;
	private HashMap<Integer,HashMap<Integer,edge_data>> edges;
    private int numOfVertices;
	private int ModeCount;

	public DGraph(){
		numOfVertices=0;
		vertices=new HashMap<Integer, node_data>();
		edges=new HashMap<Integer, HashMap<Integer, edge_data>>();
	}
	public DGraph(DGraph gr){
		numOfVertices=gr.nodeSize();
		vertices=new HashMap<>(gr.getVertices());
		edges=new HashMap<>(gr.getEdges());
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
			edge_data e=edges.get(src).get(dest);
			return e;
		}
		else return null;
	}

	@Override
	public void addNode(node_data n) {
	    node n1= (node) n;
		if(!vertices.containsKey(n1.getKey())) {
			vertices.put(n1.getKey(),n1);
			edges.put(n1.getKey(),new HashMap<Integer, edge_data>());
			numOfVertices++;
			ModeCount++;
		}
	}

	@Override
	public void connect(int src, int dest, double w) {
		try {
			edge e=new edge(vertices.get(src),vertices.get(dest),w);
            edges.get(src).put(dest,e);
            numOfEdgesG++;
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
		return edges.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
        try {
            node_data n = vertices.remove(key);
            numOfVertices--;
            ModeCount++;
            Object[] arr = vertices.keySet().toArray();
            for (int i = 0; i < arr.length; i++) {
				if(edges.get(arr[i]).get(key)!=null){
					edges.get(arr[i]).remove(key);
					numOfEdgesG--;
				}
            }
            int sub=0;
            if(edges.get(key)!=null){
            	sub=edges.get(key).size();
			}
            edges.remove(key);
            numOfEdgesG-=sub;
            return n;
        } catch(Exception e){
            return null;
        }
    }
	@Override
	public edge_data removeEdge(int src, int dest) {
		try {
			numOfEdgesG--;
			return edges.get(src).remove(dest);
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
		return numOfEdgesG;
	}

	@Override
	public int getMC() {
		return ModeCount;
	}


	public HashMap<Integer,node_data> getVertices(){
		return vertices;
	}
	public HashMap<Integer,HashMap<Integer,edge_data>> getEdges(){
		return edges;
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
