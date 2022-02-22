/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import entities.User;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
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

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
    }
    
    private String username;
    private String password;
    private boolean error = false;
    private String errorMessage;
    
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

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    
    public String login(){
        UserDao userDao = new UserDao();
        User u = userDao.login(username, password);
        
        if(u == null){
            error = true;
            errorMessage = "Greska u konekciji na bazu podataka";
            return "";
        } 
        if(u.getUsername() == null){
            error = true;
            errorMessage = "Korisnik sa datim username ne postoji";
            return "";            
        }
        if(u.getPassword() == null){
            error = true;
            errorMessage = "Neispravna sifra";
            return "";            
        }
        error = false;
        SessionUtils.getSession().setAttribute("user", u);
        //SessionUtils.getSession().setAttribute("user", user); 
        if(u.isIsAdmin() == true){
            return "administrator";
        } else{
            return "organizator";
        }
    }
    
    public String logout(){
        username = "";
        password = "";
        return "index";
    }
    
}
