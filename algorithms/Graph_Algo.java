package algorithms;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import dataStructure.*;

public class Graph_Algo implements graph_algorithms, Serializable {
		private DGraph gr=new DGraph();

	@Override
	public void init(graph g) {
		gr=new DGraph((DGraph) g);
	}

	@Override
	public void init(String file_name) {
		deserialize(file_name);
	}

	@Override
	public void save(String file_name) {
		serialize(file_name);
	}

	@Override
	public boolean isConnected() {
		Object[] arr = gr.getVertices().keySet().toArray();
			DFS(((node)gr.getVertices().get(arr[0])));
			 if(CheckForFlag(gr)==false){
			 	return false;
			 }
			 setALLzero(gr);
			 DGraph g1=Transpose(gr);
		Object[] arr1 = g1.getVertices().keySet().toArray();
		DFS(((node)g1.getVertices().get(arr[0])));
		if(CheckForFlag(g1)==false){
			return false;
		}
		return true;
	}
	public void DFS (node n){
		setALLzero(gr);
		DFS2(n);

		}


	public void DFS2( node n) {
			if(n.getTag()!=1){
				n.setTag(1);
				if(n.getEdges().size()>0){
					Object []arr=n.getEdges().keySet().toArray();
					for(int i=0;i<arr.length;i++) {
						DFS2(((edge) n.getEdges().get(arr[i])).getDestination());
					}
				}
				return;

			}


	}
	public boolean CheckForFlag(DGraph g){
		Object[] arr=g.getVertices().keySet().toArray();
		for(int i=0;i<arr.length;i++){
			if(((node)g.getVertices().get(arr[i])).getTag()==0){
				return false;
			}
		}
		return true;
	}


	public void setALLzero(DGraph g){
		Object [] arr =g.getVertices().keySet().toArray();
		for(int i=0;i<arr.length;i++){
			g.getVertices().get(arr[i]).setTag(0);
		}
	}

	public DGraph Transpose(DGraph g){
		DGraph graph =new DGraph(g);
		Object [] arr=graph.getVertices().keySet().toArray();
		for(int i=0;i<arr.length;i++){
			if(((node)graph.getVertices().get(arr[i])).getEdges()!=null){
				Object [] a=((node)graph.getVertices().get(arr[i])).getEdges().keySet().toArray();
				for(int j=0;j<a.length;j++) {
					if (((node) graph.getVertices().get(arr[i])).getEdges().get(a[j]).getTag() != 1) {
						graph.removeEdge((int) arr[i], (int) a[j]);
						if (((node) graph.getVertices().get(a[j])).getEdges().get(arr[i]) != null){
							((node) graph.getVertices().get(a[j])).getEdges().get(arr[i]).setTag(1);
						graph.connect((int) arr[i], (int) a[j], 0);
						((node) graph.getVertices().get(arr[i])).getEdges().get(a[j]).setTag(1);
						//graph.connect((int) a[j], (int) arr[i], 0);
					}
						else{
							graph.connect((int)a[j],(int)arr[i],0);
							((node) graph.getVertices().get(a[j])).getEdges().get(arr[i]).setTag(1);
						}
				}
				}
			}
		}
		return graph;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		setALLzero(gr);
		setALLInfinity(gr);
		PriorityQueue<node> pq=new PriorityQueue<node>(gr.getVertices().size(),new nodeComperator());
		node s=(node)gr.getVertices().get(src);
		s.setTag(1);
		s.setWeight(0);
		Object[]arr=s.getEdges().keySet().toArray();
//		for(int i=0;i<arr.length;i++){
//			double w=s.getEdges().get(arr[i]).getWeight();
//			edge e=((edge)s.getEdges().get(arr[i]));
//			e.getDestination().setWeight(Math.min(w,e.getDestination().getWeight()));
//			pq.add(e.getDestination());
//		}
		addPq(pq,arr,s);
		while(!pq.isEmpty()){
			node x=pq.poll();
			Object[]ar=x.getEdges().keySet().toArray();
			addPq(pq,ar,x);
		}
		return gr.getVertices().get(dest).getWeight();
	}
	public PriorityQueue<node> addPq (PriorityQueue<node> pq,Object[]arr,node n){
		for(int i=0;i<arr.length;i++){
			double w=n.getEdges().get(arr[i]).getWeight();
			edge e=((edge)n.getEdges().get(arr[i]));
			e.getDestination().setWeight(Math.min(w+n.getWeight(),e.getDestination().getWeight()));
			pq.add(e.getDestination());
		}
		return pq;
	}
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		DGraph g=new DGraph(this.gr);
		return g;


	}
	private void serialize(String file_name){
		try {
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(getGr());
			out.close();
			file.close();
		} catch(IOException ex) {
			System.out.println("Error: can't save this graph to file, check again.");
		}
	}
	private  void deserialize(String file_name) {
		gr=new DGraph();
		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);
			gr = (DGraph) in.readObject();
			in.close();
			file.close();
		}
		catch(IOException ex) {
			System.out.println("Error: can't init this graph from file, check again.");
		}
		catch(ClassNotFoundException ex) {
		}
	}
	public DGraph getGr(){
	    return gr;
    }
	public void setALLInfinity(DGraph g){
		Object [] arr =g.getVertices().keySet().toArray();
		for(int i=0;i<arr.length;i++){
			g.getVertices().get(arr[i]).setWeight(Double.MAX_VALUE);
		}
	}
}
