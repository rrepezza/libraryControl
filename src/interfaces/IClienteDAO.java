/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Cliente;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
    public interface IClienteDAO {
    public void incluir(Cliente cliente) throws Exception;
    public ArrayList<Cliente> listar() throws Exception;
    public void alterar(int id) throws Exception;
}
