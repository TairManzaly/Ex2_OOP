package api;

import java.util.HashMap;

public class Node_Data implements NodeData {
    public HashMap<Integer, String> nodes = new HashMap<>();
    public int ID;
    public double x;
    public double y;
    public double z;
    public double weight;
    public int tag =0;
    public String data;

    public Node_Data(int ID, double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.ID = ID;

    }


    @Override
    public int getKey() {
        return this.ID;
    }

    @Override
    public GeoLocation getLocation() {
        GeoLocation g = new Geo_LocationI(this.x, this.y, this.z);
        return g;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.x = p.x();
        this.y = p.y();
        this.z = p.z();


    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;

    }

    @Override
    public String getInfo() {
        return this.data;
    }

    @Override
    public void setInfo(String s) {
        this.data = s;

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;

    }
}
