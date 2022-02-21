/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Events;
import entities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.EventDao;

/**
 *
 * @author Matija
 */
@Named(value = "adminManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class AdminManagedBean {

    /**
     * Creates a new instance of AdminManagedBean
     */
    public AdminManagedBean() {
    }
    
    private Users u = new Users();


    public Users getU() {
        return u;
    }

    public void setU(Users u) {
        this.u = u;
    }

    private List<Events> allActiveEvents = new ArrayList<>();
    private List<Events> allInActiveEvents = new ArrayList<>();
    private List<Events> allEvents = new ArrayList<>();
            

    public List<Events> getAllActiveEvents() {
        return allActiveEvents;
    }

    public void setAllActiveEvents(List<Events> allActiveEvents) {
        this.allActiveEvents = allActiveEvents;
    }

    public List<Events> getAllInActiveEvents() {
        return allInActiveEvents;
    }

    public void setAllInActiveEvents(List<Events> allInActiveEvents) {
        this.allInActiveEvents = allInActiveEvents;
    }

    public List<Events> getAllEvents() {
        return allEvents;
    }

    public void setAllEvents(List<Events> allEvents) {
        this.allEvents = allEvents;
    }
    
    

    @PostConstruct
    public void fetch(){
        u = (Users)(SessionUtils.getSession().getAttribute("user"));
        allActiveEvents = new EventDao().fetchAllActive();
        allInActiveEvents = new EventDao().fetchAllInactive();
        for(Events e: allActiveEvents){
            allEvents.add(e);
        }
        for(Events e: allInActiveEvents){
            allEvents.add(e);
        }
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

    }
    
    public String toggle(Events e){
        new EventDao().changeEvent(e);
        return "admin.xhtml?faces-redirect=true";
    }

}
