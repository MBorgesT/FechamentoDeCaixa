package gui;

import dao.DAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Fechamento;
import models.Saida;
import validators.FechamentoFormValidator;

public class MaisInfoFechamento extends javax.swing.JFrame {

    private Fechamento fechamento;
    private boolean editando;

    public MaisInfoFechamento(Fechamento fechamento) {
        initComponents();

        this.fechamento = fechamento;
        this.editando = false;

        preencherCampos();
        enableBotoes(false);
        realizarCalculos();

        tabelaSaidas.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void camposEditaveis(boolean b) {
        campoEntrada.setEditable(b);
        campoValorCaixa.setEditable(b);
        campoValorDisplay.setEditable(b);
        campoCartao.setEditable(b);

        tabelaSaidas.setEnabled(b);
    }

    private void enableBotoes(boolean b) {
        botaoCalcularDiferenca.setEnabled(b);

        botaoAdicionarSaida.setEnabled(b);
        botaoRemoverSaida.setEnabled(b);

        botaoCancelarEdicao.setEnabled(b);
    }

    private void preencherCampos() {
        labelId.setText("ID: " + fechamento.getIdFechamento());

        campoData.setText(DAO.selectDataFromDia(fechamento.getIdDia()));

        if (fechamento.getTurno() == 0) {
            radioManha.setSelected(true);
        } else {
            radioTarde.setSelected(true);
        }

        campoEntrada.setText(valorParaString(fechamento.getEntrada()));
        campoValorCaixa.setText(valorParaString(fechamento.getValorCaixa()));
        campoValorDisplay.setText(valorParaString(fechamento.getValorDisplay()));
        campoCartao.setText(valorParaString(fechamento.getCartao()));

        preencherTabela();
    }

    private void preencherTabela() {
        DefaultTableModel saidaModel = (DefaultTableModel) tabelaSaidas.getModel();
        saidaModel.setRowCount(0);
        for (Saida saida : fechamento.getSaidas()) {
            saidaModel.addRow(saida.saidaParaTabela());
        }
    }

    private float stringParaValor(String valor) {
        String novoValor = valor.replaceAll(",", ".");
        return Float.parseFloat(novoValor);
    }

    private String valorParaString(float valor) {
        String novoValor = String.format("%.02f", valor);
        novoValor = novoValor.replace('.', ',');
        return novoValor;
    }

    private void adicionarLinhaTabela(JTable tabela) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(model.getRowCount() + 1);
    }

    private void removerLinhaTabela(JTable tabela) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(model.getRowCount() == 0 ? 0 : model.getRowCount() - 1);
    }
    
    private void realizarCalculos() {
        float entrada = stringParaValor(campoEntrada.getText());
        float valorCaixa = stringParaValor(campoValorCaixa.getText());
        float valorDisplay = stringParaValor(campoValorDisplay.getText());
        float cartao = stringParaValor(campoCartao.getText());

        DefaultTableModel saidasModel = (DefaultTableModel) tabelaSaidas.getModel();
        float saida = 0;
        for (int i = 0; i < saidasModel.getRowCount(); i++) {
            saida += stringParaValor(String.valueOf(saidasModel.getValueAt(i, 1)));
        }
        campoTotalSaidas.setText(valorParaString(saida));

        float diferenca = (valorCaixa + saida + cartao) - (entrada + valorDisplay);

        campoDiferenca.setText(valorParaString(diferenca));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        turnoButtonGroup = new javax.swing.ButtonGroup();
        formPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campoValorCaixa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        radioManha = new javax.swing.JRadioButton();
        radioTarde = new javax.swing.JRadioButton();
        campoEntrada = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        scrollPaneSaidas = new javax.swing.JScrollPane();
        tabelaSaidas = new javax.swing.JTable();
        botaoAdicionarSaida = new javax.swing.JButton();
        botaoRemoverSaida = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        campoTotalSaidas = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        campoData = new javax.swing.JFormattedTextField();
        campoValorDisplay = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        campoCartao = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        labelId = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        campoDiferenca = new javax.swing.JTextField();
        botaoCalcularDiferenca = new javax.swing.JButton();
        botaoEditarInfo = new javax.swing.JButton();
        botaoCancelarEdicao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Fechamento de Caixa");
        setResizable(false);

        formPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setText("<html>Valor que ficou no caixa na hora do fechamento:</html>");

        campoValorCaixa.setEditable(false);
        campoValorCaixa.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoValorCaixa.setName("campoValorCaixa"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Turno:");

        turnoButtonGroup.add(radioManha);
        radioManha.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioManha.setText("Manhã");
        radioManha.setEnabled(false);
        radioManha.setName("radioManha"); // NOI18N

        turnoButtonGroup.add(radioTarde);
        radioTarde.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioTarde.setText("Tarde");
        radioTarde.setEnabled(false);
        radioTarde.setName("radioTarde"); // NOI18N

        campoEntrada.setEditable(false);
        campoEntrada.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoEntrada.setName("campoEntrada"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("<html>Valor que começou no caixa:</html>");

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel5.setText("Saídas:");

        scrollPaneSaidas.setName("scrollPaneSaidas"); // NOI18N

        tabelaSaidas.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaSaidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Valor (R$)"
            }
        ));
        tabelaSaidas.setEnabled(false);
        tabelaSaidas.setName("tabelaSaidas"); // NOI18N
        scrollPaneSaidas.setViewportView(tabelaSaidas);
        if (tabelaSaidas.getColumnModel().getColumnCount() > 0) {
            tabelaSaidas.getColumnModel().getColumn(0).setPreferredWidth(250);
            tabelaSaidas.getColumnModel().getColumn(1).setPreferredWidth(90);
        }

        botaoAdicionarSaida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        botaoAdicionarSaida.setBorder(null);
        botaoAdicionarSaida.setBorderPainted(false);
        botaoAdicionarSaida.setContentAreaFilled(false);
        botaoAdicionarSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarSaidaActionPerformed(evt);
            }
        });

        botaoRemoverSaida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        botaoRemoverSaida.setBorder(null);
        botaoRemoverSaida.setBorderPainted(false);
        botaoRemoverSaida.setContentAreaFilled(false);
        botaoRemoverSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverSaidaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel7.setText("Total:");

        campoTotalSaidas.setEditable(false);
        campoTotalSaidas.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel9.setText("Data:");

        campoData.setEditable(false);
        try {
            campoData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoData.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoData.setName("campoData"); // NOI18N

        campoValorDisplay.setEditable(false);
        campoValorDisplay.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoValorDisplay.setName("campoValorDisplay"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel10.setText("<html>Valor no display na hora do fechamento:</html>");

        jLabel12.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel12.setText("<html>Valor das vendas no cartão:</html>");

        campoCartao.setEditable(false);
        campoCartao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoCartao.setName("campoCartao"); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoData)
                            .addComponent(campoEntrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(campoCartao, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(radioManha)
                                .addGap(18, 18, 18)
                                .addComponent(radioTarde))))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(campoValorCaixa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoValorDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoTotalSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(scrollPaneSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoRemoverSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoAdicionarSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(botaoAdicionarSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoRemoverSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollPaneSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(campoTotalSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioManha)
                                    .addComponent(radioTarde)))
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campoValorDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoValorCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jSeparator1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelId.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        labelId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/caixa_registradora_48.png"))); // NOI18N
        labelId.setText("ID: 22");

        jLabel11.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel11.setText("Diferença:");

        campoDiferenca.setEditable(false);
        campoDiferenca.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoDiferenca.setName("campoDiferenca"); // NOI18N

        botaoCalcularDiferenca.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        botaoCalcularDiferenca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calculator.png"))); // NOI18N
        botaoCalcularDiferenca.setText("Realizar Cálculos");
        botaoCalcularDiferenca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCalcularDiferencaActionPerformed(evt);
            }
        });

        botaoEditarInfo.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        botaoEditarInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        botaoEditarInfo.setText("Editar Informações");
        botaoEditarInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarInfoActionPerformed(evt);
            }
        });

        botaoCancelarEdicao.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        botaoCancelarEdicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        botaoCancelarEdicao.setText("Cancelar Edição");
        botaoCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarEdicaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(formPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(botaoCancelarEdicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoEditarInfo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCalcularDiferenca)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(campoDiferenca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(6, 6, 6)
                        .addComponent(campoDiferenca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoCalcularDiferenca, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelId)))
                .addGap(18, 18, 18)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelarEdicao)
                    .addComponent(botaoEditarInfo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCalcularDiferencaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCalcularDiferencaActionPerformed
        FechamentoFormValidator validador = new FechamentoFormValidator(formPanel);

        if (validador.validate()) {
            realizarCalculos();
        }
    }//GEN-LAST:event_botaoCalcularDiferencaActionPerformed

    private void botaoAdicionarSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarSaidaActionPerformed
        adicionarLinhaTabela(tabelaSaidas);
    }//GEN-LAST:event_botaoAdicionarSaidaActionPerformed

    private void botaoRemoverSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverSaidaActionPerformed
        removerLinhaTabela(tabelaSaidas);
    }//GEN-LAST:event_botaoRemoverSaidaActionPerformed

    private void botaoEditarInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarInfoActionPerformed
        if (editando) {
            String[] options = {"SIM", "NÃO"};
            int reply = JOptionPane.showOptionDialog(null, "Você realmente deseja salvar as alterações?", "Alterações",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    options, options[0]);

            if (reply == 0 && new FechamentoFormValidator(formPanel).validate()) {

                Saida[] saidas = new Saida[tabelaSaidas.getRowCount()];
                for (int i = 0; i < tabelaSaidas.getRowCount(); i++) {
                    saidas[i] = new Saida(
                            String.valueOf(tabelaSaidas.getValueAt(i, 0)),
                            stringParaValor(String.valueOf(tabelaSaidas.getValueAt(i, 1)))
                    );
                }

                fechamento.setEntrada(stringParaValor(campoEntrada.getText()));
                fechamento.setValorCaixa(stringParaValor(campoValorCaixa.getText()));
                fechamento.setValorDisplay(stringParaValor(campoValorDisplay.getText()));
                fechamento.setCartao(stringParaValor(campoCartao.getText()));

                fechamento.setSaidas(saidas);

                if (DAO.updateFechamento(fechamento)) {
                    this.editando = false;

                    enableBotoes(false);
                    camposEditaveis(false);

                    botaoEditarInfo.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png")));
                    botaoEditarInfo.setText("Editar Informações");

                    JOptionPane.showMessageDialog(null, "Fechamento de Caixa atualizado com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Algum erro ocorreu na atualização das informações. Favor reiniciar o programa e tentar novamente.", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            this.editando = true;

            botaoEditarInfo.setIcon(new ImageIcon(getClass().getResource("/icons/save-alterations.png")));
            botaoEditarInfo.setText("Salvar Alterações");

            enableBotoes(true);
            camposEditaveis(true);
        }
    }//GEN-LAST:event_botaoEditarInfoActionPerformed

    private void botaoCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarEdicaoActionPerformed
        this.editando = false;
        
        enableBotoes(false);
        camposEditaveis(false);
        preencherCampos();
        realizarCalculos();

        botaoEditarInfo.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png")));
        botaoEditarInfo.setText("Editar Informações");
    }//GEN-LAST:event_botaoCancelarEdicaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarSaida;
    private javax.swing.JButton botaoCalcularDiferenca;
    private javax.swing.JButton botaoCancelarEdicao;
    private javax.swing.JButton botaoEditarInfo;
    private javax.swing.JButton botaoRemoverSaida;
    private javax.swing.JTextField campoCartao;
    private javax.swing.JFormattedTextField campoData;
    private javax.swing.JTextField campoDiferenca;
    private javax.swing.JTextField campoEntrada;
    private javax.swing.JTextField campoTotalSaidas;
    private javax.swing.JTextField campoValorCaixa;
    private javax.swing.JTextField campoValorDisplay;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelId;
    private javax.swing.JRadioButton radioManha;
    private javax.swing.JRadioButton radioTarde;
    private javax.swing.JScrollPane scrollPaneSaidas;
    private javax.swing.JTable tabelaSaidas;
    private javax.swing.ButtonGroup turnoButtonGroup;
    // End of variables declaration//GEN-END:variables
}
