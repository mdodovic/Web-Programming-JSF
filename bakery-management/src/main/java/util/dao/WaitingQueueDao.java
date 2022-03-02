/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.User;
import entities.WaitingQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author Matija
 */
public class WaitingQueueDao {
    
    public void addUser(User u){
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("insert into redcekanja(kupac) values(?)")){
            
            ps.setString(1, u.getUsername());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    public void removeUser(User u){
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("delete from redcekanja where kupac = ?")){
            
            ps.setString(1, u.getUsername());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    

    public List<WaitingQueue> fetchAllUsers(){
        Connection conn = DB.getInstance().getConnection();
        List<WaitingQueue> l = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement("select * from redcekanja join korisnici on(redcekanja.kupac = korisnici.korisnicko_ime)")){            
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                WaitingQueue w = new WaitingQueue();
                w.setBirthday(rs.getDate("datum_rodjenja"));
                w.setId(rs.getInt("id_red"));
                w.setUsername(rs.getString("kupac"));
                l.add(w);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        l.sort((o1, o2) -> {
            Date birthday1 = o1.getBirthday();
            Date birthday2 = o2.getBirthday();
            if(birthday1.after(birthday2))
                return 1;
            if(birthday2.after(birthday1))
                return -1;            
            return 0; //To change body of generated lambdas, choose Tools | Templates.
        });
        return l;
    }

    
}
