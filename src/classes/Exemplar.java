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
    
    private GerarID ID = null;
    private boolean disponivel = true; 
    private Livro livro = null;
    
    public Exemplar() {
        
    }
    
    public Exemplar(GerarID ID, boolean disponivel, Livro livro) {
        this.ID = ID;
        this.disponivel = disponivel;
        this.livro = livro;
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
               // throw new Exception("Faltam dados na String");
            }

            ID = new GerarID(Integer.parseInt(vetorString[0]));
            
            disponivel = Boolean.parseBoolean(vetorString[1]);
            Autor autor = new Autor(new GerarID(Integer.parseInt(vetorString[6])), vetorString[7]);            
            Editora editora = new Editora(new GerarID(Integer.parseInt(vetorString[8])), vetorString[9]);
            Livro livroRetornado = new Livro(new GerarID(Integer.parseInt(vetorString[2])), Integer.parseInt(vetorString[3]),
            vetorString[4], vetorString[5], autor, editora);
            livro = livroRetornado;
        
        }
    }

    @Override
    public String desmaterializar() {
        String saida = getID().desmaterializar() + ";" + IsDisponivel() + ";";
        saida += getLivro().desmaterializar();
        return saida;
    }

    
}
