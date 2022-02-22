/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Bolnica;
import entities.Korisnik;
import entities.Lekar;
import entities.Pacijent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import util.DB;

/**
 *
 * @author Matija
 */
public class UserDao {
    public Korisnik fetchUserByUsername(String username){
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("select * from korisnik where username = ?")){
            
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Korisnik k = new Korisnik();
                k.setEposta(rs.getString("eposta"));
                k.setIme(rs.getString("ime"));
                k.setPrezime(rs.getString("prezime"));
                k.setUsername(rs.getString("username"));
                k.setPassword(rs.getString("password"));
                return k;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return null;
    }
    
    public Lekar fetchDoctorByUsername(String username){
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("select * from lekar where username = ?")){
            
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Lekar l = new Lekar();
                l.setIdBolnice(rs.getInt("idBolnice"));
                l.setUsername(username);
                l.setSpecijalizacija(rs.getString("specijalizacija"));
                return l;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return null;
    }
    
    public Pacijent fetchPacientByUsername(String username){
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("select * from pacijent where username = ?")){
            
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Pacijent p = new Pacijent();
                p.setDatumHosp(rs.getDate("datum_hosp"));
                p.setDatumOtpust(rs.getDate("datum_otpust"));
                p.setDatumTest(rs.getDate("datum_test"));
                p.setHospitalizovan(rs.getBoolean("hospitalizovan"));
                p.setIdBolnice(rs.getInt("idBolnice"));
                p.setRezultatTestPositiv(rs.getBoolean("rezultat_test_positiv"));
                p.setStatus(rs.getString("status"));
                p.setUsername(username);
                p.setVrstaTest(rs.getString("vrsta_test"));
                return p;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return null;
    }
    
    public List<Pacijent> fetchAllPatientsByHospitalId(int id){
        List<Pacijent> allPatientData = new ArrayList<>();
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("select * from pacijent where idBolnice = ?")){
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Pacijent p = new Pacijent();
                p.setDatumHosp(rs.getDate("datum_hosp"));
                p.setDatumOtpust(rs.getDate("datum_otpust"));
                p.setDatumTest(rs.getDate("datum_test"));
                p.setHospitalizovan(rs.getBoolean("hospitalizovan"));
                p.setIdBolnice(rs.getInt("idBolnice"));
                p.setRezultatTestPositiv(rs.getBoolean("rezultat_test_positiv"));
                p.setStatus(rs.getString("status"));
                p.setUsername(rs.getString("username"));
                p.setVrstaTest(rs.getString("vrsta_test"));
                allPatientData.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return allPatientData;

    }

    public void testPatient(String username, String result, String testName) {
    
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("UPDATE pacijent SET datum_test = ?, vrsta_test = ?, rezultat_test_positiv = ? WHERE (username = ?)")){
            LocalDate l = LocalDate.now();
            
            
            Date d = new Date(l.toEpochDay());
            System.out.println(l.toEpochDay());
            System.out.println(d.getTime());
            System.out.println(new java.sql.Date(l.toEpochDay()).getTime());
            System.out.println(java.sql.Date.valueOf(LocalDate.now()).getTime());
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
            ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            if(testName.equals("PCR test")){
                ps.setString(2, "PCR");
            } else{
                ps.setString(2, "ANT");
            }
            if(result.equals("negativan")){
                ps.setInt(3, 0);    
            } else{
                ps.setInt(3, 1);
            }
            ps.setString(4, username);
            System.out.println("Ready to update " + username);
            System.out.println(ps.executeUpdate());
            System.out.println("Updated ");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }

    public void hospitalizePatient(Pacijent p, Date hosDate, Bolnica b, int brojSlobodnihKreveta) {
        Connection conn = DB.getInstance().getConnection();
        if(brojSlobodnihKreveta == 0){
            b = new HospitalDao().fetchHospitalWithBets();
        }
        try(PreparedStatement ps = conn.prepareStatement("UPDATE pacijent SET hospitalizovan = '1', datum_hosp = ?, idBolnice = ?, status = 'O' WHERE (username = ?)")){

            ps.setDate(1, new java.sql.Date(hosDate.getTime()));
            ps.setInt(2, b.getBID());    
            ps.setString(3, p.getUsername());
            System.out.println("Ready to update ");
            System.out.println(ps.executeUpdate());
            new HospitalDao().reduceBeds(b);
            System.out.println("Updated ");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        } 
    }

    public void survivedPatient(Pacijent p, Date hosDate) {
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("UPDATE pacijent SET datum_otpust = ?, status = 'P' WHERE (username = ?)")){

            ps.setDate(1, new java.sql.Date(hosDate.getTime()));
            ps.setString(2, p.getUsername());
            System.out.println("Ready to update ");
            System.out.println(ps.executeUpdate());
            new HospitalDao().increaseBeds(p.getIdBolnice());
            System.out.println("Updated ");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        } 
    }
    
    public void diedPatient(Pacijent p, Date hosDate) {
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("UPDATE pacijent SET datum_otpust = ?, status = 'U' WHERE (username = ?)")){

            ps.setDate(1, new java.sql.Date(hosDate.getTime()));
            ps.setString(2, p.getUsername());
            System.out.println("Ready to update ");
            System.out.println(ps.executeUpdate());
            new HospitalDao().increaseBeds(p.getIdBolnice());
            System.out.println("Updated ");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        } 
    }
    
}
