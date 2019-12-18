package algorithms;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;

public class Graph_Algo implements graph_algorithms{
		private DGraph gr=new DGraph();

	@Override
	public void init(graph g) {
		gr=new DGraph((DGraph) g);
		
	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConnected() {
		Object[] arr = gr.getVertices().keySet().toArray();
		for (int i = 0; i < arr.length; i++) {
			BFS(gr.getVertices().get(arr[i]));
			for (int j = 0; j < arr.length; j++) {
				if (gr.getVertices().get(arr[j]).getTag() != 1) return false;
			}
			setALLzero(gr);
		}
		return true;
	}
		/*Iterator it=gr.getVertices().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry values=(Map.Entry)it.next();
			values.getValue();

		}*/


	public void BFS(node n){
		if(n.getTag()==1){
			return;
		}
		n.setTag(1);
		Object[] arr =n.getEdges().keySet().toArray();
		for(int i=0;i<arr.length;i++) {
			n.getEdges().get(arr[i]).setTag(1);
			if (n.getEdges().get(arr[i]).getDestination() != null) {
				BFS(n.getEdges().get(arr[i]).getDestination());
			}
		}
	}
	public void setALLzero(DGraph g){
		Object [] arr =g.getVertices().keySet().toArray();
		for(int i=0;i<arr.length;i++){
			g.getVertices().get(arr[i]).setTag(0);
		}
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

}
