package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Geo_LocationITest {

    Geo_LocationI l1 = new Geo_LocationI(3,5,8);
    Geo_LocationI l2 = new Geo_LocationI(0, 3,2);
    Geo_LocationI l3 = new Geo_LocationI(5,6,2);

    @Test
    void x() {
        assertEquals(l1.x(),3);
        assertEquals(l2.x(),0);
        assertEquals(l3.x(),5);
    }

    @Test
    void y() {
        assertEquals(l1.y(),5);
        assertEquals(l2.y(),3);
        assertEquals(l3.y(),6);
    }

    @Test
    void z() {
        assertEquals(l1.z(),8);
        assertEquals(l2.z(),2);
        assertEquals(l2.z(),2);
    }


    @Test
    void distance() {
        assertEquals(l1.distance(l2), 15.449489742783179);
        assertEquals(l1.distance(l3), 7.449489742783178);
        assertEquals(l2.distance(l3), 34.0);
    }
}