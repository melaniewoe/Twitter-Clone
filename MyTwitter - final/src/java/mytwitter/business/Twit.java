/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytwitter.business;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author melaniewoe
 */
public class Twit {
    private long twitID;
    private String twitUser;
    private String emailAddress;
    private String twitDate;
    private String message;
   
    public Twit() {
        twitUser = "";
        emailAddress = "";
        twitDate = "";
        message = "";   
    }
    
    public Twit(String fromString)
    {
        String[] data = fromString.replace("[", "").split(",");
        this.set_twitUser(data[0]);
        this.setEmailAddress(data[1]);
        this.setTwitDate(data[2]);
        this.set_message(data[3]);
        
    }
    public long gettwitID() {
        return twitID;
    }

    public void settwitID(long twitID) {
        this.twitID = twitID;
    }
    
    public String get_twitUser()
    {
        return this.twitUser;
    }
    public void set_twitUser(String twitUser)
    {
        this.twitUser = twitUser;
    }  

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTwitDate() {
        return twitDate;
    }

    public void setTwitDate(String twitDate) {
        this.twitDate = twitDate;
    }
    
    public String get_message()
    {
        return this.message;
    }
    public void set_message(String message)
    {
        this.message = message;
    }
    
    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("[%s,%s,%s,%s]", this.get_twitUser(), this.getEmailAddress(), this.getTwitDate(), this.get_message()));
      return sb.toString();
    }

}
