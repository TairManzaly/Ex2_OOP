package api;

import java.io.*;
import java.util.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Directed_Weighted_Graph_AlgorithmsI implements DirectedWeightedGraphAlgorithms {
    public Directed_Weighted_Graph _g;
    public HashMap<Integer, NodeData> _nodes;
    public HashMap<Integer, EdgeData> _edges;
    public HashMap<Integer, HashMap<Integer, EdgeData>> _list;

    @Override
    public void init(DirectedWeightedGraph g) {
        int nlen = g.nodeSize();
        for (int i = 0; i < nlen; i++) {
            _nodes.put(i, g.getNode(i));
            for (int j = 0; j < g.edgeSize(); j++) {
                _edges.put(i, g.getEdge(i, j));
            }
            _list.put(i, _edges);
        }
        _g = new Directed_Weighted_Graph(_nodes, _list);
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return _g;
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
                Iterator<NodeData> transI = trans.nodeIter();
                while (transI.hasNext()) {
                    transI.next().setTag(0);

                }
                NodeData new_c = trans.getNode(k);
                boolean second_pass = DFS(trans, new_c);
                return first_pass && second_pass;

            }

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
        Stack<NodeData> Q = new Stack<>();
        double[] dist = new double[_nodes.size()];
        double[] prev = new double[_nodes.size()];
        Queue<NodeData> S = null;

        Q.add(start);
        for (NodeData v : _nodes.values()
        ) {
            dist[v.getKey()] = Integer.MAX_VALUE;
            prev[v.getKey()] = -1;
            Q.add(v);
        }
        dist[start.getKey()] = 0;

        while (!Q.isEmpty()) {
            NodeData u = Q.pop();
            if (u == dest) break;

            HashMap<Integer, EdgeData> e = _list.get(u);
            for (EdgeData v : e.values()
            ) {
                double alt = dist[u.getKey()] + v.getWeight();
                if (alt < dist[v.getDest()]) {
                    dist[v.getDest()] = alt;
                    prev[v.getDest()] = u.getKey();


                }
            }

        }
        NodeData u = _nodes.get(dest);
        if (prev[u.getKey()] != -1 || dest == start) {
            while (prev[u.getKey()] != -1) {
                S.add(u);
                u = _nodes.get(prev[u.getKey()]);
            }
        }
        return S;
    }
//1  S ← empty sequence
//2  u ← target
//3  if prev[u] is defined or u = source:          // Do something only if the vertex is reachable
//4      while u is defined:                       // Construct the shortest path with a stack S
//5          insert u at the beginning of S        // Push the vertex onto the stack
//6          u ← prev[u]                           // Traverse from target to source

    @Override
    public double shortestPathDist(int src, int dest) {
        Queue<NodeData> s = Dijkstra(_g, _nodes.get(src), _nodes.get(dest));
        double sum = 0;
        for (int i = 0; i < s.size() - 2; i++) {
            NodeData d = s.poll();
            NodeData e = s.poll();
            sum += _g.getEdge(e.getKey(), d.getKey()).getWeight();

        }
        return sum;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> ans = null;
        Queue<NodeData> s = Dijkstra(_g, _nodes.get(src), _nodes.get(dest));
        for (NodeData a : s
        ) {
            ans.add(a);
        }

        return ans;
    }

    @Override
    public NodeData center() {
        if (!isConnected()) {
            return null;
        }
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
        if (!isConnected()) {
            return null;
        }
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
            HashMap<Integer,NodeData> nodeHashMap = new HashMap<>();
            while (iNodes.hasNext()){
                node = (Map) iNodes.next();
                String pos = (String) node.get("pos");

                int id = (int) node.get("id");

                String[] p = pos.split(",");

                double x = Double.parseDouble(p[0]);
                double y = Double.parseDouble(p[1]);
                double z = Double.parseDouble(p[2]);
                NodeData nodeData = new Node_Data(id, x, y, z);
                nodeHashMap.put(id,nodeData);

            }
            Map edge;
            HashMap<Integer, EdgeData> edgeDataHashMap = new HashMap<>();
            HashMap<Integer, HashMap<Integer, EdgeData>> list = new HashMap<>();
            while (iEdges.hasNext()){
                edge = (Map) iEdges.next();
                int src = (int) edge.get("src");

                double w = (double) edge.get("w");

                int dest = (int) edge.get("dest");
                EdgeData edgeData = new Edge_Data(src, w, dest);
                edgeDataHashMap.put(src,edgeData);
                list.put(src,edgeDataHashMap);
            }
            this._g = new Directed_Weighted_Graph(nodeHashMap,list);
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