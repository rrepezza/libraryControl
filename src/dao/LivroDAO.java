/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Livro;
import interfaces.ILivroDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
        
        try {
            ArrayList<Livro> livros = new ArrayList<Livro>();
            FileReader fr = new FileReader(nomeDoArquivo);
   
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            while((linha=br.readLine()) != null){
                
                Livro livro = new Livro();
                livro.materializar(linha);
                livros.add(livro);
            }
            br.close();
            return livros;
        } catch (Exception erro) {
            throw erro;
        }
        
    }

    @Override
    public void excluir(int id) throws Exception {
        
    }

    @Override
    public void alterar(int id) throws Exception {
        
    }
    
    public Livro getLivroById(int id) throws Exception {
        
        Livro livro = null;
        
        ArrayList<Livro> listaLivros = this.consultar();
        
        for (int i = 0; i < listaLivros.size(); i++) {
            Livro temp = listaLivros.get(i);
            
            if(temp.getId() == id) {
                livro = temp;
            }
            
        }
        
        return livro;
    }
    
    public Livro getLivroByTitulo(String titulo) throws Exception {
        
        Livro livro = null;
        
        ArrayList<Livro> listaLivros = this.consultar();
        
        for (int i = 0; i < listaLivros.size(); i++) {
            Livro temp = listaLivros.get(i);
            
            if(titulo.equals(temp.getTitulo())) {
                livro = temp;
            }
            
        }
        
        return livro;
    }


    
}
