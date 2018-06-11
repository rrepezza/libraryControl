/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import interfaces.TratamentoDeDados;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author repez
 */
public class Reserva implements TratamentoDeDados {
    
    private int id = 0;
    private int exemplarID = 0;
    private int clienteID = 0;
    private Date dataReserva = null;
    private boolean ativa = true;
    
    public Reserva() {
        
    }
    
    public Reserva(int id, int exemplarID, int clienteID, Date dataReserva) {
        this.id = id;
        this.exemplarID = exemplarID;
        this.clienteID = clienteID;
        this.dataReserva = dataReserva;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the exemplarID
     */
    public int getExemplarID() {
        return exemplarID;
    }

    /**
     * @param exemplarID the exemplarID to set
     */
    public void setExemplarID(int exemplarID) {
        this.exemplarID = exemplarID;
    }

    /**
     * @return the clienteID
     */
    public int getClienteID() {
        return clienteID;
    }

    /**
     * @param clienteID the clienteID to set
     */
    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    /**
     * @return the ativa
     */
    public boolean isAtiva() {
        return ativa;
    }

    /**
     * @param ativa the ativa to set
     */
    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
    
     /**
     * @return the dataReserva
     */
    public Date getDataReserva() {
        return dataReserva;
    }

    /**
     * @param dataReserva the dataReserva to set
     */
    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }
    
    @Override
    public void materializar(String dados) throws Exception {
        if(!dados.isEmpty()) {
            String vetorString[] = dados.split(";");
            if(vetorString.length != 5) {
                throw new Exception("Faltam dados na String");
            }
            
            id = Integer.parseInt(vetorString[0]);
            exemplarID = Integer.parseInt(vetorString[1]);
            clienteID = Integer.parseInt(vetorString[2]);
            DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            dataReserva = (Date) formatoData.parse(vetorString[3]);
            ativa = Boolean.parseBoolean(vetorString[4]);
        }
    }

    @Override
    public String desmaterializar() {
        String saida = getId() + ";" + getExemplarID() + ";" + getClienteID() + ";";
        saida += getDataReserva() + ";" + isAtiva();         
        return saida;
    }

   
}
