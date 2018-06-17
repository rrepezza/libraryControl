/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Emprestimo;
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
    
    //Renova emprestimo de um determinado exemplar, caso nao exista reserva ativa do mesmo
    public void renovarEmprestimo(int id) throws Exception {
        try {
            ReservaDAO rdao = new ReservaDAO(reserva_db);
            if(!rdao.existeReserva(id)) {
                //implementar alteracao no registro do emprestimo
                //alterar a data de emprestimo e a data de retorno
            }
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
    
}
