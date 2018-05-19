/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dao.LivroDAO;
import interfaces.TratamentoDeDados;

/**
 *
 * @author repez
 */
public class Exemplar implements TratamentoDeDados {
    
    private int id = 0;
    private boolean disponivel = true; 
    private Livro livro = null;
    
    public Exemplar() {
        
    }
    
    public Exemplar(int id, boolean disponivel, Livro livro) {
        this.id = id;
        this.disponivel = disponivel;
        this.livro = livro;
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
    public Livro getLivro() {
        return livro;
    }

    /**
     * @param livro the livro to set
     */
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
    @Override
    public void materializar(String dados) throws Exception {
        if(!dados.isEmpty()) {
            String vetorString[] = dados.split(";");
            if(vetorString.length != 10) {
                throw new Exception("Faltam dados na String");
            }

            id = Integer.parseInt(vetorString[0]);
            String objDisponivel = vetorString[1];
            disponivel = objDisponivel.equals("Sim");
            Autor autor = new Autor(Integer.parseInt(vetorString[6]), vetorString[7]);
            Editora editora = new Editora(Integer.parseInt(vetorString[8]), vetorString[9]);
            Livro livroRetornado = new Livro(Integer.parseInt(vetorString[2]), Integer.parseInt(vetorString[3]),
            vetorString[4], vetorString[5], autor, editora);
            livro = livroRetornado;
        
        }
    }

    @Override
    public String desmaterializar() {
        String saida = getId() + ";" + IsDisponivel() + ";";
        saida += getLivro().desmaterializar();
        return saida;
    }

    
}
