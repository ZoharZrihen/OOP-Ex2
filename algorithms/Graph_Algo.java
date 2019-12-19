package algorithms;
import java.util.Iterator;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dataStructure.*;

public class Graph_Algo implements graph_algorithms{
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
				for(int j=0;j<a.length;j++){
					graph.removeEdge((int)arr[i],(int)a[j]);
					graph.connect((int)a[j],(int)arr[i],0);
				}
			}
		}
		return graph;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
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
}
