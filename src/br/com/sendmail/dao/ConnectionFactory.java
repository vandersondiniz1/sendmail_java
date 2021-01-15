package br.com.sendmail.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mariadb://xx.xxx.xx.xxx:3306/db","user","pass");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
