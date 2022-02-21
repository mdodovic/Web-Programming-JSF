/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Events;
import entities.Users;
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
public class EventDao {
    
    
    public  List<Events> fetchAllActive(){
        List<Events> allActiveEvents = new ArrayList<>();
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("select * from events where active = 1")){

            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Events u = new Events();
                u.setActive(rs.getShort("active"));
                u.setId(rs.getInt("id"));
                u.setMany(rs.getShort("many"));
                u.setName(rs.getString("name"));
                u.setOption1(rs.getString("option1"));
                u.setOption2(rs.getString("option2"));
                u.setOption3(rs.getString("option3"));
                u.setOption4(rs.getString("option4"));
                allActiveEvents.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return allActiveEvents;
    }
    
    public  List<Events> fetchAllInactive(){
        List<Events> allActiveEvents = new ArrayList<>();
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("select * from events where active = 0")){

            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Events u = new Events();
                u.setActive(rs.getShort("active"));
                u.setId(rs.getInt("id"));
                u.setMany(rs.getShort("many"));
                u.setName(rs.getString("name"));
                u.setOption1(rs.getString("option1"));
                u.setOption2(rs.getString("option2"));
                u.setOption3(rs.getString("option3"));
                u.setOption4(rs.getString("option4"));
                allActiveEvents.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return allActiveEvents;
    }
    
    public void changeEvent(Events e){
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("update events set active=? where id=?")){
            ps.setInt(1, 1 - e.getActive());
            ps.setInt(2, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
}
