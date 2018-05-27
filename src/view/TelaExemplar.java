/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.Exemplar;
import classes.GerarID;
import classes.Livro;
import dao.ExemplarDAO;
import dao.LivroDAO;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author repez
 */
public class TelaExemplar extends javax.swing.JFrame {
    
    String ID_db = "C:\\Users\\jhene\\Documents\\NetBeansProjects\\ProjetoLibrary\\src\\arquivos\\IDs.csv";
    //String livro_db = "C:\\Users\\jhene\\Documents\\NetBeansProjects\\ProjetoLibrary\\src\\arquivos\\Livros.csv";
    String livro_db = "C:\\Users\\jhene\\Documents\\NetBeansProjects\\ProjetoLibrary\\src\\arquivos\\Livros.csv";
    //String exemplar_db = "C:\\Users\\jhene\\Documents\\NetBeansProjects\\ProjetoLibrary\\src\\arquivos\\Exemplares.csv";
    String exemplar_db = "C:\\Users\\jhene\\Documents\\NetBeansProjects\\ProjetoLibrary\\src\\arquivos\\Exemplares.csv";

    /**
     * Creates new form TelaExemplar
     */
    public TelaExemplar() {
        initComponents();
        showExemplares();
        GerarID IDs = new GerarID();
        jTextFieldExemplarId.setEditable(false);
        jTextFieldExemplarId.setText(Integer.toString(IDs.gerarNumeroUnico()));
        
        try {
            
            LivroDAO ldao = new LivroDAO(livro_db);
            ArrayList<Livro> listaLivros = ldao.consultar();
            String[] livros = new String[listaLivros.size()];
            
            for (int i = 0; i < listaLivros.size(); i++) {
                Livro livro = listaLivros.get(i);
                livros[i] = livro.getTitulo();
            }
            
            DefaultComboBoxModel m_livros = new DefaultComboBoxModel(livros);
            jComboBoxExemplarLivro.setModel(m_livros);
            
            String[] disponibilidades = { "Sim", "Não" };
            
            DefaultComboBoxModel m_disponibilidade = new DefaultComboBoxModel(disponibilidades);
            jComboBoxExemplarDisponivel.setModel(m_disponibilidade);
            
            
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }
    
    public void showExemplares() {
        try {
            ExemplarDAO edao = new ExemplarDAO(exemplar_db);
            ArrayList<Exemplar> listaExemplares = edao.consultar();

            DefaultTableModel modelo = (DefaultTableModel) jTableExemplares.getModel();

            modelo.setNumRows(listaExemplares.size());
            
            for (int i = 0; i < listaExemplares.size(); i++) {
                Exemplar exemplar = listaExemplares.get(i);
                modelo.setValueAt(exemplar.getNumeroID(), i, 0);
                modelo.setValueAt(exemplar.getLivro().getTitulo(), i, 1);
                
                String disponivel = exemplar.IsDisponivel() ? "Sim" : "Não";
                modelo.setValueAt(disponivel, i, 2);   
                
                System.out.println(exemplar.IsDisponivel());
                
            }
        } catch (Exception erro) {
            erro.printStackTrace();
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldExemplarId = new javax.swing.JTextField();
        jComboBoxExemplarLivro = new javax.swing.JComboBox<>();
        jComboBoxExemplarDisponivel = new javax.swing.JComboBox<>();
        jButtonCadastrarExemplar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableExemplares = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Exemplares", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo Exemplar"));

        jLabel1.setText("ID:");

        jLabel2.setText("Livro:");

        jLabel3.setText("Disponível:");

        jTextFieldExemplarId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldExemplarIdActionPerformed(evt);
            }
        });

        jComboBoxExemplarLivro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxExemplarDisponivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonCadastrarExemplar.setText("Cadastrar");
        jButtonCadastrarExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarExemplarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldExemplarId, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxExemplarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxExemplarDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonCadastrarExemplar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldExemplarId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxExemplarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxExemplarDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonCadastrarExemplar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Exemplares Cadastrados"));

        jTableExemplares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Livro", "Disponivel"
            }
        ));
        jScrollPane1.setViewportView(jTableExemplares);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldExemplarIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldExemplarIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldExemplarIdActionPerformed

    private void jButtonCadastrarExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarExemplarActionPerformed
        // TODO add your handling code here:
        try {
            GerarID ID = new GerarID();
            ID.gerarNumeroUnico();
            String titulo = jComboBoxExemplarLivro.getSelectedItem().toString();
            String opcaoDisponibilidade = jComboBoxExemplarDisponivel.getSelectedItem().toString();
            
            boolean disponibilidade = opcaoDisponibilidade.equals("Sim");
            
            LivroDAO ldao = new LivroDAO(livro_db);
            Livro livro = ldao.getLivroByTitulo(titulo);
            
            if(livro != null) {
                
                Exemplar novoExemplar = new Exemplar(ID, disponibilidade, livro);
                ExemplarDAO edao = new ExemplarDAO(exemplar_db);
                
                edao.incluir(novoExemplar);
                
                showExemplares();
                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro ao vincular exemplar ao livro " + titulo);
            }
            
            
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonCadastrarExemplarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaExemplar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaExemplar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaExemplar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaExemplar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaExemplar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrarExemplar;
    private javax.swing.JComboBox<String> jComboBoxExemplarDisponivel;
    private javax.swing.JComboBox<String> jComboBoxExemplarLivro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableExemplares;
    private javax.swing.JTextField jTextFieldExemplarId;
    // End of variables declaration//GEN-END:variables
}
