/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import entities.Manifestations;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.dao.ManifestationDao;

/**
 *
 * @author Matija
 */
@Named(value = "searchManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class SearchManagedBean {

    /**
     * Creates a new instance of SearchManagedBean
     */
    public SearchManagedBean() {
    }
    
    private String name;
    private Date dateFrom;
    private Date dateTo;
    private List<Manifestations> allMatchedManifestations = new ArrayList<>();

    private String errorMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Manifestations> getAllMatchedManifestations() {
        return allMatchedManifestations;
    }

    public void setAllMatchedManifestations(ArrayList<Manifestations> allMatchedManifestations) {
        this.allMatchedManifestations = allMatchedManifestations;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    
    
    public void fetchManifestations(){
        
        ManifestationDao mDao = new ManifestationDao();

        // moze se raditi i sa java.util.Date, ali LocalDate ima mnogo metoda koje olaksavaju rad

        LocalDate beginning = null, end = null;
        if (dateFrom != null) {
            beginning = new java.sql.Date(this.dateFrom.getTime()).toLocalDate();
        }
        if (dateTo != null) {
            end = new java.sql.Date(this.dateTo.getTime()).toLocalDate();
        }
        if (beginning != null && end != null) {
            Period p = Period.between(beginning, end);
            if(p.isNegative()){
                errorMessage = "Datum pocetka ne sme biti pre datuma kraja!";
                return;
            }
        }
        allMatchedManifestations = mDao.fetchManifestations(name, beginning, end);
    }
}
