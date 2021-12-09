package api;

import java.util.Arrays;
import java.util.List;

public class Directed_Weighted_Graph_AlgorithmsI implements DirectedWeightedGraphAlgorithms {
    public DirectedWeightedGraph _g;

    @Override
    public void init(DirectedWeightedGraph g) {
        this._g = new Directed_Weighted_Graph(g.get_nodes(), g.get_list());
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return _g;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph g = new Directed_Weighted_Graph(_g.get_nodes(), _g.get_list());
        return g;
    }

    @Override
    public boolean isConnected() {
        int n = _g.nodeSize();

        boolean[] vis1 = new boolean[n];
        boolean[] vis2 = new boolean[n];
        Arrays.fill(vis1, false);
        Arrays.fill(vis2, false);
        for (int i = 0; i < n; i++) {

        }
        // Call for correct direction

        return false;

    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
