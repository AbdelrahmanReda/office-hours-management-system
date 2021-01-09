
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

import javax.mail.Session;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Session;
import sun.text.normalizer.ICUBinary;


/**
 *
 * @author toqa khaled
 */
public class test {
    
    public static void sendMail (String recipt) throws Exception  {
        System.out.println("preperaing");
        Properties prop = new Properties();
        
        






Properties properties = new Properties();

properties.setProperty("mail.smtp.auth", "true");
properties.setProperty("mail.smtp.starttls.enable", "true");
properties.setProperty("mail.smtp.host", "smtp.gmail.com");
properties.setProperty("mail.smtp.port", "587");
properties.setProperty("mail.smtp.user", "toqa khaled");
properties.setProperty("mail.smtp.password", "miapxdmqmwkensuo");
        
        String myEmail = "tkma3699@gmail.com";
        String pass = "miapxdmqmwkensuo";
        
        Session session = Session.getDefaultInstance(prop,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail,pass); //To change body of generated methods, choose Tools | Templates.
                    
                }  
        });
        
        Message message = prepareMessage(session,myEmail,recipt);
        Transport.send(message);
       
        System.out.println("success!");
        
     
         
    }

    private static Message prepareMessage(Session session, String myEmail,String recipt) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipt));
            message.setSubject("Automated Mail");
            message.setText("Hello Esraa,\n Ezaik w Ezai Omk!");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    

   
      
}
