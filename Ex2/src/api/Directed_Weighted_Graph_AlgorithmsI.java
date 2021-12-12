package api;

import java.util.HashMap;
import java.util.List;

public class Directed_Weighted_Graph_AlgorithmsI implements DirectedWeightedGraphAlgorithms {
    public DirectedWeightedGraph _g;
    public HashMap<Integer, NodeData> _nodes;
    public HashMap<Integer, EdgeData> _edges;
    public HashMap<Integer, HashMap<Integer, EdgeData>> _list;

    @Override
    public void init(DirectedWeightedGraph g) {

        int nlen = g.nodeSize();
        for (int i = 0; i < nlen; i++) {
           _nodes.put(i,g.getNode(i));
            for (int j = 0; j < g.edgeSize(); j++) {
                _edges.put(i,g.getEdge(i,j));
            }
            _list.put(i,_edges);


        }
        _g = new Directed_Weighted_Graph(_nodes,_list);
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return _g;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph g = _g;
        return g;
    }

    private static void DFS(DirectedWeightedGraph g, int v, boolean[] visited) {
        // mark current node as visited
        visited[v] = true;

        // do for every edge (v, u)

        for (int u = 0; u < v; u++) {
            // `u` is not visited
            if (!visited[u]) {
                DFS(g, u, visited);
            }
        }
    }

    @Override
    public boolean isConnected() {
        int n = _g.nodeSize();

        for (int i = 0; i < n; i++) {
            // to keep track of whether a vertex is visited or not
            boolean[] visited = new boolean[n];

            // start DFS from the first vertex
            DFS(_g, i, visited);

            // If DFS traversal doesn't visit all vertices,
            // then the graph is not strongly connected
            for (boolean b : visited) {
                if (!b) {
                    return false;
                }
            }
        }

        return true;

    }

    @Override
    public double shortestPathDist(int src, int dest) {
//        NodeData srcd = _g.getNode(src);
//        NodeData destd = _g.getNode(dest);
        double minsum = 0, sum = 0;

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
