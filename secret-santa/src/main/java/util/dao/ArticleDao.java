/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Article;
import entities.Gift;
import entities.Shop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author Matija
 */
public class ArticleDao {
 
    public HashMap<String, Integer> returnAllShops(){
        HashMap<String, Integer> allShopNames = new HashMap<>();
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM tajanstveni2019.artikal join tajanstveni2019.prodavnica on(artikal.idShop = prodavnica.id);");
                ResultSet rs = ps.executeQuery();){
                
            while(rs.next()){
//                Shop s = new Shop();
//                s.setIdShop(rs.getInt("idShop"));
//                s.setShopName(rs.getString("shopName"));
//                s.setAddress(rs.getString("address"));
                allShopNames.put(rs.getString("shopName"), rs.getInt("idShop"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        
        return allShopNames;
    } 
    
    public List<Article> fetchAppropriateArticle(String articleName, int selectedShop){
        List<Article> allArticles = new ArrayList<>();
        Connection conn = DB.getInstance().getConnection();
        String querry = "SELECT * FROM tajanstveni2019.artikal join tajanstveni2019.prodavnica on(artikal.idShop = prodavnica.id) WHERE 1=1";
        if(!(articleName == null || articleName.equals(""))){
            querry += " and name LIKE ? ";
        }
        if(selectedShop != -1){
            querry += " and id = ?";
        }
        System.out.println(querry);
        try(PreparedStatement ps = conn.prepareStatement(querry)){
            int i = 1;
            if(!(articleName == null || articleName.equals(""))){
                ps.setString(i, "%" + articleName + "%");
                i++;
            }
            if(selectedShop != -1){
                ps.setInt(i, selectedShop);
                
            }
            ResultSet rs = ps.executeQuery();
                
            while(rs.next()){
                Article a = new Article();
                a.setAmount(rs.getInt("onStock"));
                a.setIdArt(rs.getInt("idArt"));
                a.setIdShop(selectedShop);
                a.setName(rs.getString("name"));
                a.setPrice(rs.getDouble("price"));
                allArticles.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        
        return allArticles;        
        
    }
    
    public void reduceAmountOfArticle(int idArt){
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("update artikal set onStock = onStock - 1 where idArt = ?")){
            ps.setInt(1, idArt);

            ps.executeUpdate();
            System.out.println("Reduced");
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    public Gift fetchDataForThisArticle(int idArt){
        Connection conn = DB.getInstance().getConnection();
        Gift g = new Gift();
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM tajanstveni2019.artikal join tajanstveni2019.prodavnica on(artikal.idShop = prodavnica.id) WHERE idArt=?")){
            ps.setInt(1, idArt);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                g.setGiftName(rs.getString("name"));
                g.setShopAddress(rs.getString("address"));
                g.setShopName(rs.getString("shopName"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return g;
    }
}
