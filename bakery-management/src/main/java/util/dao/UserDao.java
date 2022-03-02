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
public class UserDao {
    
    public User fetchUserByUsername(String username){
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from korisnici where korisnicko_ime = ?")){
            
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                User u = new User();
                u.setBirthday(rs.getDate("datum_rodjenja"));
                u.setFirstname(rs.getString("ime"));
                u.setLastname(rs.getString("prezime"));
                u.setPassword(rs.getString("lozinka"));
                u.setType(rs.getString("tip"));
                u.setUsername(rs.getString("korisnicko_ime"));
                return u;
            }
            else{
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return null;
    }
    
}
