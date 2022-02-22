/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Bolnica;
import entities.Pacijent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author Matija
 */
public class HospitalDao {
    public Bolnica fetchHospitalById(int id){
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("select * from bolnica where bID = ?")){
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Bolnica b = new Bolnica();
                b.setBID(id);
                b.setBrojSlobodnihKreveta(rs.getInt("broj_slobodnih_kreveta"));
                b.setBrojTestAntitela(rs.getInt("broj_test_antitela"));
                b.setBrojtestPCR(rs.getInt("broj_test_PCR"));
                b.setUkupnoKreveta(rs.getInt("ukupno_kreveta"));
                b.setNaziv(rs.getString("naziv"));
                b.setLokacija(rs.getString("lokacija"));
                return b;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return null;
    }
    
    public Bolnica fetchHospitalWithBets(){
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("select * from bolnica where broj_slobodnih_kreveta > 0")){
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Bolnica b = new Bolnica();
                b.setBID(rs.getInt("bID"));
                b.setBrojSlobodnihKreveta(rs.getInt("broj_slobodnih_kreveta"));
                b.setBrojTestAntitela(rs.getInt("broj_test_antitela"));
                b.setBrojtestPCR(rs.getInt("broj_test_PCR"));
                b.setUkupnoKreveta(rs.getInt("ukupno_kreveta"));
                b.setNaziv(rs.getString("naziv"));
                b.setLokacija(rs.getString("lokacija"));
                return b;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return null;
    }
    
    public void reduceBeds(Bolnica b) {
    
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("UPDATE bolnica SET broj_slobodnih_kreveta = broj_slobodnih_kreveta - 1 WHERE (bID = ?)")){
            ps.setInt(1, b.getBID());
            System.out.println("Ready to update ");
            System.out.println(ps.executeUpdate());
            System.out.println("Updated ");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }

    public void increaseBeds(int id) {
    
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("UPDATE bolnica SET broj_slobodnih_kreveta = broj_slobodnih_kreveta + 1 WHERE (bID = ?)")){
            ps.setInt(1, id);
            System.out.println("Ready to update ");
            System.out.println(ps.executeUpdate());
            System.out.println("Updated ");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
}
