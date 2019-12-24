package algorithms;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

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
				if(gr.getEdges().get(n.getKey()).size()>0){
					Object []arr=gr.getEdges().get(n.getKey()).keySet().toArray();
					for(int i=0;i<arr.length;i++){
						DFS2((node)gr.getVertices().get(arr[i]));
					}
				}
				return;
			}


	}
	public boolean CheckForFlag(DGraph g){
		Object[] arr=g.getVertices().keySet().toArray();
		for(int i=0;i<arr.length;i++){
			if(g.getVertices().get(arr[i]).getTag()==0){
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
			if(graph.getEdges().get(arr[i]).size()>0){
				Object[] a=graph.getEdges().get(arr[i]).keySet().toArray();
				for(int j=0;j<a.length;j++) {
					if(graph.getEdges().get(arr[i]).get(a[j]).getTag()!=1){
						graph.removeEdge((int) arr[i], (int) a[j]);
						if(graph.getEdges().get(a[j]).get(arr[i])!=null){
							graph.getEdges().get(a[j]).get(arr[i]).setTag(1);
						graph.connect((int) arr[i], (int) a[j], 0);
						graph.getEdges().get(arr[i]).get(a[j]).setTag(1);
					}
						else{
							graph.connect((int)a[j],(int)arr[i],0);
							graph.getEdges().get(a[j]).get(arr[i]).setTag(1);
						}
				}
				}
			}
		}
		return graph;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		try {
			setALLzero(gr);
			setALLInfinity(gr);
			PriorityQueue<node> pq = new PriorityQueue<node>(gr.getVertices().size(), new nodeComperator());
			node s = (node) gr.getVertices().get(src);
			s.setTag(1);
			s.setWeight(0);
			Object[] arr=gr.getEdges().get(s.getKey()).keySet().toArray();
			addPq(pq, arr, s);
			while (!pq.isEmpty()) {
				node x = pq.poll();
				Object[] ar=gr.getEdges().get(x.getKey()).keySet().toArray();
				addPq(pq, ar, x);
			}
			return gr.getVertices().get(dest).getWeight();
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Error: Invalid src/dest or there is no path between src and dest");
			return 0;
		}
	}
	public PriorityQueue<node> addPq (PriorityQueue<node> pq,Object[]arr,node n){
		for(int i=0;i<arr.length;i++){
			double w=gr.getEdges().get(n.getKey()).get(arr[i]).getWeight();
			edge e=(edge)gr.getEdges().get(n.getKey()).get(arr[i]);
			e.getDestination().setWeight(Math.min(w+n.getWeight(),e.getDestination().getWeight()));
			pq.add(e.getDestination());
		}
		return pq;
	}
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> ans = new ArrayList<node_data>();
		double PathDis=shortestPathDist(src,dest);
		try{
			ans.add(gr.getVertices().get(src));
			if(src==dest) return ans;
			node n= (node) gr.getVertices().get(src);
			Queue<node_data> temp=new LinkedList<>();
			collectPath(temp,n,dest,PathDis);
			if(temp.size()==1){
				node next=(node)temp.poll();
				ans.add(next);
				while(next.getKey()!=dest){
					collectPath(temp,next,dest,PathDis);
					 next=(node) temp.poll();
					 ans.add(next);
				}
			}
			else{

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ans;
	}
	public void collectPath(Queue temp, node_data n,int dest,double path){
		Collection<edge_data> edges=gr.getEdges().get(n.getKey()).values();
		Iterator iter=edges.iterator();
		while(iter.hasNext()){
			edge e= (edge) iter.next();
			node next=e.getDestination();
			if(shortestPathDist(next.getKey(),dest)+e.getWeight()==path){
				temp.add(next);
			}
		}
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
