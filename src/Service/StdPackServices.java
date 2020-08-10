/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.PrintEntity;
import Entities.ProductsEntity;
import Entities.StationEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ulises
 */
public class StdPackServices {
    ServicesMySQL connection;
    Connection con = null;
    
    public boolean ExistStation(String noStation){
        boolean exist=false;
        int count=0;
        try{
            PreparedStatement query;
            connection =new ServicesMySQL();  
            connection.Initialize();
            
            query=connection.getConnection().prepareStatement("SELECT Count(*) FROM station_conf WHERE no_estacion=?");
            
            query.setString(1, noStation);
            
            ResultSet rs = query.executeQuery();
            if(rs.next())
            {
                count=rs.getInt(1);
                if(count>0)
                    exist=true;
            }
            return exist;
        }catch(Exception ex){
         ex.printStackTrace();
         return exist;
        }finally{
            //today
             connection.CloseConection();
            
        }
        
        
        
    }
    
    public StationEntity getStationConf(String noStation) {
        StationEntity _stationEntity = new StationEntity();
        int count=0;
        try{
            PreparedStatement query;
            connection =new ServicesMySQL();            
            connection.Initialize();
            query=connection.getConnection().prepareStatement("SELECT * FROM station_conf WHERE no_estacion=?");
            query.setString(1, noStation);
            
            ResultSet rs = query.executeQuery();
            if(rs.next())
            {
                _stationEntity.setId(rs.getInt("id")); 
                _stationEntity.setMq_server(rs.getString("mq_server")); 
                _stationEntity.setMq_user(rs.getString("mq_user")); 
                _stationEntity.setMq_pass(rs.getString("mq_pass")); 
                _stationEntity.setNo_estacion(rs.getString("no_estacion")); 
                _stationEntity.setImpre(rs.getString("impre")); 
                _stationEntity.setCancelacion(rs.getString("cancelacion"));     
                _stationEntity.setConfimar(rs.getString("confirmar"));     
                _stationEntity.setEnableStation(rs.getBoolean("enable")); 
                _stationEntity.setMysql_db(rs.getString("mysql_db")); 
            }
            return _stationEntity;
        }catch(Exception ex){
         ex.printStackTrace();
         return _stationEntity;
        }finally{
             connection.CloseConection();
        }
        
    }
    
    public boolean ValidateBartender(String table,String noSAP,String dbName){
        boolean result=false;
        
        int count=0;
        try{
            PreparedStatement query;
            connection =new ServicesMySQL();            
            connection.Initialize();
            
            query=connection.getConnection(dbName).prepareStatement("SELECT Count(*) FROM "+ table +" WHERE no_sap=?");
            query.setString(1, noSAP);
            
            ResultSet rs = query.executeQuery();
            if(rs.next())
            {
                count=rs.getInt(1);
                if(count>0)
                    result=true;
            }
            return result;
        }catch(Exception ex){
         ex.printStackTrace();
         return result;
        }
        finally{
            //connection.Initialize();
             connection.CloseConection();
            
        }
        //return result;
    }
    
     public ProductsEntity GetProduct(String table,String noSAP,String dbName){
        ProductsEntity _productsEntity=new ProductsEntity();
        
        int count=0;
        try{
            PreparedStatement query;
            connection =new ServicesMySQL();            
            connection.Initialize();
            
            query=connection.getConnection(dbName).prepareStatement("SELECT * FROM "+ table +" WHERE no_sap=?");
            query.setString(1, noSAP);
            
            ResultSet rs = query.executeQuery();
            if(rs.next())
            {
                
                _productsEntity.setNo_sap(rs.getString("no_sap")); 
                _productsEntity.setPcks(rs.getString("std_pack")); 
                _productsEntity.setNo_part(rs.getString("cust_part")); 
                //_productsEntity.setDecInt(rs.getBoolean("decInt"));
                _productsEntity.setPlat(rs.getString("platform")); 
            }
            return _productsEntity;
        }catch(Exception ex){
         ex.printStackTrace();
         return _productsEntity;
        }
        finally{
            //connection.Initialize();
             connection.CloseConection();
        }
        //return result;
    }
    
    
    public ArrayList<Object> GetTableName(String dbName){
        ArrayList<Object> listTable=new ArrayList<Object>();
        PreparedStatement query;
        connection =new ServicesMySQL();  
        connection.Initialize();
         try{
            query=connection.getConnection().prepareStatement("SELECT table_name FROM information_schema.tables WHERE table_schema=?");
            query.setString(1, dbName);

            ResultSetMetaData rsmd = null;
            ResultSet rs = query.executeQuery();
            
            

            while(rs.next()){
                listTable.add(rs.getString("table_name"));

            }  
        }catch(Exception ex){
            ex.printStackTrace();
            return listTable;
        }finally{
              connection.CloseConection();
         }
         
         
        return listTable;
    }
    
    
    
    public boolean InsertProcess(PrintEntity _printEntity){
        boolean result=false;
        int i = 0;
         PreparedStatement query;
        connection =new ServicesMySQL();    
        connection.Initialize();
         try{
            query=connection.getConnection().prepareStatement("INSERT INTO etiquetas (np,turno,linea,dmc,no_serie,plataforma, fecha,emp_num) values (?,?,?,?,?,?,?,?)");
            query.setString(1, _printEntity.getnSAP());
            query.setString(2, _printEntity.getTurno());
            query.setString(3, _printEntity.getEstacion());
            query.setString(4, "");
            query.setInt(5, _printEntity.getnSerieserie());
            query.setString(6, _printEntity.getPlat());
            query.setString(7, _printEntity.getFecha());
            query.setString(8, _printEntity.getEmp_num());
            

           
            i = query.executeUpdate();
            if(i > 0)
                result = true;
                        
         }catch(Exception ex){
            ex.printStackTrace();
            return result;
        }finally{
              connection.CloseConection();
         }
        return result;
    }
}
