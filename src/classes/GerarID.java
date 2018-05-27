package classes;

import interfaces.TratamentoDeDados;

public class GerarID implements TratamentoDeDados {

    private GerarID IDUnico = null;
    private int numeroID;
    private int valorInicial = 0;

    public GerarID() {
        numeroID = valorInicial;
    }

    public GerarID(int numero) {
        numeroID = numero;
    }

    public GerarID getInstance() {
        if (IDUnico == null) {
            IDUnico = new GerarID();
        }
        return IDUnico;
    }

    public int gerarNumeroUnico() {
        numeroID += 1;
        return numeroID;
    }

    @Override
    public void materializar(String dados) throws Exception {
        String vetorDados[] = dados.split(";");
        if (vetorDados.length != 1) {
            //throw new Exception("Faltam dados");
        }
        numeroID = Integer.parseInt(vetorDados[0]);

    }

    @Override
    public String desmaterializar() {
        String saida = numeroID + ";";
        return saida;
    }

    public int getNumeroID() {
        return numeroID;
    }

    public void setNumeroID(int numeroID) {
        this.numeroID = numeroID;
    }

    public int getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(int valorInicial) {
        this.valorInicial = valorInicial;
    }
}
