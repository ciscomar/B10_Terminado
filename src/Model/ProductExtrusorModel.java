/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entities.ProductExtrusorEntity;
import Service.ProductExtrusorService;
import java.util.ArrayList;


/**
 *
 * @author Ulises
 */
public class ProductExtrusorModel {
    
    public ArrayList GetExtrusionVulcanizado(){
        ProductExtrusorService obj = new ProductExtrusorService();
        return obj.GetExtrusionVulcanizado();
    }
    
     public ArrayList GetExtrusionVulcanizadoByPart(String partNumber){
        ProductExtrusorService obj = new ProductExtrusorService();
        return obj.GetExtrusionVulcanizadoByPartNumber(partNumber);
    }
}
