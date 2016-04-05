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
    ;
    public int test = 0;

    public Pitypang(Path foglalasPath) throws IOException {

        String seged;
        String[] f = new String[1];
        int k = 0;

        List<String> foglalasTemp = Files.readAllLines(foglalasPath);

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

    int thisTestModosito() {
        this.test = +1;
        return this.test;

    }

    int testModosito() {
        test = +1;
        return test;
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
        int temp = 0;
        String ki = "";
        for (int i = 0; i < this.foglalas.size(); i++) {

            if (this.foglalas.get(i).tartozkodas > temp) {
                temp = this.foglalas.get(i).tartozkodas;
                ki = "Leghosszabb tartózkodás: " + this.foglalas.get(i).id + " (" + this.foglalas.get(i).erk + ") " + temp;
            }

        }
        return ki;
    }

    @Override
    String fizetes(List<Honapok> honapok) {

        String ki = "";
        int fizetes = 0;
        for (int i = 0; i < this.foglalas.size(); i++) {

            if (this.foglalas.get(i).erk < honapok.get(4).sorszam) {
                fizetes = 9000 * this.foglalas.get(i).tartozkodas
                        + (this.foglalas.get(i).potagy * 2000 * this.foglalas.get(i).tartozkodas)
                        + (this.foglalas.get(i).reggeli * 1100 * this.foglalas.get(i).tartozkodas * this.foglalas.get(i).fo);
            } else if (this.foglalas.get(i).erk < honapok.get(8).sorszam) {
                fizetes = 10000 * this.foglalas.get(i).tartozkodas
                        + (this.foglalas.get(i).potagy * 2000 * this.foglalas.get(i).tartozkodas)
                        + (this.foglalas.get(i).reggeli * 1100 * this.foglalas.get(i).tartozkodas * this.foglalas.get(i).fo);
            } else if (this.foglalas.get(i).erk < (honapok.get(11).sorszam + honapok.get(11).napokSzama)) {
                fizetes = 8000 * this.foglalas.get(i).tartozkodas
                        + (this.foglalas.get(i).potagy * 2000 * this.foglalas.get(i).tartozkodas)
                        + (this.foglalas.get(i).reggeli * 1100 * this.foglalas.get(i).tartozkodas * this.foglalas.get(i).fo);
            }

            ki += this.foglalas.get(i).fogl_sor + ": " + fizetes + "\n";
            fizetes = 0;
        }

        return ki;
    }

    @Override
    String statisztika() {
        return "";
    }

    @Override
    void foglalas() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int kezdoNap = 0;
        int ejszakakSzama = 0;
        int szabadSzoba = 27;

        try {
            System.out.println("Az év hanyadik napján érkezik?");
            kezdoNap = Integer.parseInt(br.readLine());
            System.out.println("Hány éjszakát marad?");
            ejszakakSzama = Integer.parseInt(br.readLine());
        } catch (NumberFormatException nfe) {
            System.err.println("Számot kérek!");
        } catch (IOException ex) {
            Logger.getLogger(Pitypang.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Integer> napok = new ArrayList<>(kezdoNap + ejszakakSzama);

        for (int i = kezdoNap; i <= (kezdoNap + ejszakakSzama); i++) {
            napok.add(i);
        }

        for (int i = 0; i < this.foglalas.size(); i++) {
            if (szabadSzoba > 0) {
                for (int j = 0; j < this.foglalas.get(i).tartozkodas; j++) {
                    if ((napok.contains(this.foglalas.get(i).foglaltNapok.get(j)))) {
                        System.out.println("Foglalja: " + this.foglalas.get(i).id + " : " + this.foglalas.get(i).erk + " - " + this.foglalas.get(i).tav);
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
