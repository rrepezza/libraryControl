
import classes.Editora;
import classes.Livro;
import dao.EditoraDAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author repez
 */
public class Teste {
    public static void main(String[] args) {
        
        try {
            //String editora_db = "P:\\Drive\\Graduação ADS\\2SEM\\Programação Orientada a Objetos\\libraryControl\\src\\arquivos\\Editoras.csv";
            String livro_db = "D:\\Drive\\Graduação ADS\\2SEM\\Programação Orientada a Objetos\\libraryControl\\src\\arquivos\\Livros.csv";
            
            ArrayList<Livro> livros = new ArrayList<Livro>();
            FileReader fr = new FileReader(livro_db);
   
            BufferedReader br  = new BufferedReader(fr);
            String linha = null;
            while((linha=br.readLine()) != null){
                System.out.println(linha);
                //Editora editora = new Editora();
                //editora.materializar(linha);
                //editoras.add(editora);
            }
            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
