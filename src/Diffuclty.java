
import java.awt.Dimension;
import java.awt.Toolkit;
import sun.audio.AudioPlayer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Enrico
 */
public class Diffuclty extends javax.swing.JFrame {

    /**
     * Creates new form Diffuclty
     */
    public Diffuclty() {
   
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setResizable(false);
        initComponents();
       
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public static String dif ;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelStart2 = new PanelStart();
        bteasy = new javax.swing.JButton();
        btmedium = new javax.swing.JButton();
        bthard = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        bteasy.setBackground(new java.awt.Color(102, 255, 51));
        bteasy.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bteasy.setForeground(new java.awt.Color(51, 51, 51));
        bteasy.setText("EASY");
        bteasy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bteasy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bteasyMouseClicked(evt);
            }
        });
        bteasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteasyActionPerformed(evt);
            }
        });

        btmedium.setBackground(new java.awt.Color(255, 255, 0));
        btmedium.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btmedium.setForeground(new java.awt.Color(51, 51, 51));
        btmedium.setText("MEDIUM");
        btmedium.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btmediumMouseClicked(evt);
            }
        });

        bthard.setBackground(new java.awt.Color(255, 0, 51));
        bthard.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bthard.setForeground(new java.awt.Color(51, 51, 51));
        bthard.setText("HARD");
        bthard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bthardMouseClicked(evt);
            }
        });

        jButton1.setText("Help");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelStart2Layout = new javax.swing.GroupLayout(panelStart2);
        panelStart2.setLayout(panelStart2Layout);
        panelStart2Layout.setHorizontalGroup(
            panelStart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStart2Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(panelStart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bthard, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btmedium, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bteasy, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(106, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelStart2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        panelStart2Layout.setVerticalGroup(
            panelStart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelStart2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(bteasy, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btmedium, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bthard, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelStart2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelStart2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btmediumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btmediumMouseClicked
        // TODO add your handling code here:
        dif ="/Map/map_medium.png";
        Game.level = new Level(dif);
        Game.game.start();
        Game.stop_song();
        Game.load_song("siren.wav ");// untuk ganti musik 
        Game.frame.setVisible(true);
        this.setVisible(false);
        Player.cekmenang = false;
        Player.diff = "medium";
    }//GEN-LAST:event_btmediumMouseClicked

    private void bteasyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bteasyMouseClicked
        // TODO add your handling code here:
        dif ="/Map/map_easy.png";
        Game.level = new Level(dif);
        Game.game.start();
        Game.stop_song();
        Game.load_song("siren.wav ");// untuk ganti musik 
        Game.frame.setVisible(true);
        this.setVisible(false);
        Player.cekmenang = false;
        Player.diff = "easy";
    }//GEN-LAST:event_bteasyMouseClicked

    private void bthardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthardMouseClicked
        // TODO add your handling code here:
        dif ="/Map/map_hard.png";
        Game.level = new Level(dif);
        Game.game.start();
        Game.stop_song();
        Game.load_song("siren.wav ");// untuk ganti musik
        Game.frame.setVisible(true);
        this.setVisible(false);
        Player.cekmenang = false;
        Player.diff = "hard";
    }//GEN-LAST:event_bthardMouseClicked

    private void bteasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteasyActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_bteasyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        HelpFrame hf= new HelpFrame();
        hf.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Diffuclty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Diffuclty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Diffuclty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Diffuclty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Diffuclty().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bteasy;
    private javax.swing.JButton bthard;
    private javax.swing.JButton btmedium;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private PanelStart panelStart2;
    // End of variables declaration//GEN-END:variables
}
