/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Autor;
import java.util.ArrayList;

/**
 *
 * @author repez
 */
public interface IAutorDAO {
    public void incluir(Autor autor) throws Exception;
    public ArrayList<Autor> consultar() throws Exception;
    public void excluir(int id) throws Exception;
    public void alterar(int id) throws Exception;
}
