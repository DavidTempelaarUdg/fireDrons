import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 * @author Llorenç
 */

public class UI extends javax.swing.JFrame {
    
    private Escenari escena;
    
    public Timer timer = new Timer(300, new ActionListener (){
        public void actionPerformed(ActionEvent e){
             boolean end ;
            if(escena != null){
                end = escena.Step();
                initable();
                pinta();

                if(end){
                 DefaultTableModel Tmodel= new DefaultTableModel(4,escena.Robots.size()+1);
                 jTable2.setModel(Tmodel);
                 for(int i = 0; i!=escena.Robots.size(); i++){
                     jTable2.setValueAt(i,0,i+1);
                     jTable2.setValueAt(escena.Robots.get(i).getEnergia(),1,i+1);
                     jTable2.setValueAt(escena.Robots.get(i).getPunts(),2,i+1);
                 }
                 
                 
                     jTable2.setValueAt("Robot num",0,0);
                     jTable2.setValueAt("energia",1,0);
                     jTable2.setValueAt("puntuació",2,0);

                     jTable2.setValueAt(escena.numArbrescremats,3,1);
                     jTable2.setValueAt("Arbres Cremats",3,0);
                     
                    
                     timer.stop();
                }
            }
        }
    }); 

    public UI() {
        initComponents();
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(550);
        jTable2.getColumnModel().getColumn(0).setWidth(550);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setToolTipText("Es començara la simulació");
        jButton1.setLabel("Step");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "La Taula esta buida, carrega les dades amb el boto Fitxer --->"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setEnabled(false);
        jTable2.setGridColor(new java.awt.Color(255, 255, 255));
        jTable2.setRowHeight(50);
        jTable2.setRowMargin(0);
        jScrollPane2.setViewportView(jTable2);

        jButton2.setText("Fitxer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Run");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Stop");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Callback del Boto Start.
        boolean end ;
        if(escena != null){
            end = escena.Step();
            initable();
            pinta();
            
            if(end){
             DefaultTableModel Tmodel= new DefaultTableModel(3,escena.Robots.size()+1);
             jTable2.setModel(Tmodel);
             for(int i = 0; i!=escena.Robots.size(); i++){
                 jTable2.setValueAt(i,0,i+1);
                 jTable2.setValueAt(escena.Robots.get(i).getEnergia(),1,i+1);
                 jTable2.setValueAt(escena.Robots.get(i).getPunts(),2,i+1);
             }
                 jTable2.setValueAt("Robot num",0,0);
                 jTable2.setValueAt("energia",1,0);
                 jTable2.setValueAt("puntuació",2,0);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         JFileChooser escollit = new JFileChooser(System.getProperty("user.dir") + "//escenaris");
         int option = escollit.showOpenDialog(this);
         if (option == JFileChooser.APPROVE_OPTION) {
             escena = new Escenari();
            try {
                escena.Crea(escollit.getSelectedFile());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
             
             //L'escenari esta creat, inicialitsem la taula
             initable();
             pinta();
             
        }else{
             System.err.println("Error: Fitxer no seleccionat!");
         }
         
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       timer.start();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        timer.stop();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

    public void pinta() {
        //pintem buits
        for (int i = 0; i != escena.getX(); i++) {
            for (int j = 0; j != escena.getY(); j++) {
                    String dir = System.getProperty("user.dir") + "\\Imatges\\buit.jpg";
                    ImageIcon icon= new ImageIcon(dir);
                    jTable2.setValueAt(icon, (int) i, (int) j);//la visualitzacio s'ha de girar
            }
        }
        
        //pintem arbres
            for( int i =0; i!= escena.Arbres.size(); i++){
                Arbre r = escena.Arbres.get(i);
                ImageIcon icon ;
                icon = r.getImatge();
                jTable2.setValueAt(icon, (int)r.x, (int)r.y);
            }
         //Pintem focs
            for( int i =0; i!= escena.Focs.size(); i++){
                Foc r = escena.Focs.get(i);
                ImageIcon icon ;
                icon = r.getImatge();
                jTable2.setValueAt(icon, (int)r.x, (int)r.y);
            }
        //pintem diposit    
            ImageIcon icon = escena.Diposit.getImatge();
            jTable2.setValueAt(icon, (int)escena.Diposit.x, (int)escena.Diposit.y);
        
        //pintem dron    
            for( int i =0; i!= escena.Robots.size(); i++){
                Robot r = escena.Robots.get(i);
                icon = r.getImatge();
                jTable2.setValueAt(icon, (int)r.getX(), (int)r.getY());
            }
            
    }
    
    public void initable(){
             int x,y;
             x=(int)escena.getX();
             y=(int)escena.getY();
             DefaultTableModel Tmodel= new DefaultTableModel(x,y);
             jTable2.setModel(Tmodel);
             for( int i =0;i!=y;i++){
                 jTable2.getColumnModel().getColumn(i).setCellRenderer(new ImageRenderer());
             //posem amplada preferida i actual a 50
            jTable2.getColumnModel().getColumn(i).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(i).setWidth(50);
        }

        //posem el color
       // jTable2.setBackground(Color.white);

        //posem les mides a les celles
        jTable2.setRowHeight(50);
    }
    
}
