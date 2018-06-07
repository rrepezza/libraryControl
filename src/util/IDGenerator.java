/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author repez
 */
public class IDGenerator {

    String id_db = "./src/arquivos/IDs.csv";
    
    public int getNovoID() throws Exception {
        try {
            int id = 0;
            File f = new File(id_db);
            Scanner s = new Scanner(f);
            if(s.hasNext()) {
                id = Integer.parseInt(s.nextLine()) + 1;
            }
            return id;
        } catch (Exception erro) {
            erro.printStackTrace();
            throw erro;
        }
    }
    
    public void gravaID(int id) throws Exception {
        try{
            //Abre o arquivo para gravacao e grava o novo id retornado
            FileWriter fw = new FileWriter(id_db, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(id + "\n");
            bw.close();		
           
        }catch(Exception erro){
            erro.printStackTrace();
            throw erro;
        }

    }
    
}
