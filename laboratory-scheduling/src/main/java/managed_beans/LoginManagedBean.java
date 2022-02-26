/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import com.mysql.cj.Session;
import entities.User;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.UserDao;

/**
 *
 * @author Matija
 */
@Named(value = "loginManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class LoginManagedBean {

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
    }
    
    private String username;
    private String password;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
    
    
    public String login(){
        User u = new UserDao().getUserByUsername(username);
        if(!u.getPassword().equals(this.password)){
            password = "";
            message = "Pogresna lozinka";   
            return "index.xhtml?faces-rendered=true";
        }
        SessionUtils.getSession().setAttribute("user", u);
        if(u.getType().equals("laborant")){
            return "labTechnician";
        } else{
            return "teacher";
        }
    }
    
    public String logout(){
        return "index";
    }
    
}
