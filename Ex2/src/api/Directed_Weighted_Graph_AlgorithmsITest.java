package api;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class Directed_Weighted_Graph_AlgorithmsITest {
    HashMap<Integer, NodeData> Nodes= new HashMap<Integer, NodeData>();
    HashMap<Integer, HashMap<Integer, EdgeData>> Edges=new HashMap<Integer, HashMap<Integer, EdgeData>>() ;
    Directed_Weighted_Graph graph = new Directed_Weighted_Graph(Nodes,Edges);
    Directed_Weighted_Graph_AlgorithmsI graph_a = new Directed_Weighted_Graph_AlgorithmsI(null);
    Directed_Weighted_Graph_AlgorithmsI graph_b = new Directed_Weighted_Graph_AlgorithmsI(null);
    Directed_Weighted_Graph_AlgorithmsI graph_c = new Directed_Weighted_Graph_AlgorithmsI(null);

    @Test
    void init() {
        long startTime = System.currentTimeMillis();
        graph_a.init(graph);
        assertEquals(graph_a.getGraph(),graph);
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }

    @Test
    void getGraph() {
        graph_a.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G1.json");
        long startTime = System.currentTimeMillis();
        graph_a.getGraph();
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        graph_b.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G2.json");
        startTime = System.currentTimeMillis();
        graph_b.getGraph();
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        graph_c.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G3.json");
        startTime = System.currentTimeMillis();
        graph_c.getGraph();
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

    }

    @Test
    void copy() {
        graph_a.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G1.json");
        long startTime = System.currentTimeMillis();
        Directed_Weighted_Graph graph2 = (Directed_Weighted_Graph) graph_a.copy();
        graph_a.getGraph().equals(graph2);
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        graph_b.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G2.json");
        startTime = System.currentTimeMillis();
        Directed_Weighted_Graph graph1 = (Directed_Weighted_Graph) graph_b.copy();
        graph_b.getGraph().equals(graph1);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        graph_c.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G3.json");
        Directed_Weighted_Graph graph3 = (Directed_Weighted_Graph) graph_c.copy();
        graph_c.getGraph().equals(graph3);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }

    @Test
    void isConnected() {
        graph_a.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G1.json");
        long startTime = System.currentTimeMillis();
        boolean isConnected0 = graph_a.isConnected();
        assertEquals(true, isConnected0);
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");


        graph_a.getGraph().removeEdge(2, 3);
        startTime = System.currentTimeMillis();
        boolean isConnected1 = graph_a.isConnected();
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        assertEquals(true, isConnected1);

        startTime = System.currentTimeMillis();
        boolean isConnected2 = graph_a.isConnected();
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        assertEquals(true, isConnected2);

        graph_b.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G2.json");
        startTime = System.currentTimeMillis();
        boolean isConnected3 = graph_b.isConnected();
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        assertEquals(true, isConnected3);

        graph_c.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G3.json");
        startTime = System.currentTimeMillis();
        boolean isConnected4 = graph_c.isConnected();
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        assertEquals(true, isConnected4);
    }

    @Test
    void shortestPathDist() {
        graph_a.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G1.json");
        long startTime = System.currentTimeMillis();
        double a = graph_a.shortestPathDist(12, 5);
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        assertEquals(0.0, a);

        startTime = System.currentTimeMillis();
        double b = graph_a.shortestPathDist(2, 16);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        assertEquals(0.0, b);

        startTime = System.currentTimeMillis();
        double c = graph_a.shortestPathDist(3, 8);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        assertEquals(0.0, c);

        graph_b.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G2.json");
        startTime = System.currentTimeMillis();
        double d = graph_b.shortestPathDist(30, 11);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        assertEquals(0.0, d);

        graph_c.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G3.json");
        startTime = System.currentTimeMillis();
        double e = graph_c.shortestPathDist(45, 21);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        assertEquals(0.0,e);
    }

    @Test
    void shortestPath() {

        graph_a.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G1.json");

        long startTime = System.currentTimeMillis();
        List<NodeData> a = graph_a.shortestPath(10, 999);
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        List<NodeData> b = graph_a.shortestPath(600, 51);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        List<NodeData> c = graph_a.shortestPath(320, 893);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        graph_b.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G2.json");
        startTime = System.currentTimeMillis();
        List<NodeData> d = graph_b.shortestPath(320, 7399);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        List<NodeData> e = graph_b.shortestPath(15, 6598);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        graph_c.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G3.json");
        startTime = System.currentTimeMillis();
        List<NodeData> f = graph_c.shortestPath(320, 7399);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }

    @Test
    void center() {
        graph_a.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G1.json");
        long startTime = System.currentTimeMillis();
        Node_Data a = (Node_Data) graph_a.center();
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        System.out.println(a.getKey());

        graph_b.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G2.json");
        startTime = System.currentTimeMillis();
        Node_Data b = (Node_Data) graph_b.center();
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        System.out.println(b.getKey());

        graph_c.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G3.json");
        startTime = System.currentTimeMillis();
        Node_Data d = (Node_Data) graph_c.center();
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        System.out.println(d.getKey());

    }

    @Test
    void tsp() {
        graph_a.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G1.json");
        List<NodeData> a = Arrays.asList(graph_a.getGraph().getNode(14), graph_a.getGraph().getNode(5), graph_a.getGraph().getNode(3), graph_a.getGraph().getNode(10), graph_a.getGraph().getNode(1));
        long startTime = System.currentTimeMillis();
        graph_a.tsp(a);
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        List<NodeData> b = Arrays.asList(graph_a.getGraph().getNode(2), graph_a.getGraph().getNode(4), graph_a.getGraph().getNode(8), graph_a.getGraph().getNode(1), graph_a.getGraph().getNode(12));
        startTime = System.currentTimeMillis();
        graph_a.tsp(b);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        graph_b.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G2.json");
        List<NodeData> c = Arrays.asList(graph_b.getGraph().getNode(15), graph_b.getGraph().getNode(3), graph_b.getGraph().getNode(25), graph_b.getGraph().getNode(21), graph_b.getGraph().getNode(10));
        startTime = System.currentTimeMillis();
        graph_b.tsp(c);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        List<NodeData> d = Arrays.asList(graph_b.getGraph().getNode(1), graph_b.getGraph().getNode(13), graph_b.getGraph().getNode(28), graph_b.getGraph().getNode(9), graph_b.getGraph().getNode(20));
        startTime = System.currentTimeMillis();
        graph_b.tsp(d);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");


        graph_c.load("C:\\Users\\User\\IdeaProjects\\Ex2\\src\\data\\G3.json");
        List<NodeData> f = Arrays.asList(graph_c.getGraph().getNode(1), graph_c.getGraph().getNode(14), graph_c.getGraph().getNode(30), graph_c.getGraph().getNode(19), graph_c.getGraph().getNode(5));
        startTime = System.currentTimeMillis();
        graph_c.tsp(f);
        endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }

}