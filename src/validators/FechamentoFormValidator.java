
package validators;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FechamentoFormValidator {
    
    JFormattedTextField campoData;
    JRadioButton radioManha, radioTarde;
    JTextField campoEntrada, campoValorCaixa, campoValorDisplay, campoCartao;
    JScrollPane scrollPaneSaidas, scrollPaneFicaCaixa;
    JTable tabelaSaidas;
    
    public FechamentoFormValidator(JPanel panel){
        
        Component[] components = panel.getComponents();
        HashMap componentMap = new HashMap<String, Component>();
        for (int i = 0; i < components.length; i++) {
            componentMap.put(components[i].getName(), components[i]);
        }

        campoData = (JFormattedTextField) componentMap.get("campoData");
        
        radioManha = (JRadioButton) componentMap.get("radioManha");
        radioTarde = (JRadioButton) componentMap.get("radioTarde");
        
        campoEntrada = (JTextField) componentMap.get("campoEntrada");
        campoValorCaixa = (JTextField) componentMap.get("campoValorCaixa");
        campoValorDisplay = (JTextField) componentMap.get("campoValorDisplay");
        campoCartao = (JTextField) componentMap.get("campoCartao");
        
        scrollPaneSaidas = (JScrollPane) componentMap.get("scrollPaneSaidas");
        scrollPaneFicaCaixa = (JScrollPane) componentMap.get("scrollPaneFicaCaixa");
        
        components = scrollPaneSaidas.getViewport().getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i].getName().equals("tabelaSaidas")){
                tabelaSaidas = (JTable) components[i];
                break;
            }
        }
    }
    
    
    
    public boolean validate(){
        return (
                validarCamposVazios() &&
                validarData() &&
                validarTurnoSelecionado() &&
                validarValorEntrada() &&
                validarValorCaixa() &&
                validarValorDisplay() &&
                validarValorCartao() &&
                validarTabelaSaidas()
        );
    }
    
    
    
    private boolean validarCamposVazios(){
        if(
                campoData.getText().isEmpty() ||
                campoEntrada.getText().isEmpty() ||
                campoValorCaixa.getText().isEmpty() ||
                campoValorDisplay.getText().isEmpty() ||
                campoCartao.getText().isEmpty()
        ){
            JOptionPane.showMessageDialog(null, "Algum campo está vazio. Favor preenche-lo.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }else{
            return true;
        }
    }
    
            
    
    private boolean validarData(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(campoData.getText());
            
            return true;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "A Data está incorreta.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    
    
    private boolean validarTurnoSelecionado(){
        if (radioManha.isSelected() || radioTarde.isSelected()){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Favor selecionar um Turno.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    
    
    public boolean validarValorEntrada(){
        if (validarValorMonetario(campoEntrada.getText())){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "O valor da Entrada está incorreto.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    public boolean validarValorCaixa(){
        if (validarValorMonetario(campoValorCaixa.getText())){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "O Valor no Caixa está incorreto.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    public boolean validarValorDisplay(){
        if (validarValorMonetario(campoValorDisplay.getText())){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "O Valor no Display está incorreto.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    public boolean validarValorCartao(){
        if (validarValorMonetario(campoCartao.getText())){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "O valor do Cartão está incorreto.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    
    
    public boolean validarTabelaSaidas(){
        if (!validarCamposVaziosEmTabela(tabelaSaidas)){
            JOptionPane.showMessageDialog(null, "Favor preencher todos os campos da Tabela de Saídas.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if (!validarTabelaValoresNumericos(tabelaSaidas)){
            JOptionPane.showMessageDialog(null, "Algum valor na Tabela de Saídas está incorreto.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarCamposVaziosEmTabela(JTable tabela){
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        
        for (int i = 0; i < model.getRowCount(); i++){
            for (int j = 0; j < model.getColumnCount(); j++){
                if (String.valueOf(model.getValueAt(i, j)).isEmpty())
                    return false;
            }
        }
        
        return true;
    }
    
    private boolean validarTabelaValoresNumericos(JTable tabela){
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        String valor;
        
        for (int i = 0; i < model.getRowCount(); i++){
            valor = String.valueOf(model.getValueAt(i, 1));
            if (!validarValorMonetario(valor))
                return false;
        }
        
        return true;
    }
    
    
    
    private boolean validarValorMonetario(String valor){
        String novoValor = valor.replaceAll(",", ".");
        
        try {
            Float.parseFloat(novoValor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
