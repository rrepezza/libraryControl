/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Cliente;
import interfaces.IClienteDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public class ClienteDAO implements IClienteDAO{
    
    private String nomeDoArquivo = "";
    
    public ClienteDAO(String nomeDoArquivo){
        this.nomeDoArquivo = nomeDoArquivo;
    }

    @Override
    public ArrayList<Cliente> listar() throws Exception {
        try {
            ArrayList<Cliente> clientes = new ArrayList();
            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br  = new BufferedReader(fr);
            String linha;
            while((linha=br.readLine()) != null){
                Cliente cliente = new Cliente();
                cliente.materializar(linha);
                clientes.add(cliente);
            }
            br.close();
            return clientes;
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void alterar(int id) throws Exception {
        
    }

    @Override
    public void incluir(Cliente cliente) throws Exception {
        try{
            //verificando antes de incluir se já existe um cliente
            //cadastrado com o cpf informado
            ArrayList<Cliente> clientesCadastrados = this.listar();
            boolean encontrado = false;
            for (int i = 0; i < clientesCadastrados.size(); i++) {
                Cliente cadastrado = clientesCadastrados.get(i);
                if(cadastrado.getCpf().equals(cliente.getCpf())){
                    encontrado = true;
                }
            }
            
            //caso não exista, inclui
            if(!encontrado) {
                FileWriter fw = new FileWriter(nomeDoArquivo,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(cliente.desmaterializar() + "\n");
                bw.close();	
            } else {
                throw new Exception();
            }
            	
        }catch(Exception erro){
            throw erro;
        }
    }
    
    public Cliente getClienteByNome(String nome) throws Exception {
        Cliente clienteEncontrado = new Cliente();
        ArrayList<Cliente> listaClientes = this.listar();
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente temp = listaClientes.get(i);
            if(nome.equals(temp.getNome())) {
                clienteEncontrado = temp;
            }
        }
        return clienteEncontrado;
    }
    
    public ArrayList<Cliente> getClientesByNome(String nome) throws Exception {
        ArrayList<Cliente> clientesEncontrados = new ArrayList();
        ArrayList<Cliente> listaClientes = this.listar();
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente temp = listaClientes.get(i);
            if(temp.getNome().contains(nome)) {
                clientesEncontrados.add(temp);
            }
        }
        return clientesEncontrados;
    }
 
}


