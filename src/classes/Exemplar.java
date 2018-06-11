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
    private boolean disponivel = false; 
    private int livroID = 0;
    private int quantidadeDeExemplares = 0;
    private boolean exemplarFixo = true;
    
    public Exemplar() {
        
    }
    
    public Exemplar(int id, int livroID, int quantidadeDeExemplares) {
        this.id = id;
        this.disponivel = false;
        this.livroID = livroID;
        this.quantidadeDeExemplares = quantidadeDeExemplares;
        this.exemplarFixo = true;
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
     * @return the disponivel
     */
    public boolean IsDisponivel() {
        return disponivel;
    }

    /**
     * @param disponivel the isDisponivel to set
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * @return the livro
     */
    public int getLivroID() {
        return livroID;
    }

    /**
     * @param livro the livro to set
     */
    public void setLivroID(int livroID) {
        this.livroID = livroID;
    }
    
     /**
     * @return the quantidadeDeExemplares
     */
    public int getQuantidadeDeExemplares() {
        return quantidadeDeExemplares;
    }

    /**
     * @param quantidadeDeExemplares the quantidadeDeExemplares to set
     */
    public void setQuantidadeDeExemplares(int quantidadeDeExemplares) {
        this.quantidadeDeExemplares = quantidadeDeExemplares;
    }   
    
    /**
     * @return the exemplarFixo
     */
    public boolean isExemplarFixo() {
        return exemplarFixo;
    }

    /**
     * @param exemplarFixo the exemplarFixo to set
     */
    public void setExemplarFixo(boolean exemplarFixo) {
        this.exemplarFixo = exemplarFixo;
    }
    
    @Override
    public void materializar(String dados) throws Exception {
        if(!dados.isEmpty()) {
            String vetorString[] = dados.split(";");
            if(vetorString.length != 3) {
                throw new Exception("Faltam dados na String");
            }

            id = Integer.parseInt(vetorString[0]);
            disponivel = Boolean.parseBoolean(vetorString[1]);
            livroID = Integer.parseInt(vetorString[2]);
        
        }
    }

    @Override
    public String desmaterializar() {
        String saida = getId() + ";" + IsDisponivel() + ";" + getLivroID();
        return saida;
    }
    
}
