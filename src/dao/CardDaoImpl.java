/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Card;


/**
 *
 * @author Phuc ND
 */
public class CardDaoImpl extends JDBCConnection implements CardDao{

    @Override
    public Card getAll(String serialNumber) {
          String sql = "SELECT u.card_id, u.serial_number, u.card_amount FROM card u WHERE u.serial_number LIKE ? ";
        try {
            Connection conn = super.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%" + serialNumber + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Card card = new Card();
                card.setCardId(resultSet.getInt("card_id"));
                card.setSerialNumber(resultSet.getString("serial_number"));              
                card.setAmount(resultSet.getInt("card_amount"));
                return card;
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e);
        }
        return null;
    }
    

    @Override
    public void deleteCard(String serialNumber) {
        String sql = "DELETE FROM card WHERE serial_number = ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, serialNumber);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Exception: " + e);
		}
        
    }
    
}
