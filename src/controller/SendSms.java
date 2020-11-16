/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.DataConfig;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Phuc ND
 */
public class SendSms extends  Thread{

    public void sendSms(String phoneReceive, String messages) throws GatewayException {
//        try {
//            SerialModemGateway serialModemGateway = new SerialModemGateway(DataConfig.GATEWAY_ID, DataConfig.GATEWAY_PORT, DataConfig.GATEWAY_BAUDRATE, null, null);
            System.out.println("Send message! ");
//            serialModemGateway.setInbound(true);
//            serialModemGateway.setOutbound(true);
//            Service.getInstance().findGateway("modem.com15");
//            try {
//                Service.getInstance().addGateway(serialModemGateway);
//            } catch (GatewayException ex) {
//                Logger.getLogger(SendSms.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                Service.getInstance().startService();
//
//            } catch (SMSLibException | IOException | InterruptedException ex) {
//                Logger.getLogger(SendSms.class.getName()).log(Level.SEVERE, null, ex);
//            }
            OutboundMessage msg = new OutboundMessage(phoneReceive, messages);
            try {
                Service.getInstance().sendMessage(msg);

                System.out.println("Message send: " + msg);

            } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
                Logger.getLogger(SendSms.class.getName()).log(Level.SEVERE, null, ex);
            }
            //stop process
           
            
//            Service.getInstance().stopService();
//            
//            System.out.println("Finish!");
//        } catch (SMSLibException | IOException | InterruptedException ex) {
//            Logger.getLogger(SendSms.class.getName()).log(Level.SEVERE, null, ex);
//        }
    } 
 
}
