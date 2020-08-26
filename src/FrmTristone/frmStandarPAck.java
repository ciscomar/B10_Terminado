/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FrmTristone;

import Entities.ConsumerJSONEntity;
import Entities.EmployeeEntity;
import Entities.PrintEntity;
import Entities.ProductsEntity;
import Entities.SendJsonEntity;
import Entities.StationEntity;
import Entities.ValidacionEntity;
import Model.EmployeesModel;
import Model.ProductModel;
import Model.StationModel;
import com.google.gson.Gson;
import com.rabbitmq.client.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;


/**
 *
 * @author Ulises
 */
public class frmStandarPAck extends javax.swing.JFrame {

    /**
     * Creates new form frmStandarPAck
     */
    Channel channel;
    ProductsEntity _ProductsEntity;
    
    String empaqueg="";
    int contStdPack = 0;
    String cancelCode="";
    String _noEmployee = "";
    String _station="";
    String _scanSleep="";
    private static final String QUEUE_NAME = "workQueues";
    private static String employee = "";
    private static int i=0;
    private static final String EXCHANGE_NAME = "stations";
    //private static final String EXCHANGE_NAME = "station";
    //private static final String EXCHANGE_NAME = "workExchange";
    //private static final String EXCHANGE_NAME = "etiquetasB10";
    private Connection connection;
    //private Channel channel;
    DeliverCallback deliverCallback = null;
    
    
    
    public frmStandarPAck()  {
            this.setIconImage(new ImageIcon(getClass().getResource("/img/icon_tristone.png")).getImage());
            initComponents();
            jProgressBar1.setVisible(false);
            jProgressBar1.setIndeterminate(true);
            getContentPane().setBackground(new Color(51,51,51));
            jtxtMessage.setEditable(false);
            jtxtSAPNumb.setEditable(false);
            jtxtStdarPack.setEditable(false);
            jtxtPartNumCopy.setEditable(false);
            jButton2.setVisible(false);
            jButton1.setVisible(false);
            btnPrint.setVisible(false);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            //jlblPartNumb.setVisible(false);
            //jtxtPartNum.setVisible(false);
            //jlblSdarPack.setVisible(false);
            //jtxtStdarPack.setVisible(false);
            jLabel6.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tristone_White.png")).getImage().getScaledInstance(260, 150, Image.SCALE_SMOOTH)));
    }
    
    public void initialize(){
        channel = null;
        _ProductsEntity = null;
        contStdPack = 0;
        cancelCode="";
        _noEmployee = "";
        _station="";
        _scanSleep="";
        i=0;
        connection = null;
        deliverCallback=null;
    }

    public void SetEmployee(String noEmployee){
        EmployeesModel objEmployee=new EmployeesModel();
        EmployeeEntity _employeeEntity=new EmployeeEntity();
        _employeeEntity = objEmployee.getEmployee(noEmployee);
                
        jlblEmployeeName.setText(_employeeEntity.getNameEmployee());
        jlblEmployeeNumb.setText(_employeeEntity.getNumEmployee());
        _noEmployee = noEmployee;
        employee=_employeeEntity.getIdEmployee();
        
        
    }
    
    public void SetNoSAP(String NoSAP){
         ProductModel objProductModel =new ProductModel();
         _ProductsEntity = new ProductsEntity();
         _ProductsEntity = objProductModel.getProduct(NoSAP);
        
        jtxtSAPNumb.setText(_ProductsEntity.getNo_sap());
        jtxtPartNumCopy.setText(_ProductsEntity.getNo_part());
        jtxtStdarPack.setText("0/"+_ProductsEntity.getPcks());
        jtxtPartNum.requestFocusInWindow();
        cancelCode = CancelCode();
    }
    
    //TODAY
    public void SetNoSAP(ProductsEntity _productsEntity, String empaque){
         _ProductsEntity=new ProductsEntity();
        _ProductsEntity.setNo_part(_productsEntity.getNo_part());
        _ProductsEntity.setNo_sap(_productsEntity.getNo_sap());
        _ProductsEntity.setPcks(_productsEntity.getPcks());
        _ProductsEntity.setPlat(_productsEntity.getPlat());
        
        jtxtSAPNumb.setText(_productsEntity.getNo_sap());
        jtxtPartNumCopy.setText(_productsEntity.getNo_part());
        jtxtStdarPack.setText("0/"+_productsEntity.getPcks());
        jtxtPartNum.requestFocusInWindow();
        cancelCode = CancelCode();
        
        empaqueg=empaque;
        if (empaque=="estandar"){
            jlblEmpaque.setText("Empaque: Estandar");
            jlblmagen.setIcon(new ImageIcon(getClass().getResource("/img/plastic2.png")));
            
        }else{
            jlblEmpaque.setText("Empaque: Alternativo");
            jlblmagen.setIcon(new ImageIcon(getClass().getResource("/img/carton2.png")));
            
            
        }
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
                    _scanSleep = scanSleep;
                    _station=station;
                    _stationEntity = objStationModel.getStationConf(station);
                    _cancelCode = _stationEntity.getCancelacion();
                }
                else{
                    jtxtMessage.setText("The File doesn't exist"); 
                    jtxtMessage.setBackground(Color.decode("#FF1744")); 
                    jtxtMessage.setEditable(false);
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            return _cancelCode;
        }
    }
    
    private void ScanNoPart(String NoPart){
        
        String part=_ProductsEntity.getNo_part();
        
        
        try{
            if(NoPart.equals(cancelCode)){
                frmEmployee _frmEmployee=new frmEmployee();
               
                _frmEmployee.setVisible(true);
                //this.setVisible(false);
                this.dispose();
            }
            else if(NoPart.equals(part)){
                contStdPack++;
                jtxtStdarPack.setText(contStdPack+"/"+_ProductsEntity.getPcks());
                if(contStdPack > Integer.parseInt(_ProductsEntity.getPcks())){
                    jtxtMessage.setText("Can not be greater than the standard pack");
                    jtxtMessage.setBackground(Color.decode("#FF1744"));                 
                    jtxtMessage.setEditable(false);
                    jtxtPartNum.setEditable(true);
                    //ReseteBox();
                }
                if(contStdPack == Integer.parseInt(_ProductsEntity.getPcks())){
                    //JOptionPane.showConfirmDialog(null,"Excelente");
                    //jtxtPartNum.setEnabled(false);
                    jtxtPartNum.setVisible(false);
                    jProgressBar1.setVisible(true);
                    jtxtPartNum.setText("");
                    
                    SetSuccess();
                    //ReseteBox();
                    Send();
                    
                    //frmScanProduct _frmScanProduct=new frmScanProduct();
                    //_frmScanProduct.getEmployee(_noEmployee);
                    //_frmScanProduct.setVisible(true);
                    //frmSerialNumber _frmEmployee = new frmEmployee();
                    //_frmEmployee.setVisible(true);
                    //this.setVisible(false);
                }else{
                   SetSuccess();
                  
                    Thread.sleep(Integer.parseInt(_scanSleep));
                    
                    jtxtPartNum.requestFocus();
                }
            }else{
                 jtxtMessage.setText("Invalid Part Number");
                 jtxtMessage.setBackground(Color.decode("#FF1744"));                 
                 jtxtMessage.setEditable(false);
            }
        }
       catch (Exception e) {
            e.printStackTrace();
            jtxtPartNum.setEditable(true);
        }
        finally{
             ReseteBox();
            //SetReset();
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
    
    private void SetSuccess(){
         jtxtMessage.setText("Success");
         jtxtMessage.setBackground(Color.decode("#00C853"));                
         jtxtMessage.setEditable(false);
    }
    
    private void SetReset(){
        jtxtMessage.setBackground(Color.white); 
        jtxtMessage.setText("");                    
        jtxtPartNum.setText("");
    }
    
    private void SendTTL() throws Exception {   
        StationEntity _stationEntity = new StationEntity();
        StationModel objStationModel =new StationModel();
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
            connRabbitMQ=false;
        
            channel = connection.createChannel();
        
            //channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.TOPIC);
            channel.exchangeDeclare(EXCHANGE_NAME,"direct",true);
            
            Gson gson = new Gson();

            String message = gson.toJson(GetDataSendEntity());
            //String message = gson.toJson(GetDataEntity());
            String routingKey = "s"+ _station;
            
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                                   .expiration("80000")
                                   .build();
            
            channel.basicPublish(EXCHANGE_NAME, routingKey , properties, message.getBytes());
            
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

           
            Consumer(routingKey);
            
       
        }catch(Exception e){
            e.printStackTrace();
            if(connRabbitMQ){
                jtxtMessage.setText("RabbitMQ "+ e.getMessage());
                jtxtMessage.setBackground(Color.decode("#FF1744"));                 
                jtxtMessage.setEditable(false);
            } else if(connMySQL){
                jtxtMessage.setText("MySQL "+ e.getMessage());
                jtxtMessage.setBackground(Color.decode("#FF1744"));                 
                jtxtMessage.setEditable(false);
            }                
        }
                    
        
    }
    
    //TODAY
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
            //channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.TOPIC);
            //channel.queueDeclare(QUEUE_NAME, true, false, false, null);//(QUEUE_NAME, false, false, false, null);
            //channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true);
            Gson gson = new Gson();

            //String message = gson.toJson(GetDataEntity());
            String message = gson.toJson(GetDataSendEntity());
            
             routingKey = "s"+ _station;
            //for (int i = 0; i < 10; ++i) {
            //String message = String.format("Hello World at %s", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            //channel.basicPublish(EXCHANGE_NAME, "" , null, message.getBytes());
            //channel.basicPublish(EXCHANGE_NAME, routingKey , null, message.getBytes());
            channel.basicPublish("", QUEUE_NAME,null, message.getBytes());
            //System.out.println(String.format("Sent «%s»", message));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

            //Thread.sleep(10000);
            
       
        }catch(Exception e){
            if(connRabbitMQ){
                jtxtMessage.setText("RabbitMQ "+ e.getMessage());
                jtxtMessage.setBackground(Color.decode("#FF1744"));                
                jtxtMessage.setEditable(false);
            } else if(connMySQL){
                jtxtMessage.setText("MySQL "+ e.getMessage());
                jtxtMessage.setBackground(Color.decode("#FF1744"));                 
                jtxtMessage.setEditable(false);
            }                
        }finally{
            channel.close();
            connection.close();
            Consumer(routingKey);            
        }
//        finally{
//            try {
//                    connection.close();
//                    channel.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(frmStandarPAck.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (TimeoutException ex) {
//                    Logger.getLogger(frmStandarPAck.class.getName()).log(Level.SEVERE, null, ex);
//                }
//        }
                    
        
    }

    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }
    
    public static void Consumer() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.queuePurge(QUEUE_NAME);

            channel.basicQos(1);

            System.out.println(" [x] Awaiting RPC requests");

            Object monitor = new Object();
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();

                String response = "";

                try {
                    String message = new String(delivery.getBody(), "UTF-8");
                    int n = Integer.parseInt(message);

                    System.out.println(" [.] fib(" + message + ")");
                    response += fib(n);
                } catch (RuntimeException e) {
                    System.out.println(" [.] " + e.toString());
                } finally {
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    // RabbitMq consumer worker thread notifies the RPC server owner thread
                    synchronized (monitor) {
                        monitor.notify();
                    }
                }
            };

            channel.basicConsume(QUEUE_NAME, false, deliverCallback, (consumerTag -> { }));
            // Wait and be prepared to consume the message from RPC client.
            while (true) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
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
                
                //WorkQueue("123",employee);
               
               
                

                //System.out.println(" [x] Received '" + message + "'");
    //            try {
    //                doWork(message);
    //            } finally {
    //                System.out.println(" [x] Done");
    //                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
    //            }
                                               };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { 
                
            });
        }catch(Exception e){
            if(connRabbitMQ){
                jtxtMessage.setText("RabbitMQ "+ e.getMessage());
                jtxtMessage.setBackground(Color.decode("#FF1744"));                
                jtxtMessage.setEditable(false);
            } else if(connMySQL){
                jtxtMessage.setText("MySQL "+ e.getMessage());
                jtxtMessage.setBackground(Color.decode("#FF1744"));                
                jtxtMessage.setEditable(false);
            }                
        }finally{
           
        }
        
        
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
    
    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
   
    private boolean GetBartender(String dbName,String noSAP){
        boolean result = false;
        StationModel objStationModel =new StationModel();
        ArrayList<Object> listTable=new ArrayList<Object>();
        listTable = objStationModel.GetTableName(dbName);
        
        if(listTable.size() > 0){
            for(Object o : listTable){
                result= objStationModel.ValidateBartender(o.toString(), noSAP,dbName);
                if(result == true)
                    break;
            }
        }
        
        return result;
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
                    innerPass = sc.nextLine();
                    scanSleep = sc.nextLine();
                    
                    
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
                    _sendJsonEntity.setBartender(GetBartender(_stationEntity.getMysql_db() ,_sendJsonEntity.getnSAP()));
                    _sendJsonEntity.setEmpresa(company);
                    _sendJsonEntity.setEstacion(station);
                    _sendJsonEntity.setImpresoraBartender(_stationEntity.getImpre());
                    _sendJsonEntity.setEmpaque(empaqueg);
                    //_printEntity.setnSerieserie(0);
                }
                else{
                    jtxtMessage.setText("The File doesn't exist"); 
                    jtxtMessage.setBackground(Color.decode("#FF1744")); 
                    jtxtMessage.setEditable(false);
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
                    innerPass = sc.nextLine();
                    scanSleep = sc.nextLine();
                    
                    
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
                    _printEntity.setBartender(GetBartender(_stationEntity.getMysql_db() ,_printEntity.getnSAP()));
                    _printEntity.setEmpresa(company);
                    _printEntity.setEstacion(station);
                    _printEntity.setEmp_num(jlblEmployeeNumb.getText());
                    _printEntity.setImpresoraBartender(_stationEntity.getImpre());
                    //_printEntity.setnSerieserie(0);
                }
                else{
                    jtxtMessage.setText("The File doesn't exist"); 
                    jtxtMessage.setBackground(Color.decode("#FF1744")); 
                    jtxtMessage.setEditable(false);
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
    
    private void Receiver()throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        Connection connection = factory.newConnection();
        if(channel == null)
            channel = connection.createChannel();
        //Channel channel = connection.createChannel();
       //String routingKey = "s"+_station;
       String routingKey = "s400";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true);
        //channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT,true);
        String queueName = channel.queueDeclare().getQueue();

        

        channel.queueBind(queueName, EXCHANGE_NAME, "");
        //channel.basicQos(1);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        
        PrintEntity queue = new PrintEntity();
        boolean autoAck = false;
        queue.setBartender(true);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message);
             //   delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            //String message = new String(delivery.getBody());
            
            //System.out.println(" " + routingKey);
            //System.out.println(" " + delivery.getEnvelope().getRoutingKey());
            //System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey());
              //  delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
           PrintEntity p = new Gson().fromJson(message,PrintEntity.class);
           //System.out.println(" " +p.getEstacion());
           if(routingKey.equals("s" + p.getEstacion())){
                queue.setEstacion(p.getEstacion());
                System.out.println(" " + queue.getEstacion());
               queue.setBartender(true);
               
            }else
                queue.setBartender(false);
                    
           
           
        };
        
        channel.basicConsume(QUEUE_NAME, queue.getBartender(), deliverCallback, consumerTag -> {
           
        });
       
             //  delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
    }
    
//    private void Receiver()throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        factory.setVirtualHost("/");
//        connection = factory.newConnection();
//        channel = connection.createChannel();
//        channel.queueDeclare(QUEUE_NAME, true, false, false, null);//(QUEUE_NAME, false, false, false, null);
//        channel.queueBind(QUEUE_NAME,"stations" ,"direct");
//
//            Consumer consumer = new DefaultConsumer(channel) {
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    String message = new String(body, "UTF-8");
//                    System.out.println(String.format("Received  «%s»", message));
//                }
//            };
//            
//            channel.basicConsume("", true, consumer);
//            //channel.basicPublish("station",QUEUE_NAME, true, consumer);
//
//            Thread.sleep(5000);
//             channel.close();
//            connection.close();
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jtxtPartNum = new javax.swing.JTextField();
        jtxtSAPNumb = new javax.swing.JTextField();
        jtxtStdarPack = new javax.swing.JTextField();
        lblSAPNum = new javax.swing.JLabel();
        jlblPartNumb = new javax.swing.JLabel();
        jlblSdarPack = new javax.swing.JLabel();
        jlblEmployeeName = new javax.swing.JLabel();
        jlblEmployeeNumb = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtMessage = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jtxtPartNumCopy = new javax.swing.JTextField();
        jlblPartNumb1 = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        jlblEmpaque = new javax.swing.JLabel();
        jlblmagen = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tristone.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 12, 350, 212));

        jtxtPartNum.setBackground(new java.awt.Color(51, 51, 51));
        jtxtPartNum.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtPartNum.setForeground(new java.awt.Color(255, 255, 255));
        jtxtPartNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtPartNum.setBorder(null);
        jtxtPartNum.setOpaque(false);
        jtxtPartNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtPartNumActionPerformed(evt);
            }
        });
        jtxtPartNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtPartNumKeyPressed(evt);
            }
        });
        getContentPane().add(jtxtPartNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 260, -1));

        jtxtSAPNumb.setBackground(new java.awt.Color(51, 51, 51));
        jtxtSAPNumb.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtSAPNumb.setForeground(new java.awt.Color(255, 255, 255));
        jtxtSAPNumb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtSAPNumb.setBorder(null);
        jtxtSAPNumb.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jtxtSAPNumb.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        jtxtSAPNumb.setEnabled(false);
        jtxtSAPNumb.setOpaque(false);
        jtxtSAPNumb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtSAPNumbActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtSAPNumb, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 260, 30));

        jtxtStdarPack.setBackground(new java.awt.Color(51, 51, 51));
        jtxtStdarPack.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtStdarPack.setForeground(new java.awt.Color(255, 255, 255));
        jtxtStdarPack.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtStdarPack.setBorder(null);
        jtxtStdarPack.setOpaque(false);
        getContentPane().add(jtxtStdarPack, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 260, -1));

        lblSAPNum.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lblSAPNum.setForeground(new java.awt.Color(255, 255, 255));
        lblSAPNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSAPNum.setText("SAP Number");
        getContentPane().add(lblSAPNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 20, 266, 24));

        jlblPartNumb.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jlblPartNumb.setForeground(new java.awt.Color(255, 255, 255));
        jlblPartNumb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblPartNumb.setText("Scan Part Number");
        getContentPane().add(jlblPartNumb, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 152, 266, -1));

        jlblSdarPack.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jlblSdarPack.setForeground(new java.awt.Color(255, 255, 255));
        jlblSdarPack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblSdarPack.setText("Standard Pack");
        getContentPane().add(jlblSdarPack, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, 266, -1));

        jlblEmployeeName.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jlblEmployeeName.setForeground(new java.awt.Color(255, 255, 255));
        jlblEmployeeName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlblEmployeeName.setText("Employee Name:");
        jlblEmployeeName.setName("jlblEmployee"); // NOI18N
        getContentPane().add(jlblEmployeeName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 470, -1));

        jlblEmployeeNumb.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jlblEmployeeNumb.setForeground(new java.awt.Color(255, 255, 255));
        jlblEmployeeNumb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlblEmployeeNumb.setText("Employee Number:");
        jlblEmployeeNumb.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jlblEmployeeNumb.setName("jlblEmployee"); // NOI18N
        getContentPane().add(jlblEmployeeNumb, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 470, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Employee Name:");
        jLabel1.setName(""); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 170, 19));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Employee ID:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 171, -1));

        jtxtMessage.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtMessage.setForeground(new java.awt.Color(255, 255, 255));
        jtxtMessage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(jtxtMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 800, 31));

        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 51, 102));
        jButton1.setText("Consumer");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 100, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 51, 102));
        jButton2.setText("Send");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 100, -1));

        jtxtPartNumCopy.setBackground(new java.awt.Color(51, 51, 51));
        jtxtPartNumCopy.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jtxtPartNumCopy.setForeground(new java.awt.Color(255, 255, 255));
        jtxtPartNumCopy.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtPartNumCopy.setBorder(null);
        jtxtPartNumCopy.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jtxtPartNumCopy.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        jtxtPartNumCopy.setEnabled(false);
        jtxtPartNumCopy.setOpaque(false);
        jtxtPartNumCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtPartNumCopyActionPerformed(evt);
            }
        });
        jtxtPartNumCopy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtPartNumCopyKeyPressed(evt);
            }
        });
        getContentPane().add(jtxtPartNumCopy, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 260, 30));

        jlblPartNumb1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jlblPartNumb1.setForeground(new java.awt.Color(255, 255, 255));
        jlblPartNumb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblPartNumb1.setText("Part Number");
        getContentPane().add(jlblPartNumb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 86, 182, 24));

        btnPrint.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 51, 102));
        btnPrint.setText("Print");
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrintMouseClicked(evt);
            }
        });
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        btnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPrintKeyPressed(evt);
            }
        });
        getContentPane().add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 100, -1));
        btnPrint.getAccessibleContext().setAccessibleName("btnPrint");

        jlblEmpaque.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jlblEmpaque.setForeground(new java.awt.Color(255, 255, 255));
        jlblEmpaque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblEmpaque.setText("Empaque");
        getContentPane().add(jlblEmpaque, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 276, -1));
        getContentPane().add(jlblmagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(611, 317, 82, 50));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 470, 10));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 470, 10));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 260, 20));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 260, 20));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 260, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_barcode_reader.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, -1, -1));

        jProgressBar1.setMaximum(10);
        jProgressBar1.setToolTipText("");
        jProgressBar1.setValue(10);
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 200, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtPartNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtPartNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtPartNumActionPerformed

    private void jtxtPartNumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtPartNumKeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER )
        {
            ScanNoPart(jtxtPartNum.getText()); 
            jtxtPartNum.setText("");
        }
    }//GEN-LAST:event_jtxtPartNumKeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
       
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {        
            // TODO add your handling code here:

            // TODO add your handling code here:
            //send();
            Send();
            //GetDataEntity();
        } catch (Exception ex) {
            Logger.getLogger(frmStandarPAck.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            //Receiver();
            Consumer("s400");
        } catch (Exception ex) {
           
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtxtPartNumCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtPartNumCopyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtPartNumCopyActionPerformed

    private void jtxtPartNumCopyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtPartNumCopyKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtPartNumCopyKeyPressed

    private void btnPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintMouseClicked

    private void btnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPrintKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintKeyPressed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        
        try {
            //ReseteBox();
            //SetSuccess();
            btnPrint.setEnabled(false);
            Send();
        } catch (Exception ex) {
            Logger.getLogger(frmStandarPAck.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void jtxtSAPNumbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtSAPNumbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtSAPNumbActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmStandarPAck.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmStandarPAck().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel jlblEmpaque;
    private javax.swing.JLabel jlblEmployeeName;
    private javax.swing.JLabel jlblEmployeeNumb;
    private javax.swing.JLabel jlblPartNumb;
    private javax.swing.JLabel jlblPartNumb1;
    private javax.swing.JLabel jlblSdarPack;
    private javax.swing.JLabel jlblmagen;
    private javax.swing.JTextField jtxtMessage;
    private javax.swing.JTextField jtxtPartNum;
    private javax.swing.JTextField jtxtPartNumCopy;
    private javax.swing.JTextField jtxtSAPNumb;
    private javax.swing.JTextField jtxtStdarPack;
    private javax.swing.JLabel lblSAPNum;
    // End of variables declaration//GEN-END:variables
}
