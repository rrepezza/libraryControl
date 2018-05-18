/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Livro;
import interfaces.ILivroDAO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author repez
 */
public class LivroDAO implements ILivroDAO {
    
    private String nomeDoArquivo = "";
    
    public LivroDAO(String nomeDoArquivo){
        this.nomeDoArquivo = nomeDoArquivo;
    }

    @Override
    public void incluir(Livro livro) throws Exception {
        try{
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivo,true);
            //Criar o buffer do arquivo
            BufferedWriter bw = new BufferedWriter(fw);
            //Escreve no arquivo
            bw.write(livro.desmaterializar() + "\n");
            //fecha o arquivo
            bw.close();		
        }catch(Exception erro){
            throw erro;
        }
    }

    @Override
    public ArrayList<Livro> consultar() throws Exception {
        ArrayList livros = new ArrayList();
        
        return livros;
    }

    @Override
    public void excluir(int id) throws Exception {
        
    }

    @Override
    public void alterar(int id) throws Exception {
        
    }

    
}
