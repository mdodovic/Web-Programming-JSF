/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author Matija
 */
public class UserDao {
    
    public User fetchAndTestUserByUsername(String username){
        Connection conn = DB.getInstance().getConnection();
        User u = null;
        try(PreparedStatement ps = conn.prepareStatement("select * from korisnik where username = ?")){
            
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                u = new User();
                u.setBirthday(rs.getDate("birthday").toLocalDate());
                u.setFirstName(rs.getString("firstname"));
                u.setLastName(rs.getString("lastname"));
                u.setIsAdmin(rs.getBoolean("isAdmin"));
                u.setPassword(rs.getString("password"));
                u.setPlayCurrentGame(rs.getBoolean("playCurrentGame"));
                u.setUsername(username);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return u;
    }
    
    public List<User> fetchAllTeachers(){
        Connection conn = DB.getInstance().getConnection();
            
        List<User> allTeachers = new ArrayList<>();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from korisnik where isAdmin != 1");
            
            ResultSet rs = ps.executeQuery();){
            
            while(rs.next()){
                User u = new User();
                u.setBirthday(rs.getDate("birthday").toLocalDate());
                u.setFirstName(rs.getString("firstname"));
                u.setLastName(rs.getString("lastname"));
                u.setIsAdmin(rs.getBoolean("isAdmin"));
                u.setPassword(rs.getString("password"));
                u.setPlayCurrentGame(rs.getBoolean("playCurrentGame"));
                u.setUsername(rs.getString("username"));

                allTeachers.add(u);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return allTeachers;
    }

    public void addTeacherToTheGame(String username){
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("update korisnik set playCurrentGame = '1' where username = ?")){
            
            ps.setString(1, username);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }

    }
    public void addTeacherToTheGame(String username, boolean choice){
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("update korisnik set playCurrentGame = ? where username = ?")){
            
            ps.setString(2, username);
            ps.setBoolean(1, choice);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }

    }
    
}
