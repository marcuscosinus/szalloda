package szalloda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args)
            throws FileNotFoundException, IOException {

        
        Path honapokPath = null;
        Path foglalasPath = null;
        Pitypang pitypang = null;
        List<String> honapTemp = null;
        
        
        honapokPath = new File("honapok.txt").toPath();
        foglalasPath = new File("pitypang.txt").toPath();

        
        try {
            honapTemp = Files.readAllLines(honapokPath);
        } catch (FileNotFoundException ioe) {
            System.err.println("Fájl beolvasási hiba: " + ioe.getMessage());
        } catch (IOException ioe) {
            System.err.println("IO hiba: " + ioe.getMessage());
        }
        
        List<Honapok> honapok = new ArrayList<>();
        

        for (int j = 0; j < honapTemp.size(); j += 3) {
            honapok.add(new Honapok(
                    honapTemp.get(j),
                    Integer.parseInt(honapTemp.get(j + 1)),
                    Integer.parseInt(honapTemp.get(j + 2))
            )
            );
        }

        
        try {
            pitypang = new Pitypang(foglalasPath);
        } catch (IOException iOException) {
            System.err.println("IO hiba! " + iOException.getMessage());
        }
        
        // Feladatok meghívása
        System.out.println(pitypang.leghosszabb());
        pitypang.fizetes(honapok);
        pitypang.statisztika(honapok);
        pitypang.foglalas();

    }
}
