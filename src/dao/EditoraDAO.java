/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Editora;
import interfaces.IEditoraDAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author repez
 */
public class EditoraDAO implements IEditoraDAO {
    
    private String nomeDoArquivo = "";
    
    public EditoraDAO(String nomeDoArquivo){
        this.nomeDoArquivo = nomeDoArquivo;
    }

    @Override
    public void excluir(int id) throws Exception {
        
    }

    @Override
    public void alterar(int id) throws Exception {
        
    }

    @Override
    public void incluir(Editora editora) throws Exception {
        
    }

    @Override
    public ArrayList<Editora> consultar() throws Exception {
        try {
            ArrayList editoras = new ArrayList();
            FileReader fr = new FileReader(nomeDoArquivo);
   
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            while((linha=br.readLine()) != null){
                Editora editora = new Editora();
                editora.materializar(linha);
                editoras.add(editora);
            }
            br.close();
            return editoras;
        } catch (Exception erro) {
            throw erro;
        }
         
    }   
    
}
