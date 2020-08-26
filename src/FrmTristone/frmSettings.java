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
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
        
        this.setIconImage(new ImageIcon(getClass().getResource("/img/icon_tristone.png")).getImage());
        initComponents();
        //SetPortCOM();
        getContentPane().setBackground(new Color(51,51,51));
        //getContentPane().setBackground(Color.black);
        getPathDB();   
        //jtxtScanSleep.setVisible(false);
        //lbl1.setVisible(false);
        lbl.setVisible(false);
        lbl1.setText("Scan Delay");
        cbSerialPort.setVisible(false);
//        lbl2.setVisible(false);
//        jtxtSubStation.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jLabel6.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tristone_White.png")).getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH)));
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
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();

        setMaximumSize(new java.awt.Dimension(800, 480));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tristone.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 250, 150));

        jtxtServer.setBackground(new java.awt.Color(51, 51, 51));
        jtxtServer.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtServer.setForeground(new java.awt.Color(255, 255, 255));
        jtxtServer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtServer.setBorder(null);
        jtxtServer.setOpaque(false);
        getContentPane().add(jtxtServer, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 150, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Server");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 66, -1));

        jtxtDB.setBackground(new java.awt.Color(51, 51, 51));
        jtxtDB.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtDB.setForeground(new java.awt.Color(255, 255, 255));
        jtxtDB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtDB.setBorder(null);
        jtxtDB.setOpaque(false);
        getContentPane().add(jtxtDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 150, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DataBase");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, 14));

        jtxtUser.setBackground(new java.awt.Color(51, 51, 51));
        jtxtUser.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtUser.setForeground(new java.awt.Color(255, 255, 255));
        jtxtUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtUser.setBorder(null);
        jtxtUser.setOpaque(false);
        getContentPane().add(jtxtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 150, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("User");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 66, -1));

        jtxtPass.setBackground(new java.awt.Color(51, 51, 51));
        jtxtPass.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtPass.setForeground(new java.awt.Color(255, 255, 255));
        jtxtPass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtPass.setBorder(null);
        jtxtPass.setOpaque(false);
        getContentPane().add(jtxtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 150, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Password");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, -1, -1));

        jtxtNoStation.setBackground(new java.awt.Color(51, 51, 51));
        jtxtNoStation.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtNoStation.setForeground(new java.awt.Color(255, 255, 255));
        jtxtNoStation.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtNoStation.setBorder(null);
        jtxtNoStation.setOpaque(false);
        getContentPane().add(jtxtNoStation, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 150, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("No. Station");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, -1, -1));

        jtxtInnerPass.setBackground(new java.awt.Color(51, 51, 51));
        jtxtInnerPass.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtInnerPass.setForeground(new java.awt.Color(255, 255, 255));
        jtxtInnerPass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtInnerPass.setBorder(null);
        jtxtInnerPass.setOpaque(false);
        getContentPane().add(jtxtInnerPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 150, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Inner Password");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 149, -1));

        jbtnSave.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jbtnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_save.png"))); // NOI18N
        jbtnSave.setToolTipText("");
        jbtnSave.setBorderPainted(false);
        jbtnSave.setContentAreaFilled(false);
        jbtnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnSave.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_save_colored.png"))); // NOI18N
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 360, -1, -1));

        jtxtScanSleep.setBackground(new java.awt.Color(51, 51, 51));
        jtxtScanSleep.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtScanSleep.setForeground(new java.awt.Color(255, 255, 255));
        jtxtScanSleep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtScanSleep.setBorder(null);
        jtxtScanSleep.setOpaque(false);
        getContentPane().add(jtxtScanSleep, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 150, -1));

        lbl.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        lbl.setForeground(new java.awt.Color(255, 255, 255));
        lbl.setText("Serial Port");
        getContentPane().add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 149, -1));

        jtxtCompany.setBackground(new java.awt.Color(51, 51, 51));
        jtxtCompany.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtCompany.setForeground(new java.awt.Color(255, 255, 255));
        jtxtCompany.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtCompany.setBorder(null);
        jtxtCompany.setOpaque(false);
        getContentPane().add(jtxtCompany, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 150, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Company");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, -1, -1));

        lbl1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        lbl1.setForeground(new java.awt.Color(255, 255, 255));
        lbl1.setText("Serial Port");
        getContentPane().add(lbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, -1, -1));

        cbSerialPort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10", "COM11", "COM12", "COM13", "COM14", "COM15", "COM16", "COM17", "COM18", "COM19", "COM20", "COM21", "COM22", "COM23", "COM24", "COM25", "COM26", "COM27", "COM28" }));
        getContentPane().add(cbSerialPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 150, -1));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 150, 20));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 150, 20));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 150, 20));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 150, 20));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 150, 10));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 150, 10));

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 150, 10));

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 150, 10));

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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JTextField jtxtCompany;
    private javax.swing.JTextField jtxtDB;
    private javax.swing.JTextField jtxtInnerPass;
    private javax.swing.JTextField jtxtNoStation;
    private javax.swing.JTextField jtxtPass;
    private javax.swing.JTextField jtxtScanSleep;
    private javax.swing.JTextField jtxtServer;
    private javax.swing.JTextField jtxtUser;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lbl1;
    // End of variables declaration//GEN-END:variables
}
