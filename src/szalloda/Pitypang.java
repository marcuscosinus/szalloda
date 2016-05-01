package szalloda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Pitypang implements Szalloda {

    private List<Foglalas> foglalas = new ArrayList<>();

    public Pitypang(Path foglalasPath) throws IOException {

        String seged;
        String[] f = new String[1];
        int k = 0;
        List<String> foglalasTemp = null;

        try {
            foglalasTemp = Files.readAllLines(foglalasPath);
        } catch (IOException iOException) {
            System.out.println("Hiba a fájl beolvasásnál! " + iOException.getMessage());
        }

        for (int i = 1; i < foglalasTemp.size(); i++) {
            seged = foglalasTemp.get(i);
            f = seged.split(" ");

            foglalas.add(new Foglalas(
                    Integer.parseInt(f[k]),
                    Integer.parseInt(f[k + 1]),
                    Integer.parseInt(f[k + 2]),
                    Integer.parseInt(f[k + 3]),
                    Integer.parseInt(f[k + 4]),
                    Integer.parseInt(f[k + 5]),
                    f[k + 6]
            ));

        }

    }

    void kiIr() {

        for (int i = 0; i < this.foglalas.size(); i++) {
            System.out.println("Foglalási sor: " + this.foglalas.get(i).fogl_sor);
            System.out.println("Szoba száma: " + this.foglalas.get(i).szoba);
            System.out.println("Érkezés: " + this.foglalas.get(i).erk);
            System.out.println("Távozás: " + this.foglalas.get(i).tav);
            System.out.println("Fő: " + this.foglalas.get(i).fo);
            System.out.println("Reggeli?: " + this.foglalas.get(i).reggeli);
            System.out.println("Azonosító: " + this.foglalas.get(i).id);
            System.out.println("");

        }

    }

    @Override
    public String leghosszabb() {
        System.out.println("2. feladat: \n");
        int max = 0;
        String ki = "";
        for (int i = 0; i < foglalas.size(); i++) {

            if (foglalas.get(i).tartozkodas > max) {
                max = foglalas.get(i).tartozkodas;
                ki = "Leghosszabb tartózkodás: " + foglalas.get(i).id + " (" + foglalas.get(i).erk + ") " + max;
            }

        }
        return ki;
    }

    @Override
    public void fizetes(List<Honapok> honapok) {

        FileWriter ki = null;
        try {
            ki = new FileWriter(new File("bevetel.txt"));
            int fizetes = 0;
            ki.write("\n3. feladat: \r\n");        
            
            for (int i = 0; i < foglalas.size(); i++) {

                if (foglalas.get(i).erk < honapok.get(4).sorszam) {
                    fizetes = Szalloda.TAVASZ * foglalas.get(i).tartozkodas
                        + (foglalas.get(i).potagy * Szalloda.PÓTÁGY * foglalas.get(i).tartozkodas)
                        + (foglalas.get(i).reggeli * Szalloda.REGGELI * foglalas.get(i).tartozkodas * foglalas.get(i).fo);
                } else if (foglalas.get(i).erk < honapok.get(8).sorszam) {
                    fizetes = Szalloda.NYÁR * foglalas.get(i).tartozkodas
                        + (foglalas.get(i).potagy * Szalloda.PÓTÁGY * foglalas.get(i).tartozkodas)
                        + (foglalas.get(i).reggeli * Szalloda.REGGELI * foglalas.get(i).tartozkodas * foglalas.get(i).fo);
                } else if (foglalas.get(i).erk < (honapok.get(11).sorszam + honapok.get(11).napokSzama)) {
                    fizetes = Szalloda.ŐSZ * foglalas.get(i).tartozkodas
                        + (foglalas.get(i).potagy * Szalloda.PÓTÁGY * foglalas.get(i).tartozkodas)
                        + (foglalas.get(i).reggeli * Szalloda.REGGELI * foglalas.get(i).tartozkodas * foglalas.get(i).fo);
                }

            ki.write(foglalas.get(i).fogl_sor + ": " + fizetes + "\r\n"); 
            fizetes = 0;
        }
            ki.close();
            System.out.println("\n3. feladat kész \n");
        } catch (IOException ex) {
            System.out.println("IO hiba: " + ex.getMessage());
        }

    }

    @Override
    public void statisztika(List<Honapok> honapok) {
        int[] nap = new int[366];
        for (int i = 0; i < nap.length; i++) {
            nap[i] = 0;
        }

        for (Foglalas foglalas : foglalas) {
            for (int i = foglalas.getErk(); i < foglalas.getTav(); i++) {
                nap[i] += foglalas.getFo();
            }
        }

        System.out.println("4. feladat:\n");

        for (int i = 0; i < honapok.size(); i++) {
            int vendeg = 0;
            for (int j = honapok.get(i).sorszam; j < honapok.get(i).sorszam + honapok.get(i).napokSzama; j++) {
                vendeg += nap[j];
            }
            System.out.println(i + 1 + ": " + vendeg + " vendégéj");
        }

    }

    @Override
    public void foglalas() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int kezdoNap = 0;
        int ejszakakSzama = 0;
        int szabadSzoba = 27;
        boolean error = false;
        
        System.out.println("\n5. feladat: \n");
        do {
            try {
                System.out.println("Az év hanyadik napján érkezik?");
                kezdoNap = Integer.parseInt(br.readLine());
                if (kezdoNap < 1 || kezdoNap > 364) {
                    throw new IllegalArgumentException();
                }
                System.out.println("Hány éjszakát marad?");
                ejszakakSzama = Integer.parseInt(br.readLine());
                if (ejszakakSzama < 1) {
                    throw new IllegalArgumentException();
                }
            } catch (NumberFormatException nfe) {
                error = true;
                System.err.println("Számot kérek! " + nfe.getMessage());                
            } catch (IOException ex) {
                System.err.println("IO hiba! " + ex.getMessage());
            } catch (IllegalArgumentException ae) {
                error = true;   
                System.err.println("Nem lehet 1-nél kisebb! " + ae.getMessage());
                
            }

        } while (error);

        List<Integer> napok = new ArrayList<>(kezdoNap + ejszakakSzama);

        for (int i = kezdoNap; i < (kezdoNap + ejszakakSzama); i++) {
            napok.add(i);
        }

        for (int i = 0; i < foglalas.size(); i++) {
            if (szabadSzoba > 0) {
                for (int j = 0; j < foglalas.get(i).tartozkodas; j++) {
                    if ((napok.contains(foglalas.get(i).foglaltNapok.get(j)))) {
                        //System.out.println("Foglalja: " + foglalas.get(i).id 
                        //+ " : " + foglalas.get(i).erk + " - " + foglalas.get(i).tav);
                        szabadSzoba--;
                        break;
                    }
                }
            } else {
                break;
            }

        }
        System.out.println("Szabad szoba: " + szabadSzoba);
    }

}
