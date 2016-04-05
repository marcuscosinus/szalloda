/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szalloda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

/**
 *
 * @author Marcus
 */
public class Main {

    public static void main(String[] args)
            throws FileNotFoundException, IOException {

        

        Path honapokPath = new File("honapok.txt").toPath();
        Path foglalasPath = new File("pitypang.txt").toPath();

        List<String> honapTemp = Files.readAllLines(honapokPath);
        List<Honapok> honapok = new ArrayList<>();
        

        for (int j = 0; j < honapTemp.size(); j += 3) {
            honapok.add(new Honapok(
                    honapTemp.get(j),
                    Integer.parseInt(honapTemp.get(j + 1)),
                    Integer.parseInt(honapTemp.get(j + 2))
            )
            );

        }

        Pitypang pitypang = new Pitypang(foglalasPath);
        
       // pitypang.kiIr();
        pitypang.foglalas();
        //System.out.println(pitypang.fizetes(honapok));

        
       
        
        
          
        
            
        /*
        for (int k = 0; k < honapok.size(); k++) {
            System.out.println("Név: " + honapok.get(k).nev);
            System.out.println("Napok száma: " + honapok.get(k).napokSzama);
            System.out.println("Sorszáma: " + honapok.get(k).sorszam);
            System.out.println("");
        }
         */
    }
}
