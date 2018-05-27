/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.GerarID;
import java.util.ArrayList;

/**
 *
 * @author jhene
 */
public interface IGerarID {
    public void incluir(GerarID ID) throws Exception;
    public ArrayList<GerarID> consultar() throws Exception;
    public void excluir(int id) throws Exception;
    public void alterar(int id) throws Exception;
}
