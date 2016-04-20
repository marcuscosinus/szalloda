/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szalloda;

import java.util.List;

/**
 *
 * @author Marcus
 */
abstract class Szalloda {
    
    
    abstract String leghosszabb();
    abstract String fizetes(List<Honapok> honapok);
    abstract void statisztika(List<Honapok> honapok);
    abstract void foglalas();
    
}
