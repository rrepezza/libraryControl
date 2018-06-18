/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.Cliente;
import classes.Emprestimo;
import classes.Exemplar;
import classes.Livro;
import dao.ClienteDAO;
import dao.EmprestimoDAO;
import dao.ExemplarDAO;
import dao.LivroDAO;
import dao.ReservaDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.IDGenerator;

/**
 *
 * @author repez
 */
public class TelaEmprestimo extends javax.swing.JFrame {
    
    private String emprestimo_db = "./src/arquivos/Emprestimos.csv";
    private String exemplar_db = "./src/arquivos/Exemplares.csv";
    private String cliente_db = "./src/arquivos/Clientes.csv";
    private String livro_db = "./src/arquivos/Livros.csv";
    private String reserva_db = "./src/arquivos/Reservas.csv";
    
    public boolean clienteAptoParaRetirar(int quantidadeDeEmprestimos, String tipoCliente) {
        if(tipoCliente.equals("ALUNO")) {
            return quantidadeDeEmprestimos < 3;
        } else {
            return quantidadeDeEmprestimos < 5;
        }
    }
    
    public int duracaoEmprestimo(String tipoPessoa) {
        if(tipoPessoa.equals("ALUNO")){
            return 10;
        } else {
            return 15;
        }
    }
    
    /**
     * Creates new form TelaEmprestimo
     */
    public TelaEmprestimo() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //botoes de edicao do emprestimo e jlabel do id iniciam invisiveis, até que uma linha da tabela seja clicada
        jLabelEmprestimoID.setVisible(false);
        jButtonCancelarEdicao.setVisible(false);
        jButtonFinalizarEmprestimo.setVisible(false);
        jButtonRenovarEmprestimo.setVisible(false);
        verificaDividas();
        
        try {
            
            ExemplarDAO edao = new ExemplarDAO(exemplar_db);
            ClienteDAO cdao = new ClienteDAO(cliente_db);
            LivroDAO ldao = new LivroDAO(livro_db);
            
            ArrayList<Exemplar> listaExemplares = edao.getExemplaresSemReserva();
            ArrayList<Cliente> listaClientes = cdao.getClientesSemDivida();
            
            String exemplares[] = new String[listaExemplares.size()];
            String clientes[] = new String[listaClientes.size()];
            
            for (int i = 0; i < listaExemplares.size(); i++) {
                Exemplar e = listaExemplares.get(i);
                int livroID = e.getLivroID();
                Livro livro = ldao.getLivroByID(livroID);
                exemplares[i] = livro.getTitulo() + " | ID " + e.getId();
            }
            
            for (int i = 0; i < listaClientes.size(); i++) {
                Cliente c = listaClientes.get(i);
                clientes[i] = c.getNome();
            }
            
            Arrays.sort(exemplares);
            Arrays.sort(clientes);

            DefaultComboBoxModel m_exemplares = new DefaultComboBoxModel(exemplares);
            jComboBoxEmprestimoExemplar.setModel(m_exemplares);
            
            DefaultComboBoxModel m_clientes = new DefaultComboBoxModel(clientes);
            jComboBoxEmprestimoCliente.setModel(m_clientes);
            
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }
    
    public void showEmprestimos(ArrayList<Emprestimo> listaEmprestimos) {
        try {

            DefaultTableModel modelo = (DefaultTableModel) jTableEmprestimo.getModel();

            modelo.setNumRows(listaEmprestimos.size());
            
            ClienteDAO cdao = new ClienteDAO(cliente_db);
            ExemplarDAO edao = new ExemplarDAO(exemplar_db);
            LivroDAO ldao = new LivroDAO(livro_db);
            
            for (int i = 0; i < listaEmprestimos.size(); i++) {
                
                Emprestimo emp = listaEmprestimos.get(i);
  
                Exemplar exemplar = edao.getExemplarByID(emp.getExemplarID());
                Livro livro = ldao.getLivroByID(exemplar.getLivroID());
                Cliente cliente = cdao.getClienteById(emp.getClienteID());
                
                String livroEIDExemplar = livro.getTitulo() + " | ID: " + exemplar.getId();
                
                modelo.setValueAt(emp.getId(), i, 0);
                modelo.setValueAt(livroEIDExemplar, i, 1);
                modelo.setValueAt(cliente.getNome(), i, 2);   
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                modelo.setValueAt(sdf.format(emp.getDataEmprestimo()), i, 3);
                modelo.setValueAt(sdf.format(emp.getDataDevolucao()), i, 4);
                
                String emprestimoExpirado = emp.isAtivo() ? "NÃO" : "SIM";
                modelo.setValueAt(emprestimoExpirado, i, 5);
 
            }
            
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }
    
    //Metodo que percorre todas os emprestimos ativos e, caso estejam atrasados,
    //atualiza o saldo devedor do cliente que os fez 
    public void verificaDividas() {
        try {
            
            EmprestimoDAO empdao = new EmprestimoDAO(emprestimo_db);
            ArrayList<Emprestimo> emprestimosAtivos = empdao.getEmprestimosAtivos();
            ClienteDAO cdao = new ClienteDAO(cliente_db);
            ArrayList<Cliente> clientesComEmprestimosAtivos = cdao.getClientesComEmprestimosAtivos();
            Date hoje = new Date();
            
            for (int i = 0; i < clientesComEmprestimosAtivos.size(); i++) {
                int quantidadeDeDiasEmAtraso = 0;
                Cliente c = clientesComEmprestimosAtivos.get(i);
                for (int j = 0; j < emprestimosAtivos.size(); j++) {
                    Emprestimo emp = emprestimosAtivos.get(j);
                    if(c.getId() == emp.getClienteID() && hoje.after(emp.getDataDevolucao())) {
                        long diferenca = hoje.getTime() - emp.getDataDevolucao().getTime();
                        
                        quantidadeDeDiasEmAtraso += (int) TimeUnit.DAYS.convert(diferenca, TimeUnit.MILLISECONDS); 
                        System.out.println(quantidadeDeDiasEmAtraso);
                    }
                }
                
                if(quantidadeDeDiasEmAtraso > 0) {
                    c.setSaldoDevedor(quantidadeDeDiasEmAtraso * 2);
                    System.out.println(c.getSaldoDevedor());
                    cdao.alterar(c);
                }
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
        jButtonCadastrarEmprestimo = new javax.swing.JButton();
        jComboBoxEmprestimoExemplar = new javax.swing.JComboBox<>();
        jComboBoxEmprestimoCliente = new javax.swing.JComboBox<>();
        jButtonFinalizarEmprestimo = new javax.swing.JButton();
        jButtonCancelarEdicao = new javax.swing.JButton();
        jButtonRenovarEmprestimo = new javax.swing.JButton();
        jLabelEmprestimoID = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldBuscaEmprestimo = new javax.swing.JTextField();
        jButtonBuscarEmprestimo = new javax.swing.JButton();
        jButtonListarEmprestimos = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmprestimo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Empréstimos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo Empréstimo"));

        jLabel1.setText("Exemplar:");

        jLabel2.setText("Cliente:");

        jButtonCadastrarEmprestimo.setText("Cadastrar");
        jButtonCadastrarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarEmprestimoActionPerformed(evt);
            }
        });

        jComboBoxEmprestimoExemplar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxEmprestimoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonFinalizarEmprestimo.setText("Finalizar Empréstimo");
        jButtonFinalizarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarEmprestimoActionPerformed(evt);
            }
        });

        jButtonCancelarEdicao.setText("Cancelar Edição");
        jButtonCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarEdicaoActionPerformed(evt);
            }
        });

        jButtonRenovarEmprestimo.setText("Renover Empréstimo");
        jButtonRenovarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRenovarEmprestimoActionPerformed(evt);
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(65, 65, 65)
                                .addComponent(jComboBoxEmprestimoExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxEmprestimoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabelEmprestimoID))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonCadastrarEmprestimo)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonFinalizarEmprestimo)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRenovarEmprestimo)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelarEdicao)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEmprestimoExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelEmprestimoID))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEmprestimoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrarEmprestimo)
                    .addComponent(jButtonFinalizarEmprestimo)
                    .addComponent(jButtonCancelarEdicao)
                    .addComponent(jButtonRenovarEmprestimo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Busca de Empréstimo"));

        jLabel4.setText("Título:");

        jButtonBuscarEmprestimo.setText("Buscar");
        jButtonBuscarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarEmprestimoActionPerformed(evt);
            }
        });

        jButtonListarEmprestimos.setText("Listar Empréstimos");
        jButtonListarEmprestimos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarEmprestimosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBuscaEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBuscarEmprestimo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(jButtonListarEmprestimos)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldBuscaEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarEmprestimo)
                    .addComponent(jButtonListarEmprestimos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Empréstimos Encontrados"));

        jTableEmprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Livro", "Cliente", "Data Empréstimo", "Data Devolução", "Expirado"
            }
        ));
        jTableEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEmprestimoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEmprestimo);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jButtonCadastrarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarEmprestimoActionPerformed
        // TODO add your handling code here:
        try {
             
            //Cria uma instancia dos DAOS de cliente, exemplar e emprestimo
            ClienteDAO cdao = new ClienteDAO(cliente_db);
            ExemplarDAO edao = new ExemplarDAO(exemplar_db);
            EmprestimoDAO empdao = new EmprestimoDAO(emprestimo_db);
            
            //Cria um objeto de cliente a partir do ID do cliente selecionado
            Cliente c = cdao.getClienteByNome(jComboBoxEmprestimoCliente.getSelectedItem().toString());
            
            //Pega o item selecionado com o nome do livro e ID do exemplar
            String livroExemplarID = jComboBoxEmprestimoExemplar.getSelectedItem().toString();
            
            //Quebra a string e pega o ID do exemplar
            String pieces[] = livroExemplarID.split("\\| ID ");
            int exemplarID = Integer.parseInt(pieces[1]);
            
            //Cria um objeto Exemplar a partir do ID do exemplar selecionado
            Exemplar exemplarEmprestado = edao.getExemplarByID(exemplarID);
            
            //Busca a quantidade de emprestimos do cliente que tentará realizar o empréstimo
            int quantidadeDeEmprestimosDoCliente = empdao.getQuantidadeDeEmprestimosDoCliente(c.getId());
            
            //Caso o cliente esteja apto para retirar o exemplar por empréstimo, executa o bloco abaixo
            if(clienteAptoParaRetirar(quantidadeDeEmprestimosDoCliente, c.getTipoPessoa())) {
                
                //Gera um ID para o novo empréstimo
                IDGenerator novoID = new IDGenerator();
                int id = novoID.getNovoID();
                
                //Cria um novo objeto de empr;estimo
                Emprestimo novoEmprestimo = new Emprestimo(id, exemplarID, c.getId());
                
                //Seta, no objeto do novo empréstimo, a data prevista de devolução do exemplar retirado
                novoEmprestimo.setDataDevolucao(novoEmprestimo.calculaDataDeDevolucao(c.getTipoPessoa()));
                
                //Inclui o novo empréstimo no CSV de emprestimos
                empdao.incluir(novoEmprestimo);
                
                //Grava o último ID gerado
                novoID.gravaID(id);
                
                //No registro do exemplar, seta como indisponível (emprestado) e seta também a data de devolução como a data que o exemplar estará 
                //disponível, caso não haja atraso na devolução e altera o mesmo no CSV de exemplares
                exemplarEmprestado.setDisponivelAPartirDe(novoEmprestimo.calculaDataDeDevolucao(c.getTipoPessoa()));
                exemplarEmprestado.setDisponivel(false);
                edao.alterar(exemplarEmprestado);
                
            }
            
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonCadastrarEmprestimoActionPerformed

    private void jButtonListarEmprestimosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarEmprestimosActionPerformed
        // TODO add your handling code here:
        try {
            EmprestimoDAO empdao = new EmprestimoDAO(emprestimo_db);
            ArrayList<Emprestimo> listaEmprestimos = empdao.listar();
            
            if(listaEmprestimos.size() > 0) {
                showEmprestimos(listaEmprestimos);
            } else {
                DefaultTableModel modelo = (DefaultTableModel) jTableEmprestimo.getModel();
                modelo.setRowCount(0);
                JOptionPane.showMessageDialog(rootPane, "Nenhum empréstimo cadastrado."); 
            }   
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonListarEmprestimosActionPerformed

    private void jButtonFinalizarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarEmprestimoActionPerformed
        // TODO add your handling code here:
        try {
            int emprestimoID = Integer.parseInt(jLabelEmprestimoID.getText());
            if(!jLabelEmprestimoID.getText().isEmpty()) {
                
                EmprestimoDAO empdao = new EmprestimoDAO(emprestimo_db);
                Emprestimo emprestimo = empdao.getEmprestimoById(emprestimoID);
                                
                ExemplarDAO edao = new ExemplarDAO(exemplar_db);
                Exemplar exemplar = edao.getExemplarByID(emprestimo.getExemplarID());

                float saldoDevedor = emprestimo.calculaSaldoDevedor();
                if(saldoDevedor > 0) {
                    ClienteDAO cdao = new ClienteDAO(cliente_db);
                    Cliente c = cdao.getClienteById(emprestimo.getClienteID());
                    c.setSaldoDevedor(saldoDevedor);
                    cdao.alterar(c);
                }
                
                Date hoje = new Date();
                
                emprestimo.setIsAtivo(false);
                emprestimo.setDataDevolucao(hoje);
                exemplar.setDisponivel(true);
                exemplar.setDisponivelAPartirDe(hoje);

                empdao.alterar(emprestimo);
                edao.alterar(exemplar);
                
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonFinalizarEmprestimoActionPerformed

    private void jButtonCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarEdicaoActionPerformed
        // TODO add your handling code here:
        try {
            jButtonCadastrarEmprestimo.setVisible(true);
            jButtonCancelarEdicao.setVisible(false);
            jButtonFinalizarEmprestimo.setVisible(false);
            jButtonRenovarEmprestimo.setVisible(false);
            jLabelEmprestimoID.setText("");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
        
    }//GEN-LAST:event_jButtonCancelarEdicaoActionPerformed

    private void jTableEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmprestimoMouseClicked
        // TODO add your handling code here:
        try {
            jButtonCadastrarEmprestimo.setVisible(false);
            jButtonCancelarEdicao.setVisible(true);
            jButtonFinalizarEmprestimo.setVisible(true);
            jButtonRenovarEmprestimo.setVisible(true);
            int linhaSelecionada = jTableEmprestimo.getSelectedRow();
            jLabelEmprestimoID.setText(jTableEmprestimo.getValueAt(linhaSelecionada, 0).toString());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jTableEmprestimoMouseClicked

    private void jButtonRenovarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRenovarEmprestimoActionPerformed
        // TODO add your handling code here:
        try {
            int emprestimoID = Integer.parseInt(jLabelEmprestimoID.getText());
            if(!jLabelEmprestimoID.getText().isEmpty()) {
                
                EmprestimoDAO empdao = new EmprestimoDAO(emprestimo_db);
                Emprestimo emprestimo = empdao.getEmprestimoById(emprestimoID);
                
                ReservaDAO rdao = new ReservaDAO(reserva_db);
                boolean existeReservaDoExemplar = rdao.existeReserva(emprestimo.getExemplarID());
                
                float saldoDevedor = emprestimo.calculaSaldoDevedor();
                
                ClienteDAO cdao = new ClienteDAO(cliente_db);
                Cliente cliente = cdao.getClienteById(emprestimo.getClienteID());
                
                if(!existeReservaDoExemplar && saldoDevedor == 0) {
                    
                    ExemplarDAO edao = new ExemplarDAO(exemplar_db);
                    Exemplar exemplar = edao.getExemplarByID(emprestimo.getExemplarID());

                    emprestimo.setDataDevolucao(emprestimo.calculaDataDeDevolucao(cliente.getTipoPessoa()));
                    exemplar.setDisponivelAPartirDe(emprestimo.calculaDataDeDevolucao(cliente.getTipoPessoa()));
                    exemplar.setDisponivel(false);
                    
                    edao.alterar(exemplar);
                    empdao.alterar(emprestimo);
                    
                    JOptionPane.showMessageDialog(rootPane, "Empréstimo renovado com sucesso!");
                    
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Não é possível renovar o empréstimo. Já existe(m) reserva(s) para o exemplar informado.");
                    
                    if(saldoDevedor > 0) {
                        float saldoDevedorAtualCliente = cliente.getSaldoDevedor();
                        if(saldoDevedorAtualCliente > 0) {
                            cliente.setSaldoDevedor(saldoDevedorAtualCliente + saldoDevedor);
                            cdao.alterar(cliente);
                        }
                    }
                }
            }
            
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonRenovarEmprestimoActionPerformed

    private void jButtonBuscarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarEmprestimoActionPerformed
        // TODO add your handling code here:
        try {
            String busca = jTextFieldBuscaEmprestimo.getText();
            if(!busca.isEmpty()) {
                EmprestimoDAO empdao = new EmprestimoDAO(emprestimo_db);
                ArrayList<Emprestimo> emprestimosEncontrados = empdao.getEmprestimosByTitulo(busca);
                if(emprestimosEncontrados.size() > 0) {
                    showEmprestimos(emprestimosEncontrados);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Nenhum empréstimo encontrado para exemplares do livro " + busca);
                }
            }else {
                JOptionPane.showMessageDialog(rootPane, "Preencha o campo Título antes de fazer a busca.");
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonBuscarEmprestimoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEmprestimo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarEmprestimo;
    private javax.swing.JButton jButtonCadastrarEmprestimo;
    private javax.swing.JButton jButtonCancelarEdicao;
    private javax.swing.JButton jButtonFinalizarEmprestimo;
    private javax.swing.JButton jButtonListarEmprestimos;
    private javax.swing.JButton jButtonRenovarEmprestimo;
    private javax.swing.JComboBox<String> jComboBoxEmprestimoCliente;
    private javax.swing.JComboBox<String> jComboBoxEmprestimoExemplar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelEmprestimoID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmprestimo;
    private javax.swing.JTextField jTextFieldBuscaEmprestimo;
    // End of variables declaration//GEN-END:variables
}
