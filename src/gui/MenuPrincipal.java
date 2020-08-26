package gui;

import dao.DAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Dia;

public class MenuPrincipal extends javax.swing.JFrame {

    Dia[] todosDias;
    Dia[] diasNaTabela;

    public MenuPrincipal() {
        initComponents();

        this.todosDias = DAO.selectAllDias();
        this.diasNaTabela = this.todosDias;

        this.todosDias = ordenarDias(this.todosDias, true);
        this.diasNaTabela = ordenarDias(this.diasNaTabela, false);

        preencherTabelaDias();
    }
    
    public void resetTabelaDias(){
        this.todosDias = DAO.selectAllDias();
        this.diasNaTabela = this.todosDias;
        
        preencherTabelaDias();
    }

    private void preencherTabelaDias() {
        DefaultTableModel model = (DefaultTableModel) tabelaFechamentos.getModel();
        model.setRowCount(0);
        for (Dia dia : diasNaTabela) {
            model.addRow(dia.diaParaTabela());
        }
    }

    private boolean validarDatas() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date dataDe = sdf.parse(campoDataDe.getText());
            Date dataAte = sdf.parse(campoDataAte.getText());

            if (dataAte.before(dataDe)) {
                JOptionPane.showMessageDialog(null, "A data De precisa ser antes de Até.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            return true;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Alguma das datas está incorreta.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private Dia[] ordenarDias(Dia[] dias, boolean crescente) {
        for (int i = 0; i < dias.length; i++) {
            for (int j = i + 1; j < dias.length; j++) {
                Date diaI = dias[i].getData().getTime();
                Date diaJ = dias[j].getData().getTime();

                if (crescente) {
                    if (diaI.after(diaJ)) {
                        Dia aux = dias[i];
                        dias[i] = dias[j];
                        dias[j] = aux;
                    }
                } else {
                    if (diaI.before(diaJ)) {
                        Dia aux = dias[i];
                        dias[i] = dias[j];
                        dias[j] = aux;
                    }
                }
            }
        }

        return dias.clone();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFechamentos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campoDataDe = new javax.swing.JFormattedTextField();
        campoDataAte = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        botaoBuscar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        botaoNovoFechamento = new javax.swing.JButton();
        botaoMaisInformacoes = new javax.swing.JButton();
        botaoRelatorio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fechamento de Caixa");
        setResizable(false);

        tabelaFechamentos.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaFechamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Total Manhã", "Saída Manhã", "Total Tarde", "Saída Tarde", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaFechamentos);

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel1.setText("Busca:");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("De:");

        try {
            campoDataDe.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoDataDe.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        try {
            campoDataAte.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoDataAte.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("Até:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoDataDe)
                    .addComponent(campoDataAte)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDataDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDataAte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoBuscar.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        botaoBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBuscarActionPerformed(evt);
            }
        });

        botaoLimpar.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        botaoLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.png"))); // NOI18N
        botaoLimpar.setText("Limpar");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        botaoNovoFechamento.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        botaoNovoFechamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nova_entrada_32.png"))); // NOI18N
        botaoNovoFechamento.setText("Novo Fechamento");
        botaoNovoFechamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoFechamentoActionPerformed(evt);
            }
        });

        botaoMaisInformacoes.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        botaoMaisInformacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/more-info.png"))); // NOI18N
        botaoMaisInformacoes.setText("Mais Informações");
        botaoMaisInformacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMaisInformacoesActionPerformed(evt);
            }
        });

        botaoRelatorio.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        botaoRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/relatorio_32.png"))); // NOI18N
        botaoRelatorio.setText("Relatório");
        botaoRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoMaisInformacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(botaoNovoFechamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoNovoFechamento)
                .addGap(18, 18, 18)
                .addComponent(botaoRelatorio)
                .addGap(18, 18, 18)
                .addComponent(botaoMaisInformacoes)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoBuscar))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoBuscar)
                            .addComponent(botaoLimpar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoNovoFechamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoFechamentoActionPerformed
        new NovoFechamento(this).setVisible(true);
    }//GEN-LAST:event_botaoNovoFechamentoActionPerformed

    private void botaoMaisInformacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMaisInformacoesActionPerformed
        int row = tabelaFechamentos.getSelectedRow();
        if (row >= 0) {
            Dia diaSelecionado = diasNaTabela[row];

            String[] options = {"Manhã", "Tarde"};
            int reply = JOptionPane.showOptionDialog(null, "Favor selecionar um turno.", "Mais Informações",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    options, options[0]);

            if (reply == 0) {
                if (diaSelecionado.getFechamentoManha() != null) {
                    new MaisInfoFechamento(diaSelecionado.getFechamentoManha()).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Este fechamento de caixa não está cadastrado.", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            } else if (reply == 1) {
                if (diaSelecionado.getFechamentoTarde() != null) {
                    new MaisInfoFechamento(diaSelecionado.getFechamentoTarde()).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Este fechamento de caixa não está cadastrado.", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Favor selecionar um elemento da tabela.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_botaoMaisInformacoesActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed
        if (validarDatas()) {
            try {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                Date dataDe = sdf.parse(campoDataDe.getText());
                Date dataAte = sdf.parse(campoDataAte.getText());

                int inicio = -1;
                Date dataAux;

                for (int i = 0; i < todosDias.length; i++) {
                    dataAux = todosDias[i].getData().getTime();
                    if ((sdf.format(dataAte).equals(sdf.format(dataAux)) || dataAte.after(dataAux)) &&
                            (sdf.format(dataDe).equals(sdf.format(dataAux)) || dataDe.before(dataAux))) {
                        inicio = i;
                        break;
                    }
                }

                if (inicio != -1) {
                    
                    int fim = -1;
                    
                    for (int i = inicio; i < todosDias.length; i++) {
                        dataAux = todosDias[i].getData().getTime();
                        if (sdf.format(dataAte).equals(sdf.format(dataAux)) || dataAte.before(dataAux)) {
                            fim = i + 1;
                            break;
                        }
                    }

                    if (fim == -1) {
                        fim = todosDias.length;
                    }

                    Dia[] aux;
                    if (inicio <= fim) {
                        aux = Arrays.copyOfRange(todosDias, inicio, fim);
                    } else {
                        aux = Arrays.copyOfRange(todosDias, fim, inicio);
                    }

                    this.diasNaTabela = aux;
                    ordenarDias(this.diasNaTabela, false);
                    preencherTabelaDias();
                    
                } else {
                    this.diasNaTabela = new Dia[0];
                    preencherTabelaDias();
                }

            } catch (ParseException ex) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRelatorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoRelatorioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoMaisInformacoes;
    private javax.swing.JButton botaoNovoFechamento;
    private javax.swing.JButton botaoRelatorio;
    private javax.swing.JFormattedTextField campoDataAte;
    private javax.swing.JFormattedTextField campoDataDe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaFechamentos;
    // End of variables declaration//GEN-END:variables
}
