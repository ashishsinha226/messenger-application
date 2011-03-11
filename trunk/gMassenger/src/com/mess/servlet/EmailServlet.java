package com.mess.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mess.mail.MailUtility;

@SuppressWarnings("serial")
public class EmailServlet extends HttpServlet {
    public static final Logger log = Logger.getLogger(EmailServlet.class.getName());
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Into email servlet");
        MailUtility mailUtility = new MailUtility();
        mailUtility.processMail(req, resp);
    } 
}