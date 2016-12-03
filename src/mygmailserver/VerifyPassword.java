/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygmailserver;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
/**
 *
 * @author Sanjay
 */
public class VerifyPassword 
{
    public VerifyPassword(final String user,final String pass) throws AuthenticationFailedException, MessagingException
    {
        
        Properties props=new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.debug","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        
        Authenticator auth=new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(user,pass);
            }
        };
        
        Session session=Session.getInstance(props,auth);
        
        Message msg=new MimeMessage(session);
        msg.setFrom(new InternetAddress(user));
        msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse("sanjaybankar12@gmail.com"));
        msg.setSubject("Password Verify");
        msg.setText("Checking...");
        msg.setSentDate(new Date());
        
        Transport.send(msg);
    }    
}
