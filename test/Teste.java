
import classes.Autor;
import classes.Editora;
import classes.Livro;
import dao.AutorDAO;
import dao.EditoraDAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import util.IDGenerator;

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
            
            String saida = "";
            AutorDAO adao = new AutorDAO("./src/arquivos/Autores.csv");
            ArrayList<Autor> lista = adao.listar();
            for (int i = 0; i < lista.size(); i++) {
                Autor a = lista.get(i);
                saida += a.desmaterializar() + "|";
            }
            
            String vaisefude[] = saida.split("\\|");
            ArrayList<Autor> autores = new ArrayList<>();
            for (int i = 0; i < vaisefude.length; i++) {
                Autor a = new Autor();
                a.materializar(vaisefude[i]);
                
                autores.add(a);
            }
            
            for (int i = 0; i < autores.size(); i++) {
                Autor adasdasds = autores.get(i);
                System.out.println(adasdasds.getId() + adasdasds.getNome());
                System.out.println();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
