package com.mess.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import com.mess.servlet.SpamCronJobServlet;

public class Utility {
    
    public static final Logger log = Logger.getLogger(Utility.class.getName());
    
    public String todayAsString(){
        Date today = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        return df.format(today);
    }   
    
    public void printExceptionStackTrace(Exception ex) {
        StringWriter writerStr = new StringWriter();
        PrintWriter myPrinter = new PrintWriter(writerStr);
        ex.printStackTrace(myPrinter);
        String stackTraceStr = writerStr.toString();
        log.info("Following is the stacktrace of the error \n" + stackTraceStr);
    }
}