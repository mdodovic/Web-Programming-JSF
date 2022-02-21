/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Users;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.UsersDao;

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
        System.out.println(username);
        System.out.println(password);
        Users u = new UsersDao().getUserByUsername(username);
        if(u == null){
            this.message = "Korisnik sa datim korisnickim imenom ne postoji";
            return "";
        }
        System.out.println(u.getPassword());
        if(!u.getPassword().equals(this.password)){
            this.message = "Lozinka nije dobra";
            return "";
        }
        SessionUtils.getSession().setAttribute("user", u);
        if(u.getType().equals("admin")){
            return "admin";
        }else{
            return "philosopher";
        }

    }
    public String logout(){
        SessionUtils.getSession().removeAttribute("user");
        return "index";
    }
    
}
