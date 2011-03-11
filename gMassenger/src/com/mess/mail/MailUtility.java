package com.mess.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.dao.MailDao;

public class MailUtility {
    
    public static final Logger log = Logger.getLogger(MailUtility.class.getName());
    private static final MailDao mailDao = new MailDao();
    private String body = "";
    
    public void processMail(HttpServletRequest req, HttpServletResponse resp){
        try {
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session, req.getInputStream());
            Address[] sender = message.getFrom();
            String senderName = ((InternetAddress)sender[0]).getPersonal();
            log.info("Email sender name : " + senderName);
            String senderAddress = ((InternetAddress)sender[0]).getAddress();
            log.info("Email sender address : " + senderAddress);
            String subject = message.getSubject();
            log.info("Got an email. Subject = " + subject);
            String contentType = message.getContentType();
            log.info("Email Content Type : " + contentType);
            printParts(message);
            Address[] allRecipients = message.getAllRecipients();
            String recieverAddress = ((InternetAddress)allRecipients[0]).getAddress();
            log.info("Recipient is " + recieverAddress);
            log.info("Body is " + body + ":");
            mailDao.logEmailDetails(senderName, senderAddress, subject, body, recieverAddress);
            
        } catch (Exception ex) {
            log.log(Level.WARNING, "Failure in receiving email : " + ex.getMessage());
        }
    }
    
    private void printParts(Part p) throws IOException, MessagingException {
        Object o = p.getContent();
        if (o instanceof String) {
            log.info("This is a String");
            log.info((String) o);
            body = body + (String)o;
            log.info("leaving string");
        } else if (o instanceof Multipart) {
            log.info("This is a Multipart");
            Multipart mp = (Multipart) o;
            int count = mp.getCount();
            for (int i = 0; i < count; i++) {
                printParts(mp.getBodyPart(i));
            }
            log.info("leaving multipart");
        } else if (o instanceof InputStream) {
            log.info("This is just an input stream");
            InputStream is = (InputStream) o;
            int c;
            while ((c = is.read()) != -1){
                System.out.write(c);
            }
            log.info("leaving inputstream");
        }
    }
}