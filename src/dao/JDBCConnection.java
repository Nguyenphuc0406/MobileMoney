/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Phuc ND
 */
public class JDBCConnection {
    public Connection getConnection() {
		final String url = "jdbc:mysql://localhost:3306/mobile_money_v2?autoReconnect=true&useSSL=false";
		final String user = "root";
		final String pass = "root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
		}
		return null;

	}

	
}
