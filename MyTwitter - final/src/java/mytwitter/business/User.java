/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytwitter.business;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @javabean for User Entity
 */
public class User implements Serializable {
    //define attributes fullname, ...
    
    //define set/get methods for all attributes.
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
    private String DateofBirth;
    private String securityQuestion;
    private String profilePicture;
    private int twitNumber;
    private String logoutTime;
    private int followingNumber;
    private int followerNumber;
    
    public User()
    {
        fullName = "";
        email = "";
        password = "";
        confirmPassword = "";
        DateofBirth = "";
        securityQuestion = "";
        profilePicture = "";
    }
    public User(String fullName, String email, String password, String DateofBirth, String securityQuestion, int twitNumber, String logoutTime)
    {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.DateofBirth = DateofBirth ;
        this.securityQuestion = securityQuestion;
        this.twitNumber = twitNumber;
        this.logoutTime = logoutTime;
    }
    public User(String fromString)
    {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.DateofBirth = DateofBirth;
        this.securityQuestion = securityQuestion;
        this.profilePicture = profilePicture;
        this.twitNumber = twitNumber;
        this.logoutTime = logoutTime;
    }
    
    public String getFullName()
    {
        return this.fullName;
    }
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getconfirmPassword()
    {
        return this.confirmPassword;
    }
    public void setconfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }
    public String getDateofBirth() {
        return this.DateofBirth;
    }

    public void setDateofBirth(String DateofBirth) {
        this.DateofBirth = DateofBirth;
    }
    public String getsecurityQuestion()
    {
        return this.securityQuestion;
    }
    public void setsecurityQuestion(String securityQuestion)
    {
        this.securityQuestion = securityQuestion;
    }
    public String getprofilePicture()
    {
        return profilePicture;
    }
    public void setprofilePicture(String profilePicture)
    {
        this.profilePicture = profilePicture;
    }
    public int getTwitNumber()
    {
        return this.twitNumber;
    }
    public void setTwitNumber(int twitNumber)
    {
        this.twitNumber = twitNumber;
    }
    public String getlogoutTime()
    {
        return logoutTime;
    }
    public void setlogoutTime(String logoutTime)
    {
        this.logoutTime = logoutTime;
    }
   
     public int getFollowingNumber()
    {
        return this.followingNumber;
    }
    public void setFollowingNumber(int followingNumber)
    {
        this.followingNumber = followingNumber;
    }
    public int getFollowerNumber()
    {
        return this.followerNumber;
    }
    public void setFollowerNumber(int followerNumber)
    {
        this.followerNumber = followerNumber;
    }
    
}
