/*
 * @name 		--> JavaMailApp.java
 * @description	--> Classe responsavel por criar a logica de envio do email
 * @author 		--> vanderson.lima
 * @since		--> 2020.01.01
 * @version		--> 1.0
 * Adapted from --> https://www.devmedia.com.br/enviando-email-com-javamail-utilizando-gmail/18034
 */

package br.com.sendmail.main;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//DECLARACAO DA CLASSE
public class JavaMailApp
{
	//SE PRECISAR FAZER ALGUM TESTE, SO HABILITAR O MAIN
	//public static void main(String[] args) {
    public String enviarEmail(String email){
    
    //CRIANDO UM NOVO OBJETO	
	Properties props = new Properties();
  
   //CONFIGURACOES DE CONEXAO COM O GMAIL
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    //CRIANDO UMA SESSAO COM AS CONFIGURACOES PASSADAS
    Session session = Session.getDefaultInstance(props,
      new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication()
           {
                 return new PasswordAuthentication("meuemail@mail.com.br","minhasenha");
           }
      });
    
    //RECEBE O(S) EMAIL(S) COMO PARAMETRO
    String mailto = email;
    
    //ATIVA O MODO DEBUG PARA A SESSAO - ESSE DEBUG SERA MOSTRADO NO TERMINAL OU CONSOLE DO ECLIPSE
    session.setDebug(true);

    try {

      //EXIBIRA A MENSAGEM RETORNADA NA SESSAO
      Message message = new MimeMessage(session);
      
      //REMETENTE
      message.setFrom(new InternetAddress("meuemail@mail.com.br"));

      /*DESTINATARIO - mailto
       * O DESTINATARIO ESTA SENDO PASSADO NA CHAMADA DO METODO. ENQUANTO TIVER DESTINATARIO, FAZ A 
       * CHAMADA DO METODO DECLARA NA CLASSE SELECTQUERY E ENVIA O EMAIL
       */
      Address[] toUser = InternetAddress.parse(mailto);
      message.setRecipients(Message.RecipientType.TO, toUser);

      //ASSUNTO
      message.setSubject("Parabéns!");//Assunto
      
      //TEXTO DO CORPO DO EMAIL
      message.setText("Hoje é o seu Aniversário e o Grupo Vicoa Brasil...");
      
      //QUEM REALMENTE ENVIA A MENSAGEM
      Transport.send(message);

      //SAIDA COLOCADA APENAS AFIM DE TESTES
      //System.out.println("Feito!!!");

     } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
    
    //RETORNO DE SUCESSO
	return "Enviado";
  }
}
