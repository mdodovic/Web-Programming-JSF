/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import entities.Manifestations;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.dao.ManifestationDao;

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
    
    private List<Manifestations> allNewManifestations = new ArrayList<>();

    public List<Manifestations> getAllNewManifestations() {
        return allNewManifestations;
    }

    public void setAllNewManifestations(List<Manifestations> allNewManifestations) {
        this.allNewManifestations = allNewManifestations;
    }

    @PostConstruct
    public void getAllNewManifestationsFromDatabase(){
        allNewManifestations = new ManifestationDao().getAllNewManifestations();
    }
    
    public String approve(Manifestations newMan){
        new ManifestationDao().approveManifestation(newMan);
        return "administrator.xhtml?faces-redirect=true";
    }

    public String disapprove(Manifestations newMan){
        new ManifestationDao().disapproveManifestation(newMan);
        return "administrator.xhtml?faces-redirect=true";
                                    
    }
    
}
