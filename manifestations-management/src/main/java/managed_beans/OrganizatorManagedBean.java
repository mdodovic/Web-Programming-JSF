/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import entities.Manifestations;
import entities.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.ManifestationDao;

/**
 *
 * @author Matija
 */
@Named(value = "manifestationManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class OrganizatorManagedBean {

    /**
     * Creates a new instance of ManifestationManagedBean
     */
    public OrganizatorManagedBean() {
    }
    
    private List<Manifestations> allManifestations = new ArrayList<>();

    private String manifestationName;
    private Date dateFrom;
    private Date dateTo;
    private String type;

    public String getManifestationName() {
        return manifestationName;
    }

    public void setManifestationName(String manifestationName) {
        this.manifestationName = manifestationName;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public List<Manifestations> getAllManifestations() {
        return allManifestations;
    }

    public void setAllManifestations(List<Manifestations> allManifestations) {
        this.allManifestations = allManifestations;
    }
    
    @PostConstruct
    public void getAllManifestationsFromDatabase(){
        
        ManifestationDao mdao = new ManifestationDao();
        User u = (User) SessionUtils.getSession().getAttribute("user");
        allManifestations = mdao.getAllManifestationsOfUser(u);
        
    }
    
    public String addNewManifestation(){
        int nextId = 0;
        for(Manifestations m: allManifestations){
            if(m.getId()>nextId)
                nextId = m.getId();
        }
        nextId++;
        User u = ((User) SessionUtils.getSession().getAttribute("user"));
        Manifestations m = new Manifestations();
        m.setId(nextId);
        m.setDateFrom(new java.sql.Date(this.dateFrom.getTime()).toLocalDate());
        m.setDateTo(new java.sql.Date(this.dateTo.getTime()).toLocalDate());
        m.setName(manifestationName);
        m.setOrganizer(u.getUsername());
        m.setStatus("nova");
        m.setType(type);
        
        ManifestationDao mDao = new ManifestationDao();
        mDao.insertNewManifestation(m);
        return "organizator.xhtml?faces-redirect=true";
    }
    
    public String cancelManifestation(Manifestations m){
        new ManifestationDao().cancelManifestation(m);
        return "organizator.xhtml?faces-redirect=true";
    }
    
    
}
