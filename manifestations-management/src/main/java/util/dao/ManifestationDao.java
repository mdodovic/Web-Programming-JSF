/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Manifestations;
import entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author Matija
 */
public class ManifestationDao implements ManifestationDaoInterface{

    @Override
    public List<Manifestations> getAllManifestations() {
        Connection conn = DB.getInstance().getConnection();
        if(conn == null){
            return null;
        }
        List<Manifestations> allManifestations = new ArrayList<>();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from manifestations");
                ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                Manifestations man = new Manifestations();
                
                man.setId(rs.getInt("id"));
                man.setName(rs.getString("name"));
                man.setDateFrom(rs.getDate("date_from").toLocalDate());
                man.setDateTo(rs.getDate("date_to").toLocalDate());
                man.setOrganizer(rs.getString("organizer"));
                man.setStatus(rs.getString("status"));                
                man.setType(rs.getString("type"));
                
                allManifestations.add(man);                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManifestationDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        
        return allManifestations;
        
    }
    
    
    @Override
    public List<Manifestations> getAllManifestationsOfUser(User u) {
        
        Connection conn = DB.getInstance().getConnection();
        if(conn == null){
            return null;
        }
        List<Manifestations> allManifestations = new ArrayList<>();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from manifestations where organizer = ?")){
            
            ps.setString(1, u.getUsername());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Manifestations man = new Manifestations();
                
                man.setId(rs.getInt("id"));
                man.setName(rs.getString("name"));
                man.setDateFrom(rs.getDate("date_from").toLocalDate());
                man.setDateTo(rs.getDate("date_to").toLocalDate());
                man.setOrganizer(rs.getString("organizer"));
                man.setStatus(rs.getString("status"));                
                man.setType(rs.getString("type"));
                
                allManifestations.add(man);                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManifestationDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        
        return allManifestations;
        
    }
    @Override
    public void insertNewManifestation(Manifestations m){
        Connection conn = DB.getInstance().getConnection();
        
        try(PreparedStatement ps = conn.prepareStatement("insert into manifestations (name,date_from,date_to,type,status,organizer) values (?,?,?,?,?,?)")){
            
            ps.setString(1, m.getName());
            ps.setDate(2, java.sql.Date.valueOf(m.getDateFrom()));
            ps.setDate(3, java.sql.Date.valueOf(m.getDateTo()));
            ps.setString(4, m.getType());
            ps.setString(5, m.getStatus());
            ps.setString(6, m.getOrganizer());

            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ManifestationDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
    
    @Override
    public void cancelManifestation(Manifestations m){
        Connection conn = DB.getInstance().getConnection();
        System.out.println(m.getId());
        try(PreparedStatement ps = conn.prepareStatement("update manifestations set status = 'otkazana' where id = ?")){
            
            ps.setInt(1, m.getId());

            System.out.println(ps.executeUpdate());
            
        } catch (SQLException ex) {
            Logger.getLogger(ManifestationDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
    @Override
    public List<Manifestations> getAllNewManifestations(){
        Connection conn = DB.getInstance().getConnection();

        List<Manifestations> allNewManifestations = new ArrayList<>();
        
        try(PreparedStatement ps = conn.prepareStatement("select * from manifestations where status = 'nova'")){
           
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Manifestations man = new Manifestations();
                
                man.setId(rs.getInt("id"));
                man.setName(rs.getString("name"));
                man.setDateFrom(rs.getDate("date_from").toLocalDate());
                man.setDateTo(rs.getDate("date_to").toLocalDate());
                man.setOrganizer(rs.getString("organizer"));
                man.setStatus(rs.getString("status"));                
                man.setType(rs.getString("type"));
                
                allNewManifestations.add(man);                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManifestationDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        
        return allNewManifestations;
  
    }

    @Override
    public void approveManifestation(Manifestations newMan) {
        Connection conn = DB.getInstance().getConnection();
        System.out.println(newMan.getId());
        try(PreparedStatement ps = conn.prepareStatement("update manifestations set status = 'odobrena' where id = ?")){
            
            ps.setInt(1, newMan.getId());

            System.out.println(ps.executeUpdate());
            
        } catch (SQLException ex) {
            Logger.getLogger(ManifestationDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }

    @Override
    public void disapproveManifestation(Manifestations newMan) {

        Connection conn = DB.getInstance().getConnection();
        System.out.println(newMan.getId());
        try(PreparedStatement ps = conn.prepareStatement("update manifestations set status = 'odbijena' where id = ?")){
            
            ps.setInt(1, newMan.getId());

            System.out.println(ps.executeUpdate());
            
        } catch (SQLException ex) {
            Logger.getLogger(ManifestationDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }        
    }

    @Override
    public List<Manifestations> fetchManifestations(String name, LocalDate dateFrom, LocalDate dateTo) {
        Connection conn = DB.getInstance().getConnection();
        
        List<Manifestations> allManifestations = new ArrayList<>();
        String querry = "select * from manifestations ";
        List<String> conditions = new ArrayList<>();
        if(name != null && !name.equals("")){
            conditions.add(" name = '" + name + "'");
        }
        if(dateFrom != null){
            conditions.add(" date_to > '" + java.sql.Date.valueOf(dateFrom) + "'");
        }        
        if(dateTo != null){
            conditions.add(" date_from < '" + java.sql.Date.valueOf(dateTo) + "'");
        }
        String whereCondition = "";
        if(conditions.size() > 0){
            whereCondition += "where ";
            whereCondition += conditions.remove(0);
            for(String con: conditions){
                whereCondition += " and " + con;
            }
        }
        System.out.println(querry + whereCondition);
        try(PreparedStatement ps = conn.prepareStatement(querry + whereCondition)){
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Manifestations man = new Manifestations();
                
                man.setId(rs.getInt("id"));
                man.setName(rs.getString("name"));
                man.setDateFrom(rs.getDate("date_from").toLocalDate());
                man.setDateTo(rs.getDate("date_to").toLocalDate());
                man.setOrganizer(rs.getString("organizer"));
                man.setStatus(rs.getString("status"));                
                man.setType(rs.getString("type"));
                
                allManifestations.add(man);                
            }
            System.out.println("Size:" + allManifestations.size());
            
        } catch (SQLException ex) {
            Logger.getLogger(ManifestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allManifestations;
    }


}
