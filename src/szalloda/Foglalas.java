package szalloda;

import java.util.ArrayList;
import java.util.List;

public class Foglalas {

    protected int fogl_sor;
    protected int szoba;
    protected int erk;
    protected int tav;
    protected int fo;
    protected int reggeli;
    protected String id;

    protected int tartozkodas;
    protected int potagy;
    protected List<Integer> foglaltNapok;
    protected int vendegEjszaka;

    public Foglalas(int sor, int szoba, int erk, int tav, int fo, int reggeli, String id) {
        this.fogl_sor = sor;
        this.szoba = szoba;
        this.erk = erk;
        this.tav = tav;
        this.fo = fo;
        this.reggeli = reggeli;
        this.id = id;
        this.foglaltNapok = new ArrayList<Integer>();

        this.tartozkodas = this.tav - this.erk;

        if (this.fo > 2) {
            this.potagy = 1;
        } else {
            this.potagy = 0;
        }

        for (int i = this.erk; i < (this.erk + this.tartozkodas); i++) {
            this.foglaltNapok.add(i);
        }

        this.vendegEjszaka = this.fo * this.tartozkodas;

    }

    public int getErk() {
        return erk;
    }

    public int getTav() {
        return tav;
    }

    public int getFo() {
        return fo;
    }

}
