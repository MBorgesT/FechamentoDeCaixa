package models;

public class Fechamento {

    private int idFechamento, idDia;
    private int turno; // 0 - manhã; 1 - tarde
    private float entrada, valorCaixa, valorDisplay, cartao;
    private Saida[] saidas;
    private SaidaBeth[] saidasBeth;

    public Fechamento(int idFechamento, int idDia, int turno, float entrada, float valorTotal, float valorDisplay, float cartao, Saida[] saidas, SaidaBeth[] saidasBeth) {
        this.idFechamento = idFechamento;
        this.idDia = idDia;
        this.turno = turno;
        this.entrada = entrada;
        this.valorCaixa = valorTotal;
        this.valorDisplay = valorDisplay;
        this.cartao = cartao;
        this.saidas = saidas;
        this.saidasBeth = saidasBeth;
    }

    public Fechamento(int turno, float entrada, float valorTotal, float valorDisplay, float cartao, Saida[] saidas, SaidaBeth[] saidasBeth) {
        this.turno = turno;
        this.entrada = entrada;
        this.valorCaixa = valorTotal;
        this.valorDisplay = valorDisplay;
        this.cartao = cartao;
        this.saidas = saidas;
        this.saidasBeth = saidasBeth;
    }

    public float getValorTotal() {
        return (valorCaixa + cartao + this.getTotalSaidasBeth() + this.getTotalSaidas() - entrada);
    }

    public float getTotalSaidas() {
        float totalSaidas = 0;
        for (Saida s : saidas) {
            totalSaidas += s.getValor();
        }

        return totalSaidas;
    }

    public float getTotalSaidasBeth() {
        float totalSaidasBeth = 0;
        for (SaidaBeth sb: saidasBeth){
            totalSaidasBeth += sb.getValor();
        }
        
        return totalSaidasBeth;
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

    public SaidaBeth[] getSaidasBeth() {
        return saidasBeth;
    }

    public void setSaidasBeth(SaidaBeth[] saidasBeth) {
        this.saidasBeth = saidasBeth;
    }

}
