/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Transaction;
import model.User;

/**
 *
 * @author Phuc ND
 */
public class TransactionDaoImpl extends JDBCConnection implements TransactionDao{

    @Override
    public void addTransaction(int idSender, int idReceiver, int amount, String tran_type) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String sql = "INSERT INTO transaction (sender_id, receiver_id, tran_type, tran_amount, tran_datetime) VALUES(?,?,?,?,?)";
        try {
            Connection conn = super.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);      
            preparedStatement.setInt(1, idSender);
            preparedStatement.setInt(2,idReceiver);
            preparedStatement.setString(3, tran_type);
            preparedStatement.setInt(4, amount );
            preparedStatement.setString(5, dateFormat.format(date));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }
    }
    
}
