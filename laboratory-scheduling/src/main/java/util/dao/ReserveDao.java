/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Reservation;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author Matija
 */
public class ReserveDao {
    
    public List<Reservation> getAllReservations(){
        
        Connection conn = DB.getInstance().getConnection();
        
        List<Reservation> allReservations = new ArrayList<>();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from rezervacija");
            ResultSet rs = ps.executeQuery();){
            
            while(rs.next()){
                Reservation r = new Reservation();
                r.setBegin(rs.getTimestamp("datumOd"));
                r.setEnd(rs.getTimestamp("datumDo"));
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("naziv"));
                r.setHalls(rs.getString("prostorije").split(","));
                allReservations.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return allReservations;
        
    }
    
    public void createReservation(Reservation r){
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("insert into rezervacija(naziv, datumOd, datumDo, prostorije) values(?,?,?,?)")){
            
            ps.setString(1, r.getName());
            ps.setTimestamp(2, new Timestamp(r.getBegin().getTime()));
            ps.setTimestamp(3, new Timestamp(r.getEnd().getTime()));
            String halls = r.getHalls()[0];
            for(int i = 1; i < r.getHalls().length; i++){
                halls += "," + r.getHalls()[i];
            }
            ps.setString(4, halls);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
    public void deleteReservation(Reservation r){
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("delete from rezervacija where id = ?")){
            
            ps.setInt(1, r.getId());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
}
