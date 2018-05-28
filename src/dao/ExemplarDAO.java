/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Exemplar;
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
    
    public ExemplarDAO(String nomeDoArquivo){
        this.nomeDoArquivo = nomeDoArquivo;
    }

    @Override
    public void incluir(Exemplar exemplar) throws Exception {
        try{
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivo,true);
            //Criar o buffer do arquivo
            BufferedWriter bw = new BufferedWriter(fw);
            //Escreve no arquivo
            bw.write(exemplar.desmaterializar() + "\n");
            //fecha o arquivo
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
            while((linha = br.readLine()) != null){
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
    public void alterar(int id) throws Exception {
        
    }
    
}
