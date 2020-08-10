/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import java.sql.*;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ulises
 */
public class ServicesMySQL {
    private static Connection con = null;
    
    public static void Initialize(){
        con = null;
    }
    
    public static Connection getConnection(){
        //con = null;
        if(con == null){
            try{
                String dbPath="";
                String dbName="";
                String userDB="";
                String passDB="";
                String station="";
                String scanSleep="";
                
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
                    scanSleep = sc.nextLine();
                    
                     
                     
                     
                }
                else
                    return null;
                
                
                Class.forName("com.mysql.jdbc.Driver");
                //con= DriverManager.getConnection("jdbc:mysql://"+dbPath+"/"+dbName+"", "root", "");
                con= DriverManager.getConnection("jdbc:mysql://"+dbPath+"/"+dbName+"", ""+userDB+"", ""+passDB+"");
            } catch (Exception ex) {
                ex.printStackTrace();
            
            }
           
        }
         return con;
    }
    
    public static Connection getConnection(String dbName){
        
        if(con == null){
            try{
                String dbPath="";
                String dbName1="";
                String userDB="";
                String passDB="";
                String station="";
                String scanSleep="";
                
                String path=new File ("TristonePath.txt").getAbsolutePath ();
                      
                File file = new File(path);
                if(file.exists())
                {
                    Scanner sc = new Scanner(file);                      
                            
                    dbPath = sc.nextLine();
                    dbName1 = sc.nextLine();
                    userDB = sc.nextLine();
                    passDB = sc.nextLine();
                    station = sc.nextLine();
                    scanSleep = sc.nextLine();

                }
                else
                    return null;
                
                
                Class.forName("com.mysql.jdbc.Driver");
                con= DriverManager.getConnection("jdbc:mysql://"+dbPath+"/"+dbName+"", ""+userDB+"", ""+passDB+"");
            } catch (Exception ex) {
                ex.printStackTrace();
            
            }
           
        }
         return con;
    }
    
     public static void CloseConection()   {
      if (con != null) {
          try {
              con.close();
          } catch (SQLException ex) {
              Logger.getLogger(ServicesMySQL.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
   }
}
