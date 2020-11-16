/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.HandelMessage;
import java.io.IOException;
import org.smslib.*;
import org.smslib.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.SecretKeySpec;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.AGateway.Protocols;
import org.smslib.Message.MessageTypes;
import org.smslib.crypto.AESKey;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Phuc ND
 */
public class ReadSms {

//    public static String message;
//    public  static String phoneNumber;
    public void doIt() throws Exception {
        // Define a list which will hold the read messages.
//        List<InboundMessage> msgList;
        // Create the notification callback method for inbound & status report
        // messages.
        // InboundNotification inboundNotification = new InboundNotification();
        // Create the notification callback method for inbound voice calls.
        // CallNotification callNotification = new CallNotification();
        // Create the notification callback method for gateway statuses.
        // GatewayStatusNotification statusNotification = new GatewayStatusNotification();
        // OrphanedMessageNotification orphanedMessageNotification = new OrphanedMessageNotification();
        try {
            System.out.println("Example: Read messages from a serial gsm modem.");
            System.out.println(Library.getLibraryDescription());
            System.out.println("Version: " + Library.getLibraryVersion());
            // Create the Gateway representing the serial GSM modem.
            SerialModemGateway gateway = new SerialModemGateway("modem.com15", "COM15", 115200, "Huawei", "E160");
            // Set the modem protocol to PDU (alternative is TEXT). PDU is the default, anyway...
            gateway.setProtocol(Protocols.PDU);
            // Do we want the Gateway to be used for Inbound messages?
            gateway.setInbound(true);
            // Do we want the Gateway to be used for Outbound messages?
            gateway.setOutbound(true);
            // Let SMSLib know which is the SIM PIN.
            gateway.setSimPin("0000");
            // Set up the notification methods.

            Service.getInstance().setInboundMessageNotification(ReadSms::processInboundMsg);
            Service.getInstance().setCallNotification(ReadSms::processCall);
            Service.getInstance().setGatewayStatusNotification(ReadSms::processGatewayNoti);
            Service.getInstance().setOrphanedMessageNotification(ReadSms::process);
            // Add the Gateway to the Service object.
            Service.getInstance().addGateway(gateway);
            // Similarly, you may define as many Gateway objects, representing
            // various GSM modems, add them in the Service object and control all of them.
            // Start! (i.e. connect to all defined Gateways)
            Service.getInstance().startService();
            // Printout some general information about the modem.
            System.out.println();
            System.out.println("Modem Information: ");
            System.out.println("  Manufacturer: " + gateway.getManufacturer());
            System.out.println("  Model: " + gateway.getModel());
            System.out.println("  Serial No: " + gateway.getSerialNo());
            System.out.println("  SIM IMSI: " + gateway.getImsi());
            System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
            System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
            System.out.println();
            // In case you work with encrypted messages, its a good time to declare your keys.
            // Create a new AES Key with a known key value. 
            // Register it in KeyManager in order to keep it active. SMSLib will then automatically
            // encrypt / decrypt all messages send to / received from this number.
            Service.getInstance().getKeyManager().registerKey("+84796131183", new AESKey(new SecretKeySpec("0011223344556677".getBytes(), "AES")));
            // Read Messages. The reading is done via the Service object and
            // affects all Gateway objects defined. This can also be more directed to a specific
            // Gateway - look the JavaDocs for information on the Service method calls.

            // Sleep now. Emulate real world situation and give a chance to the notifications
            // methods to be called in the event of message or voice call reception.
            System.out.println("Now Sleeping - Hit <enter> to stop service.");
            System.in.read();
        } catch (IOException | InterruptedException | SMSLibException e) {
            throw new Error();
        } finally {
            Service.getInstance().stopService();
        }
    }

    public static void processInboundMsg(AGateway gateway, MessageTypes msgType, InboundMessage msg) {
        // TODO handel message 
        if (msgType == MessageTypes.INBOUND) {
            try {
                String message, phoneNumber;
                System.out.println(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
//                List<InboundMessage> msgList = new ArrayList<>();
//                Service.getInstance().readMessages(msgList, MessageClasses.ALL);
//                msg = msgList.get(msgList.size() - 1);
                message = msg.getText();
                phoneNumber = "+" + msg.getOriginator();
                System.out.println("Message content: " + message);
                System.out.println("Phone send: " + phoneNumber);

//                gateway.stopGateway();
                HandelMessage handelMessage = new HandelMessage();
                handelMessage.handelMessage(message, phoneNumber);
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(ReadSms.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (msgType == MessageTypes.STATUSREPORT) {
            System.out.println(">>> New Inbound Status Report message detected from Gateway: " + gateway.getGatewayId());
        }
        try {
            Service.getInstance().deleteMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void processCall(AGateway gateway, String callerId) {
        System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);
    }

    public static void processGatewayNoti(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus) {
        System.out.println(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
    }

    public static boolean process(AGateway gateway, InboundMessage msg) {
        System.out.println(">>> Orphaned message part detected from " + gateway.getGatewayId());
        System.out.println(msg);
        // Since we are just testing, return FALSE and keep the orphaned message part.
        return false;
    }

    public static void main(String args[]) throws Exception {
        ReadSms app = new ReadSms();
        app.doIt();
    }
}
