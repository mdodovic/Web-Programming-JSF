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
import entities.Statistics;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Local;
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
@Named(value = "testManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class TestManagedBean {

    /**
     * Creates a new instance of TestManagedBean
     */
    public TestManagedBean() {
    }
    
    private String testName;
    private String result;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    private Lekar l = new Lekar();
    private Korisnik u = new Korisnik();
    private Bolnica b = new Bolnica();
    private Pacijent p = new Pacijent();
    
    @PostConstruct
    public void init(){
        u = (Korisnik)(SessionUtils.getSession().getAttribute("user"));
        p = (Pacijent)(SessionUtils.getSession().getAttribute("patientToBeTested"));
        l = (Lekar)(SessionUtils.getSession().getAttribute("doctor"));
        b = new HospitalDao().fetchHospitalById(l.getIdBolnice());
    }
    
    public String testThisPatient(){
        new UserDao().testPatient(p.getUsername(), this.result, this.testName);
        List<Statistics> statList =  (List<Statistics>) SessionUtils.getSession().getAttribute("list");
        Statistics s = statList.remove(0);
        s.setNumberTested(s.getNumberTested() + 1);
        statList.add(s);
        SessionUtils.getSession().setAttribute("list", statList);
        if(this.result.equals("negativan")){
            return "doctor";
        }
        statList =  (List<Statistics>) SessionUtils.getSession().getAttribute("list");
        s = statList.remove(0);
        s.setNumberPositiveOnTest(s.getNumberPositiveOnTest() + 1);
        s.setNumberNewHospitalized(s.getNumberNewHospitalized() + 1);
        statList.add(s);
        SessionUtils.getSession().setAttribute("list", statList);

        return "placeToHospital";
    }
    
}
