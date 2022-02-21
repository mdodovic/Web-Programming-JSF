/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Game;
import entities.GameGift;
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
public class GameGiftsDao {
    
    public void addParticipants(Game newGame, List<User> usersThatParticipate, User[] secondColumn){
        
        for(int i = 0; i < usersThatParticipate.size(); i++){
        
            Connection conn = DB.getInstance().getConnection();

            try(PreparedStatement ps = conn.prepareStatement("insert into igra_pokloni (idcustomer, idreceiver, id_game) values(?,?,?)")){

                ps.setString(1, usersThatParticipate.get(i).getUsername());
                ps.setString(2, secondColumn[i].getUsername());
                ps.setInt(3, newGame.getIdGame());

                ps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                DB.getInstance().putConnection(conn);
            }
        }
        
    }
    
    public GameGift getReceiver(int idGame, String username){
        Connection conn = DB.getInstance().getConnection();
        
        GameGift gg = new GameGift();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from igra_pokloni where id_game = ? and idcustomer = ?")){

            ps.setInt(1, idGame);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();
            System.out.println("Selected !");
            if(rs.next()){
                gg.setId(rs.getInt("idGift"));
                gg.setIdGame(idGame);
                gg.setSelectedGift(rs.getInt("selected_gift"));
                gg.setUsernameCustomer(username);
                gg.setUsernameReceiver(rs.getString("idreceiver"));
                return gg;
                
            } else{
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return null;
        
    }
    
    public void setGiftFromInGame(int idArt, String username, int idGame){
        
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("update igra_pokloni set selected_gift = ? where id_game = ? and idcustomer = ?")){
            ps.setInt(1, idArt);
            ps.setInt(2, idGame);
            ps.setString(3, username);
            ps.executeUpdate();
            System.out.println("Updated ");
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
    public int getMyGiftReceiver(int idGame, String username){
        Connection conn = DB.getInstance().getConnection();
        
        GameGift gg = new GameGift();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from igra_pokloni where id_game = ? and idreceiver = ?")){

            ps.setInt(1, idGame);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();
            System.out.println("Selected !");
            if(rs.next()){

                return rs.getInt("selected_gift");
                
            } else{
                return 0;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return 0;
        
    }
    
}
