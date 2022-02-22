/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Bolnica;
import entities.Korisnik;
import entities.Lekar;
import entities.Pacijent;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.HospitalDao;
import util.dao.UserDao;

/**
 *
 * @author Matija
 */
@Named(value = "hospitalizePatient")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class HospitalizePatient {

    /**
     * Creates a new instance of HospitalizePatient
     */
    public HospitalizePatient() {
    }
    
    private Lekar l = new Lekar();
    private Korisnik u = new Korisnik();
    private Bolnica b = new Bolnica();
    private Pacijent p = new Pacijent();
    
    private Date hosDate;

    public Date getHosDate() {
        return hosDate;
    }

    public void setHosDate(Date hosDate) {
        this.hosDate = hosDate;
    }
 
    public Lekar getL() {
        return l;
    }

    public void setL(Lekar l) {
        this.l = l;
    }

    public Korisnik getU() {
        return u;
    }

    public void setU(Korisnik u) {
        this.u = u;
    }

    public Bolnica getB() {
        return b;
    }

    public void setB(Bolnica b) {
        this.b = b;
    }

    public Pacijent getP() {
        return p;
    }

    public void setP(Pacijent p) {
        this.p = p;
    }
    
    
    
    @PostConstruct
    public void init(){
        u = (Korisnik)(SessionUtils.getSession().getAttribute("user"));
        p = (Pacijent)(SessionUtils.getSession().getAttribute("patientToBeTested"));
        l = (Lekar)(SessionUtils.getSession().getAttribute("doctor"));
        b = new HospitalDao().fetchHospitalById(l.getIdBolnice());
    }
    
    public String hosPat(){
        
        new UserDao().hospitalizePatient(p, this.hosDate, b, b.getBrojSlobodnihKreveta());
        return "index";
    }
}
