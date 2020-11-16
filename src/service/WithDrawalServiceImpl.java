/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import config.DataConfig;
import controller.SendSms;
import dao.BranchDao;
import dao.BranchDaoImpl;
import dao.TransactionDao;
import dao.TransactionDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Branch;
import model.Transaction;
import model.User;
import org.smslib.GatewayException;

/**
 *
 * @author Phuc ND
 */
public class WithDrawalServiceImpl implements WithDrawalService {

    private BranchDao branchDao = new BranchDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void withDrawal(String phoneSend, int amount, String branchCode, String password) {
        User user = userDao.getAllUserSend(phoneSend);
        Branch branch = branchDao.getAllBranch(branchCode);
        TransactionDao transactionDao = new TransactionDaoImpl();
        SendSms sendSms = new SendSms();
        if (user != null) {
            if (user.getPassword().equals(password)) {

                if (branch != null) {
                    if (user.getAmount() >= amount) {
                        //update user
                        User userUpdate = new User();
                        userUpdate.setId(user.getId());
                        userUpdate.setUserName(user.getUserName());
                        userUpdate.setPhoneNumber(user.getPhoneNumber());
                        userUpdate.setAmount(user.getAmount() - amount);
                        userUpdate.setPassword(user.getPassword());
                        userDao.updateUser(userUpdate);

                        //update branch
                        Branch branchUpdate = new Branch();
                        branchUpdate.setId(branch.getId());
                        branchUpdate.setBranchName(branch.getBranchName());
                        branchUpdate.setBranchCode(branch.getBranchCode());
                        branchUpdate.setBranchAmount(branch.getBranchAmount() + amount);
                        branchDao.updateBranch(branchUpdate);

                        //add transaction
                        transactionDao.addTransaction(user.getId(), user.getId(), amount, DataConfig.TRANSACTION_TYPE_WITHDRAWAL);
                        String messageFeedback = "Quy khach rut thanh cong " + amount + "VND. So du hien tai: " + userUpdate.getAmount();

                        try {
                            sendSms.sendSms(phoneSend, messageFeedback);
                        } catch (GatewayException ex) {
                            Logger.getLogger(WithDrawalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Qúy khách vừa rút thành công " + amount + "VNĐ !");
                    } else {
                        try {
                            sendSms.sendSms(phoneSend, DataConfig.ERROR_AMOUNT);
                        } catch (GatewayException ex) {
                            Logger.getLogger(WithDrawalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    try {
                        sendSms.sendSms(phoneSend, DataConfig.ERROR_BRANCH);
                    } catch (GatewayException ex) {
                        Logger.getLogger(WithDrawalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                try {
                    sendSms.sendSms(phoneSend, DataConfig.ERROR_PASSWORD);
                } catch (GatewayException ex) {
                    Logger.getLogger(WithDrawalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                sendSms.sendSms(phoneSend, DataConfig.ERROR_USER);
            } catch (GatewayException ex) {
                Logger.getLogger(WithDrawalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
