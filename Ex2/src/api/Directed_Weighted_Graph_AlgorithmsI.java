package api;

import java.util.*;

public class Directed_Weighted_Graph_AlgorithmsI implements DirectedWeightedGraphAlgorithms {
    public Directed_Weighted_Graph _g;
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
        DirectedWeightedGraph g = new Directed_Weighted_Graph(_nodes,_list);
        return g;
    }

//    private static void DFS(DirectedWeightedGraph g, int v, boolean[] visited) {
//        // mark current node as visited
//        visited[v] = true;
//
//        // do for every edge (v, u)
//
//        for (int u = 0; u < v; u++) {
//            // `u` is not visited
//            if (!visited[u]) {
//                DFS(g, u, visited);
//            }
//        }
//    }

    private boolean DFS(DirectedWeightedGraph g, NodeData start) {
        Stack<NodeData> not_visited = new Stack<>();
        not_visited.add(start);
        while (!not_visited.isEmpty()){
            NodeData current = not_visited.pop();
            current.setTag(1);
            if (g.edgeIter(current.getKey())!= null){
                Iterator<EdgeData> edgeI = g.edgeIter(current.getKey());
                try{
                    while (edgeI.hasNext()) {
                        NodeData n = (NodeData) g.getNode(edgeI.next().getDest());
                        if(n.getTag() != 1){
                            not_visited.add(n);
                        }
                    }
                }
                catch (ConcurrentModificationException e){
                    throw new RuntimeException();
                }
            }
        }
        boolean ans = true;
        Iterator <NodeData> nodeI = g.nodeIter();
        try{
            while (nodeI.hasNext()){
                NodeData current = (NodeData) nodeI.next();
                ans = ans && (current.getTag()==1);
            }
        }
        catch (ConcurrentModificationException e){
            throw new RuntimeException();
        }
        return ans;
    }

    public DirectedWeightedGraph trans() {
        DirectedWeightedGraph trans = new Directed_Weighted_Graph(_g.get_nodes(), _g.get_list());
//        trans.
        return trans;
    }

//    @Override
//    public boolean isConnected() {
//        int n = _g.nodeSize();
//
//        for (int i = 0; i < n; i++) {
//            // to keep track of whether a vertex is visited or not
//            boolean[] visited = new boolean[n];
//
//            // start DFS from the first vertex
//            DFS(_g, i, visited);
//
//            // If DFS traversal doesn't visit all vertices,
//            // then the graph is not strongly connected
//            for (boolean b : visited) {
//                if (!b) {
//                    return false;
//                }
//            }
//        }

        @Override
        public boolean isConnected() {
        Iterator<NodeData> nodeI = _g.nodeIter();
        try{
            if (nodeI.hasNext()){
                NodeData current = (NodeData) nodeI.next();
                int k = current.getKey();
                boolean first_pass = DFS((DirectedWeightedGraph)_g, current);
                DirectedWeightedGraph trans = (((DirectedWeightedGraph)_g).trans);
                Iterator<NodeData> transI = trans.nodeIter();
                while (transI.hasNext()){
                    transI.next().setTag(0);
                }
                NodeData new_c = (NodeData)trans.getNode(k);
            }
        }
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