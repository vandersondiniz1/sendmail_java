/*
 * @name 		--> SendEmail.java
 * @description	--> Classe responsavel por retornar um select no banco de dados
 * @author 		--> Adapted from https://www.splessons.com/lesson/javamail-send-inline-image/
 * @since		--> 2021.01.09
 * @version		--> 1.0
 */
package br.com.sendmail.main;	

import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.sendmail.properties.FileProperties;

public class SendEmail {
  
	
	//public static void main(String[] args) {
	 public String enviarEmail(String email) throws IOException{ 
	
      //CHAMA O ARQUIVO DE PROPRIEDADES
	  FileProperties fileproperties = new FileProperties();
	  
	  //PEGANDO OS PARAMETROS DO ARQUIVO DE CONFIGURACOES
	  String dir = fileproperties.getProp().getProperty("prop.dir");
	
	  //DESTINATARIO
      String to = email;

      //REMETENTE
      String from = fileproperties.getProp().getProperty("prop.from");
      
      //DADOS DE LOGIN
      final String username = fileproperties.getProp().getProperty("prop.username");
      final String password = fileproperties.getProp().getProperty("prop.password");

      //CONFIGURACAO DO SERVIDOR SMTP
      String host = "smtp.gmail.com";

      //CONFIGURACOES DO SERVIDOR DE EMAIL
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");

      //TENTATIVA DE INICIAR A SESSAO NO SERVIDOR DE EMAIL
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {

         // CRIA UM OBJETO DO TIPO MIMETYPE
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Hoje é seu dia!");

         // This mail has 2 part, the BODY and the embedded image
         MimeMultipart multipart = new MimeMultipart("related");

         // first part (the html)
         BodyPart messageBodyPart = new MimeBodyPart();
         String htmlText = "<H1>Parabéns!</H1><img src=\"cid:image\">";
         messageBodyPart.setContent(htmlText, "text/html");
         // add it
         multipart.addBodyPart(messageBodyPart);

         // second part (the image)
         messageBodyPart = new MimeBodyPart();
        
         //DIRETORIO DE CONFIGURACAO DA IMAGEM
         DataSource fds = new FileDataSource(dir);

         messageBodyPart.setDataHandler(new DataHandler(fds));
         messageBodyPart.setHeader("Content-ID", "<image>");

         // add image to the multipart
         multipart.addBodyPart(messageBodyPart);

         // put everything together
         message.setContent(multipart);
         // Send message
         Transport.send(message);

         System.out.println("Mensagem Enviada com Sucesso!");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
      
    //RETORNO DE SUCESSO
  	return "Enviado";
   }

	private Properties getProp() {
		return null;
	}
}