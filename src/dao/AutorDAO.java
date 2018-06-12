/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Autor;
import interfaces.IAutorDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author repez
 */
public class AutorDAO implements IAutorDAO {
    
    private String nomeDoArquivo = "";
    
    public AutorDAO(String nomeDoArquivo){
        this.nomeDoArquivo = nomeDoArquivo;
    }

    @Override
    public ArrayList<Autor> listar() throws Exception {
        try {
            ArrayList<Autor> autores = new ArrayList();
            FileReader fr = new FileReader(nomeDoArquivo);
   
            BufferedReader br  = new BufferedReader(fr);
            
            String linha = "";
            while((linha=br.readLine()) != null){
                Autor autor = new Autor();
                autor.materializar(linha);
                autores.add(autor);
            }
            br.close();
            return autores;
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void alterar(Autor autor) throws Exception {
        try {
            ArrayList<Autor> autoresCadastrados = this.listar();
            ArrayList<Autor> novosAutores = new ArrayList();
            for (int i = 0; i < autoresCadastrados.size(); i++) {
                Autor temp = autoresCadastrados.get(i);
                if(temp.getId() == autor.getId()) {
                    novosAutores.add(autor);
                }else{
                    novosAutores.add(temp);
                }
            }
            
            FileWriter fw = new FileWriter(nomeDoArquivo,false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < novosAutores.size(); i++) {
                Autor novo = novosAutores.get(i);
                bw.write(novo.desmaterializar() + "\n");
            }
            bw.close();	
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void incluir(Autor autor) throws Exception {
        try{
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivo,true);
            //Criar o buffer do arquivo
            BufferedWriter bw = new BufferedWriter(fw);
            //Escreve no arquivo
            bw.write(autor.desmaterializar() + "\n");
            //fecha o arquivo
            bw.close();		
        }catch(Exception erro){
            throw erro;
        }
    }
    
    public Autor getAutorByNome(String nome) throws Exception {
        Autor autor = null;
        ArrayList<Autor> listaAutores = this.listar();
        for (int i = 0; i < listaAutores.size(); i++) {
            Autor temp = listaAutores.get(i);
            if(nome.equals(temp.getNome())) {
                autor = temp;
            }
            
        }
        return autor;
    }
    
    public ArrayList getAutoresByNome(String nome) throws Exception {
        ArrayList<Autor> autoresEncontrados = new ArrayList();
        ArrayList<Autor> listaAutores = this.listar();
        for (int i = 0; i < listaAutores.size(); i++) {
            Autor temp = listaAutores.get(i);
            String nomeDoAutor = temp.getNome();
            if(nomeDoAutor.contains(nome)) {        
                autoresEncontrados.add(listaAutores.get(i));
            }
        }
        return autoresEncontrados;
    }
    
    public Autor getAutorByID(int id) throws Exception {
        Autor autor = null;
        ArrayList<Autor> listaAutores = this.listar();
        for (int i = 0; i < listaAutores.size(); i++) {
            Autor temp = listaAutores.get(i);
            if(id == temp.getId()) {
                autor = temp;
            }
        }
        return autor;
    }
    
}
