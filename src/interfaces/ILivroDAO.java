/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Livro;
import java.util.ArrayList;

/**
 *
 * @author repez
 */
public interface ILivroDAO {
    public void incluir(Livro livro) throws Exception;
    public ArrayList<Livro> consultar() throws Exception;
    public void excluir(int id) throws Exception;
    public void alterar(int id) throws Exception;
}
