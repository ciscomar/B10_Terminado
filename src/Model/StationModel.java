/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entities.PrintEntity;
import Entities.ProductsEntity;
import Entities.StationEntity;
import Service.StdPackServices;
import java.util.ArrayList;

/**
 *
 * @author Ulises
 */
public class StationModel {
    public boolean SttionExist(String noStation){
        StdPackServices obj = new StdPackServices();
        return obj.ExistStation(noStation);
    }
    
    public StationEntity getStationConf(String noStation){
        StdPackServices obj = new StdPackServices();
        return obj.getStationConf(noStation);
    }
    
    public boolean ValidateBartender(String table,String noSAP,String dbName){
         StdPackServices obj = new StdPackServices();
        return obj.ValidateBartender(table,noSAP, dbName);
    }
    
    public ArrayList<Object> GetTableName(String dbName){
        StdPackServices obj = new StdPackServices();
        return obj.GetTableName(dbName);
    } 
    
    public ProductsEntity GetDataProduct(String table,String noSAP,String dbName){
        StdPackServices obj = new StdPackServices();
        return obj.GetProduct(table,noSAP,dbName);
        
    }
    public boolean InsertProcess(PrintEntity _printEntity){
        StdPackServices obj = new StdPackServices();
        return obj.InsertProcess(_printEntity);
    }
   
}
