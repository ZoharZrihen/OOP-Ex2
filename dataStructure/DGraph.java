package dataStructure;

import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph{
	private int numOfVertices;
	private int numOfEdges;
	private HashMap<Integer, node> vertices;
	private int ModeCount;

	public DGraph(){
		numOfVertices=0;
		vertices=new HashMap<Integer, node>();
	}
	public DGraph(DGraph gr){
		numOfVertices=gr.nodeSize();
		numOfEdges=gr.edgeSize();
		vertices=new HashMap<>(gr.getVertices());
		ModeCount=gr.getMC();
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
			return vertices.get(src).getEdge(dest);
		}
		else return null;
	}

	@Override
	public void addNode(node_data n) {
		if(!vertices.containsKey(n.getKey())) {
			vertices.put(n.getKey(),(node)n);
			numOfVertices++;
			numOfEdges+=((node) n).getNumOfEdges();
			ModeCount++;
		}
	}

	@Override
	public void connect(int src, int dest, double w) {
		try {
			vertices.get(src).getEdges().put(dest, new edge(vertices.get(src), vertices.get(dest), w));
			vertices.get(src).setNumOfEdges(vertices.get(src).getNumOfEdges()+1);
			vertices.get(dest).setNumOfEdges(vertices.get(dest).getNumOfEdges()+1);
			numOfEdges++;
			ModeCount++;
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

	@Override
	public Collection<node_data> getV() {
		return (Collection<node_data>) vertices;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return (Collection<edge_data>) vertices.get(node_id).getEdges();
	}

	@Override
	public node_data removeNode(int key) {
		node n=vertices.get(key);
		int k=vertices.get(key).getNumOfEdges();
		numOfEdges-=vertices.get(key).getNumOfEdges();
		vertices.remove(key);
		numOfVertices--;
		ModeCount++;

		return n;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		try {
			edge e = new edge(vertices.get(src).getEdges().get(dest));
			vertices.get(src).getEdges().remove(dest);
			numOfEdges--;
			return e;
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
		return numOfEdges;
	}

	@Override
	public int getMC() {
		return ModeCount;
	}


	public HashMap<Integer,node> getVertices(){
		return vertices;
	}

}
