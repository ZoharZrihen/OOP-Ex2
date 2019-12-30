package gui;

import dataStructure.DGraph;
import dataStructure.edge;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import utils.Point3D;
import utils.Range;
import utils.StdDraw;
import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;

public class Graph_Gui {
    private graph gr;

    public static void main(String[] args) {
        Graph_Gui gg = new Graph_Gui();
        //gg.DrawGraph(1000, 600, new Range(-10, 60), new Range(-10, 60),gg.getGr());
        DGraph g=new DGraph();
        g.addNode(new node(1,new Point3D(20,20,0),0));
        g.addNode(new node(2,new Point3D(30,30,0),0));
        g.connect(1,2,5);
        g.connect(2,1,3);
        Graph_Gui g1=new Graph_Gui(g);
        DGraph g11=new DGraph();
        Graph_Gui gg2=new Graph_Gui(g11);
        gg2.DrawGraph(1000, 600, new Range(-10, 60), new Range(-10, 60),gg2.getGr());
    }

    public Graph_Gui() {
        gr = GraphFactory();

    }

    public Graph_Gui(graph g) {
        gr = new DGraph((DGraph) g);
    }

    public void DrawGraph(int w, int h, Range rx, Range ry, DGraph g) {
        StdDraw.setgraph(g);
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        Collection<node_data> nodes = gr.getV();
        Iterator iter = nodes.iterator();
        while (iter.hasNext()) {
            node n = (node) iter.next();
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.02);
            StdDraw.point(n.getLocation().x(), n.getLocation().y());
            int key = n.getKey();
            StdDraw.text(n.getLocation().x() - 1, n.getLocation().y() + 1, Integer.toString(key));
            Collection<edge_data> edges = gr.getE(n.getKey());
            Iterator iterE = edges.iterator();
            while (iterE.hasNext()) {
                edge e = (edge) iterE.next();
                StdDraw.setPenColor(Color.RED);
                StdDraw.setPenRadius(0.007);
                Point3D p1 = gr.getNode(e.getSrc()).getLocation();
                Point3D p2 = gr.getNode(e.getDest()).getLocation();
                StdDraw.line(p1.x(), p1.y(), p2.x(), p2.y());
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.text((p1.x() + p2.x()) / 2, ((p1.y() + p2.y()) / 2) + 1.5, Double.toString(e.getWeight()));
                StdDraw.setPenColor(Color.YELLOW);
                StdDraw.setPenRadius(0.02);
                StdDraw.point(p1.x() + 0.85 * (p2.x() - p1.x()), p1.y() + 0.85 * (p2.y() - p1.y()));

            }
        }
        StdDraw.save("MyGraph.jpg");
    }

    public DGraph GraphFactory() {
        DGraph gr = new DGraph();
        for (int i = 1; i < 11; i++) {
            gr.addNode(new node(i, new Point3D((int) (Math.random() * 50) + 1, (int) (Math.random() * 50) + 1, 0), 0));
        }
        for (int i = 1; i < 9; i++) {
            gr.connect(i, i + 1, (int) (Math.random() * 20) + 1);
            gr.connect(i, i + 2, (int) (Math.random() * 20) + 1);
        }
        return gr;
    }

    public DGraph getGr(){
        return (DGraph) gr;
    }
}
