/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokoobat;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Didin
 */
public class Transaksi extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    DefaultTableModel tableobat;
    ResultSet rs;
    
    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
         try {
            conn = Koneksi.getConnection();
            System.out.println("Koneksi berhasil");
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Koneksi gagal");
        }
        try {
            showdata();
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            nokategori();
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            tampildata();
             jComboBox1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        tampildata1();
                    } catch (SQLException ex) {
                        Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            tampildata3();
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void showdata() throws SQLException {
        String sql = "SELECT * FROM transaksi";
        try {
            pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            tableobat = new DefaultTableModel();
            tableobat.addColumn("id transaksi");
            tableobat.addColumn("id customer");
            tableobat.addColumn("nama customer");
            tableobat.addColumn("id barang");
            tableobat.addColumn("jumlah");
            tableobat.addColumn("harga");
            tableobat.addColumn("total");
         while(rs.next()) {
           tableobat.addRow(new Object[]{
           rs.getString(1),
           rs.getString(2),
           rs.getString(3),
           rs.getString(4),
           rs.getString(5),
           rs.getString(6),
           rs.getString(7)
           });
        }
        jTable1.setModel(tableobat);
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void nokategori() throws SQLException {
        conn = Koneksi.getConnection();
        try {
            rs = conn.createStatement().executeQuery("select * from transaksi order by id_transaksi desc");
            if (rs.next()) {
                String nofak = rs.getString("id_transaksi").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0000";
                } else if (AN.length() == 2) {
                    Nol = "000";
                } else if (AN.length() == 3) {
                    Nol = "00";
                } else if (AN.length() == 4) {
                    Nol = "0";
                } else if (AN.length() == 5) {
                    Nol = "";
                }

                jTextField1.setText("T" + Nol + AN);
            } else {
                jTextField1.setText("T00001");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     private void tampildata() throws SQLException {
     conn=Koneksi.getConnection();
    //jTextField3.setText("");
        try {
            rs=conn.createStatement().executeQuery("select id_customer from customer");
            while (rs.next()){
                jComboBox1.addItem(rs.getString(1));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
    }
    public void tampildata1() throws SQLException {
        conn = Koneksi.getConnection();
        try {
            rs = conn.createStatement().executeQuery("select nama_customer from customer where id_customer='" + jComboBox1.getSelectedItem() + "'");      // disini saya menampilkan NIM, anda dapat menampilkan
            while (rs.next()) {
                Object[] ob = new Object[2];
                ob[0] = rs.getString(1);

                jTextField2.setText((String) ob[0]);
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void tampildata3() throws SQLException {
     conn=Koneksi.getConnection();
    //jTextField3.setText("");
        try {
            rs=conn.createStatement().executeQuery("select id_obt from obat");
            while (rs.next()){
                jComboBox2.addItem(rs.getString(1));
                jComboBox2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        tampildata4();
                    } catch (SQLException ex) {
                        Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
    }
     public void tampildata4() throws SQLException {
        conn = Koneksi.getConnection();
        try {
            rs = conn.createStatement().executeQuery("select nama_obt,jumlah,harga from obat where id_obt='" + jComboBox2.getSelectedItem() + "'");      // disini saya menampilkan NIM, anda dapat menampilkan
            while (rs.next()) {
                Object[] ob = new Object[3];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);

                jTextField3.setText((String) ob[0]);
                jTextField4.setText((String) ob[1]);
                jTextField5.setText((String) ob[2]);
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void reset(){
    jTextField2.setText("");
    jTextField3.setText("");
    jTextField4.setText("");
    jTextField5.setText("");
    jTextField6.setText("");
    jTextField7.setText("");
    
    }
    private void refresh(){
        try {
            tampildata3();
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
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

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setEditable(false);

        jLabel1.setText("id transaksi");

        jLabel2.setText("id customer");

        jLabel3.setText("Nama customer");

        jLabel4.setText("id barang");

        jLabel5.setText("Nama barang");

        jTextField4.setEditable(false);

        jTextField5.setEditable(false);

        jLabel6.setText("Stok terakhir");

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });

        jLabel7.setText("jumlah");

        jLabel8.setText("harga");

        jLabel9.setText("Total");

        jTextField7.setEditable(false);
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel10.setText("Transaksi obat");

        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                .addComponent(jTextField3)
                                .addComponent(jTextField4)
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField7))
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jButton1)
                        .addGap(35, 35, 35)
                        .addComponent(jButton2)
                        .addGap(30, 30, 30)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel10)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed

    }//GEN-LAST:event_jTextField7KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        int satu = Integer.valueOf(jTextField6.getText());
        int dua = Integer.valueOf(jTextField5.getText());
        int hasil = (satu*dua);
       jTextField7.setText(String.valueOf(hasil));
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTextField5.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Nama anda  belum di isi Mohon untuk diisi!");
            jTextField2.requestFocus();
        }
        
        else{
            try {
                conn = Koneksi.getConnection();
                conn.createStatement().executeUpdate("insert into transaksi values"+"('"+jTextField1.getText()+"','"+jComboBox1.getSelectedItem()+"','"+jTextField2.getText()+"','"+jComboBox2.getSelectedItem()+"','"+jTextField6.getText()+"','"+jTextField5.getText()+"','"+jTextField7.getText()+"')");
                showdata();
                nokategori();
                reset();
            } catch (SQLException ex) {
                Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
          int baris = jTable1.getSelectedRow();
       if(baris>-1){
        String id = jTable1.getValueAt(baris, 0).toString();
        jTextField1.setText(id);
        String id_customer = jTable1.getValueAt(baris, 1).toString();
        jComboBox1.setSelectedItem(id_customer);
        String nama = jTable1.getValueAt(baris, 2).toString();
        jTextField2.setText(nama);
        String id_obat = jTable1.getValueAt(baris, 3).toString();
        jComboBox2.setSelectedItem(id_obat);
        String jumlah = jTable1.getValueAt(baris, 4).toString();
        jTextField6.setText(jumlah);
        String harga = jTable1.getValueAt(baris, 5).toString();
        jTextField5.setText(harga);
        String total  = jTable1.getValueAt(baris, 6).toString();
        jTextField7.setText(total);
        
        
       }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int n = JOptionPane.showOptionDialog(this, "ingin update", "update", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
       
       if(n == JOptionPane.YES_OPTION){
           try {
               conn = Koneksi.getConnection();
               conn.createStatement().executeUpdate("update transaksi set id_barang='"+jComboBox1.getSelectedItem()+"', harga='"+jTextField5.getText()+"', jumlah='"+jTextField6.getText()+"',total='"+jTextField7.getText()+"' where id_transaksi='"+jTextField1.getText()+"'");
               showdata();
               nokategori();
               reset();
           } catch (SQLException ex) {
               Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
               
       } 
       } else {
       
       reset();
           try {
               nokategori();
           } catch (SQLException ex) {
               Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int n = JOptionPane.showOptionDialog(this, "Apkakah anda ingin delete data", "delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
       
       if(n == JOptionPane.YES_OPTION){
           try {
               conn = Koneksi.getConnection();
               conn.createStatement().executeUpdate("delete from transaksi where id_transaksi='"+jTextField1.getText()+"'");
               showdata();
               nokategori();
               reset();
           } catch (SQLException ex) {
               Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
               
       } 
       } else {
       
       reset();
           try {
               nokategori();
           } catch (SQLException ex) {
               Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
