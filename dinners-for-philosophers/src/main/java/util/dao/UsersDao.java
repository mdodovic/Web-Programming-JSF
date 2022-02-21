/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Users;
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
public class UsersDao {
    
    public Users getUserByUsername(String username){
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from users where username=?")){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Users u = new Users();
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setPassword(rs.getString("password"));
                u.setType(rs.getString("type"));
                u.setUsername(username);
                
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return null;
    }
    
}
