/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Book;
import entities.Order;
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
public class BooksDao {
    
    public List<Book> fetchAllBooksFromDB(){
        Connection conn = DB.getInstance().getConnection();
        
        List<Book> allBooks = new ArrayList<>();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from knjige");
                ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                Book b = new Book();
                b.setAmount(rs.getInt("naStanju"));
                b.setAuthor(rs.getString("autor"));
                b.setIdK(rs.getInt("idK"));
                b.setImage(rs.getString("slika"));
                b.setNaslov(rs.getString("naslov"));
                b.setNumPages(rs.getInt("brStr"));
                b.setYear(rs.getInt("godina"));
                allBooks.add(b);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BooksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return allBooks;
    }
    
    public void buyBook(Book b, int amount){
        Connection conn = DB.getInstance().getConnection();

        try(PreparedStatement ps = conn.prepareStatement("update knjige set naStanju = naStanju - ? where idK = ?")){

            ps.setInt(1, amount);
            ps.setInt(2, b.getIdK());
     
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BooksDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        
    }
    
}
