/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_beans;

import entities.User;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.annotation.FacesConfig;
import javax.faces.view.ViewScoped;
import util.dao.UserDao;

/**
 *
 * @author Matija
 */
@Named(value = "addUserManagedBean")
@ViewScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class addUserManagedBean implements Serializable{

    /**
     * Creates a new instance of addUserManagedBean
     */
    public addUserManagedBean() {}
    
    private String firstname;
    private String lastname;
    private String gender;
    private String email;
    private String password;
    private String type;
    private String message;
    private boolean userAddedToDB = false;
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUserAddedToDB() {
        return userAddedToDB;
    }

    public void setUserAddedToDB(boolean userAddedToDB) {
        this.userAddedToDB = userAddedToDB;
    }
    
    
    public void addUserToDatabase(){
        System.out.println("User " + email.split("@")[0]);
        User u = new UserDao().getUserByUsername(email.split("@")[0]);
        //System.out.println(u);
        if(u == null){
            u = new User();
            u.setFirstname(firstname);
            u.setGender(gender);
            u.setLastname(lastname);
            u.setPassword(password);
            u.setType(type);
            u.setUsername(email.split("@")[0]);
            new UserDao().createUser(u);
            this.message = "Uspesno dodat korisnik " + this.firstname;
            this.userAddedToDB = true;
        } else{
            this.message = "Korisnicko ime " + email.split("@")[0] + " vec postoji";
            this.userAddedToDB = true;            
        }
    }
}
