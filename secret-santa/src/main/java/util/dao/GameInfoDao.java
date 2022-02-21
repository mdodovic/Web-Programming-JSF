/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Game;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author Matija
 */
public class GameInfoDao {
    
    
    public void cancelCurrentGame(){
        
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("update igra_info set actual = 0 where actual = 1")){
                
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
    public Game addNewGame(){
        
        Game g = new Game();
        g.setActual(true);
        g.setStartDate(LocalDate.now());
        
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("insert into igra_info (startDate, actual) values(?,?)")){
            
            ps.setDate(1, java.sql.Date.valueOf(g.getStartDate()));
            ps.setBoolean(2, g.isActual());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        
        conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from igra_info where actual = 1")){
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                g.setIdGame(rs.getInt("idGame"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        
        return g;
    }
    

    public Game getActiveGame(){
        
        Connection conn = DB.getInstance().getConnection();
        Game g = new Game();
        try(PreparedStatement ps = conn.prepareStatement("select * from igra_info where actual = 1")){
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                g.setIdGame(rs.getInt("idGame"));
                g.setActual(true);
                g.setStartDate(rs.getDate("startDate").toLocalDate());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return g;
    }

}
