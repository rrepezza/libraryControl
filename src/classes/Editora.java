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
public class Editora implements TratamentoDeDados {
    
    private GerarID ID = null;
    private String nome = "";
    
    public Editora() {
        
    }
    
    public Editora(GerarID ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    /**
     * @return the id
     */
    public GerarID getID(){
        return ID;
    }
   
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void materializar(String dados) throws Exception {
        if(!dados.isEmpty()) {
            String vetorString[] = dados.split(";");
            if(vetorString.length != 2) {
                throw new Exception("Faltam dados na String");
            }

            ID = new GerarID(Integer.parseInt(vetorString[0]));
            nome = vetorString[1];
        }
    }

    @Override
    public String desmaterializar() {
        String saida = getID().desmaterializar() + ";" + nome;
        return saida;
    }
}
