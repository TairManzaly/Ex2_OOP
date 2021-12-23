package api;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Directed_Weighted_Graph_AlgorithmsI implements DirectedWeightedGraphAlgorithms {
    public Directed_Weighted_Graph _g;
    public HashMap<Integer, NodeData> _nodes ;
    public HashMap<Integer, EdgeData> _edges;
    public HashMap<Integer, HashMap<Integer, EdgeData>> _list = new HashMap<>();
public Directed_Weighted_Graph_AlgorithmsI(){
        this._g = new Directed_Weighted_Graph(_nodes,_list);
    }
    public Directed_Weighted_Graph_AlgorithmsI(Directed_Weighted_Graph g){
        this._g = g;
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this._g = (Directed_Weighted_Graph) g;
    }


    @Override
    public DirectedWeightedGraph getGraph() {
        return this._g;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph g = new Directed_Weighted_Graph(_nodes, _list);
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
        while (!not_visited.isEmpty()) {
            NodeData current = not_visited.pop();
            current.setTag(1);
            if (g.edgeIter(current.getKey()) != null) {
                Iterator<EdgeData> edgeI = g.edgeIter(current.getKey());
                try {
                    while (edgeI.hasNext()) {
                        NodeData n = (NodeData) g.getNode(edgeI.next().getDest());
                        if (n.getTag() != 1) {
                            not_visited.add(n);
                        }
                    }
                } catch (ConcurrentModificationException e) {
                    throw new RuntimeException();
                }
            }
        }
        boolean ans = true;
        Iterator<NodeData> nodeI = g.nodeIter();
        try {
            while (nodeI.hasNext()) {
                NodeData current = (NodeData) nodeI.next();
                ans = ans && (current.getTag() == 1);
            }
        } catch (ConcurrentModificationException e) {
            throw new RuntimeException();
        }
        return ans;
    }


    @Override
    public boolean isConnected() {
        Iterator<NodeData> nodeI = _g.nodeIter();
        try {
            if (nodeI.hasNext()) {
                NodeData current = (NodeData) nodeI.next();
                int k = current.getKey();
                boolean first_pass = DFS((DirectedWeightedGraph) _g, current);
                DirectedWeightedGraph trans = (Directed_Weighted_Graph) _g.trans();
//                Iterator<NodeData> transI = trans.nodeIter();
//                while (transI.hasNext()) {
//                    transI.next().setTag(0);
                return first_pass;
            }
//                NodeData new_c = trans.getNode(k);
//                boolean second_pass = DFS(trans, new_c);




        } catch (ConcurrentModificationException e) {
            throw new RuntimeException();
        }
        Iterator<NodeData> nodeIt = _g.nodeIter();
        boolean ans = true;
        try {
            while (nodeIt.hasNext()) {
                NodeData curr = nodeIt.next();
                ans = ans && (curr.getTag() == 1);
            }
        } catch (ConcurrentModificationException e) {
            throw new RuntimeException();
        }
        return ans;

    }


    private Queue<NodeData> Dijkstra(DirectedWeightedGraph g, NodeData start, NodeData dest) {
        try {
            Queue<Integer> Q = new PriorityQueue<>();

            double[] dist = new double[g.nodeSize()];
            double[] prev = new double[g.nodeSize()];
            Queue<NodeData> S = new PriorityQueue<>();

            Q.add(start.getKey());
            for (int i = 0; i <g.nodeSize() ; i++) {
                dist[i] = Integer.MAX_VALUE;
                prev[i] = -1;
                Q.add(g.nodeIter().next().getKey());


            }

            dist[start.getKey()] = 0;
            double Weight = 0;
            int u =0;
            while (!Q.isEmpty()) {
                u = Q.poll();
                for (int v = 0; v < _g.nodeSize(); v++) {
                    if (_g.getEdge(u, v) == null || Q.contains(v) == false) {
                        continue;
                    }
                    Weight = dist[u] + _g.getEdge(u, v).getWeight();
                    if (Weight < dist[v]) {
                        dist[v] = Weight;
                        prev[v] = u;


                    }
                }

            }
            NodeData i = _g.getNode(dest.getKey());
            if (prev[i.getKey()] != -1 || dest == start) {
                while (prev[i.getKey()] != -1) {
                    S.add(i);
                    i = _g.getNode((int) prev[i.getKey()]);
                }
            }
            return S;
        } catch (RuntimeException e) {
            return null;
        }
    }
//1  S ← empty sequence
//2  u ← target
//3  if prev[u] is defined or u = source:          // Do something only if the vertex is reachable
//4      while u is defined:                       // Construct the shortest path with a stack S
//5          insert u at the beginning of S        // Push the vertex onto the stack
//6          u ← prev[u]                           // Traverse from target to source

    @Override
    public double shortestPathDist(int src, int dest) {
        try {
            Queue<NodeData> s = Dijkstra(_g, _g.getNode(src),_g.getNode(dest));

            double sum = 0;
            for (int i = 0; i < s.size() - 2; i++) {
                NodeData d = s.poll();
                NodeData e = s.poll();
                sum += _g.getEdge(e.getKey(), d.getKey()).getWeight();

            }
            return sum;
        } catch (RuntimeException e) {
            return -1;
        }
    }
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        try {
            ArrayList<NodeData> ans = new ArrayList<>();

            Queue<NodeData> s = new PriorityQueue<>();
            s = Dijkstra(_g, _g.getNode(src), _g.getNode(dest));
            if (s !=null) {
                for (NodeData a : s
                ) {
                    ans.add(a);
                }
            }
            return ans;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public NodeData center() {
//        if (!isConnected()) {
//            return null;
//        }
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        NodeData ans = null;
        for (int i = 0; i < _g.nodeSize(); i++) {
            for (int j = 0; j < _g.nodeSize(); j++) {
                if (j != i) {
                    double temp = shortestPathDist(j, i);
                    if (temp > max) {
                        max = temp;
                    }
                }
            }
            double center = max;
            if (center < min) {
                min = center;
                ans = _g.getNode(i);
            }

        }
        return ans;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
//        if (!isConnected()) {
//            return null;
//        }
        List<Integer> citiesdata = new ArrayList<>();
        List<NodeData> result = new ArrayList<>();
        int same, place;
        for (int i = 0; i < cities.size(); i++) {
            citiesdata.add(cities.get(i).getKey());
        }
        List<NodeData> list = new ArrayList<>();
        NodeData temp = (NodeData) cities.get(0);
        result.add(_g.getNode(citiesdata.get(0)));
        citiesdata.remove(0);
        while (citiesdata.size() >= 1) {
            double min = Double.MAX_VALUE;
            same = -1;
            place = -1;
            for (int i = 0; i < citiesdata.size(); i++) {
                int open = citiesdata.get(i);
                if (shortestPathDist(temp.getKey(), open) < min) {
                    min = shortestPathDist(temp.getKey(), open);
                    same = open;
                    place = i;
                }
            }

            list = shortestPath(temp.getKey(), same);
            while (list.size() >= 1) {
                if (!(result.contains(list.get(0)))) {
                    result.add(list.get(0));
                    ;
                }
                list.remove(0);
            }

            int q = citiesdata.get(place);
            temp = (NodeData) _g.getNode(q);
            citiesdata.remove(citiesdata.get(place));
            if (citiesdata.size() == 1 && !result.contains(_g.getNode(same + 1)))
                result.add(_g.getNode(same + 1));
        }
        return result;

    }


    @Override
    public boolean save(String file) {
        try {
            FileWriter json_file = new FileWriter(file);
            BufferedWriter a = new BufferedWriter(json_file);
            a.write(this._g.toString());
            a.close();
            json_file.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public boolean load(String file) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(file)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject js_obj = (JSONObject) obj;

            JSONArray nodes = (JSONArray) js_obj.get("Nodes");
            JSONArray edges = (JSONArray) js_obj.get("Edges");
            Iterator iEdges = edges.iterator();
            Iterator iNodes = nodes.iterator();
            Map node;
            HashMap<Integer, NodeData> nodeHashMap = new HashMap<>();
            while (iNodes.hasNext()) {
                node = (Map) iNodes.next();
                String pos = (String) node.get("pos");

                int id = Integer.parseInt(Objects.toString(node.get("id")));

                String[] p = pos.split(",");

                double x = Double.parseDouble(p[0]);
                double y = Double.parseDouble(p[1]);
                double z = Double.parseDouble(p[2]);
                NodeData nodeData = new Node_Data(id, x, y, z);
                nodeHashMap.put(id, nodeData);

            }
            Map edge;
            HashMap<Integer, EdgeData> edgeDataHashMap = new HashMap<>();
            HashMap<Integer, HashMap<Integer, EdgeData>> list = new HashMap<>();
            while (iEdges.hasNext()) {
                edge = (Map) iEdges.next();
                int src = Integer.parseInt(Objects.toString(edge.get("src")));

                double w = (double) edge.get("w");

                int dest = Integer.parseInt(Objects.toString(edge.get("dest")));
                EdgeData edgeData = new Edge_Data(src, w, dest);
                ((Node_Data) nodeHashMap.get(src)).addToList(edgeData);
                //list.get(src).put(src,edgeData);
                if(list.get(src)!=null){
                    list.get(src).put(dest, edgeData);
                }
                else{
                    HashMap<Integer, EdgeData> ed = new HashMap<Integer, EdgeData>();
                    ed.put(dest, edgeData);
                    list.put(src,ed);
                }
            }
            this._g = new Directed_Weighted_Graph(nodeHashMap, list);
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

}
