/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Reserva;
import java.util.ArrayList;

/**
 *
 * @author rodolpho.repezza
 */
public interface IReservaDAO {
    public void incluir(Reserva reserva) throws Exception;
    public ArrayList<Reserva> listar() throws Exception;
    public void alterar(int id) throws Exception;
}
