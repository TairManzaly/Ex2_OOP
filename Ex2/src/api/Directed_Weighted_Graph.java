package api;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Directed_Weighted_Graph implements DirectedWeightedGraph {
    public HashMap<Integer, NodeData> _nodes;
    public HashMap<Integer, HashMap<Integer, EdgeData>> _list;
    public int MC = 0;
    public int _edges_size = 0;


    public Directed_Weighted_Graph(HashMap<Integer, NodeData> n, HashMap<Integer, HashMap<Integer, EdgeData>> e) {
        this._nodes = n;
        this._list = e;
        Object[] keys = new Object[_list.size()];
        keys = _list.keySet().toArray();
        for (int i = 0; i < _list.size(); i++) {
            _edges_size += _list.get(keys[i]).size();
        }
    }

    public HashMap<Integer, NodeData> get_nodes() {
        return _nodes;
    }

    public HashMap<Integer, HashMap<Integer, EdgeData>> get_list() {
        return _list;
    }

    public DirectedWeightedGraph trans() {
        DirectedWeightedGraph trans = new Directed_Weighted_Graph(this._nodes,this._list);
        for (int i = 0; i < _nodes.size(); i++) {
            for (int j = 0; j < _nodes.size(); j++) {
                removeEdge(i,j);
                removeEdge(j,i);
            }
        }
        Iterator<EdgeData> edgeIter = this.edgeIter();
        while (edgeIter.hasNext()){
            EdgeData curr = edgeIter.next();
            int src = curr.getSrc();
            int dest = curr.getDest();
            double w = curr.getWeight();
            trans.connect(dest,src,w);
        }
//        trans.
        return trans;
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
        if(_list.get(src)!=null){
            _list.get(src).put(dest, e);
        }
        else{
            HashMap<Integer, EdgeData> ed = new HashMap<Integer, EdgeData>();
            ed.put(dest, e);
            _list.put(src,ed);
        }
        MC++;
        _edges_size++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        Iterator<NodeData> nodes = _nodes.values().iterator();
        return nodes;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        ArrayList<EdgeData> _edges = new ArrayList<EdgeData>();
        Object[] keys = new Object[_list.size()];
        keys = _list.keySet().toArray();
        ArrayList<Object[]> k = new ArrayList<Object[]>();
        for (int i = 0; i < _list.size(); i++) {
            Object[] e = new Object[_list.get(keys[i]).size()];
            e = _list.get(keys[i]).keySet().toArray();
            k.add(e);
        }
        for (int i = 0; i < keys.length; i++) {
            for (int j = 0; j < k.get(i).length; j++) {
                _edges.add(_list.get(keys[i]).get(k.get(i)[j]));
            }
        }
        Iterator<EdgeData> edge = _edges.iterator();
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
                _edges_size--;
                MC++;
            }
        }
        return n;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData e = _list.get(src).get(dest);
        if (_list.get(src).containsKey(dest)) {
            _list.get(src).remove(dest);
            _edges_size--;
            MC++;
        }
        return e;
    }

    @Override
    public int nodeSize() {
        return _nodes.size();
    }

    @Override
    public int edgeSize() {
        return _edges_size;
    }

    @Override
    public int getMC() {
        return MC;
    }

}