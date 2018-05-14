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
    
    private int id;
    private int isbn;
    private String titulo;
    private String fotoDaCapa;
    private Autor autor;
    private Editora editora;
   

    @Override
    public void materializar(String dados) throws Exception {
        String vetorString[] = dados.split(";");
        if(vetorString.length != 8) 
            throw new Exception("Faltam dados na String");
        
        id = Integer.parseInt(vetorString[0]);
        isbn = Integer.parseInt(vetorString[1]);
        titulo = vetorString[2];
        fotoDaCapa = vetorString[3];
        autor.setId(Integer.parseInt(vetorString[4]));
        autor.setNome(vetorString[5]);
        editora.setId(Integer.parseInt(vetorString[6]));
        editora.setNome(vetorString[7]);

    }

    @Override
    public String desmaterializar() {
        String saida = id + ";" + isbn + ";" + titulo + ";" + fotoDaCapa;
        saida += autor.desmaterializar() + ";";
        saida += editora.desmaterializar();
        return saida;
    }
    
    
}
