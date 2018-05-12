/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import java.util.ArrayList;

/**
 *
 * @author eugeniojulio
 */
public interface CRUD {
    public void incluir() throws Exception;
    public ArrayList<Object> consultar() throws Exception;
    public void excluir(int id) throws Exception;
    public void alterar(int id) throws Exception;
    
}
