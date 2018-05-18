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
    
    private int id = 0;
    private String nome = "";
    
    public Editora() {
        
    }
    
    public Editora(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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

            id = Integer.parseInt(vetorString[0]);
            nome = vetorString[1];
        }
    }

    @Override
    public String desmaterializar() {
        String saida = id + ";" + nome;
        return saida;
    }
}
