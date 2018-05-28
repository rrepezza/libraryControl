/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Exemplar;
import java.util.ArrayList;

/**
 *
 * @author repez
 */
public interface IExemplarDAO {
    public void incluir(Exemplar exemplar) throws Exception;
    public ArrayList<Exemplar> listar() throws Exception;
    public void alterar(int id) throws Exception;
}
