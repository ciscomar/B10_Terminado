/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrmTristone;

import Entities.ConsumerJSONEntity;
import Entities.PrintEntity;
import Entities.ProductExtrusorEntity;
import Entities.ProductsEntity;
import Entities.SendJsonEntity;
import Entities.StationEntity;
import Entities.ValidacionEntity;
import Model.ProductExtrusorModel;
import Model.StationModel;
import com.google.gson.Gson;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ulises
 */
public class frmExtrusora extends javax.swing.JFrame {

    
    Channel channel;
    
    
    int contStdPack = 0;
    String cancelCode="";
     private static String employee = "";
    ProductsEntity _ProductsEntity;
    int stdPack = 0;
    String _station="";
    String scanSleep="";
    private static final String QUEUE_NAME = "workQueues";   
    private static int i=0;
    private static final String EXCHANGE_NAME = "stations";   
    private Connection connection;
    //private Channel channel;
    DeliverCallback deliverCallback = null;
    
    
    /**
     * Creates new form frmExtrusora
     */
    public frmExtrusora() {
        initComponents();
        getContentPane().setBackground(Color.DARK_GRAY);
        //jTableProduct.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableProduct.setAutoscrolls(true);
        jTableProduct.setPreferredScrollableViewportSize(new Dimension(500, 70));
         GetData("");
         setJTexFieldChanged(jtxtSearchText);
    }
    
    private void setJTexFieldChanged(JTextField txt){
        DocumentListener documentListener = new DocumentListener() {
 
        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }
 
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }
 
        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }
        };
        txt.getDocument().addDocumentListener(documentListener);
 
    }
 
    private void printIt(DocumentEvent documentEvent) {
        DocumentEvent.EventType type = documentEvent.getType();
 
        if (type.equals(DocumentEvent.EventType.CHANGE))
        {
 
        }
        else if (type.equals(DocumentEvent.EventType.INSERT))
        {
            txtEjemploJTextFieldChanged();
        }
        else if (type.equals(DocumentEvent.EventType.REMOVE))
        {
            txtEjemploJTextFieldChanged();
        }
    }
 
    private void txtEjemploJTextFieldChanged(){
        //Copiar el contenido del jtextfield al jlabel        
        
        String partNumber = jtxtSearchText.getText();
        GetData(partNumber);
    }
    
    private void GetData(String partNumber){
        ProductExtrusorModel obj=new  ProductExtrusorModel();
        ArrayList PL = new ArrayList();
        CleanJTable();
        if(partNumber.equals("Enter text to search") || partNumber.equals(""))
            PL = obj.GetExtrusionVulcanizado();
        else
            PL = obj.GetExtrusionVulcanizadoByPart(partNumber);
        Object O[]=null;
         DefaultTableModel modelo = (DefaultTableModel) jTableProduct.getModel();
        //Object filaNueva[] = {obj.GetExtrusionVulcanizado()};
       for (int i = 0; i < PL.size(); i++) {
            modelo.addRow(O);
            ProductExtrusorEntity getP = (ProductExtrusorEntity) PL.get(i);
            //modelo.setValueAt(getP.getId(), i, 0);
            modelo.setValueAt(getP.getPart_number(), i, 0);
            modelo.setValueAt(getP.getMandrel(), i, 1);
            modelo.setValueAt(getP.getRack(), i, 2);
            modelo.setValueAt(getP.getDescription(), i, 3);
            modelo.setValueAt(getP.getUbicacion(), i, 4);
            modelo.setValueAt(getP.getFamilia(), i, 5);
            modelo.setValueAt(getP.getLongitud(), i, 6);
            modelo.setValueAt(getP.getQuantity(), i, 7);
            modelo.setValueAt(getP.getArea(), i, 8);
            modelo.setValueAt(getP.getBuild(), i, 9);
            modelo.setValueAt(getP.getTime(), i, 10);
            modelo.setValueAt(getP.getF12(), i, 11);
            modelo.setValueAt(getP.getF13(), i, 12);
        }
        
        //modelo.addRow(filaNueva);
        
        jTableProduct.setModel(modelo); 
    }
    
     public void CleanJTable(){
        DefaultTableModel tb = (DefaultTableModel) jTableProduct.getModel();
        int a = jTableProduct.getRowCount()-1;
        for (int i = a; i >= 0; i--) {           
        tb.removeRow(tb.getRowCount()-1);
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
        jPanel1 = new javax.swing.JPanel();
        jtxtSearchText = new javax.swing.JTextField();
        scrollbar1 = new java.awt.Scrollbar();
        textField1 = new java.awt.TextField();
        jtxtRecord = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnCancel = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProduct = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tristone.png"))); // NOI18N

        jtxtSearchText.setText("Enter text to search");
        jtxtSearchText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtSearchTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtSearchTextFocusLost(evt);
            }
        });
        jtxtSearchText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxtSearchTextMouseClicked(evt);
            }
        });

        textField1.setText("textField1");

        jtxtRecord.setEnabled(false);

        jLabel1.setText("Selected Records:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Extrusión", "Vulcanizado" }));

        jLabel2.setText("Extrusora:");

        jLabel3.setText("Quantity:");

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jTableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Part Number", "Mandrel", "Rack", "Description", "Ubicacion", "Familia", "Longitud", "Quantity", "Area", "Build", "Time", "F12", "F13"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProduct.setColumnSelectionAllowed(true);
        jTableProduct.getTableHeader().setReorderingAllowed(false);
        jTableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableProduct);
        jTableProduct.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTableProduct.getColumnModel().getColumnCount() > 0) {
            jTableProduct.getColumnModel().getColumn(0).setResizable(false);
            jTableProduct.getColumnModel().getColumn(1).setResizable(false);
            jTableProduct.getColumnModel().getColumn(2).setResizable(false);
            jTableProduct.getColumnModel().getColumn(3).setResizable(false);
            jTableProduct.getColumnModel().getColumn(4).setResizable(false);
            jTableProduct.getColumnModel().getColumn(5).setResizable(false);
            jTableProduct.getColumnModel().getColumn(6).setResizable(false);
            jTableProduct.getColumnModel().getColumn(7).setResizable(false);
            jTableProduct.getColumnModel().getColumn(8).setResizable(false);
            jTableProduct.getColumnModel().getColumn(9).setResizable(false);
            jTableProduct.getColumnModel().getColumn(10).setResizable(false);
            jTableProduct.getColumnModel().getColumn(11).setResizable(false);
            jTableProduct.getColumnModel().getColumn(12).setResizable(false);
        }

        jScrollPane1.setViewportView(jScrollPane2);

        jScrollPane3.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtRecord)
                .addGap(45, 45, 45))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnPrint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jtxtSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtxtSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel)
                    .addComponent(btnPrint))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtSearchTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtSearchTextMouseClicked
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_jtxtSearchTextMouseClicked

    private void jtxtSearchTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtSearchTextFocusLost
        // TODO add your handling code here:
        if(jtxtSearchText.getText().isEmpty())
            jtxtSearchText.setText("Enter text to search");
        
    }//GEN-LAST:event_jtxtSearchTextFocusLost

    private void jtxtSearchTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtSearchTextFocusGained
        // TODO add your handling code here:
        if(jtxtSearchText.getText().equals("Enter text to search"))
            jtxtSearchText.setText("");
    }//GEN-LAST:event_jtxtSearchTextFocusGained

    private void jTableProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProductMouseClicked
        // TODO add your handling code here:
        int row = jTableProduct.getSelectedRow();
        String record = (String)jTableProduct.getValueAt(0, row);
        jtxtRecord.setText(record);
        //JOptionPane.showConfirmDialog(null,"" + a);
    }//GEN-LAST:event_jTableProductMouseClicked

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        frmEmployee _frmEmployee=new frmEmployee();
        _frmEmployee.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        if(jtxtRecord.getText().equals("")){
            JOptionPane.showConfirmDialog(null, "Part Number is missing", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            return;
        }
            
    }//GEN-LAST:event_btnPrintActionPerformed

    private void Send() throws Exception {   
        String routingKey="";
        StationEntity _stationEntity = new StationEntity();
        StationModel objStationModel =new StationModel();
        boolean connMySQL=true;
        boolean connRabbitMQ=true;
        _stationEntity = objStationModel.getStationConf(_station);
        connMySQL=false;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(_stationEntity.getMq_server());
        //factory.setPort(5672);
        factory.setUsername(_stationEntity.getMq_user());
        factory.setPassword(_stationEntity.getMq_pass());
        //factory.setVirtualHost("/");
        
        try{
            connection = factory.newConnection();
            connRabbitMQ=false;
        
            channel = connection.createChannel();
        
            channel.exchangeDeclare(EXCHANGE_NAME,"direct",true);
          
            Gson gson = new Gson();

            
            String message = gson.toJson(GetDataSendEntity());
            
             routingKey = "s"+ _station;
           
            channel.basicPublish("", QUEUE_NAME,null, message.getBytes());
            //System.out.println(String.format("Sent «%s»", message));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

            //Thread.sleep(10000);
            
       
        }catch(Exception e){
                   
        }finally{
            channel.close();
            connection.close();
            Consumer(routingKey);            
        }

                    
        
    }
    
    private SendJsonEntity GetDataSendEntity(){
        SendJsonEntity _sendJsonEntity=new SendJsonEntity();     
       
        
        String dbPath="";
        String dbName="";
        String userDB="";
        String passDB="";
        String station="";
        String scanSleep="";
        String company="";
        String innerPass="";
        String _cancelCode = "";
        String COM="";
        
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
                    innerPass = sc.nextLine();
                    scanSleep = sc.nextLine();
                    company = sc.nextLine();
                    COM = sc.nextLine();
                    
                    _station = station;
                    _stationEntity = objStationModel.getStationConf(station);
                    _cancelCode = _stationEntity.getCancelacion();
                    
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now(); 
                    
                    _sendJsonEntity.setnSAP(_ProductsEntity.getNo_sap());
                    _sendJsonEntity.setPckd(_ProductsEntity.getPcks());
                    //_printEntity.setImpresora(_stationEntity.getImpre());
                    //_printEntity.setFecha(dtf.format(now));
                    //_printEntity.setnVali("");
                    //_printEntity.setPlat(_ProductsEntity.getPlat());
                    //_printEntity.setCust(_ProductsEntity.getCust());
                    //_printEntity.setnParte(_ProductsEntity.getNo_part());
                    _sendJsonEntity.setBartender(false);
                    _sendJsonEntity.setEmpresa(company);
                    _sendJsonEntity.setEstacion(station);
                    _sendJsonEntity.setImpresoraBartender(_stationEntity.getImpre());
                    //_printEntity.setnSerieserie(0);
                }
                
                
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            return _sendJsonEntity;    
        }
        
    }
    
    private void Consumer(String stations)throws Exception{
        //Thread.sleep(10000);
        StationEntity _stationEntity = new StationEntity();
        StationModel objStationModel =new StationModel();
        
       // ConnectionFactory factory = new ConnectionFactory();
        //factory.setHost("localhost");
        boolean connMySQL=true;
        boolean connRabbitMQ=true;
        _stationEntity = objStationModel.getStationConf(_station);
        connMySQL=false;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(_stationEntity.getMq_server());
       
        factory.setUsername(_stationEntity.getMq_user());
        factory.setPassword(_stationEntity.getMq_pass());
        try{
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true);
            //channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String queueName = channel.queueDeclare().getQueue();

            String bindingKey = stations;
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);

    //channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    //        channel.basicQos(1);
            
            deliverCallback = (consumerTag, delivery) -> {
                
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
                System.out.println(" [x] Done");
               ConsumerJSONEntity p = new Gson().fromJson(message,ConsumerJSONEntity.class);
               ValidacionEntity v = new Gson().fromJson(p.getDato(),ValidacionEntity.class);
               
                WorkQueue(v.getNoValidacion(),employee);
                
           
                                               };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { 
                
            });
        }catch(Exception e){
                         
        }finally{
           
        }
        
        
    }
    
    private PrintEntity GetDataEntity(){
        PrintEntity _printEntity=new PrintEntity();     
       
        
        String dbPath="";
        String dbName="";
        String userDB="";
        String passDB="";
        String station="";
        String scanSleep="";
        String innerPass="";
        String company="";
        String _cancelCode = "";
        String COM="";
        
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
                    innerPass = sc.nextLine();
                    scanSleep = sc.nextLine();
                    company = sc.nextLine();
                    COM = sc.nextLine();
                    
                    _station = station;
                    _stationEntity = objStationModel.getStationConf(station);
                    _cancelCode = _stationEntity.getCancelacion();
                    
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();             
                    
                    
                    _printEntity.setnSAP(_ProductsEntity.getNo_sap());
                    _printEntity.setPckd(Integer.parseInt(_ProductsEntity.getPcks()));
                    //_printEntity.setImpresora(_stationEntity.getImpre());
                    _printEntity.setFecha(dtf.format(now));
                    //_printEntity.setnVali("");
                    _printEntity.setPlat(_ProductsEntity.getPlat());
                    //En espera de que me asignen los horarios para el turno
                    _printEntity.setTurno(getShedule());
                    _printEntity.setCust(_ProductsEntity.getCust());
                    //_printEntity.setnParte(_ProductsEntity.getNo_part());
                    _printEntity.setBartender(false);
                    _printEntity.setEmpresa(company);
                    _printEntity.setEstacion(station);
                    _printEntity.setEmp_num(employee);
                    _printEntity.setImpresoraBartender(_stationEntity.getImpre());
                    //_printEntity.setnSerieserie(0);
                }
               
                
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            return _printEntity;    
        }
        
    }
     
    private String getShedule(){
        String workShedule = "";
        try {
            
            SimpleDateFormat df = new SimpleDateFormat("hh:mm");
            Date now = new Date();
            
            Date _workTime = df.parse(df.format(now));
            
            //shedule 1
            Date d1 = df.parse("06:00");
            Date d2 = df.parse("15:30");
            if(_workTime.compareTo(d1) > 0 && _workTime.compareTo(d2) < 0){
                workShedule = "T1";
            } else{            
                //shedule 2
                Date d3 = df.parse("15:31");
                Date d4 = df.parse("00:24");
                if(_workTime.compareTo(d3) > 0 && _workTime.compareTo(d4) < 0){
                    workShedule = "T2";
                } else {
                    //shedule 3
                    Date d5 = df.parse("00:25");
                    Date d6 = df.parse("05:59");
                    if(_workTime.compareTo(d5) > 0 && _workTime.compareTo(d6) < 0){
                        workShedule = "T3";
                    }
                }
            }
            
            
        } catch (ParseException ex) {
            Logger.getLogger(frmStandarPAck.class.getName()).log(Level.SEVERE, null, ex);
        }
        return workShedule;
    }
      
    private void WorkQueue(String serialNumber, String _noEmployee){

       
            frmSerialNumber _frmSerialNumber = new frmSerialNumber();

            _frmSerialNumber.SetSerialNumber(GetDataEntity());
            _frmSerialNumber.SetEmployee(_noEmployee);
            _frmSerialNumber.SetSerialNumber(Integer.parseInt(serialNumber));
            _frmSerialNumber.setVisible(true);
             try {
                    channel.close();
                    connection.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(frmStandarPAck.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TimeoutException ex) {
                    Logger.getLogger(frmStandarPAck.class.getName()).log(Level.SEVERE, null, ex);
                }
             
            this.dispose();
       
    } 
    
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
            java.util.logging.Logger.getLogger(frmExtrusora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmExtrusora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmExtrusora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmExtrusora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmExtrusora().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTableProduct;
    private javax.swing.JTextField jtxtRecord;
    private javax.swing.JTextField jtxtSearchText;
    private java.awt.Scrollbar scrollbar1;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
