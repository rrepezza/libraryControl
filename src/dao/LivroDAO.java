/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Livro;
import interfaces.ILivroDAO;
import java.util.ArrayList;

/**
 *
 * @author repez
 */
public class LivroDAO implements ILivroDAO {

    @Override
    public void incluir(Livro livro) throws Exception {
        
    }

    @Override
    public ArrayList<Livro> consultar() throws Exception {
        ArrayList livros = new ArrayList();
        
        return livros;
    }

    @Override
    public void excluir(int id) throws Exception {
        
    }

    @Override
    public void alterar(int id) throws Exception {
        
    }

    
}
