package szalloda;

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

    public Foglalas(int sor, int szoba, int erk, int tav, int fo, int reggeli, String id) {
        this.fogl_sor = sor;
        this.szoba = szoba;
        this.erk = erk;
        this.tav = tav;
        this.fo = fo;
        this.reggeli = reggeli;
        this.id = id;
        
        this.tartozkodas = this.tav - this.erk;
        
        if (this.fo > 2) {
            this.potagy = 1;
        } else {this.potagy = 0;}

    }
}
