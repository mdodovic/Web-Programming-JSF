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
@Named(value = "doctorManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class DoctorManagedBean {

    /**
     * Creates a new instance of DoctorManagedBean
     */
    public DoctorManagedBean() {
    }
    private List<Pacijent> allPatientData = new ArrayList<>();
    private List<Korisnik> allKorisnikData = new ArrayList<>();
    private Lekar l = new Lekar();
    private Korisnik u = new Korisnik();
    private Bolnica b = new Bolnica();
    private boolean tested;

    public List<Pacijent> getAllPatientData() {
        return allPatientData;
    }

    public void setAllPatientData(List<Pacijent> allPatientData) {
        this.allPatientData = allPatientData;
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

    public boolean isTested() {
        return tested;
    }

    public void setTested(boolean tested) {
        this.tested = tested;
    }

    
    
    @PostConstruct
    public void init(){
        u = (Korisnik)(SessionUtils.getSession().getAttribute("user"));
        l = (Lekar)(SessionUtils.getSession().getAttribute("doctor"));
        b = new HospitalDao().fetchHospitalById(l.getIdBolnice());
               
        allPatientData = new UserDao().fetchAllPatientsByHospitalId(b.getBID());
        for(Pacijent p : allPatientData){
            //System.out.println(p.getDatumTest());
            Korisnik user = new UserDao().fetchUserByUsername(p.getUsername());
            allKorisnikData.add(user);
        }
    }

    public List<Korisnik> getAllKorisnikData() {
        return allKorisnikData;
    }

    public void setAllKorisnikData(List<Korisnik> allKorisnikData) {
        this.allKorisnikData = allKorisnikData;
    }
    
    
    
    public String fetchName(Pacijent p){
        return allKorisnikData.get(allPatientData.indexOf(p)).getIme();
    }
    public String fetchLastName(Pacijent p){
        return allKorisnikData.get(allPatientData.indexOf(p)).getPrezime();
    }

    
    public String testThisPatient(Pacijent p){
        
        SessionUtils.getSession().setAttribute("patientToBeTested", p);
        return "testPatient";
    }
    
    public boolean shouldBeTested(Pacijent p){
        return p.getDatumTest()== null;
    }
    
    public String closePatient(Pacijent p){
        SessionUtils.getSession().setAttribute("patientToBeClosed", p);
        return "closePatient";
    }
    
    
}
