/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrmTristone;

import Entities.EmployeeEntity;
import Entities.PrintEntity;
import Entities.SendValidationEntity;
import Entities.StationEntity;
import Model.EmployeesModel;
import Model.StationModel;
import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;

/**
 *
 * @author Ulises
 */
public class frmSerialNumber extends javax.swing.JFrame {

    /**
     * Creates new form frmSerialNumber
     */
    PrintEntity _PrintEntity;
    SendValidationEntity _sendValidationEntity;
    String _noEmployee = "";
    int _serialNumber = 0;
    Channel channel;
    private static final String QUEUE_NAME = "workQueues";
    private Connection connection;
    private static final String EXCHANGE_NAME = "stations";
    String _cancelCode2="";
    
    public frmSerialNumber() {
        initComponents();
        
        getContentPane().setBackground(Color.DARK_GRAY);
        jtxtMessage.setEditable(false);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public void initialize(){
        _PrintEntity=null;
        _sendValidationEntity=null;
        _noEmployee = "";
        _serialNumber = 0;
        channel=null;
        connection=null;
    }
    
    public static boolean ValidateNumber(String number){
        Pattern pat = Pattern.compile("^[0-9]+([,\\.][0-9]*)?$");
        Matcher mat = pat.matcher(number);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void SetSerialNumber(int serialNumber){
        if(serialNumber==0){
            jlblSerialNumber.setText("Error");
             jtxtMessage.setText("Error");
            jtxtMessage.setBackground(Color.red);
            jtxtMessage.setEditable(false);
            _serialNumber = serialNumber;
        }else{
         jlblSerialNumber.setText("" + serialNumber);
        _serialNumber = serialNumber;
        }
    }
    
    private void ReseteBox(){
       Timer timer;
       TimerTask task;
       int time=2000;
       task = new TimerTask() {
           @Override
           public void run() {
               jtxtMessage.setText("");
                jtxtMessage.setBackground(Color.white);                
                jtxtMessage.setEditable(false);
           }
       };
       
       timer =new Timer();
       timer.schedule(task, time);
       
   }
    
    
    public void SetSerialNumber(PrintEntity printEntity){
        //_serialNumber = printEntity.getnSerieserie();
        _PrintEntity=new PrintEntity();
         _PrintEntity.setEstacion(printEntity.getEstacion());
        _PrintEntity.setBartender(printEntity.getBartender());
        _PrintEntity.setCust(printEntity.getCust());
        _PrintEntity.setEmpresa(printEntity.getEmpresa());
        _PrintEntity.setEstacion(printEntity.getEstacion());
        _PrintEntity.setFecha(printEntity.getFecha());
        _PrintEntity.setImpresora(GenerateWebServicePrint(printEntity));
        _PrintEntity.setImpresoraBartender(printEntity.getImpresoraBartender());
        _PrintEntity.setPckd(printEntity.getPckd());
        _PrintEntity.setPlat(printEntity.getPlat());
        _PrintEntity.setnParte(printEntity.getnParte());
        _PrintEntity.setnProveedor(printEntity.getnProveedor());
        _PrintEntity.setnSAP(printEntity.getnSAP());
        _PrintEntity.setnSerieserie(printEntity.getnSerieserie());
        _PrintEntity.setnVali(printEntity.getnVali());
        _PrintEntity.setTurno(printEntity.getTurno());
        _PrintEntity.setEmpresa(printEntity.getEmpresa());
        _PrintEntity.setEmp_num(printEntity.getEmp_num());
        //jlblSerialNumber.setText(""+printEntity.getnSerieserie());
    }
    
    private PrintEntity getDataEntity(SendValidationEntity sendValidationEntity){
        _PrintEntity=new PrintEntity();
        _PrintEntity.setEstacion(sendValidationEntity.getEstacion());
        _PrintEntity.setBartender(sendValidationEntity.isBartender());
        //_PrintEntity.setCust(sendValidationEntity.getCust());
        _PrintEntity.setEmpresa(sendValidationEntity.getEmpresa());
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now(); 
        
        _PrintEntity.setFecha(dtf.format(now));
        
        
        _PrintEntity.setImpresoraBartender(sendValidationEntity.getImpresoraBartender());
        _PrintEntity.setPckd(sendValidationEntity.getPckd());
        //_PrintEntity.setPlat(sendValidationEntity.getPlat());
        
        
        //_PrintEntity.setnSAP();
        _PrintEntity.setnSerieserie(sendValidationEntity.getnVali());
        
        
        
        
        
        return _PrintEntity;
    }
    
    private SendValidationEntity getSendDataEntity(){
        SendValidationEntity _sendValidationEntity = new SendValidationEntity();
        _sendValidationEntity.setBartender(_PrintEntity.getBartender());
        _sendValidationEntity.setEmpresa(_PrintEntity.getEmpresa());
        _sendValidationEntity.setEstacion(_PrintEntity.getEstacion());
        _sendValidationEntity.setImpresoraBartender(_PrintEntity.getImpresoraBartender());
        _sendValidationEntity.setPckd(_PrintEntity.getPckd());
        _sendValidationEntity.setnVali(_serialNumber);
        
        return _sendValidationEntity;
    }
    
    
    private boolean InsertData(PrintEntity printEntity){
        boolean result=false;
        
        StationModel obj=new StationModel();
        result = obj.InsertProcess(printEntity);
        
        return result;
    }
    
     
    
    private void CleanBox(){
        jtxtMessage.setBackground(Color.white); 
        jtxtMessage.setText("");
        jlblSerialNumber.setText("Serial Number");
        jtxtSerialNumber.setText("");
//         ReseteBox();
    }
    
    public boolean ValidateSerialNumber(String serialNumber){
        boolean validateSN = false;
        String SN="S"+_serialNumber;
        if(serialNumber.equals(SN) || serialNumber.equals("s"+_serialNumber)){
            validateSN = true;           
           
        }else{
            
            validateSN = false;
            jtxtMessage.setText("Invalid Serial Number");
            jtxtMessage.setBackground(Color.red);
            jtxtMessage.setEditable(false);
            jtxtSerialNumber.setText("");
            ReseteBox();
            
        }
        return validateSN;
    }
    
    private void SendTTL(PrintEntity _printEntity) throws Exception {   
        StationEntity _stationEntity = new StationEntity();
        StationModel objStationModel =new StationModel();
        boolean connMySQL=true;
        boolean connRabbitMQ=true;
        _stationEntity = objStationModel.getStationConf(_printEntity.getEstacion());
        connMySQL=false;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(_stationEntity.getMq_server());
       
        factory.setUsername(_stationEntity.getMq_user());
        factory.setPassword(_stationEntity.getMq_pass());
        
        
        try{
            connection = factory.newConnection();
            connRabbitMQ=false;
        
            channel = connection.createChannel();
        
            //channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.TOPIC);
            channel.exchangeDeclare(EXCHANGE_NAME,"direct",true);
            
            Gson gson = new Gson();

            String message = gson.toJson(_printEntity);
            String routingKey = "s"+ _printEntity.getEstacion();
            
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                                   .expiration("60000")
                                   .build();
            
            channel.basicPublish(EXCHANGE_NAME, routingKey , properties, message.getBytes());
            
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

           
            
            
       
        }catch(Exception e){
            e.printStackTrace();
            if(connRabbitMQ){
                jtxtMessage.setText("RabbitMQ "+ e.getMessage());
                jtxtMessage.setBackground(Color.red);                
                jtxtMessage.setEditable(false);
            } else if(connMySQL){
                jtxtMessage.setText("MySQL "+ e.getMessage());
                jtxtMessage.setBackground(Color.red);                
                jtxtMessage.setEditable(false);
            }                
        }
                    
        
    }
    
    private void Send(SendValidationEntity _sendValidationEntity) throws Exception {   
        StationEntity _stationEntity = new StationEntity();
        StationModel objStationModel =new StationModel();
        boolean connMySQL=true;
        boolean connRabbitMQ=true;
        _stationEntity = objStationModel.getStationConf(_sendValidationEntity.getEstacion());
        connMySQL=false;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(_stationEntity.getMq_server());
       
        factory.setUsername(_stationEntity.getMq_user());
        factory.setPassword(_stationEntity.getMq_pass());
        
        
        try{
            connection = factory.newConnection();
            connRabbitMQ=false;
        
            channel = connection.createChannel();
        
            channel.exchangeDeclare(EXCHANGE_NAME,"direct",true);
            
            Gson gson = new Gson();

            //String message = gson.toJson(GetDataEntity());
            String message = gson.toJson(_sendValidationEntity);
            
            String routingKey = "s"+ _sendValidationEntity.getEstacion();
            
            channel.basicPublish("", QUEUE_NAME,null, message.getBytes());
            //System.out.println(String.format("Sent «%s»", message));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

           
       
        }catch(Exception e){
            if(connRabbitMQ){
                jtxtMessage.setText("RabbitMQ "+ e.getMessage());
                jtxtMessage.setBackground(Color.red);                
                jtxtMessage.setEditable(false);
                ReseteBox();
            } else if(connMySQL){
                jtxtMessage.setText("MySQL "+ e.getMessage());
                jtxtMessage.setBackground(Color.red);                
                jtxtMessage.setEditable(false);
                ReseteBox();
            }                
        }
                    
        
    }
     
    private String GenerateWebServicePrint(PrintEntity _printEntity){
        String webServicePrint="";
        if(_printEntity.getBartender()){
            webServicePrint="http://tftdelsrv004:8086/Integration/gmgm1/Execute?id=1&no_sap="+_printEntity.getnSAP()+"&std_pack="+_printEntity.getPckd()+"&cust_part="+_printEntity.getnParte()+""+
                        "&platform=GM1&cont_type=SC242311&custpla_"+
                        "dock=CI MT&custpla_name=CAMI CANADA&delivery_note=&from=CARRETERA PANAMERICANA KM 135&gro_weight=18&madein=Made In MEXICO&mhcr=DOCK&part_num=23463169&pci_13=AUTRANS\n" +
                        "&pci_14=EMPTY:      STOCKMAN:&pci_15=XXXXXXXAUTRA&pci_17=HOSE ASM-RAD IN&quant=50&reference=353H000G&sup_cont=(52) 639 132 6040\n" +
                        "&sup_duns=815734256&sup_name=Tristone Flowtech de Mexico&suppla_dock=&to=17 UNDERWOOD ROAD&serial_num="+_printEntity.getnSerieserie()+"&printer="+_printEntity.getImpresoraBartender()+"";
        }else{
            webServicePrint="http://tftdelsrv004:8086/Integration/gm/Execute?id=1&no_sap="+_printEntity.getnSAP()+"&std_pack="+_printEntity.getPckd()+""
                            + "&cust_part="+_printEntity.getnParte()+"&serial_num="+_printEntity.getnSerieserie()+"&pack_quantity=10&packs=10"
                            + "&total_quant=10&printer="+_printEntity.getImpresora()+"";
        }
        return webServicePrint;
    }
    
    public void SetEmployee(String noEmployee){
        EmployeesModel objEmployee=new EmployeesModel();
        EmployeeEntity _employeeEntity=new EmployeeEntity();
        _employeeEntity = objEmployee.getEmployee(noEmployee);

        jlblEmployeeName.setText(_employeeEntity.getNameEmployee());
        jlblEmployeeNumb.setText(_employeeEntity.getNumEmployee());
        _noEmployee = noEmployee;
        
         _cancelCode2=CancelCode();
    }
    
    
    
    
    private String CancelCode(){
        String dbPath="";
        String dbName = "";
        String userDB="";
        String passDB="";
        String station="";
        String passApp="";
        String scanSleep="";
        String company="";
        //String serialPort="";
        
        String _cancelCode = "";
        
        StationEntity _stationEntity = new StationEntity();
        StationModel objStationModel =new StationModel();
        
        String path=new File ("TristonePath.txt").getAbsolutePath ();
                      
        File file = new File(path);
        try{
                if(file.exists())
                {
                    Scanner sc = new Scanner(file); 

                    dbPath = sc.nextLine();
                    dbName = sc.nextLine();
                    userDB = sc.nextLine();
                    passDB = sc.nextLine();
                    station = sc.nextLine();
                    company = sc.nextLine();
                    passApp = sc.nextLine();
                    scanSleep = sc.nextLine();
                    
                    //serialPort = sc.nextLine();
              
                    _stationEntity = objStationModel.getStationConf(station);
                    
                    _cancelCode2 = _stationEntity.getCancelacion();
                    
                }
                else{
                    jtxtMessage.setText("The File doesn't exist"); 
                    jtxtMessage.setBackground(Color.red);
                    jtxtMessage.setEditable(false);
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            return _cancelCode2;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlblEmployeeName = new javax.swing.JLabel();
        jlblEmployeeNumb = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtxtSerialNumber = new javax.swing.JTextField();
        jlblSerialNumber = new javax.swing.JLabel();
        jtxtMessage = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tristone.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Employee ID:");

        jlblEmployeeName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlblEmployeeName.setForeground(new java.awt.Color(255, 255, 255));
        jlblEmployeeName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblEmployeeName.setText("Employee Name:");
        jlblEmployeeName.setName("jlblEmployee"); // NOI18N

        jlblEmployeeNumb.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlblEmployeeNumb.setForeground(new java.awt.Color(255, 255, 255));
        jlblEmployeeNumb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblEmployeeNumb.setText("Employee Number:");
        jlblEmployeeNumb.setName("jlblEmployee"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Employee Name:");
        jLabel4.setName(""); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Serial Number:");
        jLabel5.setName(""); // NOI18N

        jtxtSerialNumber.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtxtSerialNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtSerialNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtSerialNumberKeyPressed(evt);
            }
        });

        jlblSerialNumber.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlblSerialNumber.setForeground(new java.awt.Color(255, 255, 255));
        jlblSerialNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSerialNumber.setText("Serial Number");
        jlblSerialNumber.setName(""); // NOI18N

        jtxtMessage.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jtxtMessage.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jlblEmployeeNumb, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addComponent(jlblEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtSerialNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jlblSerialNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addGap(38, 38, 38)))
                .addGap(102, 102, 102))
            .addComponent(jtxtMessage)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtSerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblSerialNumber))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblEmployeeName)
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblEmployeeNumb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jtxtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtSerialNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtSerialNumberKeyPressed
        // TODO add your handling code here:
        boolean result = false;
         if(evt.getKeyCode() == KeyEvent.VK_ENTER ){
             String serialNumber=jtxtSerialNumber.getText();
             //if(ValidateNumber(serialNumber)){
              String noSerial = jtxtSerialNumber.getText();
             if(noSerial.equals(_cancelCode2)){
                frmEmployee _frmEmployee=new frmEmployee();
               
                _frmEmployee.setVisible(true);
                //this.setVisible(false);
                this.dispose();
            }else
             
                if(ValidateSerialNumber(serialNumber)){
                    _PrintEntity.setnSerieserie(_serialNumber);
                    result = InsertData(_PrintEntity);
                    if(result){
                        try {
                            Send(getSendDataEntity());
                            } catch (Exception ex) {
                        Logger.getLogger(frmSerialNumber.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            frmEmployee _frmEmployee = new frmEmployee();
                            _frmEmployee.setVisible(true);
                            this.dispose();
                        
                    }
                    else{
                        jtxtMessage.setText("Invalid Serial Number");
                        jtxtMessage.setBackground(Color.red);
                        jtxtMessage.setEditable(false);
                        ReseteBox();
                    }
                }else{
                    jtxtMessage.setText("Invalid Serial Number");
                    jtxtMessage.setBackground(Color.red);
                    jtxtMessage.setEditable(false);
                    ReseteBox();
                }           
                 
         }
    }//GEN-LAST:event_jtxtSerialNumberKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(frmSerialNumber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSerialNumber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSerialNumber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSerialNumber.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSerialNumber().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jlblEmployeeName;
    private javax.swing.JLabel jlblEmployeeNumb;
    private javax.swing.JLabel jlblSerialNumber;
    private javax.swing.JTextField jtxtMessage;
    private javax.swing.JTextField jtxtSerialNumber;
    // End of variables declaration//GEN-END:variables
}
