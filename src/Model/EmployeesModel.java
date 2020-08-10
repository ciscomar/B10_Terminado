/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Entities.EmployeeEntity;
import Service.EmployeeServices;

/**
 *
 * @author Ulises
 */
public class EmployeesModel {
    public boolean EmployeeExist(String idEmployee){
        EmployeeServices obj = new EmployeeServices();
        return obj.EmployeeExist(idEmployee);
    }
    
     public EmployeeEntity getEmployee(String idEmployee){
        EmployeeServices obj = new EmployeeServices();
        return obj.getEmployee(idEmployee);
    }
}
