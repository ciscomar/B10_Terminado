/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.EmployeeEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Service.ServicesMySQL;
import java.sql.DriverManager;
/**
 *
 * @author Ulises
 */
public class EmployeeServices {
    ServicesMySQL connection;
            Connection con = null;
    
    public boolean EmployeeExist(String employeeNumber) 
    {
        boolean exist=false;
        int count=0;
        try{
            
            PreparedStatement query;
            connection =new ServicesMySQL();
            connection.Initialize();
            
            // Class.forName("com.mysql.jdbc.Driver");
            // con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "");
             
           // query=con.prepareStatement("SELECT Count(*) FROM usuarios WHERE id=?");
            query=connection.getConnection().prepareStatement("SELECT Count(*) FROM empleados WHERE emp_tag=?");
            query.setString(1, employeeNumber);
            
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
    
    public EmployeeEntity getEmployee(String idEmployee) 
    {
        EmployeeEntity _employeeEntity = new EmployeeEntity();
        
        try{
            PreparedStatement query;
            connection =new ServicesMySQL();
            connection.Initialize();
            
            // Class.forName("com.mysql.jdbc.Driver");
            // con= DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "");
             
           // query=con.prepareStatement("SELECT Count(*) FROM usuarios WHERE id=?");
            query=connection.getConnection().prepareStatement("SELECT * FROM empleados WHERE emp_tag=?");
            query.setString(1, idEmployee);
            
            ResultSet rs = query.executeQuery();
            if(rs.next())
            {
                _employeeEntity.setIdEmployee( rs.getString("emp_tag")); 
                _employeeEntity.setNameEmployee(rs.getString("emp_name"));
                _employeeEntity.setNumEmployee(rs.getString("emp_num"));
            }
           
            return _employeeEntity;
            
        }catch(Exception ex){
         ex.printStackTrace();
         return _employeeEntity;
        }finally{
        connection.CloseConection();
        }
        
    }
    
    
}
    
