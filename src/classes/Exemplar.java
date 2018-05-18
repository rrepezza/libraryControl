/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import interfaces.TratamentoDeDados;

/**
 *
 * @author repez
 */
public class Exemplar implements TratamentoDeDados {
    
    private int id = 0;
    private boolean isDisponivel = true; 
    private Livro livro = null;

    @Override
    public void materializar(String dados) throws Exception {
        
    }

    @Override
    public String desmaterializar() {
        return "";
    }
    
    
}
