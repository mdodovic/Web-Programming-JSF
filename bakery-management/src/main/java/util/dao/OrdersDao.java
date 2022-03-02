/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Orders;
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
public class OrdersDao {
    
    public List<Orders> fetchAllOrders(){
        Connection conn = DB.getInstance().getConnection();
        List<Orders> l = new ArrayList<>();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from narudzbine")){            
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Orders o = new Orders();
                o.setAmount(rs.getInt("kolicina"));
                o.setId(rs.getInt("id_nar"));
                o.setStatus(rs.getString("status"));
                o.setUsername(rs.getString("kupac"));
                l.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }

        return l;
    }
    
    public void accept(Orders o){
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("update narudzbine set status='prihvaceno' where kupac = ?")){
            
            ps.setString(1, o.getUsername());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }

    }
 
    public void addNewOrder(User u, int amount){
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("insert into narudzbine(kupac, kolicina, status) values(?,?,?)")){
            
            ps.setString(1, u.getUsername());
            ps.setInt(2, amount);
            ps.setString(3, "naruceno");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
}
