/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn;

import java.util.LinkedList;

/**
 *
 * @author CARLOS OSORIO
 */
public class ColaCL {
    
    LinkedList<String> cola=new LinkedList<String>();
    
    public void agregarcola(String elemento){
    
     cola.add(elemento);
    }
    
    public void eliminarcola(){
    
     cola.removeFirst();
    }
}
    
