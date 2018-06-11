/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
    public void alterar(int id) throws Exception {
        
    }
    
    public boolean existeReserva(int exemplarID) throws Exception {
        try {
            boolean hasReserva = false;
            ArrayList<Reserva> reservas = this.listar();
            for (int i = 0; i < reservas.size(); i++) {
                Reserva temp = reservas.get(i);
                if(temp.getExemplarID() == exemplarID) {
                    hasReserva = true;
                }
            }
            return hasReserva;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
}
