/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygmailserver;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
/**
 *
 * @author Sanjay
 */
public class SendMail 
{
    public SendMail(final String user,final String pass,String recipients,String subject,String message,String filepath,String filename) throws MessagingException
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
        
        Session session=Session.getInstance(props, auth);
        
        MimeMessage msg=new MimeMessage(session);
        msg.setHeader("Content-Type","text/html; charset=UTF-8");
        msg.setHeader("format","flowed");
        msg.setHeader("Content-Transfer-Encoding","8bit");
        
        msg.setFrom(new InternetAddress(user));
        //msg.setReplyTo(InternetAddress.parse(user));
        InternetAddress[] to=InternetAddress.parse(recipients);
        msg.setRecipients(Message.RecipientType.TO,to);
        msg.setSubject(subject,"UTF-8");
        msg.setSentDate(new Date());
        
        /* for message text */
        Multipart multipart=new MimeMultipart();
        
        BodyPart messagePart=new MimeBodyPart();
        messagePart.setText(message);
        
        multipart.addBodyPart(messagePart);
        
        /* attachement */
        BodyPart attachment=new MimeBodyPart();
        if(!filepath.equals(""))
        {
            DataSource source=new FileDataSource(filepath);
            DataHandler handler=new DataHandler(source);
            attachment.setDataHandler(handler);
            attachment.setFileName(filename);
            multipart.addBodyPart(attachment);
        }
        
        /*  image attach  */
       /* if(true)
        {
            attachment.setHeader("Content-ID","image_id");
            BodyPart imagebody=new MimeBodyPart();
            imagebody.setContent("<h3>Attached Image</h3><img src='cid:image_id' />","text/html");
            multipart.addBodyPart(imagebody);
        }*/
        
        msg.setContent(multipart);
        
        Transport.send(msg);
        
    }
}
