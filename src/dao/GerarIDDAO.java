package dao;
import classes.GerarID;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


/**
 *
 * @author jhenerson
 */
public class GerarIDDAO {
    

    private String nomeDoArquivo = "";
    
    public GerarIDDAO(String nomeDoArquivo){
        this.nomeDoArquivo = nomeDoArquivo;
    }

    
    public void excluir(int id) throws Exception {
        try{
            ArrayList<GerarID> listaDeIDs = this.consultar();
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivo);
            //Criar o buffer do arquivo
            BufferedWriter bw =new BufferedWriter(fw);
            for(int pos=0;pos<listaDeIDs.size();pos++){
                GerarID temp = listaDeIDs.get(pos);
                if(!(temp.getNumeroID() == id)){
                   bw.write(temp.desmaterializar()+"\n");
                }
            }
            bw.close();
        }catch(Exception erro){
            throw erro;
        }
    }

    
    public void alterar(int id) throws Exception {        
    }


    public void incluir(GerarID ID) throws Exception {
        try{
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivo,true);
            //Criar o buffer do arquivo
            BufferedWriter bw = new BufferedWriter(fw);
            //Escreve no arquivo
            bw.write(ID.desmaterializar() + "\n");
            //fecha o arquivo
            bw.close();		
        }catch(Exception erro){
            throw erro;
        }
    }

    
    public ArrayList<GerarID> consultar() throws Exception {
        try {
            ArrayList<GerarID> IDs = new ArrayList<GerarID>();
            FileReader fr = new FileReader(nomeDoArquivo);
   
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            while((linha=br.readLine()) != null){
                GerarID ID = new GerarID();
                ID.materializar(linha);
                IDs.add(ID);
            }
            br.close();
            return IDs;
        } catch (Exception erro) {
            throw erro;
        }
         
    }   
    
    public GerarID getIDByNumero(int numero) throws Exception {
        GerarID ID = null;
        
        ArrayList<GerarID> listaDeIDs = this.consultar();
        
        for (int i = 0; i < listaDeIDs.size(); i++) {
            GerarID temp = listaDeIDs.get(i);
            
            if(numero == temp.getNumeroID()) {
                ID = temp;
                
            }
            
        }
        
        return ID;
    }
    
}

