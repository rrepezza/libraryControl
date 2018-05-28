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
    
    private GerarID ID = null;
    private boolean disponivel = true; 
    private int livroID = 0;
    
    public Exemplar() {
        
    }
    

    public Exemplar(GerarID ID, boolean disponivel, Livro livro) {
        this.ID = ID;
        this.disponivel = disponivel;
        this.livroID = livroID;
    }
    
    /**
     * @return the id
     */
    public GerarID getID(){
        return ID;
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
    
    @Override
    public void materializar(String dados) throws Exception {
        if(!dados.isEmpty()) {
            String vetorString[] = dados.split(";");

            if(vetorString.length != 10) {
               // throw new Exception("Faltam dados na String");
            }

            ID = new GerarID(Integer.parseInt(vetorString[0]));
            
            disponivel = Boolean.parseBoolean(vetorString[1]);

            id = Integer.parseInt(vetorString[0]);
            disponivel = Boolean.parseBoolean(vetorString[1]);
            livroID = Integer.parseInt(vetorString[2]);

        
        }
    }

    @Override
    public String desmaterializar() {
        String saida = getID().desmaterializar() + ";" + IsDisponivel() + ";";
        saida += getLivro().desmaterializar();
        return saida;
    }

    
}
