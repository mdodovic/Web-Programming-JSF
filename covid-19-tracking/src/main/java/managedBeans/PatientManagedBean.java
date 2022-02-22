/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Bolnica;
import entities.Korisnik;
import entities.Pacijent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.HospitalDao;
import util.dao.UserDao;

/**
 *
 * @author Matija
 */
@Named(value = "patientManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class PatientManagedBean {

    /**
     * Creates a new instance of PatientManagedBean
     */
    public PatientManagedBean() {
    }
    
    private List<Pacijent> allPatientData = new ArrayList<>();
    private Pacijent p = new Pacijent();
    private Korisnik u = new Korisnik();
    private Bolnica b = new Bolnica();
    private boolean tested;
    public List<Pacijent> getAllPatientData() {
        return allPatientData;
    }

    public void setAllPatientData(List<Pacijent> allPatientData) {
        this.allPatientData = allPatientData;
    }

    public Pacijent getP() {
        return p;
    }

    public void setP(Pacijent p) {
        this.p = p;
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

    public boolean isTested() {
        return tested;
    }

    public void setTested(boolean tested) {
        this.tested = tested;
    }
    
    @PostConstruct
    public void init(){
        u = (Korisnik)(SessionUtils.getSession().getAttribute("user"));
        p = (Pacijent)(SessionUtils.getSession().getAttribute("patient"));
        
        allPatientData.add(p);
        if(p.getDatumTest() != null){
            this.tested = true;
        } else{
            this.tested = false;
        }
    }
    
    public String getHospital(int idHospital){
        b = new HospitalDao().fetchHospitalById(idHospital);
        return b.getNaziv();
    }
    
    
    
}
