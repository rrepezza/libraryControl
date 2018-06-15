/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Exemplar;
import classes.Reserva;
import interfaces.IReservaDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author rodolpho.repezza
 */
public class ReservaDAO implements IReservaDAO {
    
    private String nomeDoArquivo = "";
    private String exemplar_db = "./src/arquivos/Exemplares.csv";
    
    public ReservaDAO(String nomeDoArquivo){
        this.nomeDoArquivo = nomeDoArquivo;
    }
    
    @Override
    public void incluir(Reserva reserva) throws Exception {
        try {
            ArrayList<Reserva> reservasCadastradas = this.listar();
            boolean flag = false;
            if(reservasCadastradas.size() > 0) {
                for (int i = 0; i < reservasCadastradas.size(); i++) {
                    Reserva temp = reservasCadastradas.get(i);
                    if(temp.getClienteID() == reserva.getClienteID() &&
                       temp.getExemplarID() == reserva.getExemplarID() &&
                       temp.getDataReserva() == reserva.getDataReserva()) {
                        flag = true;
                    }
                }
            }
            if(!flag) {
                FileWriter fw = new FileWriter(nomeDoArquivo,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(reserva.desmaterializar() + "\n");
                bw.close();	
            } else {
                throw new Exception("Já existe uma reserva com os dados informados.");
            }
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public ArrayList<Reserva> listar() throws Exception {
        try {
            ArrayList<Reserva> listaReservas = new ArrayList();
            FileReader fr = new FileReader(nomeDoArquivo);
   
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            while((linha=br.readLine()) != null){
                Reserva reserva = new Reserva();
                reserva.materializar(linha);
                listaReservas.add(reserva);
            }
            br.close();
            return listaReservas;
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void alterar(Reserva reserva) throws Exception {
        try {
            ArrayList<Reserva> reservasCadastradas = this.listar();
            ArrayList<Reserva> novasReservas = new ArrayList();
            for (int i = 0; i < reservasCadastradas.size(); i++) {
                Reserva temp = reservasCadastradas.get(i);
                if(temp.getId() == reserva.getId()) {
                    novasReservas.add(reserva);
                }else{
                    novasReservas.add(temp);
                }
            }
            
            FileWriter fw = new FileWriter(nomeDoArquivo,false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < novasReservas.size(); i++) {
                Reserva nova = novasReservas.get(i);
                bw.write(nova.desmaterializar() + "\n");
            }
            bw.close();	
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    //Verifica se um determinado exemplar possui alguma reserva não expirada
    public boolean existeReserva(int exemplarID) throws Exception {
        try {
            boolean hasReserva = false;
            ArrayList<Reserva> reservas = this.listar();
            for (int i = 0; i < reservas.size(); i++) {
                Reserva temp = reservas.get(i);
                if(temp.getExemplarID() == exemplarID && temp.isAtiva()) {
                    hasReserva = true;
                }
            }
            return hasReserva;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    //Retorna a quantidade de reservas ativas de um determinado cliente
    public int getQuantidadeDeReservasDoCliente(int clienteID) throws Exception {
        try {
            int quantidadeDeReservas = 0;
            ArrayList<Reserva> listaReservas = this.listar();
            for (int i = 0; i < listaReservas.size(); i++) {
                Reserva temp = listaReservas.get(i);
                if(temp.getClienteID() == clienteID && temp.isAtiva()) {
                    quantidadeDeReservas++;
                }
            }
            return quantidadeDeReservas;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    //Altera status da reserva para false, de acordo com a data de retorno do exemplar
    public void atualizarReservasExpiradas() throws Exception {
        try {
            ExemplarDAO edao = new ExemplarDAO(exemplar_db);
            ArrayList<Exemplar> listaExemplares = edao.getExemplaresDisponiveis();
            ArrayList<Reserva> listaReservas = this.listar();
            for (int i = 0; i < listaExemplares.size(); i++) {
                
            }
        } catch (Exception erro) {
            throw erro;
        }
    }
    
}
