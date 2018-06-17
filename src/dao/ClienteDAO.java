/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Cliente;
import classes.Emprestimo;
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
    private String emprestimo_db = "./src/arquivos/Emprestimos.csv";
    
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
    public void alterar(Cliente cliente) throws Exception {
        try {
            ArrayList<Cliente> clientesCadastrados = this.listar();
            ArrayList<Cliente> novosClientes = new ArrayList();
            for (int i = 0; i < clientesCadastrados.size(); i++) {
                Cliente temp = clientesCadastrados.get(i);
                if(temp.getId() == cliente.getId()) {
                    novosClientes.add(cliente);
                }else{
                    novosClientes.add(temp);
                }
            }
            
            FileWriter fw = new FileWriter(nomeDoArquivo,false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < novosClientes.size(); i++) {
                Cliente novo = novosClientes.get(i);
                bw.write(novo.desmaterializar() + "\n");
            }
            bw.close();	
        } catch (Exception erro) {
            throw erro;
        }
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
     
    public Cliente getClienteById(int clienteID) throws Exception {
        try {
            Cliente encontrado = null;
            ArrayList<Cliente> listaClientes = this.listar();
            for (int i = 0; i < listaClientes.size(); i++) {
                Cliente temp = listaClientes.get(i);
                if(temp.getId() == clienteID) {
                    encontrado = temp;
                }
            }
            return encontrado;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    public ArrayList<Cliente> getClientesSemDivida() throws Exception {
        try {
            ArrayList<Cliente> clientesSemDivida = new ArrayList<>();
            ArrayList<Cliente> listaClientes = this.listar();
            for (Cliente cliente : listaClientes) {
                if(cliente.getSaldoDevedor() == 0){
                    clientesSemDivida.add(cliente);
                }
            }
            return clientesSemDivida;
        } catch (Exception erro) {
            throw erro;
        }
    }
    
    public ArrayList<Cliente> getClientesComEmprestimosAtivos() throws Exception {
        try {
            ArrayList<Cliente> clientesComEmprestimos = new ArrayList<>();
            ArrayList<Cliente> clientesCadastrados = this.listar();
            EmprestimoDAO empdao = new EmprestimoDAO(emprestimo_db);
            ArrayList<Emprestimo> listaEmprestimosAtivos = empdao.getEmprestimosAtivos();
            for (int i = 0; i < clientesCadastrados.size(); i++) {
                Cliente temp = clientesCadastrados.get(i);
                for (int j = 0; j < listaEmprestimosAtivos.size(); j++) {
                    Emprestimo emp = listaEmprestimosAtivos.get(j);
                    if(emp.getClienteID() == temp.getId()) {
                        clientesComEmprestimos.add(temp);
                    }
                }
            }
            return clientesComEmprestimos;
        } catch (Exception erro) {
            throw erro;
        }
    }
}


