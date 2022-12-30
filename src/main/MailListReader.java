package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MailListReader {
    public static List<String> read(String fileName) throws IOException{
        String cadena;
        List<String> emails = new ArrayList();
        
        FileReader f = new FileReader(fileName);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!= null) {
            if(cadena.contains("@")){
                emails.add(cadena);
            }
        }
        b.close();
        return emails;
    }
}