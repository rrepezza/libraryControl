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
public class Livro implements TratamentoDeDados {
    
    private GerarID ID = null;
    private int isbn = 0;
    private String titulo = "";
    private String fotoDaCapa = "";
    private int autorID = 0;
    private int editoraID = 0;
    
    public Livro() {
        
    }
    

    public Livro(GerarID ID, int isbn, String titulo, String fotoDaCapa, Autor autor, Editora editora) {
        this.ID = ID;
        this.isbn = isbn;
        this.titulo = titulo;
        this.fotoDaCapa = fotoDaCapa;
        this.autorID = autorID;
        this.editoraID = editoraID;
    }
    
    /**
     * @return the id
     */
    public GerarID getID(){
        return ID;
    }
        /**
     * @return the isbn
     */
    public int getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the fotoDaCapa
     */
    public String getFotoDaCapa() {
        return fotoDaCapa;
    }

    /**
     * @param fotoDaCapa the fotoDaCapa to set
     */
    public void setFotoDaCapa(String fotoDaCapa) {
        this.fotoDaCapa = fotoDaCapa;
    }

    /**
     * @return the autor
     */
    public int getAutorID() {
        return autorID;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutorID(int autorID) {
        this.autorID = autorID;
    }

    /**
     * @return the editora
     */
    public int getEditoraID() {
        return editoraID;
    }

    /**
     * @param editora the editora to set
     */
    public void setEditoraID(int editoraID) {
        this.editoraID = editoraID;
    }
    
    @Override
    public void materializar(String dados) throws Exception {
        if(!dados.isEmpty()) {
            String vetorString[] = dados.split(";");
            if(vetorString.length != 6) {
                throw new Exception("Faltam dados na String");
            }
            
            ID = new GerarID(Integer.parseInt(vetorString[0]));
            isbn = Integer.parseInt(vetorString[1]);
            titulo = vetorString[2];
            fotoDaCapa = vetorString[3];
            autorID = Integer.parseInt(vetorString[4]);
            editoraID = Integer.parseInt(vetorString[5]);
        }
    }

    @Override
    public String desmaterializar() {

        String saida = getID().desmaterializar()+ ";" + getIsbn() + ";" + getTitulo() + ";" + getFotoDaCapa() + ";";
        saida += getAutor().desmaterializar() + ";";
        saida += getEditora().desmaterializar();

        return saida;
    }

}
