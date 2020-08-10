/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entities.ProductsEntity;
import Service.ProductService;

/**
 *
 * @author Ulises
 */
public class ProductModel {
    public boolean ProductExist(String noSAP){
        ProductService obj = new ProductService();
        return obj.NoSAP(noSAP);
    }
    
     public ProductsEntity getProduct(String noSAP){
        ProductService obj = new ProductService();
        return obj.GetNoSAP(noSAP);
    }
}
