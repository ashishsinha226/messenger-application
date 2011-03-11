package com.mess.entities;

import java.io.Serializable;

import javax.persistence.Id;

public class MailLogDetails implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    Long                      mailId;
    private String            subject;
    private String            mailBody;
    private String            senderName;
    private String            senderAddress;
    private String            recieverAddress;
    private String            date;
    
    public MailLogDetails(String subject, String mailBody, String sender, String date, String senderAddress, String recieverAddress) {
        this.subject = subject;
        this.mailBody = mailBody;
        this.senderName = sender;
        this.date = date;
        this.senderAddress = senderAddress;
        this.recieverAddress = recieverAddress;
    }
    
    public String getRecieverAddress() {
        return recieverAddress;
    }

    public void setRecieverAddress(String recieverAddress) {
        this.recieverAddress = recieverAddress;
    }

    public MailLogDetails() {
    }
    
    public Long getMailId() {
        return mailId;
    }
    
    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getMailBody() {
        return mailBody;
    }
    
    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }
}