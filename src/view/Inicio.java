/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author repez
 */
public class Inicio extends javax.swing.JFrame {

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelCadastrosBasicos = new javax.swing.JPanel();
        jButtonLivro = new javax.swing.JButton();
        jButtonExemplar = new javax.swing.JButton();
        jButtonEditora = new javax.swing.JButton();
        jButtonAutor = new javax.swing.JButton();
        jButtonCliente = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButtonNovaReserva = new javax.swing.JButton();
        jButtonNovoEmprestimo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("libraryControl");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "libraryControl", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jPanelCadastrosBasicos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastros Básicos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jPanelCadastrosBasicos.setToolTipText("Cadastros Básicos");

        jButtonLivro.setText("Livro");
        jButtonLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLivroActionPerformed(evt);
            }
        });

        jButtonExemplar.setText("Exemplar");
        jButtonExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExemplarActionPerformed(evt);
            }
        });

        jButtonEditora.setText("Editora");
        jButtonEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditoraActionPerformed(evt);
            }
        });

        jButtonAutor.setText("Autor");
        jButtonAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAutorActionPerformed(evt);
            }
        });

        jButtonCliente.setText("Cliente");
        jButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCadastrosBasicosLayout = new javax.swing.GroupLayout(jPanelCadastrosBasicos);
        jPanelCadastrosBasicos.setLayout(jPanelCadastrosBasicosLayout);
        jPanelCadastrosBasicosLayout.setHorizontalGroup(
            jPanelCadastrosBasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadastrosBasicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAutor)
                .addGap(18, 18, 18)
                .addComponent(jButtonEditora)
                .addGap(18, 18, 18)
                .addComponent(jButtonLivro)
                .addGap(18, 18, 18)
                .addComponent(jButtonExemplar)
                .addGap(18, 18, 18)
                .addComponent(jButtonCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCadastrosBasicosLayout.setVerticalGroup(
            jPanelCadastrosBasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadastrosBasicosLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanelCadastrosBasicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAutor)
                    .addComponent(jButtonEditora)
                    .addComponent(jButtonLivro)
                    .addComponent(jButtonExemplar)
                    .addComponent(jButtonCliente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ações"));

        jButtonNovaReserva.setText("Reserva");
        jButtonNovaReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaReservaActionPerformed(evt);
            }
        });

        jButtonNovoEmprestimo.setText("Empréstimo");
        jButtonNovoEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoEmprestimoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonNovoEmprestimo)
                    .addComponent(jButtonNovaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonNovaReserva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButtonNovoEmprestimo)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCadastrosBasicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCadastrosBasicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovaReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaReservaActionPerformed
        // TODO add your handling code here:
        TelaReserva tr = new TelaReserva();
        tr.setVisible(true);
    }//GEN-LAST:event_jButtonNovaReservaActionPerformed

    private void jButtonNovoEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoEmprestimoActionPerformed
        // TODO add your handling code here:
        TelaEmprestimo te = new TelaEmprestimo();
        te.setVisible(true);
    }//GEN-LAST:event_jButtonNovoEmprestimoActionPerformed

    private void jButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClienteActionPerformed
        // TODO add your handling code here:
        TelaCliente tc = new TelaCliente();
        tc.setVisible(true);
    }//GEN-LAST:event_jButtonClienteActionPerformed

    private void jButtonAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAutorActionPerformed
        // TODO add your handling code here:
        TelaAutor a = new TelaAutor();
        a.setVisible(true);
        //dispose();
    }//GEN-LAST:event_jButtonAutorActionPerformed

    private void jButtonEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditoraActionPerformed
        // TODO add your handling code here:
        TelaEditora e = new TelaEditora();
        e.setVisible(true);
        //dispose();
    }//GEN-LAST:event_jButtonEditoraActionPerformed

    private void jButtonExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExemplarActionPerformed
        // TODO add your handling code here:
        TelaExemplar te = new TelaExemplar();
        te.setVisible(true);
    }//GEN-LAST:event_jButtonExemplarActionPerformed

    private void jButtonLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLivroActionPerformed
        // TODO add your handling code here:
        TelaLivro l = new TelaLivro();
        l.setVisible(true);
    }//GEN-LAST:event_jButtonLivroActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAutor;
    private javax.swing.JButton jButtonCliente;
    private javax.swing.JButton jButtonEditora;
    private javax.swing.JButton jButtonExemplar;
    private javax.swing.JButton jButtonLivro;
    private javax.swing.JButton jButtonNovaReserva;
    private javax.swing.JButton jButtonNovoEmprestimo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelCadastrosBasicos;
    // End of variables declaration//GEN-END:variables
}
