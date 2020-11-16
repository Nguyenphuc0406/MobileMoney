/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Phuc ND
 */
public interface TransactionDao {
    void addTransaction(int idSender, int idReceiver, int amount, String tran_type);
}
