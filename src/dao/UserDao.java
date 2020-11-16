/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Branch;
import model.Transaction;
import model.User;

/**
 *
 * @author Phuc ND
 */
public interface UserDao {

    void updateUser(User user);
    
    void addUser(User user);

    User getAllUserSend(String phoneSend);

    User getAllUserReceive(String phoneReceive);
    
    
    

}
