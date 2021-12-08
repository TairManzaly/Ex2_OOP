package api;

import java.util.HashMap;

public class Edge_Data implements EdgeData{
    public int _src;
    public double _weight;
    public int _dest;
    public String _info;
    public int _tag;
    
    public Edge_Data(int src, double weight, int dest){
        this._src = src;
        this._weight = weight;
        this._dest = dest;
    }
    
    @Override
    public int getSrc() {
        return this._src;
    }

    @Override
    public int getDest() {
        return this._dest;
    }

    @Override
    public double getWeight() {
        return this._weight;
    }

    @Override
    public String getInfo() {
        return this._info;
    }

    @Override
    public void setInfo(String s) {
        this._info = s;
    }

    @Override
    public int getTag() {
        return this._tag;
    }

    @Override
    public void setTag(int t) {
        this._tag = t;
    }
}
