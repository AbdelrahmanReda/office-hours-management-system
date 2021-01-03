import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author toqa khaled
 */
public class sendGenerate {
    
    private static String USER_NAME = "esraasaeed556";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "rkottkeywqjubctq"; // GMail password
    private static String RECIPIENT = "tkma3699@gmail.com";
    
    public static void SendEmail(String Password,String email) {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { email }; // list of recipient email addresses
        String subject = "Java send mail example";
        String body = Password;

        Properties props = System.getProperties();
        String host = "pop.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
         //  props.put("mail.imap.ssl", "true");
        //props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //props.put("mail.smtp.EnableSSL.enable", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    public static char[] generatePassword(int length) {
      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
      String specialCharacters = "!@#$";
      String numbers = "1234567890";
      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
      Random random = new Random();
      char[] password = new char[length];

      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
      password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
      password[3] = numbers.charAt(random.nextInt(numbers.length()));
   
      for(int i = 4; i< length ; i++) {
         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
      }
      return password;
   }
    
     public static void SendEmailToStaff(String emailFrom ,String emailTo ,String emailSubject,String emailBody ,String password ) {
        String from = emailFrom;
        String pass = password;
        String[] to = { emailTo }; // list of recipient email addresses
        String subject =emailSubject ;
        String body = emailBody;

        Properties props = System.getProperties();
        String host = "pop.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
         //  props.put("mail.imap.ssl", "true");
        //props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //props.put("mail.smtp.EnableSSL.enable", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    
    
}
