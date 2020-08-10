/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrmTristone;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ulises
 */
public class frmSettings extends javax.swing.JFrame {

    Enumeration commports= null;
    CommPortIdentifier myCPI = null;
    Scanner mySC;
    PrintStream myPS;
    Thread t;
    SerialPort mySP = null;
    DefaultComboBoxModel modeloCombo;
    /**
     * Creates new form frmSettings
     */
    public frmSettings() {
        
        
        initComponents();
        //SetPortCOM();
        getContentPane().setBackground(Color.DARK_GRAY);
        //getContentPane().setBackground(Color.black);
        getPathDB();   
        //jtxtScanSleep.setVisible(false);
        //lbl1.setVisible(false);
        lbl.setVisible(false);
        lbl1.setText("Scan Delay");
        cbSerialPort.setVisible(false);
        lbl2.setVisible(false);
        jtxtSubStation.setVisible(false);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
    } 
     
    
    private void writePathDB(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            String path=new File ("TristonePath.txt").getAbsolutePath ();
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);

            //for (int i = 0; i < 10; i++)
            pw.println(jtxtServer.getText());
            pw.println(jtxtDB.getText());
            pw.println(jtxtUser.getText());
            pw.println(jtxtPass.getText());
            pw.println(jtxtNoStation.getText());
            //pw.println(jtxtSubStation.getText());
            pw.println(jtxtCompany.getText());
            pw.println(jtxtInnerPass.getText());
            pw.println(jtxtScanSleep.getText());
            
            //pw.println(cbSerialPort.getSelectedItem());
            
            frmEmployee _frmEmployee=new frmEmployee();
            _frmEmployee.setVisible(true);
            this.setVisible(false);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    private void SetPortCOM(){
        modeloCombo = new DefaultComboBoxModel();
        commports = CommPortIdentifier.getPortIdentifiers();
        t = new Thread(new frmDigitalScale.ReadSerial());
        CommPort puerto = null;
        mySP = null;
        modeloCombo.addElement("Select Serial Port");
        while(commports.hasMoreElements()){
           
             myCPI = (CommPortIdentifier) commports.nextElement();
             System.out.println("Puerto " +myCPI.getName());
             modeloCombo.addElement(myCPI.getName());
             cbSerialPort.setModel(modeloCombo);
        }
    }
            
    private void getPathDB(){
        BufferedReader br = null;
        try
        {
            String dbPath="";
            String dbName = "";
            String userDB="";
            String passDB="";
            String station="";
            String passApp="";
            String scanSleep="";
            String company="";
            //String serialPort="";
            //String subStation="";
            
            String path=new File ("TristonePath.txt").getAbsolutePath ();                      
            File file = new File(path);
           
            if(file.exists())
            {
                Scanner sc = new Scanner(file); 

                dbPath = sc.nextLine();
                dbName = sc.nextLine();
                userDB = sc.nextLine();
                passDB = sc.nextLine();
                station = sc.nextLine();
                company = sc.nextLine();
                //subStation = sc.nextLine();
                passApp = sc.nextLine();                
                scanSleep = sc.nextLine();
                
                //serialPort = sc.nextLine();

                 jtxtServer.setText(dbPath);
                 jtxtDB.setText(dbName);
                 jtxtUser.setText(userDB);
                 jtxtPass.setText(passDB);
                 jtxtNoStation.setText(station);
                 jtxtInnerPass.setText(passApp);
                 jtxtScanSleep.setText(scanSleep);
                 jtxtCompany.setText(company);
                 //jtxtSubStation.setText(subStation);
                 //cbSerialPort.setSelectedItem(serialPort);
            }
            else
                JOptionPane.showMessageDialog(null,"No Existe Archivo");
                
             
        }
        catch (Exception ex) {
          ex.printStackTrace();

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

        jLabel6 = new javax.swing.JLabel();
        jtxtServer = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxtDB = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtxtUser = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtxtPass = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtxtNoStation = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtxtInnerPass = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jbtnSave = new javax.swing.JButton();
        jtxtScanSleep = new javax.swing.JTextField();
        lbl = new javax.swing.JLabel();
        jtxtCompany = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        cbSerialPort = new javax.swing.JComboBox<>();
        jtxtSubStation = new javax.swing.JTextField();
        lbl2 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(800, 480));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tristone.png"))); // NOI18N

        jtxtServer.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtxtServer.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Server");

        jtxtDB.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtxtDB.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DataBase");

        jtxtUser.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtxtUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("User");

        jtxtPass.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtxtPass.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Password");

        jtxtNoStation.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtxtNoStation.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("No. Station");

        jtxtInnerPass.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtxtInnerPass.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Inner Password");

        jbtnSave.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jbtnSave.setText("Save");
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });

        jtxtScanSleep.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtxtScanSleep.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbl.setForeground(new java.awt.Color(255, 255, 255));
        lbl.setText("Serial Port");

        jtxtCompany.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtxtCompany.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Company");

        lbl1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbl1.setForeground(new java.awt.Color(255, 255, 255));
        lbl1.setText("Serial Port");

        cbSerialPort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10", "COM11", "COM12", "COM13", "COM14", "COM15", "COM16", "COM17", "COM18", "COM19", "COM20", "COM21", "COM22", "COM23", "COM24", "COM25", "COM26", "COM27", "COM28" }));

        jtxtSubStation.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtxtSubStation.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lbl2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbl2.setForeground(new java.awt.Color(255, 255, 255));
        lbl2.setText("Sub.Station");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnSave)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtxtInnerPass, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSerialPort, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtServer, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtDB, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtUser, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(83, 83, 83))
                            .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbl2)
                                        .addComponent(jtxtSubStation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jtxtNoStation, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jtxtCompany, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                        .addComponent(jtxtScanSleep)
                                        .addComponent(lbl1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jtxtPass, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(83, 83, 83))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(89, 89, 89))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtNoStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtDB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtInnerPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(lbl1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtScanSleep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSerialPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 53, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtSubStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jbtnSave))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //frmScanProduct _frmScanProduct =new frmScanProduct();
        frmEmployee _frmEmployee= new frmEmployee();
        _frmEmployee.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed
        // TODO add your handling code here:
        writePathDB();
    }//GEN-LAST:event_jbtnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(frmSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSettings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbSerialPort;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JTextField jtxtCompany;
    private javax.swing.JTextField jtxtDB;
    private javax.swing.JTextField jtxtInnerPass;
    private javax.swing.JTextField jtxtNoStation;
    private javax.swing.JTextField jtxtPass;
    private javax.swing.JTextField jtxtScanSleep;
    private javax.swing.JTextField jtxtServer;
    private javax.swing.JTextField jtxtSubStation;
    private javax.swing.JTextField jtxtUser;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    // End of variables declaration//GEN-END:variables
}
