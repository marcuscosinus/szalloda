/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szalloda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pitypang extends Szalloda {

    private List<Foglalas> foglalas = new ArrayList<>();
    public int test = 0;

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
    String leghosszabb() {
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
    String fizetes(List<Honapok> honapok) {

        String ki = "3. feladat: \n";
        int fizetes = 0;
        for (int i = 0; i < foglalas.size(); i++) {

            if (foglalas.get(i).erk < honapok.get(4).sorszam) {
                fizetes = 9000 * foglalas.get(i).tartozkodas
                        + (foglalas.get(i).potagy * 2000 * foglalas.get(i).tartozkodas)
                        + (foglalas.get(i).reggeli * 1100 * foglalas.get(i).tartozkodas * foglalas.get(i).fo);
            } else if (foglalas.get(i).erk < honapok.get(8).sorszam) {
                fizetes = 10000 * foglalas.get(i).tartozkodas
                        + (foglalas.get(i).potagy * 2000 * foglalas.get(i).tartozkodas)
                        + (foglalas.get(i).reggeli * 1100 * foglalas.get(i).tartozkodas * foglalas.get(i).fo);
            } else if (foglalas.get(i).erk < (honapok.get(11).sorszam + honapok.get(11).napokSzama)) {
                fizetes = 8000 * foglalas.get(i).tartozkodas
                        + (foglalas.get(i).potagy * 2000 * foglalas.get(i).tartozkodas)
                        + (foglalas.get(i).reggeli * 1100 * foglalas.get(i).tartozkodas * foglalas.get(i).fo);
            }

            ki += foglalas.get(i).fogl_sor + ": " + fizetes + "\n";
            fizetes = 0;
        }

        return ki;
    }

    @Override
    void statisztika(List<Honapok> honapok) {
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
    void foglalas() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int kezdoNap = 0;
        int ejszakakSzama = 0;
        int szabadSzoba = 27;
        boolean error = false;

        do {
            try {
                System.out.println("Az év hanyadik napján érkezik?");
                kezdoNap = Integer.parseInt(br.readLine());
                if (kezdoNap < 1) {
                    throw new IllegalArgumentException();
                }
                System.out.println("Hány éjszakát marad?");
                ejszakakSzama = Integer.parseInt(br.readLine());
                if (ejszakakSzama < 1) {
                    throw new IllegalArgumentException();
                }
            } catch (NumberFormatException nfe) {
                error = true;
                System.err.println("Számot kérek!");                
            } catch (IOException ex) {
                Logger.getLogger(Pitypang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ae) {
                error = true;
                System.err.println("Nem lehet 1-nél kisebb!");
                
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
                        System.out.println("Foglalja: " + foglalas.get(i).id + " : " + foglalas.get(i).erk + " - " + foglalas.get(i).tav);
                        szabadSzoba--;
                        break;
                    }
                }
            } else {
                break;
            }

        }
        System.out.println("5. feladat: \n Szabad szoba: " + szabadSzoba);
    }

}
