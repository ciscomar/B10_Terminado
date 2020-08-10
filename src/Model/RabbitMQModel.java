/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Service.RabbitMQService;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Ulises
 */
public class RabbitMQModel {
    public void Send(String queue)throws TimeoutException{
        RabbitMQService obj = new RabbitMQService();
        //return 
        obj.Send(queue);
    }
}
