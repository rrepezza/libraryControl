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
            ArrayList<Livro> livrosCadastrados = this.listar();
            boolean flag = false;
            if(livrosCadastrados.size() > 0) {
                for (int i = 0; i < livrosCadastrados.size(); i++) {
                    Livro temp = livrosCadastrados.get(i);
                    if(temp.getIsbn() == livro.getIsbn()) {
                        flag = true;
                    }
                }
            }
            if(!flag) {
                FileWriter fw = new FileWriter(nomeDoArquivo,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(livro.desmaterializar() + "\n");
                bw.close();	
            } else {
                throw new Exception("Já existe livro com o ISBN informado.");
            }
        }catch(Exception erro){
            throw erro;
        }
    }

    @Override
    public ArrayList<Livro> listar() throws Exception {
        
        try {
            ArrayList<Livro> livros = new ArrayList();
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
    public void alterar(int id) throws Exception {
        
    }
    
    public Livro getLivroByTitulo(String titulo) throws Exception { 
        Livro livro = null;        
        ArrayList<Livro> listaLivros = this.listar();
        for (int i = 0; i < listaLivros.size(); i++) {
            Livro temp = listaLivros.get(i);
            if(titulo.equals(temp.getTitulo())) {
                livro = temp;
            }       
        }
        return livro;
    }
    
    public ArrayList<Livro> getLivrosByTitulo(String titulo) throws Exception { 
        ArrayList<Livro> livrosEncontrados = new ArrayList();
        ArrayList<Livro> listaLivros = this.listar();
        for (int i = 0; i < listaLivros.size(); i++) {
            Livro temp = listaLivros.get(i);
            if(titulo.contains(temp.getTitulo())) {
                livrosEncontrados.add(temp);
            }
        }
        return livrosEncontrados;
    }
    
    public Livro getLivroByID(int id) throws Exception { 
        Livro livro = null;        
        ArrayList<Livro> listaLivros = this.listar();
        for (int i = 0; i < listaLivros.size(); i++) {
            Livro temp = listaLivros.get(i);
            if(id == temp.getId()) {
                livro = temp;
            } 
        }
        return livro;
    }    
}
