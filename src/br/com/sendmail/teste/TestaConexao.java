package br.com.sendmail.teste;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.sendmail.dao.ConnectionFactory;

public class TestaConexao {

	public static void main(String[] Args) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("Conexão aberta!");
		connection.close();
	}

}