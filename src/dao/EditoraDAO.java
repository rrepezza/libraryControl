/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Editora;
import interfaces.IEditoraDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
        try{
            ArrayList<Editora> listaDeEditoras = this.consultar();
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivo);
            //Criar o buffer do arquivo
            BufferedWriter bw =new BufferedWriter(fw);
            for(int pos=0;pos<listaDeEditoras.size();pos++){
                Editora temp = listaDeEditoras.get(pos);
                if(!(temp.getId() == id)){
                   bw.write(temp.desmaterializar()+"\n");
                }
            }
            bw.close();
        }catch(Exception erro){
            throw erro;
        }
    }

    @Override
    public void alterar(int id) throws Exception {
        
    }

    @Override
    public void incluir(Editora editora) throws Exception {
        try{
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivo,true);
            //Criar o buffer do arquivo
            BufferedWriter bw = new BufferedWriter(fw);
            //Escreve no arquivo
            bw.write(editora.desmaterializar() + "\n");
            //fecha o arquivo
            bw.close();		
        }catch(Exception erro){
            throw erro;
        }
    }

    @Override
    public ArrayList<Editora> consultar() throws Exception {
        try {
            ArrayList<Editora> editoras = new ArrayList<Editora>();
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
    
    public Editora getEditoraByNome(String nome) throws Exception {
        Editora editora = null;
        
        ArrayList<Editora> listaEditoras = this.consultar();
        
        for (int i = 0; i < listaEditoras.size(); i++) {
            Editora temp = listaEditoras.get(i);
            
            if(nome.equals(temp.getNome())) {
                editora.setId(temp.getId());
                editora.setNome(temp.getNome());
            }
            
        }
        
        return editora;
    }
    
}
