/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Emprestimo;
import java.util.ArrayList;

/**
 *
 * @author repez
 */
public interface IEmprestimoDAO {
    public void incluir(Emprestimo emprestimo) throws Exception;
    public ArrayList<Emprestimo> listar() throws Exception;
    public void alterar(Emprestimo emprestimo) throws Exception;
}
