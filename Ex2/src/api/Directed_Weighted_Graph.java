package api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Directed_Weighted_Graph implements DirectedWeightedGraph {
    public HashMap<Integer, NodeData> _nodes;
    public HashMap<Integer, HashMap<Integer, EdgeData>> _list;

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
    }

    @Override
    public void connect(int src, int dest, double w) {
        EdgeData e = new Edge_Data(src, w, dest);
        _list.get(src).put(dest, e);
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        NodeData n = _nodes.get(key);
        if (_nodes.containsKey(key)){ _nodes.remove(key); }
        if (_list.containsKey(key)){ _list.remove(key); }
        Object[] keys = new Object[_list.size()];
        keys = _list.keySet().toArray();
        for (int i=0; i<_list.size(); i++){
            if (_list.get(keys[i]).containsKey(key)){
                _list.get((keys[i])).remove(key);
            }
        }
        return n;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData e = _list.get(src).get(dest);
        if (_list.get(src).containsKey(dest)){ _list.get(src).remove(dest); }
        return e;
    }

    @Override
    public int nodeSize() {
        return _nodes.size();
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
    }
}