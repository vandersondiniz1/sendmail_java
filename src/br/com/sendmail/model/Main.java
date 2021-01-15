/*
 * @name 		--> SelectQuery.java
 * @description	--> Classe responsavel por retornar um select no banco de dados
 * @author 		--> vanderson.lima
 * @since		--> 2020.01.01
 * @version		--> 1.0
 */
package br.com.sendmail.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.com.sendmail.dao.ConnectionFactory;
import br.com.sendmail.main.SendEmail;

//DECLARACAO DA CLASSE
public class Main {

	//METODO PRINCIPAL
	public static void main(String[] Args) throws SQLException {
		try {
			
			//INICIANDO AS CONFIGURACOES DO LOG4J
			final Logger logger = LogManager.getLogger(Main.class.getName());
			
			//CONFIGURANDO NIVEL DE LOG
			logger.setLevel(Level.INFO);
			
			//PRIMEIRO LOG
			logger.info("=====INICIANDO APLICACAO=====");

			//UTILIZA O PADRAO DE PROJETO CONNECTION FACTORY
			Connection conn = new ConnectionFactory().getConnection();
			logger.info("REALIZANDO A CONEXAO COM O BANCO DE DADOS");
			
			//CRIA UM STATEMENT
			Statement stmt = conn.createStatement();
			
			//DECLARA UM RESULTSET
			ResultSet rs;
			
			//CRIA UM OBJETO DA CLASSE JAVAMAILAPP PARA FAZER A CHAMADA AO METODO DE ENVIO DE EMAILS
			//JavaMailApp javaMailApp = new JavaMailApp();
			SendEmail sendemail = new SendEmail();
			
			//EXECUTA O STATEMENT COM O SELECT
			rs = stmt
					.executeQuery(CONSTANTS.SELECT);

			//ENQUANTO HOUVER RESULTADO PARA O SELECT, CONTINUA
			while (rs.next()) {
				
				//PEGA UM DETERMINADO CAMPO DO BANCO DE DADOS
				String email = rs.getString("email");
				logger.info("A MENSAGEM SERA ENVIADA HOJE PARA O SEGUINTE EMAIL:" + email);
				
				//SAIDA COLOCADA AFIM DE TESTES. SO PARA VER O QUE ESTA SENDO RETORNADO
				System.out.println(email);
				
				//CHAMA O METODO DO ENVIO DE EMAIL, PASSANDO O(S) EMAIL(S) QUE FORAM RECUPERADOS POR MEIO DA CONSULTA
				//javaMailApp.enviarEmail(email);
				sendemail.enviarEmail(email);
				logger.info("Enviando email neste momento para:" + email);
				logger.info("========FINALIZANDO APLICACAO=========");
			}

			//FECHA A CONEXAO
			conn.close();

		} catch (Exception e) {
			
			//LANCA UMA MENSAGEM DE EXCECAO
			System.err.println("Algo de Errado não está Certo!");
			
			final Logger logger = LogManager.getLogger(Main.class.getName());
			logger.info("Ocorreu um erro ao enviar o email");
			
			//IMPRIME A PILHA DE ERROS
			System.err.println(e.getMessage());
			logger.info("Ocorreu um erro ao enviar o email" + e.getMessage());
		}
	}
}
