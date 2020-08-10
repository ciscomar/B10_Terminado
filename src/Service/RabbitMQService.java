/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;



import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Ulises
 */
public class RabbitMQService {
    
    private static final String QUEUE_NAME = "workQueues";
    private Connection connection;
    private Channel channel;
    
    public void initialize() throws TimeoutException {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setPort(5672);
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            
        }
}
    public void Send(String queue) throws TimeoutException {       

        try {
            initialize();
            //ConnectionFactory factory = new ConnectionFactory();
            //factory.setHost("localhost");            
            
            //Connection 
            //connection = factory.newConnection();
             
            //channel = connection.createChannel();
            
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            for (int i = 0; i < 10; ++i) {
                String message = String.format("Hello World at %s", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(String.format("Sent «%s»", message));

                //Thread.sleep(1500);
            }
            channel.close();
            
        }catch (IOException e) {
            
        }
            
         finally {
            
        }
    }
}
