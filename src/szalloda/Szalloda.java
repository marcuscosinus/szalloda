package szalloda;

import java.util.List;

public interface Szalloda {
    
    int TAVASZ = 9000;
    int NYÁR = 10000;
    int ŐSZ = 8000;
    int PÓTÁGY = 2000;
    int REGGELI = 1100;
    
    String leghosszabb();
    void fizetes(List<Honapok> honapok);
    void statisztika(List<Honapok> honapok);
    void foglalas();
    
}
