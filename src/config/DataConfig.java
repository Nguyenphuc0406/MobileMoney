/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Phuc ND
 */
public class DataConfig {

    public static final int FLAG_STATUS = 0;
    public static final String PATTERN_SEND = "[*]112[*]";
    public static final String PATTERN_CHARGE = "[*]113[*]";
    public static final String PATTERN_WITH_DRAWAL = "[*]114[*]";
    public static final String GATEWAY_PHONE = "+84796131183";
    public static final String GATEWAY_PORT = "COM15";
    public static final int GATEWAY_BAUDRATE = 115200;
    public static final String GATEWAY_ID = "model.com15";
    public static final String PATTERN_USSD_SEND = "[\\*](112)[\\*][\\+]\\d{11}[\\*]\\w{6}[\\*]\\d+[\\#]$";
    public static final String PATTERN_USSD_CHARDE = "[\\*](113)[\\*]\\d{13}[\\*]\\w{6}[\\#]$";
    public static final String PATTERN_USSD_WITHDRAWAL = "[\\*](114)[\\*]\\w{10}[\\*]\\w{6}[\\*]\\d+[\\#]$";
    public static final String PATTERN_USSD_CHECK_ACCOUNT = "[*]119[#]$";
    public static final String PATTERN_USSD_CHANGE_PASSWORD = "[\\*](115)[\\*]\\w{6}[\\*]\\w{6}[\\#]$";
    public static final String TRANSACTION_TYPE_SEND = "SEND";
    public static final String TRANSACTION_TYPE_CHARGE = "CHARGE";
    public static final String TRANSACTION_TYPE_WITHDRAWAL = "WITHDRAWAL";
    public static final String ERROR_AMOUNT = "So tien khong hop le!";
    public static final String ERROR_USER = "Loi thong tin nguoi dung. Vui long dang ky dich vu!";
    public static final String ERROR_CARD_NUMBER = "Ma the khong hop le!";
    public static final String ERROR_SERVICE = "Cu phap khong xac thuc!";
    public static final String ERROR_BRANCH = "Ma cua hang khong ton tai!";
    public static final String ERROR_PASSWORD = "Mat khau nguoi dung khong dung!";
    public static final String ERROR_AMOUNT_SEND = "So tien moi lan giao dich khong vuot qua 10.000.000 VND";
    public static final String ERROR_CHANGE_PASSWORD = "Mat khau phai du 6 ky tu!";
    public static final String SUCCESS_CHANGE_PASSWORD = "Doi mat khau thanh cong!";
    
}
