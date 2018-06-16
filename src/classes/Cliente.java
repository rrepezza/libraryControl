/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import interfaces.TratamentoDeDados;
import java.io.Serializable;

/**
 *
 * @author aluno
 */

    public class Cliente implements TratamentoDeDados, Serializable {
    
    private int id = 0;
    private String cpf = "";
    private String telefone = "";
    private String uf = "";
    private String tipoPessoa = "";
    private String nome = "";
    private String endereco = "";
    private String cidade = "";
    private String email = "";
    private float saldoDevedor = 0;
    //private int quantidadeReservas = 0;
    //private int quantidadeEmprestimos = 0;
    
    public Cliente() {
        
    }
    
    public Cliente(int id, String nome, String cpf, String cidade, String uf, String email, 
            String endereco, String telefone, String tipoPessoa, float saldoDevedor) {
        
        this.id = id;
        this.endereco = endereco;
        this.nome = nome;
        this.cpf = cpf;
        this.cidade = cidade;
        this.uf = uf;
        this.email = email;
        this.telefone = telefone;
        this.tipoPessoa = tipoPessoa;
        this.saldoDevedor = saldoDevedor;
        //this.quantidadeReservas = quantidadeReservas;
        //this.quantidadeEmprestimos = quantidadeEmprestimos;
        
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return the tipoPessoa
     */
    public String getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     * @param tipoPessoa the tipoPessoa to set
     */
    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the saldoDevedor
     */
    public float getSaldoDevedor() {
        return saldoDevedor;
    }

    /**
     * @param saldoDevedor the saldoDevedor to set
     */
    public void setSaldoDevedor(float saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }
    
    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
    

    @Override
    public void materializar(String dados) throws Exception {
        if(!dados.isEmpty()) {
            String vetorString[] = dados.split(";");
            if(vetorString.length != 10) {
                throw new Exception("Faltam dados na String");
            }
            
            id = Integer.parseInt(vetorString[0]);
            cpf = vetorString[1];
            nome = vetorString[2];
            email = vetorString[3];
            telefone = vetorString[4];
            endereco = vetorString[5];
            cidade = vetorString[6];
            uf = vetorString[7];
            tipoPessoa = vetorString[8];
            saldoDevedor = Float.parseFloat(vetorString[9]);
            //quantidadeReservas = Integer.parseInt(vetorString[10]);
            //quantidadeEmprestimos = Integer.parseInt(vetorString[11]);
   
        }
    }

    @Override
    public String desmaterializar() {
        String saida = getId() + ";" + getCpf()+ ";" + getNome()+ ";" + getEmail()+ ";" 
            + getTelefone()+ ";" + getEndereco() + ";" + getCidade() + ";"
            + getUf() + ";" + getTipoPessoa() + ";" + getSaldoDevedor();            
        return saida;
    }
    
    /*
    public String desmaterializar() {
        String saida = getId() + ";" + getCpf()+ ";" + getNome()+ ";" + getEmail()+ ";" 
            + getTelefone()+ ";" + getEndereco() + ";" + getCidade() + ";"
            + getUf() + ";" + getTipoPessoa() + ";" + getSaldoDevedor() + ";"
            + getQuantidadeReservas() + ";" + getQuantidadeEmprestimos();
        return saida;
    }
    */

}

