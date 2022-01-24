package result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.AbstractFileReader;
import util.NameFileReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFileReader {
    AbstractFileReader filereader;

    @BeforeEach
    void setup(){
        filereader = new NameFileReader();
    }

    @Test
    void testfindfile(){
        assertEquals(filereader.file(), "C:\\Users\\joel2\\IdeaProjects\\team05\\result\\src\\test\\java\\result\\namnfil.txt");
    }

    @Test
    void testRead(){
        filereader.read();
        assertEquals(filereader.data.get(2), "1");
        assertEquals(filereader.data.get(3), "Anders Asson");
        assertEquals(filereader.data.get(4), "2");
        assertEquals(filereader.data.get(5), "Bengt Bsson");
        assertEquals(filereader.data.get(11), "Erik Esson");
        assertEquals(filereader.data.size(), 12);
    }

    @Test
    void testCreateObject(){
        DriverEntry mock1 = new DriverEntry("1", "Anders Asson");
        DriverEntry mock2 = new DriverEntry("2", "Bengt Bsson");
        DriverEntry mock3 = new DriverEntry("3", "Chris Csson");
        DriverEntry mock4 = new DriverEntry("4", "David Dsson");
        DriverEntry mock5 = new DriverEntry("5", "Erik Esson");

        filereader.read();

        DriverEntry e1 = ((DriverList) filereader.CreateObject()).get(0);
        DriverEntry e2 = ((DriverList) filereader.CreateObject()).get(1);
        DriverEntry e3 = ((DriverList) filereader.CreateObject()).get(2);
        DriverEntry e4 = ((DriverList) filereader.CreateObject()).get(3);
        DriverEntry e5 = ((DriverList) filereader.CreateObject()).get(4);


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
