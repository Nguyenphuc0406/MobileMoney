  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Phuc ND
 */
public class UserDaoImpl extends JDBCConnection implements UserDao {

    public static void main(String[] args) {
        String name = "+84796131183";
        UserDao connectData = new UserDaoImpl();
        User user = new User();
        user = connectData.getAllUserSend(name);
        System.out.println("Name" + user.getPhoneNumber());

    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public User getAllUserSend(String phoneSend) {
        String sql = "SELECT u.user_id, u.user_name, u.user_phone_number, u.user_amount, u.user_password FROM user u WHERE u.user_phone_number LIKE ? ";
        try {
            Connection conn = super.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%" + phoneSend + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPhoneNumber(resultSet.getString("user_phone_number"));
                user.setAmount(resultSet.getInt("user_amount"));
                user.setPassword(resultSet.getString("user_password"));
                return user;
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e);
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user SET user_name = ?, user_phone_number = ?, user_amount = ?, user_password = ? WHERE user_id = ?";
        try {
            Connection conn = super.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setInt(3, user.getAmount());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public User getAllUserReceive(String phoneReceive) {
        String sql = "SELECT u.user_id, u.user_name, u.user_phone_number, u.user_amount, u.user_password FROM user u WHERE u.user_phone_number LIKE ? ";
        try {
            Connection conn = super.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%" + phoneReceive + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setPhoneNumber(resultSet.getString("user_phone_number"));
                user.setAmount(resultSet.getInt("user_amount"));
                user.setPassword(resultSet.getString("user_password"));
                return user;
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e);
        }
        return null;
    }

}
