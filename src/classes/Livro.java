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
    
    private int id = 0;
    private int isbn = 0;
    private String titulo = "";
    private String fotoDaCapa = "";
    private Autor autor = null;
    private Editora editora = null;
    
    public Livro() {
        
    }
    
    public Livro(int id, int isbn, String titulo, String fotoDaCapa, Autor autor, Editora editora) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.fotoDaCapa = fotoDaCapa;
        this.autor = autor;
        this.editora = editora;
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
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * @return the editora
     */
    public Editora getEditora() {
        return editora;
    }

    /**
     * @param editora the editora to set
     */
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    
    @Override
    public void materializar(String dados) throws Exception {
        if(!dados.isEmpty()) {
            String vetorString[] = dados.split(";");
            if(vetorString.length != 8) {
                throw new Exception("Faltam dados na String");
            }
            
            id = Integer.parseInt(vetorString[0]);
            isbn = Integer.parseInt(vetorString[1]);
            titulo = vetorString[2];
            fotoDaCapa = vetorString[3];
            Autor autorRetornado = new Autor(Integer.parseInt(vetorString[4]), vetorString[5]);
            //autor.setId(Integer.parseInt(vetorString[4]));
            //autor.setNome(vetorString[5]);
            autor = autorRetornado;
            Editora editoraRetornada = new Editora(Integer.parseInt(vetorString[6]), vetorString[7]);
            //editora.setId(Integer.parseInt(vetorString[6]));
            //editora.setNome(vetorString[7]);
            editora = editoraRetornada;
            
        }
    }

    @Override
    public String desmaterializar() {
        String saida = getId() + ";" + getIsbn() + ";" + getTitulo() + ";" + getFotoDaCapa() + ";";
        saida += getAutor().desmaterializar() + ";";
        saida += getEditora().desmaterializar();
        return saida;
    }

}
