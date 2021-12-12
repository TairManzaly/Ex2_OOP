package api;

import java.util.HashMap;
import java.util.Iterator;

public class Directed_Weighted_Graph implements DirectedWeightedGraph {
    public HashMap<Integer, NodeData> _nodes;
    public HashMap<Integer, EdgeData> _edges;
    public HashMap<Integer, HashMap<Integer, EdgeData>> _list;
    public int MC = 0;


    public Directed_Weighted_Graph(HashMap<Integer, NodeData> n, HashMap<Integer, HashMap<Integer, EdgeData>> e) {
        this._nodes = n;
        this._list = e;


    }
    public HashMap<Integer, NodeData> get_nodes() {
        return _nodes;
    }
    public HashMap<Integer, HashMap<Integer, EdgeData>> get_list() {
        return _list;
    }

    @Override
    public NodeData getNode(int key) {
        return this._nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return this._list.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        _nodes.put(n.getKey(), n);
        MC++;

    }

    @Override
    public void connect(int src, int dest, double w) {
        EdgeData e = new Edge_Data(src, w, dest);
        _list.get(src).put(dest, e);
        MC++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        Iterator<NodeData> nodes = _nodes.values().iterator();
        return nodes;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        Iterator<EdgeData> edge = _edges.values().iterator();
        return edge;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        Iterator<EdgeData> edge = _list.get(node_id).values().iterator();
        return edge;
    }

    @Override
    public NodeData removeNode(int key) {
        NodeData n = _nodes.get(key);
        if (_nodes.containsKey(key)) {
            _nodes.remove(key);
        }
        if (_list.containsKey(key)) {
            _list.remove(key);
        }
        Object[] keys = new Object[_list.size()];
        keys = _list.keySet().toArray();
        for (int i = 0; i < _list.size(); i++) {
            if (_list.get(keys[i]).containsKey(key)) {
                _list.get((keys[i])).remove(key);
            }
        }
        MC++;
        return n;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData e = _list.get(src).get(dest);
        if (_list.get(src).containsKey(dest)) {
            _list.get(src).remove(dest);
        }
        MC++;
        return e;
    }

    @Override
    public int nodeSize() {
        return _nodes.size();
    }

    @Override
    public int edgeSize() {
        return _edges.size();
    }

    @Override
    public int getMC() {
        return MC;
    }
}