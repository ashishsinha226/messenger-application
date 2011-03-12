package com.mess.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.common.Utility;
import com.mess.dao.MailDao;
import com.mess.entities.EmailIds;

@SuppressWarnings("serial")
public class SpamCronJobServlet extends HttpServlet {
    
    public static final Logger log     = Logger.getLogger(SpamCronJobServlet.class.getName());
    private static MailDao     mailDao = new MailDao();
    private Utility            util    = new Utility();
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Into email cron job");
        sendSpamMessage();
    }
    
    private void sendSpamMessage() {
        try {
            List<EmailIds> emailIdByType = mailDao.getEmailIdByType("spam");
            //String senderEmailId = mailDao.getEmailIdByType("test").get(0).getEmailId();
            String senderEmailId = "thoughts.ashish@gmail.com";
            EmailIds spamTarget = emailIdByType.get(0);
            String recipientEmailId = spamTarget.getEmailId();
            log.info("Recipient email id is " + recipientEmailId);
            log.info("Sender email id is " + senderEmailId);
            String subject = "Mondu masala";
            String body = "Awara ho tum";
            String recipientName = "Manisha Sinha";
            String senderName = "Admin";
            if (spamTarget.isActive()) {
                Properties props = new Properties();
                Session session = Session.getDefaultInstance(props, null);
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(senderEmailId, senderName));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmailId, recipientName));
                msg.setSubject(subject);
                msg.setText(body);
                Transport.send(msg);
                log.info("mail send.");
            }
        } catch (Exception e) {
            log.info("Error occured while sending mail.");
            util.printExceptionStackTrace(e);
        }
    }
}