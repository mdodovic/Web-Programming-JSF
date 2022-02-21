/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Book;
import entities.BuyCollection;
import entities.Order;
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
import util.SessionUtils;

/**
 *
 * @author Matija
 */
public class OrderDao {
     
    public List<Order> fetchAllOrders(){
        Connection conn = DB.getInstance().getConnection();
        
        List<Order> allOrders = new ArrayList<>();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from narudzbine");
                ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                Order o = new Order();
                
                o.setAmount(rs.getInt("kolicina"));
                o.setIdBook(rs.getInt("knjiga"));
                o.setIdCustomer(rs.getInt("kupac"));
                o.setStatus(rs.getString("status"));
                o.setIdN(rs.getInt("idN"));
                allOrders.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BooksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return allOrders;

    }

    public List<BuyCollection> fetchAllBuy(){
        Connection conn = DB.getInstance().getConnection();
        
        List<BuyCollection> allOrders = new ArrayList<>();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from narudzbine join korisnici on(narudzbine.kupac = korisnici.idK) join knjige on(narudzbine.knjiga = knjige.idK)");
                ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                BuyCollection b = new BuyCollection();
                
                b.setAmount(rs.getInt("kolicina"));
                b.setBookName(rs.getString("naslov"));
                b.setCustomerName(rs.getString("ime"));
                b.setStatus(rs.getString("status"));
                b.setIdN(rs.getInt("idN"));
                allOrders.add(b);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BooksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return allOrders;

    }

    public void approve(BuyCollection b){
        Connection conn = DB.getInstance().getConnection();

        try(PreparedStatement ps = conn.prepareStatement("update narudzbine set status = 'prihvaceno' where idN = ?")){

            ps.setInt(1, b.getIdN());
     
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BooksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
    public void buyBook(Book b, User u, int amount){
        Connection conn = DB.getInstance().getConnection();

        List<Order> allOrders = this.fetchAllOrders();
        int idN = 0;
        for(Order o: allOrders){
            if(o.getIdN() > idN){
                idN = o.getIdN();
            }
        }
        idN++;
        try(PreparedStatement ps = conn.prepareStatement("insert into narudzbine(idN, kupac,knjiga,kolicina,status) values(?,?,?,?,?)")){
            ps.setInt(1, idN);
            ps.setInt(2, u.getIdK());
            ps.setInt(3, b.getIdK());
            ps.setInt(4, amount);
            ps.setString(5, "naruceno");
     
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BooksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
}
