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
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author Matija
 */
public class UserDao implements UserDaoInterface{

    @Override
    public User login(String username, String password) {
        
        Connection conn = DB.getInstance().getConnection();
        if(conn == null){
            System.err.println("*** Neuspesna konekcija na bazu podataka! ***");
            return null;
        }
        User u = new User();
        
        try(PreparedStatement stm = conn.prepareStatement("select * from users where username = ?")){
            
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            
            if(rs.next()){
                u.setUsername(rs.getString("username"));
                if(rs.getString("password").equals(password)){
                    u.setPassword(rs.getString("password"));
                    u.setLastName(rs.getString("last_name"));
                    u.setFirstName(rs.getString("first_name"));
                    u.setIsAdmin(rs.getBoolean("is_admin"));
                } else{
                    u.setPassword(null);
                }                
            } else{
                u.setUsername(null);
            } 
           
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                DB.getInstance().putConnection(conn);
        }
        return u;
    }
    
}
