/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import entities.Reservation;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.ReserveDao;

/**
 *
 * @author Matija
 */
@Named(value = "labTechnicianManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class LabTechnicianManagedBean {

    /**
     * Creates a new instance of LabTechnicianManagedBean
     */
    public LabTechnicianManagedBean() {
    }
    
    private List<Reservation> allReservations = new ArrayList<>();
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Reservation> getAllReservations() {
        return allReservations;
    }

    public void setAllReservations(List<Reservation> allReservations) {
        this.allReservations = allReservations;
    }
    
    
    
    @PostConstruct
    public void fetchAllReservations(){
        name = ((User)(SessionUtils.getSession().getAttribute("user"))).getFirstname();
        allReservations = new ReserveDao().getAllReservations();        
    }
    
    public String deleteReservation(Reservation r) {
        
        new ReserveDao().deleteReservation(r);
        
        return "labTechnician.xhtml?faces-redirect=true";
    }    
}
