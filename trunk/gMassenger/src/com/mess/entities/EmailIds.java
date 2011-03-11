package com.mess.entities;

import java.io.Serializable;

import javax.persistence.Id;

public class EmailIds implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    Long                      emailUniqueId;
    private String            emailIdName;
    private String            emailId;
    private String            note;
    private String            type;
    private boolean           isActive;
    
    public EmailIds(String emailIdName, String emailId, String note, String type) {
        this.emailIdName = emailIdName;
        this.emailId = emailId;
        this.note = note;
        this.type = type;
        this.isActive = true;
    }
    
    public EmailIds() {
    }
    
    public Long getEmailUniqueId() {
        return emailUniqueId;
    }
    
    public void setEmailUniqueId(Long emailUniqueId) {
        this.emailUniqueId = emailUniqueId;
    }
    
    public String getEmailId() {
        return emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    public String getEmailIdName() {
        return emailIdName;
    }
    
    public void setEmailIdName(String emailIdName) {
        this.emailIdName = emailIdName;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
}