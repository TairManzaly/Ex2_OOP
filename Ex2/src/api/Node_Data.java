package api;

public class Node_Data implements NodeData {
    int ID;
    double x;
    double y;
    double z;
    double weight;
    int tag;
    String data;

    public Node_Data(int a, double x, double y, double z, String d,double w, int t) {
        this.data = d;
        this.ID = a;
        this.x = x;
        this.y = y;
        this.z = z;
        this.weight = w;
        this.tag = tag;



    }

    @Override
    public int getKey() {
        return this.ID;
    }

    @Override
    public GeoLocation getLocation() {
        GeoLocation g = new Geo_LocationI(this.x,this.y,this.z);
        return  g;
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
