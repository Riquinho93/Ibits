/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ibits.telas;

import java.sql.*;
import br.com.ibits.dal.ModuloConexao;
import javax.swing.JOptionPane;
//Importando recursos da biblioteca rs2ml.jar
import net.proteanit.sql.DbUtils;

/**
 *
 * @author henri
 */
public class TelaCortador extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCortador
     */
    public TelaCortador() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
     //Adicionando usuarios
    private void Adicionar(){
        String sql = "insert into tbcortador(nomeCort,endCort,fone,email) Values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCortNome.getText());
            pst.setString(2, txtCortEndereco.getText());
            pst.setString(3, txtCortTelefone.getText());
            pst.setString(4, txtCortEmail.getText());
            // Validação dos campos obrigatórios
            if ((txtCortNome.getText().isEmpty()) || (txtCortEndereco.getText().isEmpty()) || (txtCortTelefone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios"); 
            } else {
            //Atualiazar tabela Usuario
            //Confirma a inserção dos dados
            int adicionado = pst.executeUpdate();
            //apoio de entedimento
            //System.out.println(adicionado);
            if (adicionado > 0){
               JOptionPane.showMessageDialog(null, "Cortador adicionado com sucesso"); 
                //Limpando os campos
                txtCortNome.setText(null);
                txtCortEndereco.setText(null);
                txtCortTelefone.setText(null);
                txtCortEmail.setText(null);   
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     //Metodo pesquisa faccionista pelo nome
    private void Pesquisar(){
        String sql = "select * from tbcortador where nomeCort like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //Passando a caixa de pesquisa para a interrogação
            //Atenção a porcentagem da string sql
            pst.setString(1, txtCortPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //Preencha tabela
            tblCort.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //Metodo para setar os campos do formulario 
    private void setar_campos(){
        int setar = tblCort.getSelectedRow();
        txtCortId.setText(tblCort.getModel().getValueAt(setar, 0).toString());
        txtCortNome.setText(tblCort.getModel().getValueAt(setar, 1).toString());
        txtCortEndereco.setText(tblCort.getModel().getValueAt(setar, 2).toString());
        txtCortTelefone.setText(tblCort.getModel().getValueAt(setar, 3).toString());
        txtCortEmail.setText(tblCort.getModel().getValueAt(setar, 4).toString());
        
        //botão adicionar
        btnAdicionar.setEnabled(false);
    }
    
    // alterar dados dos usuarios
    private void alterar(){
    String sql = "update tbcortador set nomeCort=?,endCort=?,fone=?, email=? where idCort=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtCortNome.getText());
            pst.setString(2,txtCortEndereco.getText());
            pst.setString(3,txtCortTelefone.getText());
            pst.setString(4,txtCortEmail.getText());
            pst.setString(5,txtCortId.getText());
            if ((txtCortNome.getText().isEmpty()) || (txtCortEndereco.getText().isEmpty()) || (txtCortTelefone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios"); 
            } else {
            //Atualiazar tabela Faccionionista
            //Confirma a inserção dos dados
            int adicionado = pst.executeUpdate();
            //apoio de entedimento
            //System.out.println(adicionado);
            if (adicionado > 0){
               JOptionPane.showMessageDialog(null, "Dados alterados com sucesso"); 
                //Limpando os campos
                txtCortId.setText(null);
                txtCortNome.setText(null);
                txtCortEndereco.setText(null);
                txtCortTelefone.setText(null);
                txtCortEmail.setText(null);   
                btnAdicionar.setEnabled(true);
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     //Metodo de remover usuários
    private void remover(){
        //Confirmar a remoção do usuário
        int confirma = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir cortador?","Atenção" , JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
        String sql="delete from tbcortador where idCort=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCortId.getText());
                int apagado = pst.executeUpdate();
                if (apagado>0){
                    JOptionPane.showMessageDialog(null, "Faccionista removido com sucesso");
                    txtCortId.setText(null);
                    txtCortNome.setText(null);
                    txtCortEndereco.setText(null);
                    txtCortTelefone.setText(null);
                    txtCortEmail.setText(null);   
                    btnAdicionar.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
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

        txtCortEndereco = new javax.swing.JTextField();
        txtCortTelefone = new javax.swing.JTextField();
        txtCortEmail = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCort = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCortId = new javax.swing.JTextField();
        txtCortPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCortNome = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cortador");
        setPreferredSize(new java.awt.Dimension(900, 500));

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ibits/icones/create.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ibits/icones/update.png"))); // NOI18N
        btnAlterar.setToolTipText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ibits/icones/delete.png"))); // NOI18N
        btnRemover.setToolTipText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ibits/icones/search.png"))); // NOI18N

        jLabel6.setText("*Campos Obrigatórios");

        tblCort.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCortMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCort);

        jLabel7.setText("Id Cortador");

        txtCortId.setEnabled(false);

        txtCortPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCortPesquisarKeyReleased(evt);
            }
        });

        jLabel1.setText("Nome*");

        jLabel2.setText("Endereço*");

        jLabel3.setText("Telefone*");

        jLabel4.setText("Email*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCortPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(124, 124, 124)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnAdicionar)
                        .addGap(43, 43, 43)
                        .addComponent(btnAlterar)
                        .addGap(51, 51, 51)
                        .addComponent(btnRemover))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCortEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCortNome, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCortTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCortEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCortId, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(282, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCortPesquisar))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCortId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCortNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCortEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCortTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCortEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRemover, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        setBounds(0, 0, 900, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // Metodo Adiconar Faccionista
        Adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void tblCortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCortMouseClicked
        // setar campos
        setar_campos();
    }//GEN-LAST:event_tblCortMouseClicked

    private void txtCortPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCortPesquisarKeyReleased
        // TODO add your handling code here:
        Pesquisar();
    }//GEN-LAST:event_txtCortPesquisarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCort;
    private javax.swing.JTextField txtCortEmail;
    private javax.swing.JTextField txtCortEndereco;
    private javax.swing.JTextField txtCortId;
    private javax.swing.JTextField txtCortNome;
    private javax.swing.JTextField txtCortPesquisar;
    private javax.swing.JTextField txtCortTelefone;
    // End of variables declaration//GEN-END:variables
}
