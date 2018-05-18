/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Exemplar;
import interfaces.IExemplarDAO;
import java.io.BufferedReader;
import java.io.FileReader;
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
    
    }

    @Override
    public ArrayList<Exemplar> consultar() throws Exception {
        try {
            ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
            FileReader fr = new FileReader(nomeDoArquivo);
   
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            while((linha=br.readLine()) != null){
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
    public void excluir(int id) throws Exception {
        
    }

    @Override
    public void alterar(int id) throws Exception {
        
    }
    
}
