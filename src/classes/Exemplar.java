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
public class Exemplar implements TratamentoDeDados {
    
    private int id = 0;
    private boolean disponivel = false; 
    private int livroID = 0;
    private boolean exemplarReserva = true;
    private Date disponivelAPartirDe = null;
    
    public Exemplar() {
        
    }
    
    public Exemplar(int id, int livroID, Date disponivelAPartirDe) {
        this.id = id;
        this.livroID = livroID;  
        this.disponivelAPartirDe = disponivelAPartirDe;
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
     * @return the disponivel
     */
    public boolean IsDisponivel() {
        return disponivel;
    }

    /**
     * @param disponivel the isDisponivel to set
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * @return the livro
     */
    public int getLivroID() {
        return livroID;
    }

    /**
     * @param livroID
     */
    public void setLivroID(int livroID) {
        this.livroID = livroID;
    }
    
    /**
     * @return the exemplarFixo
     */
    public boolean isExemplarReserva() {
        return exemplarReserva;
    }

    /**
     * @param exemplarReserva the exemplarFixo to set
     */
    public void setExemplarReserva(boolean exemplarReserva) {
        this.exemplarReserva = exemplarReserva;
    }
    
    /**
     * @return the disponivelAPartirDe
     */
    public Date getDisponivelAPartirDe() {
        return disponivelAPartirDe;
    }

    /**
     * @param disponivelAPartirDe the disponivelEm to set
     */
    public void setDisponivelAPartirDe(Date disponivelAPartirDe) {
        this.disponivelAPartirDe = disponivelAPartirDe;
    }
    
    @Override
    public void materializar(String dados) throws Exception {
        if(!dados.isEmpty()) {
            String vetorString[] = dados.split(";");
            if(vetorString.length != 5) {
                throw new Exception("Faltam dados na String");
            }

            id = Integer.parseInt(vetorString[0]);
            disponivel = Boolean.parseBoolean(vetorString[1]);
            livroID = Integer.parseInt(vetorString[2]);
            exemplarReserva = Boolean.parseBoolean(vetorString[3]);
            DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            disponivelAPartirDe = (Date) formatoData.parse(vetorString[4]);
        
        }
    }

    @Override
    public String desmaterializar() {
        String saida = getId() + ";" + IsDisponivel() + ";" + getLivroID() + ";" + isExemplarReserva() + ";";
        DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        saida += formatoData.format(getDisponivelAPartirDe());
        return saida;
    }

}
