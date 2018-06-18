
import classes.Autor;
import classes.Editora;
import classes.Livro;
import classes.Reserva;
import dao.AutorDAO;
import dao.EditoraDAO;
import dao.ReservaDAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
            
            ReservaDAO rdao = new ReservaDAO("./src/arquivos/Reservas.csv");
            ArrayList<Reserva> lista = rdao.getReservasAtivas();
            
            JOptionPane.showMessageDialog(null, lista.size());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
