package models;

public class Fechamento {

    private int idFechamento, idDia;
    private int turno; // 0 - manhã; 1 - tarde
    private float entrada, valorCaixa, valorDisplay, cartao;
    private Saida[] saidas;

    public Fechamento(int idFechamento, int idDia, int turno, float entrada, float valorTotal, float valorDisplay, float cartao, Saida[] saidas) {
        this.idFechamento = idFechamento;
        this.idDia = idDia;
        this.turno = turno;
        this.entrada = entrada;
        this.valorCaixa = valorTotal;
        this.valorDisplay = valorDisplay;
        this.cartao = cartao;
        this.saidas = saidas;
    }

    public Fechamento(int turno, float entrada, float valorTotal, float valorDisplay, float cartao, Saida[] saidas) {
        this.turno = turno;
        this.entrada = entrada;
        this.valorCaixa = valorTotal;
        this.valorDisplay = valorDisplay;
        this.cartao = cartao;
        this.saidas = saidas;
    }
    
    public float getValorTotal(){
        return (valorCaixa + cartao - (entrada + this.getTotalSaidas()));
    }
    
    public float getTotalSaidas(){
        float totalSaidas = 0;
        for (Saida s: saidas)
            totalSaidas += s.getValor();
        
        return totalSaidas;
    }

    public int getIdFechamento() {
        return idFechamento;
    }

    public void setIdFechamento(int idFechamento) {
        this.idFechamento = idFechamento;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public float getEntrada() {
        return entrada;
    }

    public void setEntrada(float entrada) {
        this.entrada = entrada;
    }

    public float getValorCaixa() {
        return valorCaixa;
    }

    public void setValorCaixa(float valorCaixa) {
        this.valorCaixa = valorCaixa;
    }

    public float getValorDisplay() {
        return valorDisplay;
    }

    public void setValorDisplay(float valorDisplay) {
        this.valorDisplay = valorDisplay;
    }

    public float getCartao() {
        return cartao;
    }

    public void setCartao(float cartao) {
        this.cartao = cartao;
    }

    public Saida[] getSaidas() {
        return saidas;
    }

    public void setSaidas(Saida[] saidas) {
        this.saidas = saidas;
    }

}
