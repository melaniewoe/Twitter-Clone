package mytwitter.data;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mytwitter.business.Twit;
import mytwitter.data.ConnectionPool;
import mytwitter.data.DBUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author macbookair
 */
public class TwitDB {
    public static int insert(Twit twit) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Twit (twitUser, emailAddress, twitDate, message) "
                + "VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, twit.get_twitUser());
            ps.setString(2, twit.getEmailAddress());
            ps.setString(3, twit.getTwitDate());
            ps.setString(4, twit.get_message());
           
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static Twit search (String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "Select * "
                + "from Twit where emailAddress = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            Twit twit = null;
            
            if (rs.next()) {
                twit = new Twit();
                twit.set_twitUser(rs.getString("twitUser"));
                twit.setEmailAddress(rs.getString("emailAddress"));
                twit.setTwitDate(rs.getString("twitDate"));
                twit.set_message(rs.getString("message"));
            }

            return twit;
            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static ArrayList<Twit> selectTwits()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Twit";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Twit> twit_list = new ArrayList<Twit>();
            
            Twit twit = null;
            
            while (rs.next()) {
                twit = new Twit();
                twit.settwitID(rs.getLong("twitID"));
                twit.set_twitUser(rs.getString("twitUser"));
                twit.setEmailAddress(rs.getString("emailAddress"));
                twit.setTwitDate(rs.getString("twitDate"));
                twit.set_message(rs.getString("message"));
                twit_list.add(twit);
            }
            return twit_list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static ArrayList<Twit> selectTwitsForProfile(String mentionUser)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Twit WHERE message LIKE ? OR twitUser = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + mentionUser);
            ps.setString(2, mentionUser);
            rs = ps.executeQuery();
            ArrayList<Twit> twit_list_for_profile = new ArrayList<Twit>();
            
            Twit twit = null;
            
            while (rs.next()) {
                twit = new Twit();
                twit.settwitID(rs.getLong("twitID"));
                twit.set_twitUser(rs.getString("twitUser"));
                twit.setEmailAddress(rs.getString("emailAddress"));
                twit.setTwitDate(rs.getString("twitDate"));
                twit.set_message(rs.getString("message"));
                twit_list_for_profile.add(twit);
            }
            return twit_list_for_profile;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static ArrayList<Twit> selectHashtagTwit(String hashtag)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Twit WHERE message LIKE ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + hashtag + "%");
            rs = ps.executeQuery();
            ArrayList<Twit> twit_list_for_hashtag = new ArrayList<Twit>();
            
            Twit twit = null;
            
            while (rs.next()) {
                twit = new Twit();
                twit.settwitID(rs.getLong("twitID"));
                twit.set_twitUser(rs.getString("twitUser"));
                twit.setEmailAddress(rs.getString("emailAddress"));
                twit.setTwitDate(rs.getString("twitDate"));
                twit.set_message(rs.getString("message"));
                twit_list_for_hashtag.add(twit);
            }
            return twit_list_for_hashtag;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static ArrayList<Twit> selectNotications(String user_name, String logout_date)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Twit WHERE message LIKE ? AND twitDate >= ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + user_name);
            ps.setString(2, logout_date);
            rs = ps.executeQuery();
            ArrayList<Twit> notification_list = new ArrayList<Twit>();
            
            Twit twit = null;
            
            while (rs.next()) {
                twit = new Twit();
                twit.settwitID(rs.getLong("twitID"));
                twit.set_twitUser(rs.getString("twitUser"));
                twit.setEmailAddress(rs.getString("emailAddress"));
                twit.setTwitDate(rs.getString("twitDate"));
                twit.set_message(rs.getString("message"));
                notification_list.add(twit);
            }
            return notification_list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static int deleteTwit(String full_name, long twitID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "DELETE FROM Twit WHERE twitID = ? AND twitUser = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, twitID);
            ps.setString(2, full_name);
             return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
