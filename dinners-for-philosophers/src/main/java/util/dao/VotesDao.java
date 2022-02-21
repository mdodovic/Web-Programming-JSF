/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Events;
import entities.Users;
import entities.Votes;
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
public class VotesDao {
    public String fetchMostVoted(Events e){
        int v = -1;
        int max = -1;
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("select vote, count(*) AS X from lunches2020.votes join lunches2020.events on(votes.event_id = events.id) where event_id = ? group by vote")){
            
            ps.setInt(1, e.getId());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                if(max < rs.getInt("X")){
                    max = rs.getInt("X");
                    v = rs.getInt("vote");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        System.out.println(v);
        System.out.println(max);
        if(v == 1){
            return e.getOption1();
        }
        if(v == 2){
            return e.getOption1();
        }
        if(v == 3){
            return e.getOption3();
        }
        return e.getOption4();
        
    }
    
    public void addVote(Users u, Events e, int singleVotedFor){
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("insert into votes(user,event_id,vote) values(?,?,?)")){
            ps.setString(1, u.getUsername());
            ps.setInt(2, e.getId());
            ps.setInt(3, singleVotedFor);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
    public List<Votes> fetchVotes(Events e){
        List<Votes> allVotes = new ArrayList<>();
        Connection conn = DB.getInstance().getConnection();
        try(PreparedStatement ps = conn.prepareStatement("select * from votes where event_id = ?")){
            
            ps.setInt(1, e.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Votes v = new Votes();
                v.setEventId(e.getId());
                v.setId(rs.getInt("id"));
                v.setUser(rs.getString("user"));
                v.setVote(rs.getInt("vote"));
                allVotes.add(v);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return allVotes;
    }
    
}
