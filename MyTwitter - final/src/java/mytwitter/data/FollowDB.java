/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytwitter.data;

/**
 *
 * @author macbookair
 */

import java.sql.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import mytwitter.business.Follow;
import mytwitter.business.User;

public class FollowDB {
    public static int insert(Follow follow) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Follow (userID, followedUserID, followDate) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, follow.getuserID());
            ps.setString(2, follow.getfollowedUser());
            ps.setString(3, follow.getfollowDate());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static Follow search (String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "Select * "
                + "from Follow where emailAddress = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            Follow follow = null;
            
            if (rs.next()) {
                follow = new Follow();
                follow.setuserID(rs.getString("userID"));
                follow.setfollowedUser(rs.getString("followedUserID"));
                follow.setfollowDate(rs.getString("followDate"));
            }

            return follow;
            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int delete(String userID, String followedUserID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "DELETE FROM Follow WHERE userID = ? AND followedUserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, followedUserID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Follow> selectFollow()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Follow ";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Follow> follow_list = new ArrayList<Follow>();
            
            Follow follow = null;
            
            while (rs.next()) {
                follow = new Follow();
                follow.setuserID(rs.getString("userID"));
                follow.setfollowedUser(rs.getString("followedUserID"));
                follow.setfollowDate(rs.getString("followDate"));
                follow_list.add(follow);
            }
            return follow_list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static ArrayList<Follow> selectFollowing(String userID)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Follow WHERE userID = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            rs = ps.executeQuery();
            ArrayList<Follow> follow_list = new ArrayList<Follow>();
            
            Follow follow = null;
            
            while (rs.next()) {
                follow = new Follow();
                follow.setuserID(rs.getString("userID"));
                follow.setfollowedUser(rs.getString("followedUserID"));
                follow.setfollowDate(rs.getString("followDate"));
                follow_list.add(follow);
            }
            return follow_list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    public static ArrayList<Follow> selectNotificationsFollow(String followedUserID, String logout_date)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Follow WHERE followedUserID = ? AND followDate >= ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, followedUserID);
            ps.setString(2, logout_date);
            rs = ps.executeQuery();
            ArrayList<Follow> notification_list_for_follow = new ArrayList<Follow>();
            
            Follow follow = null;
            
            while (rs.next()) {
                follow = new Follow();
                follow.setuserID(rs.getString("userID"));
                follow.setfollowedUser(rs.getString("followedUserID"));
                notification_list_for_follow.add(follow);
            }
            return notification_list_for_follow;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    //count total follow and followers
    public static User countFollow(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        User user =null;
        String query = "SELECT COUNT(*) AS count_following "
                + "FROM Follow WHERE userID = ?";
        String follow_total = "SELECT COUNT(*) AS count_follower "
                + "FROM Follow where followedUserID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next())
            {
                user = new User();
                user.setFollowingNumber(rs.getInt("count_following"));
                //System.out.println(rs.getInt("count_following"));
            }
            ps = connection.prepareStatement(follow_total);
            ps.setString(1, email);
            rs1 = ps.executeQuery();
            
            if(rs1.next()){
                
                user.setFollowerNumber(rs1.getInt("count_follower"));
                //System.out.println(rs1.getInt("count_follower"));
            }

            return user;
            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
