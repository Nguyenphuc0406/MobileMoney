/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import config.DataConfig;
import controller.SendSms;
import dao.UserDao;
import dao.UserDaoImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.smslib.GatewayException;

/**
 *
 * @author Phuc ND
 */
public class CheckDataServiceImpl implements CheckDataUserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void getAllData(String phoneSend) {

        User user = userDao.getAllUserSend(phoneSend);
        SendSms sendSms = new SendSms();
        if (user != null) {
            String message = "Thong tin - Chu tai khoan: " + user.getUserName() + " - So dien thoai: " + user.getPhoneNumber() + " -So du hien tai: " + user.getAmount() + " VND";

            try {
                sendSms.sendSms(phoneSend, message);
            } catch (GatewayException ex) {
                Logger.getLogger(CheckDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                sendSms.sendSms(phoneSend, DataConfig.ERROR_USER);
            } catch (GatewayException ex) {
                Logger.getLogger(CheckDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
