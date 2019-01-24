/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytwitter.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mytwitter.business.Hashtag;

/**
 *
 * @author macbookair
 */
public class HashtagDB {
    public static int insert(String hashtag) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO Hashtag (hashtagText) "
                + "VALUES (?)";
        try {
            ps = connection.prepareStatement(query);
            
            ps.setString(1, hashtag);
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static boolean hashtagExists(String hashtag) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT hashtagText FROM Hashtag "
                + "WHERE hashtagText = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, hashtag);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Hashtag> selectHashtags()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT hashtagText, count(hashtagText) h FROM Hashtag GROUP BY hashtagText ORDER BY H DESC ";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Hashtag> hashtag_list = new ArrayList<Hashtag>();
            
            Hashtag hashtag = null;
            
            while (rs.next()) {
                hashtag = new Hashtag();
                hashtag.sethashtagText(rs.getString("hashtagText"));
                hashtag_list.add(hashtag);
            }
            return hashtag_list;
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
