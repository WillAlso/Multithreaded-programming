package Server;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
 
public class Sender
{
   public static void main(String [] args){
	   
   }
   public static boolean sendMail(String to, String code,String con) {  

       try {  
           Properties props = new Properties();  
           props.put("username", "15972982686@163.com");   
           props.put("password", "54mima");   
           props.put("mail.transport.protocol", "smtp" );  
           props.put("mail.smtp.host", "smtp.163.com");  
           props.put("mail.smtp.port", "25" );  

           Session mailSession = Session.getDefaultInstance(props);  

           Message msg = new MimeMessage(mailSession);     
           msg.setFrom(new InternetAddress("15972982686@163.com"));  
           msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));   
           msg.setSubject("注册验证码");   
           msg.setContent("<h1>文件管理系统验证码"+con+"</h1>","text/html;charset=UTF-8");

           msg.saveChanges();  

           Transport transport = mailSession.getTransport("smtp");  
           transport.connect(props.getProperty("mail.smtp.host"), props  
                   .getProperty("username"), props.getProperty("password"));   
           transport.sendMessage(msg, msg.getAllRecipients());  
           transport.close();     
       } catch (Exception e) {  
           e.printStackTrace();  
           System.out.println(e);  
           return false;  
       }  
       return true;  
   }  
}