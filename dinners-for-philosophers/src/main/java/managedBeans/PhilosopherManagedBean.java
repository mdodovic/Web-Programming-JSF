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
import util.dao.VotesDao;

/**
 *
 * @author Matija
 */
@Named(value = "philosopherManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class PhilosopherManagedBean {

    /**
     * Creates a new instance of PhilosopherManagedBean
     */
    public PhilosopherManagedBean() {
    }
    
    private Users u = new Users();
    private List<Events> allActiveEvents = new ArrayList<>();
    private List<Events> allInActiveEvents = new ArrayList<>();
            
    public Users getU() {
        return u;
    }

    public void setU(Users u) {
        this.u = u;
    }

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
    
    

    @PostConstruct
    public void fetch(){
        u = (Users)(SessionUtils.getSession().getAttribute("user"));
        allActiveEvents = new EventDao().fetchAllActive();
        allInActiveEvents = new EventDao().fetchAllInactive();
    }
    
    
    public String eventChoose(Events e){
        SessionUtils.getSession().setAttribute("event", e);        
        return "vote";
    }
    
    public String getMostVotedOption(Events p){
        
        return new VotesDao().fetchMostVoted(p);
    }
}
