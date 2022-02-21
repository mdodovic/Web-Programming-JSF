/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entities.Game;
import entities.User;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.GameInfoDao;
import util.dao.UserDao;

/**
 *
 * @author Matija
 */
@Named(value = "loginManagedBean")
@SessionScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class LoginManagedBean implements Serializable{

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
    }
    
    private String username;
    private String password;
    private String status;
    
    private boolean hisBirthday;

    private String errorMessage;



    public boolean isHisBirthday() {
        return hisBirthday;
    }

    public void setHisBirthday(boolean hisBirthday) {
        this.hisBirthday = hisBirthday;
    }

    

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String login(){
        
        User u = new UserDao().fetchAndTestUserByUsername(this.username);
        if(u == null) {
            errorMessage = "Ne postoji korisnik sa datim korisnickim imenom";
            return "";
        } 
        if(!u.getPassword().equals(this.password)){
            errorMessage = "Lozinka nije ispravna";
            return "";            
        }
        if(u.isIsAdmin() && this.status.equals("T")){
            errorMessage = "Vas status je admin, a izabrali ste nastavnik!";
            return "";                        
        }
        if(!u.isIsAdmin() && this.status.equals("A")){
            errorMessage = "Vas status je nastavnik, a izabrali ste admin!";
            return "";                        
        }

        Period p = Period.between(LocalDate.now(), u.getBirthday());
        //System.out.println(p.getDays());
        //System.out.println(p.getMonths());
        //System.out.println(p.getYears());
        
        this.hisBirthday = (p.getDays() == 0 && p.getMonths() == 0);
        //System.out.println(hisBirthday);
        Game g = new GameInfoDao().getActiveGame();
        SessionUtils.getSession().setAttribute("game", g);
        SessionUtils.getSession().setAttribute("user", u);
        if(u.isIsAdmin()){
            return "administrator";
        } else{
            return "teacher";
        }
        
        
    }
    public String logout(){
        username = "";
        password = "";
        status = "";

        errorMessage = "";
        return "index";
    }
    
}
