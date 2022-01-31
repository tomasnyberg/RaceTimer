package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileReader {

    protected static List<String> readFile(String path){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> list = new ArrayList<>();
            String line = reader.readLine();
            while(line != null){
                list.add(line);
                line = reader.readLine();
            }
            return list;
        } catch (Exception e){
            System.out.println("Could not find path");
            return null;
        }
    }
}
