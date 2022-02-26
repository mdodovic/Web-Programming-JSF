/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Matija
 */
public class Reservation {
    private int id;
    private String name;
    private Date begin;
    private Date end;
    private String[] halls;

    public String getStringFromHalls(){
        String stringHalls = halls[0];
        for(int i = 1; i < halls.length; i++){
            stringHalls += "," + halls[i];
        }
        return stringHalls;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String[] getHalls() {
        return halls;
    }

    public void setHalls(String[] halls) {
        this.halls = halls;
    }
    
    
}
