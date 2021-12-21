package api;


public class Geo_LocationI implements GeoLocation {
   public double x;
   public double y;
   public double z;
    public Geo_LocationI(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Geo_LocationI(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        //d = ((x2 - x1)2 + (y2 - y1)2 + (z2 - z1)2)1/2
        double x1 = Math.abs((g.x()-this.x));
        double y1 = Math.abs((g.y()-this.y));
        double z1 = Math.abs((g.z()-this.z));

        double d = (Math.pow(x1,2) + Math.pow(y1,2) + Math.pow(z1,0.5));
        return d;
    }
}
