
package models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Dia {
    
    private int idDia;
    private Calendar data;
    private Fechamento fechamentoManha, fechamentoTarde;

    public Dia(int idDia, Calendar data, Fechamento fechamentoManha, Fechamento fechamentoTarde) {
        this.idDia = idDia;
        this.data = data;
        this.fechamentoManha = fechamentoManha;
        this.fechamentoTarde = fechamentoTarde;
    }
    
    public Object[] diaParaTabela(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String strData = sdf.format(this.data.getTime());
        
        float totalFaturamento = (fechamentoManha == null ? 0 : fechamentoManha.getValorTotal()) + (fechamentoTarde == null ? 0 : fechamentoTarde.getValorTotal());
        float totalSaida = (fechamentoManha == null ? 0 : fechamentoManha.getTotalSaidas()) + (fechamentoTarde == null ? 0 : fechamentoTarde.getTotalSaidas());
        
        return new Object[]{
            strData,
            fechamentoManha == null ? "" : valorParaString(fechamentoManha.getValorTotal()),
            fechamentoManha == null ? "" : valorParaString(fechamentoManha.getTotalSaidas()),
            fechamentoTarde == null ? "" : valorParaString(fechamentoTarde.getValorTotal()),
            fechamentoTarde == null ? "" : valorParaString(fechamentoTarde.getTotalSaidas()),
            valorParaString(totalFaturamento),
            valorParaString(totalSaida)
        };
    }
    
    private String valorParaString(float valor){
        String novoValor = String.format("%.02f", valor);
        novoValor = novoValor.replace('.', ',');
        return novoValor;
    }

    public Dia(Calendar data) {
        this.data = data;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Fechamento getFechamentoManha() {
        return fechamentoManha;
    }

    public void setFechamentoManha(Fechamento fechamentoManha) {
        this.fechamentoManha = fechamentoManha;
    }

    public Fechamento getFechamentoTarde() {
        return fechamentoTarde;
    }

    public void setFechamentoTarde(Fechamento fechamentoTarde) {
        this.fechamentoTarde = fechamentoTarde;
    }
    
}
