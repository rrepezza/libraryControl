/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Exemplar;
import classes.Livro;
import interfaces.IExemplarDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author repez
 */
public class ExemplarDAO implements IExemplarDAO {
    
    private String nomeDoArquivo = "";
    private String livro_db = "./src/arquivos/Livros.csv";
    
    public ExemplarDAO(String nomeDoArquivo){
        this.nomeDoArquivo = nomeDoArquivo;
    }

    @Override
    public void incluir(Exemplar exemplar) throws Exception {
        try{                     
            FileWriter fw = new FileWriter(nomeDoArquivo,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(exemplar.desmaterializar() + "\n");
            bw.close();		
        }catch(Exception erro){
            throw erro;
        }
    }

    @Override
    public ArrayList<Exemplar> listar() throws Exception {
        try {
            ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
            FileReader fr = new FileReader(nomeDoArquivo);
   
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            while((linha=br.readLine()) != null){
                Exemplar exemplar = new Exemplar();
                exemplar.materializar(linha);
                exemplares.add(exemplar);
            }
            br.close();
            return exemplares;
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void alterar(Exemplar exemplar) throws Exception {
        try {
            ArrayList<Exemplar> exemplaresCadastrados = this.listar();
            ArrayList<Exemplar> novosExemplares = new ArrayList();
            for (int i = 0; i < exemplaresCadastrados.size(); i++) {
                Exemplar temp = exemplaresCadastrados.get(i);
                if(temp.getId() == exemplar.getId()) {
                    novosExemplares.add(exemplar);
                }else{
                    novosExemplares.add(temp);
                }
            }
            
            FileWriter fw = new FileWriter(nomeDoArquivo,false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < novosExemplares.size(); i++) {
                Exemplar novo = novosExemplares.get(i);
                bw.write(novo.desmaterializar() + "\n");
            }
            bw.close();	
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    public ArrayList<Exemplar> getExemplarByTitulo(String titulo) throws Exception {
        try {
            ArrayList<Exemplar> exemplaresEncontrados = new ArrayList();
            ArrayList<Exemplar> listaExemplares = this.listar();
            LivroDAO ldao = new LivroDAO(livro_db);
            ArrayList<Livro> listaLivros = ldao.getLivrosByTitulo(titulo);
            for (int i = 0; i < listaLivros.size(); i++) {
                Livro tempLivro = listaLivros.get(i);
                for (int j = 0; j < listaExemplares.size(); j++) {
                    Exemplar tempExemplar = listaExemplares.get(i);
                    if(tempLivro.getId() == tempExemplar.getLivroID()) {
                        listaExemplares.add(tempExemplar);
                    }
                }
            }
            return exemplaresEncontrados;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    public ArrayList<Exemplar> getExemplarByLivroID(int livroID) throws Exception {
        try {
            ArrayList<Exemplar> exemplaresEncontrados = new ArrayList();
            ArrayList<Exemplar> listaExemplares = this.listar();
            LivroDAO ldao = new LivroDAO(livro_db);
            Livro livro = ldao.getLivroByID(livroID);
            if(livro != null) {
                for (int i = 0; i < listaExemplares.size(); i++) {
                    Exemplar temp = listaExemplares.get(i);
                    if(temp.getLivroID() == livro.getId()) {
                        exemplaresEncontrados.add(temp);
                    }
                }
            }
            return exemplaresEncontrados;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    public boolean hasExemplarReserva(int livroID) throws Exception {
        try {
            boolean has = false;
            ArrayList<Exemplar> listaExemplares = this.listar();
            LivroDAO ldao = new LivroDAO(livro_db);
            Livro livro = ldao.getLivroByID(livroID);
            if(livro != null) {
                for (int i = 0; i < listaExemplares.size(); i++) {
                    Exemplar temp = listaExemplares.get(i);
                    if(temp.getLivroID() == livro.getId()) {
                        if(temp.isExemplarReserva()){
                            has = true;
                        }
                    }
                }
            }
            return has;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    public boolean isExemplarFixo(int exemplarID) throws Exception {
        try {
            ArrayList<Exemplar> exemplaresCadastrados = this.listar();
            for (int i = 0; i < exemplaresCadastrados.size(); i++) {
                Exemplar temp = exemplaresCadastrados.get(i);
                if(temp.getId() == exemplarID && temp.isExemplarReserva()){
                    return true;
                } 
            }
            return false;
        } catch (Exception erro) {
            throw erro;
        }
    }    
    
    public ArrayList<Exemplar> getExemplaresDisponiveis() throws Exception {
        try {
            ArrayList<Exemplar> exemplaresDisponiveis = new ArrayList();
            ArrayList<Exemplar> listaExemplares = this.listar();
            for (int i = 0; i < listaExemplares.size(); i++) {
                Exemplar temp = listaExemplares.get(i);
                if(temp.IsDisponivel()) {
                    exemplaresDisponiveis.add(temp);
                }
            }
            return exemplaresDisponiveis;
        } catch (Exception erro) {
            throw erro;
        } 
    }
    
    public Exemplar getExemplarByID(int exemplarID) throws Exception {
        try {
            Exemplar exemplar = null;
            ArrayList<Exemplar> listaExemplares = this.listar();
            for (int i = 0; i < listaExemplares.size(); i++) {
                Exemplar temp = listaExemplares.get(i);
                if(temp.getId() == exemplarID) {
                    exemplar = temp;
                }
            }
            return exemplar;
        } catch (Exception erro) {
            throw erro;
        }
    }
}
