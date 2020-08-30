
package models;

public class SaidaBeth extends Saida{
    
    public SaidaBeth(int idSaida, int idFechamento, String descricao, float valor) {
        super(idSaida, idFechamento, descricao, valor);
    }
    
    public SaidaBeth(String descricao, float valor){
        super(descricao, valor);
    }
    
}
