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
public class ChangePasswordServiceImpl implements ChangePasswordService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void changePassword(String phoneSend, String passwordOld, String passwordNew) {
        User user = userDao.getAllUserSend(phoneSend);
        SendSms sendSms = new SendSms();
        if (user != null) {
            if (user.getPassword().equals(passwordOld)) {
                if (passwordNew.length() == 6) {
                    User userUpdate = new User();
                    userUpdate.setId(user.getId());
                    userUpdate.setAmount(user.getAmount());
                    userUpdate.setPassword(passwordNew);
                    userUpdate.setPhoneNumber(user.getPhoneNumber());
                    userUpdate.setUserName(user.getUserName());
                    userDao.updateUser(userUpdate);

                    try {
                        sendSms.sendSms(phoneSend, DataConfig.SUCCESS_CHANGE_PASSWORD);
                    } catch (GatewayException ex) {
                        Logger.getLogger(CheckDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        sendSms.sendSms(phoneSend, DataConfig.ERROR_CHANGE_PASSWORD);
                    } catch (GatewayException ex) {
                        Logger.getLogger(CheckDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                try {
                    sendSms.sendSms(phoneSend, DataConfig.ERROR_PASSWORD);
                } catch (GatewayException ex) {
                    Logger.getLogger(CheckDataServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
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
