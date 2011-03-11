package com.mess.dao;

import java.util.List;
import java.util.logging.Logger;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.mess.common.Utility;
import com.mess.entities.EmailIds;
import com.mess.entities.MailLogDetails;

public class MailDao {
    
    private static final Logger log       = Logger.getLogger(MailDao.class.getName());
    private Objectify           objectify = ObjectifyService.begin();
    private Utility             util      = new Utility();
    
    public void logEmailDetails(String senderName, String senderAddress, String subject, String messageBody,
            String recieverAddress) {
        log.info("messageBody " + messageBody);
        MailLogDetails mailLogDetails = new MailLogDetails(subject, messageBody, senderName, util.todayAsString(),
                senderAddress, recieverAddress);
        Key<MailLogDetails> insertResult = objectify.put(mailLogDetails);
        log.info("Email record inserted :: " + insertResult.getId());
    }
    
    public void createEmail(String emailIdName, String emailId, String note, String type){
        EmailIds emailIds = new EmailIds(emailIdName, emailId, note, type);
        Key<EmailIds> insertResult = objectify.put(emailIds);
        log.info("Email record inserted :: " + insertResult.getId());
    }
    
    public List<EmailIds> getAllAvailableEmailId(){
        List<EmailIds> list = objectify.query(EmailIds.class).list();
        log.info("<EmailIds> List size is " + list.size());
        return list;
    }
    
    public List<EmailIds> getEmailIdByType(String type){
        Query<EmailIds> result = objectify.query(EmailIds.class).filter("type", type);
        log.info("<EmailIds> List size is " + result.list().size());
        return result.list();
    }
}