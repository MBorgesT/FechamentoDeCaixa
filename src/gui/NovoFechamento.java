package gui;

import dao.DAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Fechamento;
import models.Saida;
import models.SaidaBeth;
import validators.FechamentoFormValidator;

public class NovoFechamento extends javax.swing.JFrame {

    private MenuPrincipal menuPrincipal;
    
    public NovoFechamento(MenuPrincipal menuPrincipal) {
        initComponents();
        
        this.menuPrincipal = menuPrincipal;

        Calendar agora = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        campoData.setText(sdf.format(agora.getTime()));

        tabelaSaidas.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        tabelaSaidasBeth.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
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
        
        DefaultTableModel saidasBethModel = (DefaultTableModel) tabelaSaidasBeth.getModel();
        float saidaBeth = 0;
        for (int i = 0; i < saidasBethModel.getRowCount(); i++) {
            saidaBeth += stringParaValor(String.valueOf(saidasBethModel.getValueAt(i, 1)));
        }
        campoTotalSaidasBeth.setText(valorParaString(saidaBeth));

        float diferenca = (valorCaixa + saida + saidaBeth + cartao) - (entrada + valorDisplay) ;

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
        botaoAdicionarSaidas = new javax.swing.JButton();
        botaoRemoverSaida = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        campoTotalSaidas = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        campoData = new javax.swing.JFormattedTextField();
        campoValorDisplay = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        campoCartao = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        scrollPaneSaidasBeth = new javax.swing.JScrollPane();
        tabelaSaidasBeth = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        campoTotalSaidasBeth = new javax.swing.JTextField();
        botaoRemoverSaidaBeth = new javax.swing.JButton();
        botaoAdicionarSaidasBeth = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        botaoCadastrar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        campoDiferenca = new javax.swing.JTextField();
        botaoRealizarCalculos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Fechamento de Caixa");
        setResizable(false);

        formPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setText("<html>Valor que ficou no caixa na hora do fechamento:</html>");

        campoValorCaixa.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoValorCaixa.setName("campoValorCaixa"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Turno:");

        turnoButtonGroup.add(radioManha);
        radioManha.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioManha.setText("Manhã");
        radioManha.setName("radioManha"); // NOI18N

        turnoButtonGroup.add(radioTarde);
        radioTarde.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        radioTarde.setText("Tarde");
        radioTarde.setName("radioTarde"); // NOI18N

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
        tabelaSaidas.setName("tabelaSaidas"); // NOI18N
        scrollPaneSaidas.setViewportView(tabelaSaidas);
        if (tabelaSaidas.getColumnModel().getColumnCount() > 0) {
            tabelaSaidas.getColumnModel().getColumn(0).setPreferredWidth(250);
            tabelaSaidas.getColumnModel().getColumn(1).setPreferredWidth(90);
        }

        botaoAdicionarSaidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        botaoAdicionarSaidas.setBorder(null);
        botaoAdicionarSaidas.setBorderPainted(false);
        botaoAdicionarSaidas.setContentAreaFilled(false);
        botaoAdicionarSaidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarSaidasActionPerformed(evt);
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

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel9.setText("Data:");

        try {
            campoData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoData.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoData.setName("campoData"); // NOI18N

        campoValorDisplay.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoValorDisplay.setName("campoValorDisplay"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel10.setText("<html>Valor no display na hora do fechamento:</html>");

        jLabel12.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel12.setText("<html>Valor das vendas no cartão:</html>");

        campoCartao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoCartao.setName("campoCartao"); // NOI18N

        scrollPaneSaidasBeth.setName("scrollPaneSaidasBeth"); // NOI18N

        tabelaSaidasBeth.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaSaidasBeth.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Valor (R$)"
            }
        ));
        tabelaSaidasBeth.setName("tabelaSaidasBeth"); // NOI18N
        scrollPaneSaidasBeth.setViewportView(tabelaSaidasBeth);
        if (tabelaSaidasBeth.getColumnModel().getColumnCount() > 0) {
            tabelaSaidasBeth.getColumnModel().getColumn(0).setPreferredWidth(250);
            tabelaSaidasBeth.getColumnModel().getColumn(1).setPreferredWidth(90);
        }

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel6.setText("Saídas para a Beth:");

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel8.setText("Total:");

        campoTotalSaidasBeth.setEditable(false);
        campoTotalSaidasBeth.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        botaoRemoverSaidaBeth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus.png"))); // NOI18N
        botaoRemoverSaidaBeth.setBorder(null);
        botaoRemoverSaidaBeth.setBorderPainted(false);
        botaoRemoverSaidaBeth.setContentAreaFilled(false);
        botaoRemoverSaidaBeth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverSaidaBethActionPerformed(evt);
            }
        });

        botaoAdicionarSaidasBeth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        botaoAdicionarSaidasBeth.setBorder(null);
        botaoAdicionarSaidasBeth.setBorderPainted(false);
        botaoAdicionarSaidasBeth.setContentAreaFilled(false);
        botaoAdicionarSaidasBeth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarSaidasBethActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator2)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(formPanelLayout.createSequentialGroup()
                            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(formPanelLayout.createSequentialGroup()
                                    .addComponent(radioManha)
                                    .addGap(18, 18, 18)
                                    .addComponent(radioTarde))))
                        .addGroup(formPanelLayout.createSequentialGroup()
                            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(campoEntrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(campoCartao)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(campoValorCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(campoValorDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(formPanelLayout.createSequentialGroup()
                            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addGroup(formPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(campoTotalSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(formPanelLayout.createSequentialGroup()
                                    .addComponent(scrollPaneSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(botaoRemoverSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botaoAdicionarSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(18, 18, 18)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(formPanelLayout.createSequentialGroup()
                                    .addComponent(scrollPaneSaidasBeth, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(botaoRemoverSaidaBeth, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botaoAdicionarSaidasBeth, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel6)
                                .addGroup(formPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(campoTotalSaidasBeth, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
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
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoValorDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(formPanelLayout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(campoValorCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(formPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(campoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(formPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(campoCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(botaoAdicionarSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoRemoverSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollPaneSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(campoTotalSaidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(botaoAdicionarSaidasBeth, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoRemoverSaidaBeth, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollPaneSaidasBeth, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(campoTotalSaidasBeth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/nova_entrada_48.png"))); // NOI18N
        jLabel4.setText("Novo Fechamento de Caixa");

        botaoCadastrar.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        botaoCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ok.png"))); // NOI18N
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        botaoCancelar.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        botaoCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel11.setText("Diferença:");

        campoDiferenca.setEditable(false);
        campoDiferenca.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoDiferenca.setName("campoDiferenca"); // NOI18N

        botaoRealizarCalculos.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        botaoRealizarCalculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calculator.png"))); // NOI18N
        botaoRealizarCalculos.setText("Realizar Cálculos");
        botaoRealizarCalculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRealizarCalculosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoRealizarCalculos)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoDiferenca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botaoCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCadastrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(botaoRealizarCalculos, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDiferenca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoCadastrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botaoAdicionarSaidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarSaidasActionPerformed
        adicionarLinhaTabela(tabelaSaidas);
    }//GEN-LAST:event_botaoAdicionarSaidasActionPerformed

    private void botaoRemoverSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverSaidaActionPerformed
        removerLinhaTabela(tabelaSaidas);
    }//GEN-LAST:event_botaoRemoverSaidaActionPerformed

    private void botaoRealizarCalculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRealizarCalculosActionPerformed
        FechamentoFormValidator validador = new FechamentoFormValidator(formPanel);

        if (validador.validate()) {
            realizarCalculos();
        }
    }//GEN-LAST:event_botaoRealizarCalculosActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        if (new FechamentoFormValidator(formPanel).validate()) {
            String[] options = {"SIM", "NÃO"};
            int reply = JOptionPane.showOptionDialog(null, "Realmente deseja realizar o fechamento de caixa?", "Fechamento",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    options, options[0]);

            if (reply == 0) {

                int turno = radioManha.isSelected() ? 0 : 1;
                String data = campoData.getText();

                if (!DAO.fechamentoJaCadastrado(data, turno)) {

                    Saida[] saidas = new Saida[tabelaSaidas.getRowCount()];
                    for (int i = 0; i < tabelaSaidas.getRowCount(); i++) {
                        saidas[i] = new Saida(
                                String.valueOf(tabelaSaidas.getValueAt(i, 0)),
                                stringParaValor(String.valueOf(tabelaSaidas.getValueAt(i, 1)))
                        );
                    }
                    
                    SaidaBeth[] saidasBeth = new SaidaBeth[tabelaSaidasBeth.getRowCount()];
                    for (int i = 0; i < tabelaSaidasBeth.getRowCount(); i++) {
                        saidasBeth[i] = new SaidaBeth(
                                String.valueOf(tabelaSaidasBeth.getValueAt(i, 0)),
                                stringParaValor(String.valueOf(tabelaSaidasBeth.getValueAt(i, 1)))
                        );
                    }

                    Fechamento fechamento = new Fechamento(
                            radioManha.isSelected() ? 0 : 1,
                            stringParaValor(campoEntrada.getText()),
                            stringParaValor(campoValorCaixa.getText()),
                            stringParaValor(campoValorDisplay.getText()),
                            stringParaValor(campoCartao.getText()),
                            saidas,
                            saidasBeth
                    );

                    if (DAO.insertFechamento(fechamento, campoData.getText())) {
                        JOptionPane.showMessageDialog(null, "Fechamento de Caixa cadastrado com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                        
                        menuPrincipal.resetTabelaDias();
                        
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Algum erro ocorreu ao realizar o cadastro. Favor reiniciar o programa e tentar novamente.", "Atenção", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Já existe um registro de Fechamento de Caixa para esse dia e turno.", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoRemoverSaidaBethActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverSaidaBethActionPerformed
        adicionarLinhaTabela(tabelaSaidasBeth);
    }//GEN-LAST:event_botaoRemoverSaidaBethActionPerformed

    private void botaoAdicionarSaidasBethActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarSaidasBethActionPerformed
        removerLinhaTabela(tabelaSaidasBeth);
    }//GEN-LAST:event_botaoAdicionarSaidasBethActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionarSaidas;
    private javax.swing.JButton botaoAdicionarSaidasBeth;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoRealizarCalculos;
    private javax.swing.JButton botaoRemoverSaida;
    private javax.swing.JButton botaoRemoverSaidaBeth;
    private javax.swing.JTextField campoCartao;
    private javax.swing.JFormattedTextField campoData;
    private javax.swing.JTextField campoDiferenca;
    private javax.swing.JTextField campoEntrada;
    private javax.swing.JTextField campoTotalSaidas;
    private javax.swing.JTextField campoTotalSaidasBeth;
    private javax.swing.JTextField campoValorCaixa;
    private javax.swing.JTextField campoValorDisplay;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JRadioButton radioManha;
    private javax.swing.JRadioButton radioTarde;
    private javax.swing.JScrollPane scrollPaneSaidas;
    private javax.swing.JScrollPane scrollPaneSaidasBeth;
    private javax.swing.JTable tabelaSaidas;
    private javax.swing.JTable tabelaSaidasBeth;
    private javax.swing.ButtonGroup turnoButtonGroup;
    // End of variables declaration//GEN-END:variables
}
