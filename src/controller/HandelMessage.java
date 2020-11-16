/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.DataConfig;
import dao.UserDao;
import dao.UserDaoImpl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.User;
import org.smslib.GatewayException;
import service.ChangePasswordService;
import service.ChangePasswordServiceImpl;
import service.ChargeService;
import service.ChargeServiceImpl;
import service.CheckDataServiceImpl;
import service.CheckDataUserService;
import service.SendAmountService;
import service.SendAmountServiceImpl;
import service.WithDrawalService;
import service.WithDrawalServiceImpl;

/**
 *
 * @author Phuc ND
 */
public class HandelMessage {
    
    private SendAmountService sendAmountService = new SendAmountServiceImpl();
    private ChargeService chargeService = new ChargeServiceImpl();
    private WithDrawalService drawalService = new WithDrawalServiceImpl();
    private CheckDataUserService dataUserService = new CheckDataServiceImpl();
    private ChangePasswordService passwordService = new ChangePasswordServiceImpl();

    public void handelMessage(String message, String phoneSend) throws GatewayException {
        String phoneReceive, branchCode, cardSerialNumber, password, passwordOld, passwordNew;
        int amount;
        Pattern patternSend = Pattern.compile(DataConfig.PATTERN_USSD_SEND);
        Matcher matcherSend = patternSend.matcher(message);
        Pattern patternCharge = Pattern.compile(DataConfig.PATTERN_USSD_CHARDE);
        Matcher matcherCharge = patternCharge.matcher(message);
        Pattern patternWithDrawal = Pattern.compile(DataConfig.PATTERN_USSD_WITHDRAWAL);
        Matcher matcherWithDrawal = patternWithDrawal.matcher(message);
        Pattern patternCheckAcc = Pattern.compile(DataConfig.PATTERN_USSD_CHECK_ACCOUNT);
        Matcher matcherCheckAcc = patternCheckAcc.matcher(message);
        Pattern patternChangePass = Pattern.compile(DataConfig.PATTERN_USSD_CHANGE_PASSWORD);
        Matcher matcherChangePass = patternChangePass.matcher(message);
        if (matcherSend.find()) {
            
            phoneReceive = message.substring(5, 17);
            password = message.substring(18, 24);
            amount = Integer.parseInt(message.substring(25, message.length() - 1));
            sendAmountService.send(phoneReceive, phoneSend, amount, password);
        } else if (matcherCharge.find()) {
            cardSerialNumber = message.substring(5, 18);
            password = message.substring(19, 25);
            System.out.println("Serial Number: " + cardSerialNumber);
            chargeService.charge(phoneSend, cardSerialNumber, password);
            
        } else if (matcherWithDrawal.find()) {
            branchCode = message.substring(5, 15);
            password = message.substring(16, 22);
            amount = Integer.parseInt(message.substring(23, message.length() - 1));
            drawalService.withDrawal(phoneSend, amount, branchCode, password);
        } else if (matcherCheckAcc.find()) {
            dataUserService.getAllData(phoneSend);
        } else if (matcherChangePass.find()) {
            passwordOld = message.substring(5, 11);
            passwordNew = message.substring(12, message.length() - 1);
            passwordService.changePassword(phoneSend, passwordOld, passwordNew);
        } else {
            SendSms sendSms = new SendSms();
            sendSms.sendSms(phoneSend, DataConfig.ERROR_SERVICE);
            
        }
        
    }
}
