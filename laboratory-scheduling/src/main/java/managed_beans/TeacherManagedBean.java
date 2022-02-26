/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import entities.Reservation;
import entities.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.ReserveDao;

/**
 *
 * @author Matija
 */
@Named(value = "teacherManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class TeacherManagedBean {

    /**
     * Creates a new instance of TeacherManagedBean
     */
    public TeacherManagedBean() {
    }
    
    private String name;
    private Date begin;
    private Date end;
    
    private String[] chosenHalls;
    
    private String message = "Rezervacija ";
//    private boolean finishedWithReservation;
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

    public String[] getChosenHalls() {
        return chosenHalls;
    }

    public void setChosenHalls(String[] chosenHalls) {
        this.chosenHalls = chosenHalls;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String reserve(){
        System.out.println("TeacherManagedBean{" + "name=" + name + ", begin=" + begin + ", end=" + end + ", chosenHalls=" + chosenHalls[0] + '}');
        
        List<Reservation> allReservations = new ReserveDao().getAllReservations();
        
        for(Reservation r: allReservations){
            System.out.println(r.getName() + " -> " + "begin=" + r.getBegin() + ", end=" + r.getEnd());
        }
        for(Reservation r: allReservations){        
            if((r.getBegin().after(begin) && r.getBegin().before(end)) ||
                    (r.getEnd().after(begin) && r.getEnd().before(end)) ||
                    (r.getBegin().before(begin) && r.getEnd().after(end))){
                for(String hall: r.getHalls()){
                    for(String choosenHallFor: this.chosenHalls){
                        if(hall.equals(choosenHallFor)){
                            this.message = "Postoji preklapanje sa dogadjajem " + r.getName();
                            System.out.println("INTERLIVING");
                            return "teacher.xhtml";                          
                        }
                    }
                }
            }
        }
        System.out.println("NO INTERLIVING");

        
        Reservation r = new Reservation();
        r.setBegin(begin);
        r.setEnd(end);
        r.setHalls(chosenHalls);
        r.setName(name);
        new ReserveDao().createReservation(r);
        return "teacher";//.xhtml?faces-redirect=true";
    }
    
    
    
}
