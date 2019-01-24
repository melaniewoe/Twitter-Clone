/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytwitter.controller;

import mytwitter.business.User;
import mytwitter.data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mytwitter.business.Follow;
import mytwitter.business.Hashtag;
import mytwitter.business.Twit;
import mytwitter.data.FollowDB;
import mytwitter.data.HashtagDB;
import mytwitter.data.TwitDB;
import mytwitter.utils.MailUtilGmail;
import mytwitter.utils.PasswordUtil;
import mytwitter.utils.util;

/**
 *
 * @author xl
 */
@WebServlet(name = "membershipServlet", urlPatterns = {"/membership"})
public class membershipServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "home";  // default action
        }
        
        // perform action and set URL to appropriate page
        String url = "/login.jsp";
        if (action.equals("home_page")) {
            url ="/home.jsp";
        } else if (action.equals("login")) {  
            url = "/login.jsp";
        } else if (action.equals("signup")) {
            url= "/signup.jsp";
        } else if (action.equals("signout")) {
            url = logout(request, response);
        } else if (action.equals("follow")) {
            url = follow(request, response);
        } else if (action.equals("deleteTwit")) {
            url = deleteTwit(request, response);
        }

        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext sc = this.getServletContext();
        
        // get current action
        String action = request.getParameter("action");
        
        String post_tweet1 = request.getParameter("post_tweet");
        String url = "/home.jsp";
        if(action.equals("post_tweet"))
        {
            url = postTweet(request, response);
        }
       
        if(action.equals("signup"))
        {
            url = signup(request, response);
        }
        
        if(action.equals("login"))
        {
            url = login(request, response);
        }
        
        if(action.equals("gotosignup"))
        {
            url = "/signup.jsp";
        }
        
        if(action.equals("deleteTwit"))
        {
            url = deleteTwit(request,response);
        }
        
        if(action.equals("forgetPassword"))
        {
            url = forgetPassword(request,response);
        }
        
        if(action.equals("myProfile"))
        {
            url = myProfile(request,response);
        }
        
        if(action.equals("update_profile"))
        {
            url = updateProfile(request,response);
        }
        
        if(action.equals("follow"))
        {
            url = follow(request, response);
        }
        
        if(action.equals("unfollow"))
        {
            url = unfollow(request, response);
        }
        
        if(action.equals("find_hashtag"))
        {
            url = find_hashtag(request, response);
        }
       
        manage(request, response);   
        // forward request to JSP
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    //post new tweet
    private String postTweet(HttpServletRequest request,
            HttpServletResponse response) throws IOException{
        String url = null;
        TwitDB twitDB = new TwitDB();
        Twit twit = new Twit();
        try{
            HttpSession session = request.getSession();
            String message = request.getParameter("twit_caption");
            String postTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            
            twit.set_twitUser((String)session.getAttribute("fullName"));
            twit.setEmailAddress((String)session.getAttribute("email"));
            twit.setTwitDate(postTime);
            twit.set_message(message);
            
            if(twitDB.insert(twit)>0)
            {
                System.out.println("Tweet Posted");
                url = "/home.jsp";
            }
            else{
                System.out.println("Failed");
            }
            
            session.setAttribute("twit", twit);
            /*
            ArrayList<Twit> twit_list = new ArrayList<Twit>();
            twit_list = twitDB.selectTwits();
            request.getSession().setAttribute("twit_list", twit_list); 
            */
            ArrayList<Twit> twit_list_for_profile = new ArrayList<Twit>();
            twit_list_for_profile = twitDB.selectTwitsForProfile((String)session.getAttribute("fullName"));
            request.getSession().setAttribute("twit_list_for_profile", twit_list_for_profile); 
            
            //find hashtag   
            String[] words = message.split(" "); 
            List<String> hashtag_list = new ArrayList<String>();

            for ( String word : words) {
                if (word.substring(0, 1).equals("#")) {
                    hashtag_list.add(word);    
                }
            }
            for (int i = 0; i < hashtag_list.size(); i++)
            {
                if(HashtagDB.insert(hashtag_list.get(i))>0)
                {
                    return url;
                }
            }
            
        }finally{
            System.out.println("Twit update");
        }
             
        return url;
    }
    
    //my profile
    //post new tweet
    private String myProfile(HttpServletRequest request,
            HttpServletResponse response) throws IOException{
        TwitDB twitDB = new TwitDB();
        Twit twit = new Twit();
        try{
            HttpSession session = request.getSession();
            
            session.setAttribute("twit", twit);
            
            ArrayList<Twit> twit_list_for_profile = new ArrayList<Twit>();
            twit_list_for_profile = twitDB.selectTwitsForProfile((String)session.getAttribute("fullName"));
            request.getSession().setAttribute("twit_list_for_profile", twit_list_for_profile); 
        }finally{
            System.out.println("Twit update for profile");
        }
             
        return "/profile.jsp";
    }
    
    //for signup
    private String signup(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        String url = "";
        request.getSession().setAttribute("email_exists", false);
        
        //get parameters from request
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String DateofBirth = request.getParameter("DateofBirth");
            String securityQuestion = request.getParameter("securityQuestion");
            
        // hash and salt password
        String hashedPassword;
        String salt = "";
        String saltedAndHashedPassword;
        try {
            hashedPassword = PasswordUtil.hashPassword(password);
            salt = PasswordUtil.getSalt();
            saltedAndHashedPassword = PasswordUtil.hashAndSaltPassword(password);                    
            
        } catch (NoSuchAlgorithmException ex) {
            hashedPassword = ex.getMessage();
            saltedAndHashedPassword = ex.getMessage();
        }
        
        
        // store data in User object
        User user = new User(fullName, email, password,DateofBirth,securityQuestion, 0, null);
        request.setAttribute("user",user);
        request.setAttribute("hashedPassword", hashedPassword);
        request.setAttribute("salt", salt);
        request.setAttribute("saltedAndHashedPassword", saltedAndHashedPassword);
        
        if(UserDB.emailExists(email))
        {
            System.out.println("failed !!!");
            request.getSession().setAttribute("email_exists", true);
            return "/signup.jsp";
        }
        if(UserDB.insert(user) > 0)
        {
            url = "/home.jsp";
        }
        else 
        {
            url = "/signup.jsp";
        }
            
        //request.setAttribute("message", message);
            
        return url;
    }
    
    private String login(HttpServletRequest request,
        HttpServletResponse response) throws IOException, ServletException {
        request.getSession().setAttribute("log_in_error", false);
        String url = "";
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        
        HttpSession session = request.getSession();
        
        if (rememberMe != null)
                {
                    Cookie c = new Cookie("email_cookie", email);
                    c.setMaxAge(365 * 24 * 60 * 60); // one year
                    response.addCookie(c);
                    
                    Cookie c1 = new Cookie("pass_cookie", pass);
                    c.setMaxAge(365 * 24 * 60 * 60); // one year
                    response.addCookie(c1);
                }
                else if(rememberMe == null)
                {
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null)
                    for (Cookie cookie : cookies) {
                        cookie.setValue("");
                        cookie.setPath("/");
                        cookie.setMaxAge(-1);
                        response.addCookie(cookie);
                    } 
                }

        //User user = new User();
        
        //UserDB DB = new UserDB();
        User user = UserDB.search(email);
        if(UserDB.search(email) == null)
        {
            //not found
            url = "/login.jsp";
        } 
        else
        {
            //if found account and matching password 
            if (user.getPassword().equals(pass))
            {
                
                login_update(request, response, email, user);
               
                
                url = "/home.jsp";
            }
            // if fail to verify.... -> set lag...
            else{
                request.getSession().setAttribute("log_in_error", true);
                url = "/login.jsp";
            } 
        }  
        return url;
    }

    public void login_update(HttpServletRequest request, 
        HttpServletResponse response, String email, User user)
    {
        UserDB userDB = new UserDB();
        TwitDB twitDB = new TwitDB();
        
        User userInfo = new User();
        userInfo = userDB.selectUser(user.getEmail());
        userInfo.setPassword(user.getPassword());
        
        HttpSession session = request.getSession();
        session.setAttribute("user", userInfo);
        
        
        // create 2 cookies as email and full name 
        Cookie c = new Cookie("full_name_cookie", userInfo.getFullName().replace(" ", "_"));
        c.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
        c.setPath("/");                      // allow entire app to access it
        response.addCookie(c);

        Cookie c1 = new Cookie("email_cookie", userInfo.getEmail());
        c1.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
        c1.setPath("/");                      // allow entire app to access it
        response.addCookie(c1);
        
        //get list of hashtag
        ArrayList<Hashtag> hashtag_list = new ArrayList<Hashtag>();
        hashtag_list = HashtagDB.selectHashtags();
        request.getSession().setAttribute("hashtag_list", hashtag_list);
        
        //get list of users
        ArrayList<User> user_list = new ArrayList<User>();
        user_list = userDB.selectAll(user.getEmail());
        request.getSession().setAttribute("user_list", user_list);
        
        request.getSession().setAttribute("login", true);
        request.getSession().setAttribute("fullName", userInfo.getFullName());
        request.getSession().setAttribute("email", userInfo.getEmail());
        request.getSession().setAttribute("twitNumber", userInfo.getTwitNumber());
        request.getSession().setAttribute("DateofBirth", userInfo.getDateofBirth());
        request.getSession().setAttribute("logoutTime", userInfo.getlogoutTime());
        
        ArrayList<Twit> twit_list_for_profile = new ArrayList<Twit>();
        twit_list_for_profile = twitDB.selectTwitsForProfile((String)session.getAttribute("fullName"));
        request.getSession().setAttribute("twit_list_for_profile", twit_list_for_profile);
        //System.out.println(twit_list_for_profile);
        ArrayList<Twit> notification_list = new ArrayList<Twit>();
        notification_list = twitDB.selectNotications((String)session.getAttribute("fullName"),(String)session.getAttribute("logoutTime"));
        request.getSession().setAttribute("notification_list", notification_list);
        
        ArrayList<Follow> notification_list_for_follow = new ArrayList<Follow>();
        notification_list_for_follow = FollowDB.selectNotificationsFollow((String)session.getAttribute("email"),(String)session.getAttribute("logoutTime"));
        request.getSession().setAttribute("notification_list_for_follow", notification_list_for_follow);
        
        ArrayList<Follow> follow_list = new ArrayList<Follow>();
        follow_list = FollowDB.selectFollowing((String)session.getAttribute("email"));
        request.getSession().setAttribute("follow_list", follow_list);
        
        //follow and followers 
        User user_follow_info = new User();
        user_follow_info = FollowDB.countFollow(user.getEmail());
        request.getSession().setAttribute("follower_number", user_follow_info.getFollowerNumber());
        request.getSession().setAttribute("following_number", user_follow_info.getFollowingNumber());
        
        
        /*
        String is_follow = (String)session.getAttribute("followed");
        if(is_follow != "true"){
            request.getSession().setAttribute("followed", true);
        }
        if(is_follow != "false"){
            request.getSession().setAttribute("followed", false);
        }
        */
    }
    
    private String logout(HttpServletRequest request, 
            HttpServletResponse response){
        String url = "/login.jsp";
        
        //get the user through session
        HttpSession session = request.getSession();
        String email = (String)session.getAttribute("email");
        User user = UserDB.search(email);
        
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
      
        if(user != null)
        {
            user.setlogoutTime(time);
            UserDB.update_logout(user);
        }
        
        request.getSession().setAttribute("fullName", "");
        request.getSession().setAttribute("Email", " ");
        request.getSession().setAttribute("twitNumber", "");
        request.getSession().setAttribute("login", false);
         
        session.invalidate();  
        
        return url;
    }
    
    
    private String manage(HttpServletRequest request,
            HttpServletResponse response) {
        /*
        ArrayList<Twit> twit_list = new ArrayList<Twit>();
        twit_list = TwitDB.selectTwits();
        request.setAttribute("twit_list", twit_list);
        */
        HttpSession session = request.getSession();
        ArrayList<Twit> twit_list_for_profile = new ArrayList<Twit>();
        twit_list_for_profile = TwitDB.selectTwitsForProfile((String)session.getAttribute("fullName"));
        request.setAttribute("twit_list_for_profile", twit_list_for_profile);
        return "/home.jsp";
    }
    
    
    private String deleteTwit(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        //delete only permitted if user is the one who post it
       
        String message = request.getParameter("twit_caption");
        String postTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            
        String full_name = (String)session.getAttribute("fullName");
        long twitID = Long.parseLong(request.getParameter("twitID"));
       
        TwitDB.deleteTwit(full_name,twitID);
        
        return manage(request, response);
    }
    
    private String find_hashtag(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        //delete only permitted if user is the one who post it
       
        String hashtag = request.getParameter("hashtagText");
        
        ArrayList<Twit> twit_list_for_hashtag = new ArrayList<Twit>();
        twit_list_for_hashtag = TwitDB.selectHashtagTwit(hashtag);
        request.setAttribute("twit_list_for_hashtag", twit_list_for_hashtag);
        //System.out.println(twit_list_for_hashtag);
        return "/hashtag.jsp";
    }
    
    private String forgetPassword(HttpServletRequest request,
            HttpServletResponse response) {
        
        //HttpSession session = request.getSession();
		
	//get user's email
	String email = request.getParameter("forgetPasswordEmail");
        User user = UserDB.search(email);
        if(user != null)
        {
            //generate random password
            String character = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            int N = 8;
            String random_password = "";
            Random rnd = new Random();
            for (int i = 0; i < 8; i++) {
                char c = character.charAt(rnd.nextInt(26));
                random_password+=c;
            }
            
            //update userDB with new password
            user.setPassword(random_password);
            UserDB.update_password(user);
            
            //send email
            try {
                    String message = "Dear " + user.getFullName() + "\n";
                    message += "Here is your new password: " + user.getPassword();
                      
                    MailUtilGmail.sendMail(user.getEmail(), "testingpurpose499@gmail.com", "testingpurpose449", message, false);
                } catch (MessagingException ex) {
                    Logger.getLogger(membershipServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        //still need to add info on error
        else{
            System.out.println("email doesnt exist");
        }

        return "/newpasswordsent.jsp";
		
		
    }
    
    //update notification
    private String updateProfile(HttpServletRequest request,
            HttpServletResponse response) {
        
        HttpSession session = request.getSession();
		
	String fullName = (String)session.getAttribute("fullName");
        String email = (String)session.getAttribute("email");
        String password = (String)session.getAttribute("password");
        User user = UserDB.search(email);
	
        if(user!= null){
            //update userDB with new informations
            if(request.getParameter("email") != null)
            {
                user.setEmail(request.getParameter("email"));
            }
            /*
            if(request.getParameter("fullName") != null)
            {
                user.setFullName(request.getParameter("fullName"));
            }
            if(request.getParameter("password") != null)
            {
                user.setPassword(request.getParameter("password"));
            }
            
            System.out.println(user.getFullName());
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            */
            UserDB.update(user);
            System.out.println(user.getFullName());
        }
            
        
        //still need to add info on error
        else{
            System.out.println("email doesnt exist");
        }

        return "/profile.jsp";
		
		
    }
    
    //follow
    private String follow(HttpServletRequest request,
            HttpServletResponse response) throws IOException{
        String url = null;
        FollowDB followDB = new FollowDB();
        Follow follow = new Follow();
        try{
            HttpSession session = request.getSession();
            String followTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            
            follow.setuserID((String)session.getAttribute("email"));
            follow.setfollowedUser((String)request.getParameter("followedUser"));
            follow.setfollowDate(followTime);
            
            if(followDB.insert(follow)>0)
            {
                System.out.println("Followed");
                request.getSession().setAttribute("followed", true);
                url = "/home.jsp";
            }
            else{
                System.out.println("Follow Failed");
            }
            
            session.setAttribute("follow", follow);
            ArrayList<Follow> follow_list = new ArrayList<Follow>();
        follow_list = FollowDB.selectFollowing((String)session.getAttribute("email"));
        request.getSession().setAttribute("follow_list", follow_list);
            
        //follow and followers 
        User user_follow_info = new User();
        user_follow_info = FollowDB.countFollow((String)session.getAttribute("email"));
        request.getSession().setAttribute("follower_number", user_follow_info.getFollowerNumber());
        request.getSession().setAttribute("following_number", user_follow_info.getFollowingNumber());
        
        }finally{
            System.out.println("Twit update");
        }
             
        return url;
    }
    
    //unfollow
    private String unfollow(HttpServletRequest request,
            HttpServletResponse response) throws IOException{
        String url = null;
        FollowDB followDB = new FollowDB();
        Follow follow = new Follow();
        try{
            HttpSession session = request.getSession();
            
            follow.setuserID((String)session.getAttribute("email"));
            follow.setfollowedUser((String)request.getParameter("followedUser"));
            
            if(followDB.delete(follow.getuserID(), follow.getfollowedUser())>0)
            {
                System.out.println("unfollowed");
                request.getSession().setAttribute("followed", false);
                url = "/home.jsp";
            }
            else{
                System.out.println("unfollow Failed");
            }
            
            session.setAttribute("follow", follow);
            
            ArrayList<Follow> follow_list = new ArrayList<Follow>();
        follow_list = FollowDB.selectFollowing((String)session.getAttribute("email"));
        request.getSession().setAttribute("follow_list", follow_list);
            
        //follow and followers 
        User user_follow_info = new User();
        user_follow_info = FollowDB.countFollow((String)session.getAttribute("email"));
        request.getSession().setAttribute("follower_number", user_follow_info.getFollowerNumber());
        request.getSession().setAttribute("following_number", user_follow_info.getFollowingNumber());
            
        }finally{
            System.out.println("Twit update");
        }
             
        return url;
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
  
    /**
     * Extracts file name from HTTP header content-disposition
     */
    /*
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
*/
}
