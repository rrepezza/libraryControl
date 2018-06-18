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
import classes.Reserva;
import dao.ClienteDAO;
import dao.EmprestimoDAO;
import dao.ExemplarDAO;
import dao.LivroDAO;
import dao.ReservaDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.IDGenerator;

/**
 *
 * @author repez
 */
public class TelaReserva extends javax.swing.JFrame {
    
    private String cliente_db = "./src/arquivos/Clientes.csv";
    private String exemplar_db = "./src/arquivos/Exemplares.csv";
    private String livro_db = "./src/arquivos/Livros.csv";
    private String reserva_db = "./src/arquivos/Reservas.csv";
    private String emprestimo_db = "./src/arquivos/Emprestimos.csv";
    
    public boolean clienteAptoParaReservar(int quantidadeAtualDeReservas, String tipoCliente) {
        if(tipoCliente.equals("ALUNO")) {
            return quantidadeAtualDeReservas < 3;
        } else {
            return quantidadeAtualDeReservas < 5;
        }
    }
    
    /**
     * Creates new form TelaReserva
     */
    public TelaReserva() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //botoes de edicao de reserva iniciam ocultos
        jLabelReservaID.setVisible(false);
        jButtonCancelarEdicao.setVisible(false);
        jButtonEfetuarEmprestimo.setVisible(false);
        jButtonCancelarReserva.setVisible(false);
        
        try {
            
            //atualiza reservas expiradas, setando-as como false (expiradas)
            ReservaDAO rdao = new ReservaDAO(reserva_db);
            rdao.atualizarReservasExpiradas();
            
            //carrega comboboxes
            ClienteDAO cdao = new ClienteDAO(cliente_db);
            ExemplarDAO edao = new ExemplarDAO(exemplar_db);
            LivroDAO ldao = new LivroDAO(livro_db);
            
            ArrayList<Cliente> listaClientes = cdao.listar();
            ArrayList<Exemplar> listaExemplares = edao.getExemplaresDisponiveis();
            
            String[] clientes = new String[listaClientes.size()];
            String[] exemplares = new String[listaExemplares.size()];
            
            for (int i = 0; i < listaClientes.size(); i++) {
                Cliente c = listaClientes.get(i);
                clientes[i] = c.getNome();
            }
            
            for (int i = 0; i < listaExemplares.size(); i++) {
                Exemplar e = listaExemplares.get(i);
                int livroID = e.getLivroID();
                Livro livro = ldao.getLivroByID(livroID);
                exemplares[i] = livro.getTitulo() + " | ID " + e.getId();  
            }
                        
            Arrays.sort(clientes);
            Arrays.sort(exemplares);
            
            DefaultComboBoxModel m_clientes = new DefaultComboBoxModel(clientes);
            jComboBoxReservaCliente.setModel(m_clientes);
            
            DefaultComboBoxModel m_exemplares = new DefaultComboBoxModel(exemplares);
            jComboBoxReservaExemplar.setModel(m_exemplares);
            
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }
    
    //Método para buscar os autores e preencher a jTable inicialmente
    public void showReservas(ArrayList<Reserva> listaReservas) {
        try {

            DefaultTableModel modelo = (DefaultTableModel) jTableReservas.getModel();

            modelo.setNumRows(listaReservas.size());
            
            ClienteDAO cdao = new ClienteDAO(cliente_db);
            ExemplarDAO edao = new ExemplarDAO(exemplar_db);
            LivroDAO ldao = new LivroDAO(livro_db);
            
            for (int i = 0; i < listaReservas.size(); i++) {
                
                Reserva res = listaReservas.get(i);
                
                Exemplar exemplar = edao.getExemplarByID(res.getExemplarID());
                Livro livro = ldao.getLivroByID(exemplar.getLivroID());
                Cliente cliente = cdao.getClienteById(res.getClienteID());
                
                String livroEIDExemplar = livro.getTitulo() + " | ID: " + exemplar.getId();
                
                modelo.setValueAt(res.getId(), i, 0);
                modelo.setValueAt(livroEIDExemplar, i, 1);
                modelo.setValueAt(cliente.getNome(), i, 2);   
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                modelo.setValueAt(sdf.format(res.getDataReserva()), i, 3);
                
                String reservaExpirada = res.isAtiva() ? "NÃO" : "SIM";
                modelo.setValueAt(reservaExpirada, i, 4);
 
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
        jComboBoxReservaExemplar = new javax.swing.JComboBox<>();
        jComboBoxReservaCliente = new javax.swing.JComboBox<>();
        jButtonCadastrarReserva = new javax.swing.JButton();
        jButtonEfetuarEmprestimo = new javax.swing.JButton();
        jButtonCancelarReserva = new javax.swing.JButton();
        jButtonCancelarEdicao = new javax.swing.JButton();
        jLabelReservaID = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldBuscaReserva = new javax.swing.JTextField();
        jButtonBuscarReserva = new javax.swing.JButton();
        jButtonListarReservas = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReservas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reservas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Nova Reserva"));

        jLabel1.setText("Exemplar:");

        jLabel2.setText("Cliente:");

        jComboBoxReservaExemplar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxReservaCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonCadastrarReserva.setText("Cadastrar");
        jButtonCadastrarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarReservaActionPerformed(evt);
            }
        });

        jButtonEfetuarEmprestimo.setText("Efetuar Empréstimo");
        jButtonEfetuarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEfetuarEmprestimoActionPerformed(evt);
            }
        });

        jButtonCancelarReserva.setText("Cancelar Reserva");
        jButtonCancelarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarReservaActionPerformed(evt);
            }
        });

        jButtonCancelarEdicao.setText("Cancelar Edição");
        jButtonCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarEdicaoActionPerformed(evt);
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxReservaExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(jComboBoxReservaCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabelReservaID))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonCadastrarReserva)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEfetuarEmprestimo)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelarReserva)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelarEdicao)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxReservaExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelReservaID))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxReservaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrarReserva)
                    .addComponent(jButtonEfetuarEmprestimo)
                    .addComponent(jButtonCancelarReserva)
                    .addComponent(jButtonCancelarEdicao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Busca de Reserva"));

        jLabel3.setText("Título:");

        jButtonBuscarReserva.setText("Buscar");
        jButtonBuscarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarReservaActionPerformed(evt);
            }
        });

        jButtonListarReservas.setText("Listar Reservas");
        jButtonListarReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarReservasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(40, 40, 40)
                .addComponent(jTextFieldBuscaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBuscarReserva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jButtonListarReservas)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldBuscaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarReserva)
                    .addComponent(jButtonListarReservas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservas Encontradas"));

        jTableReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Livro", "Cliente", "Data da Reserva", "Expirada"
            }
        ));
        jTableReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableReservasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableReservas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
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

    private void jButtonCadastrarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarReservaActionPerformed
        // TODO add your handling code here:
        try {
            ClienteDAO cdao = new ClienteDAO(cliente_db);
            Cliente c = cdao.getClienteByNome(jComboBoxReservaCliente.getSelectedItem().toString());
            
            String livroExemplarID = jComboBoxReservaExemplar.getSelectedItem().toString();
            String pieces[] = livroExemplarID.split("\\| ID ");
            int exemplarID = Integer.parseInt(pieces[1]);
                                    
            ReservaDAO rdao = new ReservaDAO(reserva_db);
            int qtdReservasDoCliente = rdao.getQuantidadeDeReservasDoCliente(c.getId());
            
            if(clienteAptoParaReservar(qtdReservasDoCliente, c.getTipoPessoa())) {
                                
                IDGenerator novoID = new IDGenerator();
                int id = novoID.getNovoID();
                               
                //data reserva
                Date hoje = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(hoje);
                Date dataReserva = cal.getTime();
                
                System.out.println(dataReserva);
                
                Reserva novaReserva = new Reserva(id, exemplarID, c.getId(), dataReserva);
                rdao.incluir(novaReserva);
                
                novoID.gravaID(id);
                
                JOptionPane.showMessageDialog(rootPane, "Reserva cadastrada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Cliente já reserva o número máximo de livros permitido.");
            }
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonCadastrarReservaActionPerformed

    private void jButtonListarReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarReservasActionPerformed
        // TODO add your handling code here:
        try {
            ReservaDAO rdao = new ReservaDAO(reserva_db);
            ArrayList<Reserva> listaReservas = rdao.listar();
            rdao.atualizarReservasExpiradas();
            if(listaReservas.size() > 0) {
                showReservas(listaReservas);
            } else {
                DefaultTableModel modelo = (DefaultTableModel) jTableReservas.getModel();
                modelo.setRowCount(0);
                JOptionPane.showMessageDialog(rootPane, "Nenhuma reserva cadastrada."); 
            }   
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonListarReservasActionPerformed

    private void jButtonEfetuarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEfetuarEmprestimoActionPerformed
        //Efetua empréstimo a partir de uma reserva existente
        try {
            //Busca o ID do empréstimo, que foi vinculado ao label invisível no formulário de Reservas
            int reservaID = Integer.parseInt(jLabelReservaID .getText());
            
            //Se o texto contido no label for diferente de vazio, executa
            if(!jLabelReservaID .getText().isEmpty()) {
            
                //Cria instancia de ReservaDAO
                ReservaDAO rdao = new ReservaDAO(reserva_db);
                
                //Cria um objeto Reserva de acordo com o ID selecionado
                Reserva reservaAtual = rdao.getReservaById(reservaID);
                
                //Lista as reservas que existem para o exemplar selecionado
                ArrayList<Reserva> reservasDoExemplar = rdao.getReservasByExemplarID(reservaAtual.getExemplarID());
                
                //Cria uma instancia de ClienteDAO
                ClienteDAO cdao = new ClienteDAO(cliente_db);
                
                //Cria um objeto de Cliente a partir do ID do cliente vinculado à reserva selecionada
                Cliente clienteReservaAtual = cdao.getClienteById(reservaAtual.getClienteID());
                
                //Cria uma instancia de EmprestimoDAO
                EmprestimoDAO empdao = new EmprestimoDAO(emprestimo_db);
                
                //Cria uma instancia de ExemplarDAO
                ExemplarDAO edao = new ExemplarDAO(exemplar_db);
                
                //Cria um objeto Exemplar a partir do ID do exemplar vinculado à reserva selecionada
                Exemplar exemplar = edao.getExemplarByID(reservaAtual.getExemplarID());
                
                //Cria uma instancia de IDGenerator, responsável por controlar o incremento dos IDs dos registros
                IDGenerator novoID = new IDGenerator();
                
                //Caso haja mais de uma reserva para o exemplar vinculado à reserva selecionada
                //Verifica se essa reserva atual será a que poderá gerar um empréstimo nesse momento
                //Dependendo do tipo do Cliente e / ou da data da reserva ou se existe uma reserva prioritária para o exemplar
                if(exemplar.IsDisponivel()) {
                    
                    if(reservasDoExemplar.size() > 1) {
                    
                        boolean emprestimoPoderaSerFeito = true;
                        Date dataReservaAtual = reservaAtual.getDataReserva();

                        for (int i = 0; i < reservasDoExemplar.size(); i++) {
                            Reserva reservaExistente = reservasDoExemplar.get(i);
                            //Verifica se a reserva do arraylist é diferente da reserva atual
                            //Caso o cliente possua preferencia sobre o cliente da reserva atual, não faz o empréstimo
                            //Caso o cliente possua a mesma preferencia do atual, verifica a data da reserva e só realiza o empréstimo
                            //caso a data da reserva atual for anterior à data da reserva pesquisada
                            if(reservaExistente.getId() != reservaAtual.getId()) {
                                Cliente clienteReservaExistente = cdao.getClienteById(reservaExistente.getClienteID());

                                //Proxima reserva pertencente à um professor e atual sendo de um aluno
                                //Caso sim, seta a flag que a reserva não poderá ser transformada em um empréstimo
                                //Caso não, verificaremos a data da reserva, se e só realiza o empréstimo
                                //se a data da reserva atual não for maior que nenhuma data de alguma reserva existente
                                if(clienteReservaExistente.getTipoPessoa().equals("PROFESSOR") && clienteReservaAtual.getTipoPessoa().equals("ALUNO"))  {
                                    emprestimoPoderaSerFeito = false;                               
                                } else if(clienteReservaExistente.getTipoPessoa().equals("ALUNO") && clienteReservaAtual.getTipoPessoa().equals("PROFESSOR")) {
                                    emprestimoPoderaSerFeito = true;
                                } else {
                                    Date dataReservaExistente = reservaExistente.getDataReserva();
                                    if(dataReservaAtual.after(dataReservaExistente)) {
                                        emprestimoPoderaSerFeito = false;
                                    } 
                                }
                            }
                        }

                        //Caso a flag se mantenha em verdadeiro (reserva poderá ser transformada em empréstimo)
                        //efetua o mesmo
                        if(clienteReservaAtual.getSaldoDevedor() == 0) {
                            if(emprestimoPoderaSerFeito) {

                                //Cria novo ID para o emprestimo
                                int emprestimoID = novoID.getNovoID();

                                //Cria um objeto do novo emprestimo a partir da reserva selecionada
                                Emprestimo emprestimo = new Emprestimo(emprestimoID, reservaAtual.getExemplarID(), reservaAtual.getClienteID());

                                //Seta a data de devolução do empréstimo, a partir do tipo do cliente
                                emprestimo.setDataDevolucao(emprestimo.calculaDataDeDevolucao(clienteReservaAtual.getTipoPessoa()));

                                //Seta o emprestimo como ativo (não expirado)
                                emprestimo.setIsAtivo(true);

                                //Inclui o emprestimo no CSV correspondente
                                empdao.incluir(emprestimo);

                                //Seta a reserva como não ativa (expirada)
                                reservaAtual.setIsAtiva(false);

                                //Altera a reserva no CSV correspondente
                                rdao.alterar(reservaAtual);

                                //Seta o exemplar como indisponível
                                exemplar.setDisponivel(false);

                                //Seta a data de disponibilidade do exemplar, pelo tipo do cliente
                                exemplar.setDisponivelAPartirDe(emprestimo.calculaDataDeDevolucao(clienteReservaAtual.getTipoPessoa()));

                                //Altera o exemplar no CSV correspondente
                                edao.alterar(exemplar);

                                //Grava o ID gerado no arquivo correspondente (incremento automático)
                                novoID.gravaID(emprestimoID);

                                //Exibe mensagem de sucesso
                                JOptionPane.showMessageDialog(rootPane, "Empréstimo realizado com sucesso!");                   

                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Não foi possível realizar o empréstimo da reserva "
                                        + "selecionada, pois há alguma reserva prioritária cadastrada.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "O cliente possuí dívidas com a biblioteca. Regularize antes.");
                        }

                    } else {
                        //Cria novo ID para o emprestimo
                        int emprestimoID = novoID.getNovoID();

                        //Cria um objeto do novo emprestimo a partir da reserva selecionada
                        Emprestimo emprestimo = new Emprestimo(emprestimoID, reservaAtual.getExemplarID(), reservaAtual.getClienteID());
                        //Seta a data de devolução do empréstimo, a partir do tipo do cliente
                        emprestimo.setDataDevolucao(emprestimo.calculaDataDeDevolucao(clienteReservaAtual.getTipoPessoa()));
                        //Seta o emprestimo como ativo (não expirado)
                        emprestimo.setIsAtivo(true);
                        //Inclui o emprestimo no CSV correspondente
                        empdao.incluir(emprestimo);

                        //Seta a reserva como não ativa (expirada)
                        reservaAtual.setIsAtiva(false);
                        //Altera a reserva no CSV correspondente
                        rdao.alterar(reservaAtual);

                        //Seta o exemplar como indisponível
                        exemplar.setDisponivel(false);
                        //Seta a data de disponibilidade do exemplar, pelo tipo do cliente
                        exemplar.setDisponivelAPartirDe(emprestimo.calculaDataDeDevolucao(clienteReservaAtual.getTipoPessoa()));
                        //Altera o exemplar no CSV correspondente
                        edao.alterar(exemplar);

                        //Grava o ID gerado no arquivo correspondente (incremento automático)
                        novoID.gravaID(emprestimoID);
                        //Exibe mensagem de sucesso
                        JOptionPane.showMessageDialog(rootPane, "Empréstimo realizado com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "O exemplar selecionado não está disponível no "
                            + "momento para efetivar o empréstimo através da reserva selecionada.");
                }
  
            } else {
                JOptionPane.showMessageDialog(rootPane, "Nenhuma reserva selecionada.");
            }
            
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonEfetuarEmprestimoActionPerformed

    private void jButtonCancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarReservaActionPerformed
        // TODO add your handling code here:
        try {
            //Busca o ID do empréstimo, que foi vinculado ao label invisível no formulário de Reservas
            int reservaID = Integer.parseInt(jLabelReservaID .getText());
            
            if(!jLabelReservaID .getText().isEmpty()) {
                
                ReservaDAO rdao = new ReservaDAO(reserva_db);
                Reserva reserva = rdao.getReservaById(reservaID);
                if(reserva.isAtiva()) {
                    reserva.setIsAtiva(false);
                    rdao.alterar(reserva);
                    JOptionPane.showMessageDialog(rootPane, "Reserva cancelada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "A reserva selecionada já está expirada!");
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Erro ao cancelar a reserva. Nenhuma reserva foi selecionada.");
            }
            
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonCancelarReservaActionPerformed

    private void jButtonCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarEdicaoActionPerformed
        // TODO add your handling code here:
        jLabelReservaID.setText("");
        jButtonCancelarEdicao.setVisible(false);
        jButtonEfetuarEmprestimo.setVisible(false);
        jButtonCancelarReserva.setVisible(false);
        jButtonCadastrarReserva.setVisible(true);
    }//GEN-LAST:event_jButtonCancelarEdicaoActionPerformed

    private void jButtonBuscarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBuscarReservaActionPerformed

    private void jTableReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableReservasMouseClicked
        // TODO add your handling code here:
        try {
            int linhaSelecionada = jTableReservas.getSelectedRow();
            jLabelReservaID.setText(jTableReservas.getModel().getValueAt(linhaSelecionada, 0).toString());
            jButtonCancelarEdicao.setVisible(true);
            jButtonEfetuarEmprestimo.setVisible(true);
            jButtonCancelarReserva.setVisible(true);
            jButtonCadastrarReserva.setVisible(false);
        } catch (Exception erro) {
            erro.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }//GEN-LAST:event_jTableReservasMouseClicked

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
            java.util.logging.Logger.getLogger(TelaReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarReserva;
    private javax.swing.JButton jButtonCadastrarReserva;
    private javax.swing.JButton jButtonCancelarEdicao;
    private javax.swing.JButton jButtonCancelarReserva;
    private javax.swing.JButton jButtonEfetuarEmprestimo;
    private javax.swing.JButton jButtonListarReservas;
    private javax.swing.JComboBox<String> jComboBoxReservaCliente;
    private javax.swing.JComboBox<String> jComboBoxReservaExemplar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelReservaID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableReservas;
    private javax.swing.JTextField jTextFieldBuscaReserva;
    // End of variables declaration//GEN-END:variables
}
