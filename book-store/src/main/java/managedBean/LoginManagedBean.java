/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entities.User;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import util.SessionUtils;
import util.dao.UserDao;

/**
 *
 * @author Matija
 */
@Named(value = "loginManagedBean")
@SessionScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class LoginManagedBean implements Serializable{

    private String username;
    private String password;
    private boolean worker;
    private String message;

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

    public boolean isWorker() {
        return worker;
    }

    public void setWorker(boolean worker) {
        this.worker = worker;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    public String login(){
        User u = new UserDao().fetchUserByUsername(username);
        
        if(u == null){
            this.message = "Ne postoji korisnik sa datim korisnickim imenom";
            return "";
        }
        if(!u.getPassword().equals(password)){
            this.message = "Lozinka nije ispravna";
            return "";            
        }
        if((u.getType().equals("kupac") && this.worker == true) || (u.getType().equals("radnik") && this.worker == false)){
            this.message = "Pomesani tipovi";
            return "";                        
        }
        
        if(u.getType().equals("kupac")){
            SessionUtils.getSession().setAttribute("user", u);
            return "index";
        } else{
            return "worker";
        }
        
    }
    
    public String logout(){
        SessionUtils.getSession().removeAttribute("user");
        return "index.xhtml?faces-redirect=true";
    }
    
}
