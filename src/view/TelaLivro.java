/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.Autor;
import classes.Editora;
import classes.Livro;
import dao.AutorDAO;
import dao.EditoraDAO;
import dao.LivroDAO;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.RenderizaLabel;

/**
 *
 * @author repez
 */
public class TelaLivro extends javax.swing.JFrame {
    
    //String livro_db = "P:\\Drive\\Graduação ADS\\2SEM\\Programação Orientada a Objetos\\libraryControl\\src\\arquivos\\Livros.csv";
    String livro_db = "D:\\Drive\\Graduação ADS\\2SEM\\Programação Orientada a Objetos\\libraryControl\\src\\arquivos\\Livros.csv";
    //String autor_db = "P:\\Drive\\Graduação ADS\\2SEM\\Programação Orientada a Objetos\\libraryControl\\src\\arquivos\\Autores.csv";
    String autor_db = "D:\\Drive\\Graduação ADS\\2SEM\\Programação Orientada a Objetos\\libraryControl\\src\\arquivos\\Autores.csv";
    //String editora_db = "P:\\Drive\\Graduação ADS\\2SEM\\Programação Orientada a Objetos\\libraryControl\\src\\arquivos\\Editoras.csv";
    String editora_db = "D:\\Drive\\Graduação ADS\\2SEM\\Programação Orientada a Objetos\\libraryControl\\src\\arquivos\\Editoras.csv";
    
    /**
     * Creates new form TelaLivro
     */
    public TelaLivro() {
        
        initComponents();
        showLivros();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        try {

            AutorDAO ad = new AutorDAO(autor_db);
            EditoraDAO ed = new EditoraDAO(editora_db);
            
            ArrayList<Autor> listaAutores = ad.listar();
            ArrayList<Editora> listaEditoras = ed.listar();
            
            String[] autores = new String[listaAutores.size()];
            String[] editoras = new String[listaEditoras.size()];
            
            for (int i = 0; i < listaAutores.size(); i++) {
                Autor a = listaAutores.get(i);
                autores[i] = a.getNome();
            }
            
            for (int i = 0; i < listaEditoras.size(); i++) {
                Editora e = listaEditoras.get(i);
                editoras[i] = e.getNome();
            }
            
            DefaultComboBoxModel m_autores = new DefaultComboBoxModel(autores);
            jComboBoxLivroAutor.setModel(m_autores);
            
            DefaultComboBoxModel m_editoras = new DefaultComboBoxModel(editoras);
            jComboBoLivroEditora.setModel(m_editoras);
  
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
            erro.printStackTrace();
        }
        
    }
    
    //Método para buscar os autores e preencher a jTable inicialmente
    public void showLivros() {
        try {
            ArrayList<Livro> listagem;
            LivroDAO l = new LivroDAO(livro_db);
            listagem = l.listar();
            
            DefaultTableModel modelo = (DefaultTableModel) jTableLivros.getModel();
            
            //setando o modo de renderizacão da coluna Capa da tabela
            jTableLivros.getColumn("Capa").setCellRenderer(new RenderizaLabel());
            
            modelo.setNumRows(listagem.size());
            
            AutorDAO ad = new AutorDAO(autor_db);
            EditoraDAO ed = new EditoraDAO(editora_db);
            
            for (int i = 0; i < listagem.size(); i++) {
                Livro liv = listagem.get(i);
                modelo.setValueAt(liv.getId(), i, 0);
                modelo.setValueAt(liv.getIsbn(), i, 1);
                modelo.setValueAt(liv.getTitulo(), i, 2);   
                
                Autor a = ad.getAutorByID(liv.getAutorID());
                modelo.setValueAt(a.getNome(), i, 3);
                
                Editora e = ed.getEditoraByID(liv.getEditoraID());            
                modelo.setValueAt(e.getNome(), i, 4);
                   
                ImageIcon foto = new ImageIcon(liv.getFotoDaCapa());
                Image imagem = foto.getImage();
                Image novaImagem = imagem.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon fotoRedimensionada = new ImageIcon(novaImagem);
                    
                JLabel img = new JLabel();
                img.setIcon(fotoRedimensionada);
                
                modelo.setValueAt(img, i, 5);
            }
            
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
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
        jLabel4 = new javax.swing.JLabel();
        jTextFieldLivroId = new javax.swing.JTextField();
        jTextFieldLivroISBN = new javax.swing.JTextField();
        jTextFieldLivroTitulo = new javax.swing.JTextField();
        jTextFieldLivroCapa = new javax.swing.JTextField();
        jButtonCarregaCapa = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonCadastrarLivro = new javax.swing.JButton();
        jComboBoxLivroAutor = new javax.swing.JComboBox<>();
        jComboBoLivroEditora = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLivros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Livros");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Livros", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo Livro"));

        jLabel1.setText("ID:");

        jLabel2.setText("ISBN:");

        jLabel3.setText("Título:");

        jLabel4.setText("Capa:");

        jButtonCarregaCapa.setText("Carregar");
        jButtonCarregaCapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCarregaCapaActionPerformed(evt);
            }
        });

        jLabel5.setText("Autor:");

        jLabel6.setText("Editora:");

        jButtonCadastrarLivro.setText("Cadastrar");
        jButtonCadastrarLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarLivroActionPerformed(evt);
            }
        });

        jComboBoxLivroAutor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoLivroEditora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(51, 51, 51)
                        .addComponent(jTextFieldLivroId, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(36, 36, 36)
                        .addComponent(jTextFieldLivroISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonCadastrarLivro)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldLivroTitulo)
                            .addComponent(jTextFieldLivroCapa)
                            .addComponent(jComboBoxLivroAutor, 0, 300, Short.MAX_VALUE)
                            .addComponent(jComboBoLivroEditora, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCarregaCapa)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldLivroId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldLivroISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldLivroTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldLivroCapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCarregaCapa))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxLivroAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoLivroEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonCadastrarLivro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Livros Cadastrados"));

        jTableLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ISBN", "Título", "Autor", "Editora", "Capa"
            }
        ));
        jScrollPane1.setViewportView(jTableLivros);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
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

    private void jButtonCarregaCapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarregaCapaActionPerformed
        // TODO add your handling code here:
        JFileChooser jf = new JFileChooser();
     
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        jf.showOpenDialog(this);
        
        File f = jf.getSelectedFile();
        jTextFieldLivroCapa.setText(f.getPath());
    }//GEN-LAST:event_jButtonCarregaCapaActionPerformed

    private void jButtonCadastrarLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarLivroActionPerformed
        // TODO add your handling code here:
        try {
            int id = Integer.parseInt(jTextFieldLivroId.getText());
            int isbn = Integer.parseInt(jTextFieldLivroISBN.getText());
            String titulo = jTextFieldLivroTitulo.getText();
            String capa = jTextFieldLivroCapa.getText();

            if(!jTextFieldLivroId.getText().isEmpty() && !jTextFieldLivroISBN.getText().isEmpty() 
                    && !jTextFieldLivroCapa.getText().isEmpty() && !jTextFieldLivroTitulo.getText().isEmpty()) {

                Autor autorEncontrado = null;
                if(jComboBoxLivroAutor.getSelectedItem() != null) {
                    AutorDAO ad =  new AutorDAO(autor_db);
                    autorEncontrado = ad.getAutorByNome(jComboBoxLivroAutor.getSelectedItem().toString());
                }
                
                Editora editoraEncontrada = null;
                if(jComboBoLivroEditora.getSelectedItem() != null) {
                    EditoraDAO ed =  new EditoraDAO(editora_db);
                    editoraEncontrada = ed.getEditoraByNome(jComboBoLivroEditora.getSelectedItem().toString());
                }
                
                if(editoraEncontrada != null && autorEncontrado != null) {
                    Livro novoLivro = new Livro(id, isbn, titulo, capa, autorEncontrado.getId(), editoraEncontrada.getId());
                    
                    LivroDAO ldao = new LivroDAO(livro_db);
                    
                    ldao.incluir(novoLivro);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Erro ao incluir livro.");
                }
                                
                showLivros();

            } else {
                
                JOptionPane.showMessageDialog(rootPane, "Preencha os campos antes de tentar inserir um Livro.");
            } 

        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
        
    }//GEN-LAST:event_jButtonCadastrarLivroActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLivro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrarLivro;
    private javax.swing.JButton jButtonCarregaCapa;
    private javax.swing.JComboBox<String> jComboBoLivroEditora;
    private javax.swing.JComboBox<String> jComboBoxLivroAutor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLivros;
    private javax.swing.JTextField jTextFieldLivroCapa;
    private javax.swing.JTextField jTextFieldLivroISBN;
    private javax.swing.JTextField jTextFieldLivroId;
    private javax.swing.JTextField jTextFieldLivroTitulo;
    // End of variables declaration//GEN-END:variables
}
