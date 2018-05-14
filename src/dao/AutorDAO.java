/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Autor;
import interfaces.IAutorDAO;
import java.util.ArrayList;

/**
 *
 * @author repez
 */
public class AutorDAO implements IAutorDAO {

    @Override
    public ArrayList<Autor> consultar() throws Exception {
        ArrayList autores = new ArrayList();
        
        return autores;
    }

    @Override
    public void excluir(int id) throws Exception {
        
    }

    @Override
    public void alterar(int id) throws Exception {
        
    }

    @Override
    public void incluir(Autor autor) throws Exception {
        
    }
    
}
