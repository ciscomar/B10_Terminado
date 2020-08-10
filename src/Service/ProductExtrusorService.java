/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.ProductExtrusorEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ulises
 */
public class ProductExtrusorService {
    
    ServicesMySQL connection;
    Connection con = null;
    
     public ArrayList GetExtrusionVulcanizado() 
    {
        ArrayList P = new ArrayList();
       ProductExtrusorEntity _ProductExtrusorEntity = new ProductExtrusorEntity();
        int count=0;
        try{
            PreparedStatement query;
            connection =new ServicesMySQL();            
            
            query=connection.getConnection().prepareStatement("SELECT * FROM partesextrusionvulcanizado ");
            
            
            ResultSet rs = query.executeQuery();
            
            P = ResultToArray(rs);
           
            return P;
            
        }catch(Exception ex){
         ex.printStackTrace();
         return P;
        }
        
        
        
    }
     
      public ArrayList GetExtrusionVulcanizadoByPartNumber(String partNumber){
        ArrayList P = new ArrayList();
       ProductExtrusorEntity _ProductExtrusorEntity = new ProductExtrusorEntity();
        int count=0;
        try{
            PreparedStatement query;
            connection =new ServicesMySQL();            
            
            query=connection.getConnection().prepareStatement("SELECT * FROM partesextrusionvulcanizado where part_number like ?");
            query.setString(1, "%"+partNumber+"%");
            
            ResultSet rs = query.executeQuery();
            
            P = ResultToArray(rs);
           
            return P;
        }catch(Exception ex){
         ex.printStackTrace();
         return P;
        }
        
    }
     
     private ArrayList ResultToArray(ResultSet rs){
         ProductExtrusorEntity _ProductExtrusorEntity =new ProductExtrusorEntity();
          ArrayList P = new ArrayList();
          try { 
                while(rs.next()){


                        _ProductExtrusorEntity.setId(Integer.parseInt(rs.getString("id")));             
                       _ProductExtrusorEntity.setArea(rs.getString("area")); 
                       _ProductExtrusorEntity.setRack(rs.getString("Rack")); 
                       _ProductExtrusorEntity.setBuild(rs.getString("build")); 
                       _ProductExtrusorEntity.setDescription(rs.getString("description")); 
                       _ProductExtrusorEntity.setF12(rs.getString("f12")); 
                       _ProductExtrusorEntity.setF13(rs.getString("f13")); 
                       _ProductExtrusorEntity.setFamilia(rs.getString("familia")); 
                       _ProductExtrusorEntity.setLongitud(rs.getString("longitud")); 
                       _ProductExtrusorEntity.setMandrel(rs.getString("mandrel")); 
                       _ProductExtrusorEntity.setPart_number(rs.getString("part_number")); 
                       _ProductExtrusorEntity.setQuantity(Integer.parseInt(rs.getString("quantity"))); 
                       _ProductExtrusorEntity.setTime(rs.getString("time")); 
                       _ProductExtrusorEntity.setType(rs.getString("type")); 
                       _ProductExtrusorEntity.setUbicacion(rs.getString("ubicacion")); 

                       P.add(_ProductExtrusorEntity);


                   }
         
         } catch (SQLException ex) {
                 Logger.getLogger(ProductExtrusorService.class.getName()).log(Level.SEVERE, null, ex);
             }
         
         return P;
     }
             
            
    
}
