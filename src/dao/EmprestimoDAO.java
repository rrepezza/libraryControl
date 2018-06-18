/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Emprestimo;
import classes.Exemplar;
import interfaces.IEmprestimoDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author repez
 */
public class EmprestimoDAO implements IEmprestimoDAO {
    
    private String nomeDoArquivo = "";
    private String reserva_db = "./src/arquivos/Reservas.csv";
    private String exemplar_db = "./src/arquivos/Livros.csv";
    private String livro_db = "./src/arquivos/Exemplares.csv";
    
    
    public EmprestimoDAO(String nomeDoArquivo){
        this.nomeDoArquivo = nomeDoArquivo;
    }
        
    @Override
    public void incluir(Emprestimo emprestimo) throws Exception {
        try {
            ArrayList<Emprestimo> emprestimosCadastrados = this.listar();
            boolean exemplarLivre = true;
            for (int i = 0; i < emprestimosCadastrados.size(); i++) {
                Emprestimo temp = emprestimosCadastrados.get(i);
                if(temp.isAtivo() && temp.getExemplarID() == emprestimo.getExemplarID()){
                    exemplarLivre = false;
                }
            }
            
            if(exemplarLivre) {
                FileWriter fw = new FileWriter(nomeDoArquivo,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(emprestimo.desmaterializar() + "\n");
                bw.close();	
            } else {
                throw new Exception("O exemplar já se encontra emprestado.");
            }
 
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public ArrayList<Emprestimo> listar() throws Exception {
        try {
            ArrayList<Emprestimo> listaEmprestimos = new ArrayList();
            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br  = new BufferedReader(fr);
            String linha;
            while((linha=br.readLine()) != null){
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.materializar(linha);
                listaEmprestimos.add(emprestimo);
            }
            br.close();
            return listaEmprestimos;
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void alterar(Emprestimo emprestimo) throws Exception {
        try {
            ArrayList<Emprestimo> novosEmprestimos = new ArrayList<>();
            ArrayList<Emprestimo> emprestimosCadastrados = this.listar();
            for (int i = 0; i < emprestimosCadastrados.size(); i++) {
                Emprestimo temp = emprestimosCadastrados.get(i);
                if(temp.getId() == emprestimo.getId()) {
                    novosEmprestimos.add(emprestimo);
                } else {
                    novosEmprestimos.add(temp);
                }
            }
            
            FileWriter fw = new FileWriter(nomeDoArquivo,false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < novosEmprestimos.size(); i++) {
                Emprestimo novo = novosEmprestimos.get(i);
                bw.write(novo.desmaterializar() + "\n");
            }
            bw.close();	
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    //Retorna a quantidade de emprestimos ativos por cliente
    public int getQuantidadeDeEmprestimosDoCliente(int clienteID) throws Exception {
        try {
            int quantidadeDeEmprestimos = 0;
            ArrayList<Emprestimo> listaEmprestimos = this.listar();
            for (int i = 0; i < listaEmprestimos.size(); i++) {
                Emprestimo temp = listaEmprestimos.get(i);
                if(temp.getClienteID() == clienteID && temp.isAtivo()) {
                    quantidadeDeEmprestimos++;
                }
            }
            return quantidadeDeEmprestimos;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    //Retorna lista de emprestimos ativos
    public ArrayList<Emprestimo> getEmprestimosAtivos() throws Exception {
        try {
            ArrayList<Emprestimo> emprestimosAtivos = new ArrayList<>();
            ArrayList<Emprestimo> emprestimosCadastrados = this.listar();
            for (int i = 0; i < emprestimosCadastrados.size(); i++) {
                Emprestimo temp = emprestimosCadastrados.get(i);
                if(temp.isAtivo()) {
                    emprestimosAtivos.add(temp);
                }
            }
            return emprestimosAtivos;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    //Retorna um objeto emprestimo de acordo com a id passada via parametro
    public Emprestimo getEmprestimoById(int emprestimoID) throws Exception {
        try {
            ArrayList<Emprestimo> emprestimosCadastrados = this.listar();
            Emprestimo emprestimo = null;
            for (int i = 0; i < emprestimosCadastrados.size(); i++) {
                Emprestimo temp = emprestimosCadastrados.get(i);
                if(temp.getId() == emprestimoID) {
                    emprestimo = temp;
                }
            }
            return emprestimo;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    //Retorna um arraylist de emprestimos de acordo com o titulo do livro selecionado
    public ArrayList<Emprestimo> getEmprestimosByTitulo(String titulo) throws Exception {
        try {
            ArrayList<Emprestimo> emprestimosEncontrados = null;
            ArrayList<Emprestimo> emprestimosCadastrados = this.listar(); 
            ExemplarDAO edao = new ExemplarDAO(exemplar_db);
            ArrayList<Exemplar> listaExemplares = edao.getExemplaresByTitulo(titulo);
            if(listaExemplares.size() > 0) {
                for (int i = 0; i < listaExemplares.size(); i++) {
                    Exemplar exemplar = listaExemplares.get(i);
                    for (int j = 0; j < emprestimosCadastrados.size(); j++) {
                        Emprestimo emprestimo = emprestimosCadastrados.get(j);
                        if(emprestimo.getExemplarID() == exemplar.getId()) {
                            emprestimosEncontrados.add(emprestimo);
                        }
                    }
                }
            }
            return emprestimosEncontrados;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
}
