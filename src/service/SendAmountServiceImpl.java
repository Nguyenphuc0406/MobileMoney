/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import config.DataConfig;
import controller.SendSms;
import dao.TransactionDao;
import dao.TransactionDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
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
public class SendAmountServiceImpl implements SendAmountService {

    @Override
    public void send(String phoneReceive, String phoneSend, int amount, String password) {

        TransactionDao transactionDao = new TransactionDaoImpl();
        UserDao userDao = new UserDaoImpl();
        User userSend = userDao.getAllUserSend(phoneSend);
        User userReceive = userDao.getAllUserReceive(phoneReceive);
        SendSms sendSms = new SendSms();
        if (userSend != null && userReceive != null) {
            if (userSend.getPassword().equals(password)) {

                if (amount != 0 && amount <= userSend.getAmount()) {
                    if (amount <= 10000000) {
                        System.out.println("Thực hiện lệnh chuyển tiền");

                        // update userSend
                        User userUpdateSend = new User();
                        userUpdateSend.setId(userSend.getId());
                        userUpdateSend.setUserName(userSend.getUserName());
                        userUpdateSend.setPhoneNumber(userSend.getPhoneNumber());
                        userUpdateSend.setAmount(userSend.getAmount() - amount);
                        userUpdateSend.setPassword(userSend.getPassword());
                        userDao.updateUser(userUpdateSend);

                        //update userReceiver
                        User userUpdateRecei = new User();
                        userUpdateRecei.setId(userReceive.getId());
                        userUpdateRecei.setUserName(userReceive.getUserName());
                        userUpdateRecei.setPhoneNumber(userReceive.getPhoneNumber());
                        userUpdateRecei.setAmount(userReceive.getAmount() + amount);
                        userUpdateRecei.setPassword(userReceive.getPassword());
                        userDao.updateUser(userUpdateRecei);
                        transactionDao.addTransaction(userSend.getId(), userReceive.getId(), amount, DataConfig.TRANSACTION_TYPE_SEND);

                        String messages = "Ban vua nhan " + amount + " VND tu so dien thoai " + phoneSend + ". So du hien tai: " + userUpdateRecei.getAmount() + " VND";
                        String msgFeedback = "Ban vua chuyen " + amount + " VND toi so dien thoai " + phoneReceive + ". So du hien tai: " + userUpdateSend.getAmount() + " VND";

                        // Add gatewaySend                         
                        try {
//                        SerialModemGateway gatewaySend = new SerialModemGateway(DataConfig.GATEWAY_ID, DataConfig.GATEWAY_PORT, DataConfig.GATEWAY_BAUDRATE, null, null);
//                        System.out.println("Send message! ");
//                        gatewaySend.setInbound(true);
//                        gatewaySend.setOutbound(true);
//
//                        try {
//                            Service.getInstance().addGateway(gatewaySend);
//                        } catch (GatewayException ex) {
//                            Logger.getLogger(SendSms.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        try {
//                            Service.getInstance().startService();
////                    serialModemGateway.stopGateway();
//                        } catch (SMSLibException | IOException | InterruptedException ex) {
//                            Logger.getLogger(SendSms.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                            OutboundMessage msg = new OutboundMessage(phoneReceive, messages);
                            OutboundMessage msgFb = new OutboundMessage(phoneSend, msgFeedback);
                            try {
                                Service.getInstance().sendMessage(msg);
                                Service.getInstance().sendMessage(msgFb);
//                Service.getInstance().removeGateway(serialModemGateway);
                                System.out.println("Message send: " + msg);
                                System.out.println("Message Feedback: " + msgFeedback);

                            } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
                                ex.printStackTrace();
                                Logger.getLogger(SendSms.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //stop process

                            System.out.println("Finish wait 2 ! ");
//                    
//                        Service.getInstance().stopService();
                            Thread.sleep(5000);
//                        Service.getInstance().removeGateway(gatewaySend);
                            System.out.println("Finish wait 3 ! ");
                            System.out.println("Finish send!");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            Logger.getLogger(SendSms.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            sendSms.sendSms(phoneSend, DataConfig.ERROR_AMOUNT_SEND);
                        } catch (GatewayException ex) {
                            Logger.getLogger(SendAmountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    try {
                        sendSms.sendSms(phoneSend, DataConfig.ERROR_AMOUNT);
                    } catch (GatewayException ex) {
                        Logger.getLogger(SendAmountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                try {
                    sendSms.sendSms(phoneSend, DataConfig.ERROR_PASSWORD);
                } catch (GatewayException ex) {
                    Logger.getLogger(SendAmountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            try {
                sendSms.sendSms(phoneSend, DataConfig.ERROR_USER);
            } catch (GatewayException ex) {
                Logger.getLogger(SendAmountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
