/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.ProductsEntity;
import Entities.StationEntity;
import Model.StationModel;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author Ulises
 */
public class ProductService {
    ServicesMySQL connection;
    
    Connection con = null;
    
    public boolean NoSAP(String noSAP) 
    {
        boolean exist=false;
        int count=0;
        try{
            PreparedStatement query;
            connection =new ServicesMySQL(); 
            connection.Initialize();
            
            query=connection.getConnection().prepareStatement("SELECT Count(*) FROM b10sap WHERE no_sap=?");
            query.setString(1, noSAP);
            
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
            connection.CloseConection();
        }
        
    }  
            
    
    public ProductsEntity GetNoSAP(String noSAP) 
    {
        ProductsEntity _ProductsEntity = new ProductsEntity();
        int count=0;
        try{
            PreparedStatement query;
            connection =new ServicesMySQL();   
            connection.Initialize();
            
            query=connection.getConnection().prepareStatement("SELECT * FROM b10sap WHERE no_sap=?");
            query.setString(1, noSAP);
            
            ResultSet rs = query.executeQuery();
            if(rs.next())
            {
                _ProductsEntity.setId(rs.getString("id")); 
                _ProductsEntity.setNo_sap(rs.getString("no_sap")); 
                _ProductsEntity.setPcks(rs.getString("pckd")); 
                _ProductsEntity.setNo_part(rs.getString("no_part")); 
                _ProductsEntity.setCust(rs.getString("cust")); 
                _ProductsEntity.setPlat(rs.getString("plat")); 
            }
        
            return _ProductsEntity;
        }catch(Exception ex){
         ex.printStackTrace();
         return _ProductsEntity;
        }finally{
            connection.CloseConection();
        }
        
    }
}
