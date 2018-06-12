/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Autor;
import classes.Editora;
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
    private String editora_db = "./src/arquivos/Editoras.csv";
    private String autor_db = "./src/arquivos/Autores.csv";
    
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
    public void alterar(Livro livro) throws Exception {
        
    }
    
    public Livro getLivroByTitulo(String titulo) throws Exception { 
        try {
            Livro livro = null;        
            ArrayList<Livro> listaLivros = this.listar();
            for (int i = 0; i < listaLivros.size(); i++) {
                Livro temp = listaLivros.get(i);
                if(titulo.equals(temp.getTitulo())) {
                    livro = temp;
                }       
            }
            return livro;
        } catch (Exception erro) {
            erro.printStackTrace();
            throw erro;
        }
        
    }
    
    public ArrayList<Livro> getLivrosByTitulo(String titulo) throws Exception { 
        try {
            ArrayList<Livro> livrosEncontrados = new ArrayList();
            ArrayList<Livro> listaLivros = this.listar();
            for (int i = 0; i < listaLivros.size(); i++) {
                Livro temp = listaLivros.get(i);
                if(temp.getTitulo().contains(titulo)) {
                    livrosEncontrados.add(temp);
                }
            }
            return livrosEncontrados;
        } catch (Exception erro) {
            erro.printStackTrace();
            throw erro;
        }
        
    }
    
    public ArrayList<Livro> getLivrosByEditora(String nomeEditora) throws Exception { 
        try {
            ArrayList<Livro> livrosEncontrados = new ArrayList();
            ArrayList<Livro> listaLivros = this.listar();
            EditoraDAO edao = new EditoraDAO(editora_db);
            ArrayList<Editora> listaEditoras = edao.getEditorasByName(nomeEditora);
            if(listaEditoras.size() > 0) {
                for (int i = 0; i < listaLivros.size(); i++) {
                    Livro tempLivro = listaLivros.get(i);
                    for (int j = 0; j < listaEditoras.size(); j++) {
                        Editora tempEditora = listaEditoras.get(j);
                        if(tempLivro.getEditoraID() == tempEditora.getId()) {
                            livrosEncontrados.add(tempLivro);
                        }
                    }
                }

            }
            return livrosEncontrados;
        } catch (Exception erro) {
            erro.printStackTrace();
            throw erro;
        }
        
    }
    
    public ArrayList<Livro> getLivrosByAutor(String nomeAutor) throws Exception { 
        try {
            ArrayList<Livro> livrosEncontrados = new ArrayList();
            ArrayList<Livro> listaLivros = this.listar();
            AutorDAO adao = new AutorDAO(autor_db);
            ArrayList<Autor> listaAutores = adao.getAutoresByNome(nomeAutor);
            if(listaAutores.size() > 0) {
                for (int i = 0; i < listaLivros.size(); i++) {
                    Livro tempLivro = listaLivros.get(i);
                    for (int j = 0; j < listaAutores.size(); j++) {
                        Autor tempAutor = listaAutores.get(j);
                        if(tempLivro.getAutorID() == tempAutor.getId()) {
                            livrosEncontrados.add(tempLivro);
                        }
                    }
                }

            }
            return livrosEncontrados;
        } catch (Exception erro) {
            erro.printStackTrace();
            throw erro;
        }
        
    }
    
    public Livro getLivroByID(int id) throws Exception { 
        try {
            Livro livro = null;        
            ArrayList<Livro> listaLivros = this.listar();
            for (int i = 0; i < listaLivros.size(); i++) {
                Livro temp = listaLivros.get(i);
                if(id == temp.getId()) {
                    livro = temp;
                } 
            }
            return livro;
        } catch (Exception erro) {
            erro.printStackTrace();
            throw erro;
        }
        
    }    
}
