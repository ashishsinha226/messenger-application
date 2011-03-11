package com.mess.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.dao.MailDao;
import com.mess.entities.EmailIds;

@SuppressWarnings("serial")
public class DefaultCronJobServlet extends HttpServlet {
    
    public static final Logger log = Logger.getLogger(DefaultCronJobServlet.class.getName());
    private static MailDao mailDao = new MailDao();
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Into email cron job"); 
        setDefaultEmailIds();        
    } 
    
    private void setDefaultEmailIds() {
        log.info("Into setDefaultEmailIds");
        List<EmailIds> allAvailableEmailId = mailDao.getAllAvailableEmailId();
        log.info("allAvailableEmailId " + allAvailableEmailId.size());
        if((allAvailableEmailId == null) || (allAvailableEmailId.size() < 1)){
            mailDao.createEmail("admin", "admin@messenger-app.appspotmail.com", "", "admin");
            mailDao.createEmail("webmaster", "webmaster@messenger-app.appspotmail.com", "", "webmaster");
            mailDao.createEmail("no-reply", "no-reply@messenger-app.appspotmail.com", "", "no-reply");
            mailDao.createEmail("spam", "spam@messenger-app.appspotmail.com", "", "spam");
            mailDao.createEmail("test", "test@messenger-app.appspotmail.com", "", "test");
        }
    }
}