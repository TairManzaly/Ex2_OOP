package api;

import api.EdgeData;
import api.NodeData;
import org.junit.Test;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class Directed_Weighted_GraphTest {
    HashMap<Integer, NodeData> Nodes= new HashMap<Integer, NodeData>();
    HashMap<Integer, HashMap<Integer, EdgeData>> Edges=new HashMap<Integer, HashMap<Integer, EdgeData>>() ;
    Directed_Weighted_Graph graph = new Directed_Weighted_Graph(Nodes,Edges);
    Geo_LocationI l1 = new Geo_LocationI(1,2,0);
    Geo_LocationI l2 = new Geo_LocationI(2,1,0);
    Geo_LocationI l3 = new Geo_LocationI(3,4,0);
    Geo_LocationI l4 = new Geo_LocationI(5,6,0);
    Node_Data n1 = new Node_Data(1,3.0,1,0);
    Node_Data n2 = new Node_Data(2,4.0,2,0);
    Node_Data n3 = new Node_Data(3,5.0,3,0);
    Node_Data n4 = new Node_Data(4,6.0,4,0);
    Edge_Data e1 = new Edge_Data(n1.getKey(),1, n2.getKey());
    Edge_Data e2 = new Edge_Data(n4.getKey(),3, n3.getKey());
    Edge_Data e3 = new Edge_Data(n2.getKey(),2, n3.getKey());

    @org.junit.jupiter.api.Test
    void get_nodes() {
    }

    @org.junit.jupiter.api.Test
    void get_list() {
    }

    @org.junit.jupiter.api.Test
    void trans() {
    }

    @org.junit.jupiter.api.Test
    void getNode() {
        graph.addNode(n1);
        graph.getNode(1).equals(n1.getKey());
    }

    @org.junit.jupiter.api.Test
    void getEdge() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(n1.getKey(),n2.getKey(),1);
        graph.connect(n2.getKey(),n3.getKey(),2);
        graph.connect(n4.getKey(),n3.getKey(),3);
        EdgeData e1a = (EdgeData) graph.getEdge(n1.getKey(),n2.getKey());
        EdgeData e2a = (EdgeData) graph.getEdge(n4.getKey(),n3.getKey());
        EdgeData e3a = (EdgeData) graph.getEdge(n2.getKey(),n3.getKey());
        e1.equals(e1a);
        e2.equals(e2a);
        e3.equals(e3a);
    }

    @org.junit.jupiter.api.Test
    void addNode() {
        graph.addNode(n1);
        graph.getNode(1).equals(n1.getKey());
    }

    @org.junit.jupiter.api.Test
    void connect() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(n1.getKey(),n2.getKey(),1);
        graph.connect(n2.getKey(),n3.getKey(),2);
        graph.connect(n4.getKey(),n3.getKey(),3);
        EdgeData e1a = (EdgeData) graph.getEdge(n1.getKey(),n2.getKey());
        EdgeData e2a = (EdgeData) graph.getEdge(n4.getKey(),n3.getKey());
        EdgeData e3a = (EdgeData) graph.getEdge(n2.getKey(),n3.getKey());
        e1.equals(e1a);
        e2.equals(e2a);
        e3.equals(e3a);
    }

    @org.junit.jupiter.api.Test
    void nodeIter() {
        Iterator<NodeData> iterator = graph.nodeIter();
        int i =0;
        if(graph.get_nodes()!=null){
            while (iterator.hasNext()){
                NodeData curr = iterator.next();
                Node_Data n = (Node_Data) graph.get_nodes().get(i);
                System.out.println(n);
                assertEquals(curr.getKey(),n.getKey());
                assertEquals(curr.getInfo(),n.getInfo());
                assertEquals(curr.getWeight(),n.getWeight());
                assertEquals(curr.getTag(),n.getTag());
                i++;
            }
        }
    }

    @org.junit.jupiter.api.Test
    void edgeIter() {
        Iterator<EdgeData> iterator = graph.edgeIter();
        int i =0;
        if(graph.get_list()!=null){
            while (iterator.hasNext()){
                EdgeData curr = iterator.next();
                assertEquals(curr.getSrc(),graph.get_list().get(i).get(curr.getDest()).getSrc());
                assertEquals(curr.getDest(),graph.get_list().get(i).get(curr.getDest()).getDest());
                assertEquals(curr.getInfo(),graph.get_list().get(i).get(curr.getDest()).getInfo());
                assertEquals(curr.getWeight(),graph.get_list().get(i).get(curr.getDest()).getWeight());
                assertEquals(curr.getTag(),graph.get_list().get(i).get(curr.getDest()).getTag());
                i++;
            }
        }
    }

    @org.junit.jupiter.api.Test
    void edgeIter2() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.connect(1, 2, 6);
        Iterator<EdgeData> iterator = graph.edgeIter(1);
        while (iterator.hasNext()) {
            EdgeData e = iterator.next();
            assertEquals(e.getSrc(), 1);
            assertEquals(e.getDest(), graph.getEdge(1, e.getDest()).getDest());
            assertEquals(e.getInfo(), graph.getEdge(e.getSrc(),e.getDest()).getInfo());
            assertEquals(e.getWeight(), graph.getEdge(e.getSrc(),e.getDest()).getWeight());
            assertEquals(e.getTag(), graph.getEdge(e.getSrc(),e.getDest()).getTag());
        }
    }

    @org.junit.jupiter.api.Test
    void removeNode() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(n1.getKey(),n2.getKey(),1);
        graph.connect(n2.getKey(),n3.getKey(),2);
        graph.connect(n4.getKey(),n3.getKey(),3);
        graph.removeNode(2);
        graph.removeNode(4);
        assertNull(graph.getNode(2));
        assertNull(graph.getNode(4));
    }

    @org.junit.jupiter.api.Test
    void removeEdge() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(n1.getKey(),n2.getKey(),4);
        graph.connect(n2.getKey(),n3.getKey(),3);
        graph.connect(n4.getKey(),n3.getKey(),6);
        graph.removeEdge(1,2);
        graph.removeEdge(4,3);
        assertNull(graph.getEdge(1,2));
        assertNull(graph.getEdge(4,3));
    }

    @org.junit.jupiter.api.Test
    void nodeSize() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        assertEquals(graph.nodeSize(),4);
    }

    @org.junit.jupiter.api.Test
    void edgeSize() {
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(n1.getKey(),n2.getKey(),4);
        graph.connect(n2.getKey(),n3.getKey(),3);
        graph.connect(n4.getKey(),n3.getKey(),6);
        assertEquals(graph.edgeSize(),3);
    }

    @org.junit.jupiter.api.Test
    void getMC() {
        Directed_Weighted_Graph graph = new Directed_Weighted_Graph(Nodes,Edges);
        assertEquals(graph.getMC(),0);
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(n1.getKey(),n2.getKey(),4);
        graph.connect(n2.getKey(),n3.getKey(),3);
        graph.connect(n4.getKey(),n3.getKey(),6);
        graph.removeEdge(1,2);
        assertEquals(graph.getMC(),8);
    }
}