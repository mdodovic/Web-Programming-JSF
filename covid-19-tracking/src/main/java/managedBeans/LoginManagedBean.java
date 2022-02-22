/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Korisnik;
import entities.Lekar;
import entities.Pacijent;
import entities.Statistics;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.annotation.FacesConfig;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import util.SessionUtils;
import util.dao.UserDao;

/**
 *
 * @author Matija
 */
@Named(value = "loginManagedBean")
@ViewScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class LoginManagedBean implements Serializable {

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
       
    }
    private String username;
    private String password;
    private String type;
    private List<Statistics> stat = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        stat = (List<Statistics>) SessionUtils.getSession().getAttribute("list");
        if(stat == null){
            stat = new ArrayList<>();
            System.out.println("First and last time");
            Statistics s = new Statistics();
            stat.add(s);
        }
        
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private String message;
    private String logoutMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogoutMessage() {
        return logoutMessage;
    }

    public void setLogoutMessage(String logoutMessage) {
        this.logoutMessage = logoutMessage;
    }

    public List<Statistics> getStat() {
        return stat;
    }

    public void setStat(List<Statistics> stat) {
        this.stat = stat;
    }
    
    public String login(){
        SessionUtils.getSession().setAttribute("list", stat);
        Korisnik u = new UserDao().fetchUserByUsername(this.username);
        if(u == null){
            this.message = "Ne postoji dato korisnicko ime";
            return "";
        }
        if(u.getPassword().equals(this.password)){
            
            Lekar l = new UserDao().fetchDoctorByUsername(this.username);
            Pacijent p = new UserDao().fetchPacientByUsername(this.username);
            if(l != null){
                if(this.type.equals("Doctor")){
                    SessionUtils.getSession().setAttribute("user", u);
                    SessionUtils.getSession().setAttribute("doctor", l);                    
                    return "doctor";
                }
            }
            if(p != null){
                if(this.type.equals("Patient")){
                    SessionUtils.getSession().setAttribute("user", u);
                    SessionUtils.getSession().setAttribute("patient", p);                    
                    return "patient";
                }
            }
            this.message = "Pogresan tip!";
            return "";
        } else{
            this.message = "Pogresna lozinka";
            return "";
        }
    }
    
    
    public String logout(){
        SessionUtils.getSession().removeAttribute("user");
        SessionUtils.getSession().removeAttribute("patient");
        SessionUtils.getSession().removeAttribute("doctor");
        username = "";
        password = "";
        this.message = "";
        this.logoutMessage = "Hvala što ste koristili naš sistem";
        return "index.xhtml";
    }
}
