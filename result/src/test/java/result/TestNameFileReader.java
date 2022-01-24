package result;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.AbstractFileReader;
import util.NameFileReader;

public class TestNameFileReader {
    AbstractFileReader fileReader;
    List<DriverEntry> driverList;

    @BeforeEach 
    void setUp() {
        driverList = NameFileReader.result("../namnfil.txt");
    }

    @Test
    void testCreateObject(){
        DriverEntry mock1 = new DriverEntry("1", "Anders Asson");
        DriverEntry mock2 = new DriverEntry("2", "Bengt Bsson");
        DriverEntry mock3 = new DriverEntry("3", "Chris Csson");
        DriverEntry mock4 = new DriverEntry("4", "David Dsson");
        DriverEntry mock5 = new DriverEntry("5", "Erik Esson");

        DriverEntry e1 = driverList.get(0);
        DriverEntry e2 = driverList.get(1);
        DriverEntry e3 = driverList.get(2);
        DriverEntry e4 = driverList.get(3);
        DriverEntry e5 = driverList.get(4);


        assertEquals(mock1.getName(), e1.getName());
        assertEquals(mock2.getName(), e2.getName());
        assertEquals(mock3.getName(), e3.getName());
        assertEquals(mock4.getName(), e4.getName());
        assertEquals(mock5.getName(), e5.getName());

        assertEquals(mock1.getNumber(), e1.getNumber());
        assertEquals(mock2.getNumber(), e2.getNumber());
        assertEquals(mock3.getNumber(), e3.getNumber());
        assertEquals(mock4.getNumber(), e4.getNumber());
        assertEquals(mock5.getNumber(), e5.getNumber());
    }
}
