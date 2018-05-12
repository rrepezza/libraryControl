/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import classededados.Contato;
import java.util.ArrayList;

/**
 *
 * @author eugeniojulio
 */
public interface CRUD {
    void incluir(Contato objeto) throws Exception;
    public ArrayList<Contato> recuperar() throws Exception;
    public void excluir(String nome) throws Exception;
    
}
