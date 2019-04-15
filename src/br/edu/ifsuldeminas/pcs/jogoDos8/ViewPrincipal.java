package br.edu.ifsuldeminas.pcs.jogoDos8;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ViewPrincipal extends javax.swing.JFrame {

    private String jogo;
    private Integer[][] jogoManual;
    private EstadoJogo jogoAtual;
    private int tipoBusca, tipoControle;
    private ArrayList<EstadoJogo> caminho;
    private String valorManual;

    public ViewPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        jBtnGo.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jBtn00 = new javax.swing.JButton();
        jBtn01 = new javax.swing.JButton();
        jBtn02 = new javax.swing.JButton();
        jBtn12 = new javax.swing.JButton();
        jBtn11 = new javax.swing.JButton();
        jBtn10 = new javax.swing.JButton();
        jBtn20 = new javax.swing.JButton();
        jBtn21 = new javax.swing.JButton();
        jBtn22 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBtnRdn = new javax.swing.JButton();
        jBtnMan = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboSelect = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboControl = new javax.swing.JComboBox<>();
        jBtnGo = new javax.swing.JButton();
        jBtnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBtn00.setText("0");

        jBtn01.setText("1");

        jBtn02.setText("2");

        jBtn12.setText("5");

        jBtn11.setText("4");

        jBtn10.setText("3");

        jBtn20.setText("6");

        jBtn21.setText("7");

        jBtn22.setText("8");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Quebra Cabeça de 8 Peças");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Estado Inicial:");

        jBtnRdn.setText("Gerar Aleatório");
        jBtnRdn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRndActionPerformed(evt);
            }
        });

        jBtnMan.setText("Entrar Manual");
        jBtnMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnManActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Algoritmo de Busca:");

        jComboSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Busca Gulosa", "A*" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Controle Jogadas IA:");

        jComboControl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manual", "Automático" }));

        jBtnGo.setText("Iniciar");
        jBtnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGoActionPerformed(evt);
            }
        });

        jBtnExit.setText("Sair");
        jBtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtn10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn12))
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtn00)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn01)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn02)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnRdn, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(jBtnMan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(56, 56, 56))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtn20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn22))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnGo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboSelect, 0, 129, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboControl, 0, 129, Short.MAX_VALUE)
                    .addComponent(jBtnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtn00)
                    .addComponent(jBtn01)
                    .addComponent(jBtn02)
                    .addComponent(jBtnRdn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtn10)
                    .addComponent(jBtn11)
                    .addComponent(jBtn12)
                    .addComponent(jBtnMan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtn20)
                    .addComponent(jBtn21)
                    .addComponent(jBtn22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnGo)
                    .addComponent(jBtnExit))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnRndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRndActionPerformed
        EstadoJogo estado = new EstadoJogo();
        estado.gerarEstadoInicial();
        jogo = new String();
        jogo = estado.jogoEmString();

        jBtn00.setText(jogo.substring(0, 1));
        jBtn01.setText(jogo.substring(1, 2));
        jBtn02.setText(jogo.substring(2, 3));
        jBtn10.setText(jogo.substring(3, 4));
        jBtn11.setText(jogo.substring(4, 5));
        jBtn12.setText(jogo.substring(5, 6));
        jBtn20.setText(jogo.substring(6, 7));
        jBtn21.setText(jogo.substring(7, 8));
        jBtn22.setText(jogo.substring(8, 9));

        this.jogoAtual = estado;
        jBtnGo.setEnabled(true);
    }//GEN-LAST:event_jBtnRndActionPerformed

    private void jBtnManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnManActionPerformed

        CadastroManual cadastro = new CadastroManual(this);
        cadastro.setVisible(true);

    }//GEN-LAST:event_jBtnManActionPerformed

    public void preencheDadosManual() {
        int k = 0;
        jogoManual = new Integer[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                jogoManual[i][j] = Integer.parseInt(valorManual.substring(k, k + 1));
                k++;
            }
        }

        jBtn00.setText(jogoManual[0][0].toString());
        jBtn01.setText(jogoManual[0][1].toString());
        jBtn02.setText(jogoManual[0][2].toString());
        jBtn10.setText(jogoManual[1][0].toString());
        jBtn11.setText(jogoManual[1][1].toString());
        jBtn12.setText(jogoManual[1][2].toString());
        jBtn20.setText(jogoManual[2][0].toString());
        jBtn21.setText(jogoManual[2][1].toString());
        jBtn22.setText(jogoManual[2][2].toString());

        EstadoJogo estado = new EstadoJogo(jogoManual);
        this.jogoAtual = estado;
        jBtnGo.setEnabled(true);

    }

    private void jBtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jBtnExitActionPerformed

    private void jBtnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGoActionPerformed

        if (jogoAtual.temSolucao(jogoAtual.getJogo())) {

            Busca busca = new Busca();
            
            if (jComboSelect.getSelectedIndex() == 0) {
                this.tipoBusca = 0;
            } else {
                this.tipoBusca = 1;
            }

            switch (this.tipoBusca) {
                case 0:
                    caminho = busca.buscaGulosa(jogoAtual);
                    jogoAtual.resetarEstadosJaGerados();
                    break;
                case 1:
                    caminho = busca.Aestrela(jogoAtual);
                    jogoAtual.resetarEstadosJaGerados();
                    break;
            }
            new JogoView(jogoAtual, true, caminho);

        } else {
            JOptionPane.showMessageDialog(null, "Não existe solução para este estado. Insira outro estado.");
            jBtnGo.setEnabled(false);
        }

    }//GEN-LAST:event_jBtnGoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtn00;
    private javax.swing.JButton jBtn01;
    private javax.swing.JButton jBtn02;
    private javax.swing.JButton jBtn10;
    private javax.swing.JButton jBtn11;
    private javax.swing.JButton jBtn12;
    private javax.swing.JButton jBtn20;
    private javax.swing.JButton jBtn21;
    private javax.swing.JButton jBtn22;
    private javax.swing.JButton jBtnExit;
    private javax.swing.JButton jBtnGo;
    private javax.swing.JButton jBtnMan;
    private javax.swing.JButton jBtnRdn;
    private javax.swing.JComboBox<String> jComboControl;
    private javax.swing.JComboBox<String> jComboSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    /**
     * @param valorManual the valorManual to set
     */
    public void setValorManual(String valorManual) {
        this.valorManual = valorManual;
    }
}
