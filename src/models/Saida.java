package models;

public class Saida {

    private int idSaida, idFechamento;
    private String descricao;
    private float valor;

    public Saida(int idSaida, int idFechamento, String descricao, float valor) {
        this.idSaida = idSaida;
        this.idFechamento = idFechamento;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Saida(String descricao, float valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Object[] saidaParaTabela() {
        return new Object[]{
            this.descricao,
            valorParaString(this.valor)
        };
    }

    private String valorParaString(float valor) {
        String novoValor = String.format("%.02f", valor);
        novoValor = novoValor.replace('.', ',');
        return novoValor;
    }

    public int getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(int idSaida) {
        this.idSaida = idSaida;
    }

    public int getIdFechamento() {
        return idFechamento;
    }

    public void setIdFechamento(int idFechamento) {
        this.idFechamento = idFechamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}
