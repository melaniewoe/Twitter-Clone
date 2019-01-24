/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytwitter.business;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 *
 * @author macbookair
 */
public class Follow {
    private String userID;
    private String followedUser;
    private String followDate;
    private String follow;
   
    public Follow() {
        userID = "";
        followedUser = "";
        followDate = ""; 
    }
    
    public Follow(String fromString)
    {
        String[] data = fromString.replace("[", "").split(",");
        this.setuserID(data[0]);
        this.setfollowedUser(data[1]);
        this.setfollowDate(data[2]);
        
    }
    public String getuserID() {
        return userID;
    }

    public void setuserID(String userID) {
        this.userID = userID;
    }
    
    public String getfollowedUser()
    {
        return this.followedUser;
    }
    public void setfollowedUser(String followedUser)
    {
        this.followedUser = followedUser;
    }  

    public String getfollowDate() {
        return followDate;
    }

    public void setfollowDate(String followDate) {
        this.followDate = followDate;
    }
    
    public String getfollow() {
        return followDate;
    }

    public void setfollow(String follow) {
        this.follow = follow;
    }

    
    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("[%s,%s,%s]", this.getuserID(), this.getfollowedUser(), this.getfollowDate()));
      return sb.toString();
    }
}
