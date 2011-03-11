package com.mess.servlet;

import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;

import com.googlecode.objectify.ObjectifyService;
import com.mess.dao.MailDao;
import com.mess.entities.EmailIds;
import com.mess.entities.MailLogDetails;

@SuppressWarnings("serial")
public class EnvSetupServlet extends HttpServlet {
    
    private static final Logger log = Logger.getLogger(EnvSetupServlet.class.getName());
    private static MailDao mailDao = new MailDao();
    
    public void init() {
        log.info("***************************************************************************************");
        log.info("                              EnvSetupServlet started.");
        log.info("***************************************************************************************");
        registerAllEntities();
        setDefaults();
        log.info("***************************************************************************************");
        log.info("                              EnvSetupServlet ended.");
        log.info("***************************************************************************************");
    }
    
    private void setDefaults() {
        log.info("Setting defaults.");
        log.info("Defaults set.");
    }

    private void registerAllEntities() {
        ObjectifyService.register(MailLogDetails.class);
        log.info("MailLogDetails class registered.");
        ObjectifyService.register(EmailIds.class);
        log.info("EmailIds class registered.");
    }
}