/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import config.DataConfig;
import controller.SendSms;
import dao.CardDao;
import dao.CardDaoImpl;
import dao.TransactionDao;
import dao.TransactionDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Card;
import model.User;
import org.smslib.GatewayException;

/**
 *
 * @author Phuc ND
 */
public class ChargeServiceImpl implements ChargeService {

    private UserDao userDao = new UserDaoImpl();
    private TransactionDao transactionDao = new TransactionDaoImpl();
    private CardDao cardDao = new CardDaoImpl();

    @Override
    public void charge(String phoneSend, String serialNumber, String password) {
        System.out.println("-------------------");
        System.out.println("Thực hiện lệnh nạp tiền");

        User user = userDao.getAllUserSend(phoneSend);
        Card card = cardDao.getAll(serialNumber);
        User userUpdate = new User();
        SendSms sendSms = new SendSms();
        if (user != null) {
            if (user.getPassword().equals(password)) {
                if (card != null && card.getSerialNumber().equals(serialNumber)) {
                    int amount = card.getAmount();
                    //check user amount 
                    if (amount != 0 && user.getAmount() + amount < 50000000) {

                        userUpdate.setId(user.getId());
                        userUpdate.setUserName(user.getUserName());
                        System.out.println("Ien: " + user.getUserName());
                        System.out.println("So tien truoc khi nap: " + user.getAmount());
                        userUpdate.setPhoneNumber(user.getPhoneNumber());
                        System.out.println("SDT: " + user.getPhoneNumber());
                        userUpdate.setAmount(user.getAmount() + amount);
                        System.out.println("So tien sau khi nap: " + userUpdate.getAmount());
                        userUpdate.setPassword(user.getPassword());
                        userDao.updateUser(userUpdate);

                        //add transaction
                        transactionDao.addTransaction(user.getId(), user.getId(), amount, DataConfig.TRANSACTION_TYPE_CHARGE);

                        System.out.println("-------------------");
                        String messageFeedback = "Quy khach nap thanh cong " + amount + " VND. So du hien tai: " + userUpdate.getAmount();
                        //delete card number
                        cardDao.deleteCard(serialNumber);
                        try {
                            sendSms.sendSms(phoneSend, messageFeedback);
                        } catch (GatewayException ex) {
                            Logger.getLogger(ChargeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            sendSms.sendSms(phoneSend, DataConfig.ERROR_AMOUNT);
                        } catch (GatewayException ex) {
                            Logger.getLogger(ChargeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    try {
                        sendSms.sendSms(phoneSend, DataConfig.ERROR_CARD_NUMBER);
                    } catch (GatewayException ex) {
                        Logger.getLogger(ChargeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                try {
                    sendSms.sendSms(phoneSend, DataConfig.ERROR_PASSWORD);
                } catch (GatewayException ex) {
                    Logger.getLogger(ChargeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                sendSms.sendSms(phoneSend, DataConfig.ERROR_USER);
            } catch (GatewayException ex) {
                Logger.getLogger(ChargeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
