package util;

import result.DriverList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractFileReader {

    private static String line;
    public static List<String> data;


 public void read(){
     data= new ArrayList<String>();
     try{

         FileReader fr = new FileReader(file());
         BufferedReader br = new BufferedReader(fr);
        while((line = br.readLine()) !=null) {
            String [] s = line.split("; ");
            data.add(s[0]);
            data.add(s[1]);

        }
     } catch (IOException e){
         System.out.println("FILE COULD NOT BE READ");
         e.printStackTrace();
     }
 }

    public abstract String file();
    public abstract Object CreateObject();

}
