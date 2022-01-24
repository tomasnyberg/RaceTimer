package result;
//ahsdlajshdv pahsvdpa hvsdpjha vs
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
        assertEquals(filereader.file(), "../namnfil.txt");
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

}
