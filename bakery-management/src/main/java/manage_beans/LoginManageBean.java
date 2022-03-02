/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage_beans;

import entities.User;
import entities.WaitingQueue;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.UserDao;
import util.dao.WaitingQueueDao;

/**
 *
 * @author Matija
 */
@Named(value = "loginManageBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class LoginManageBean {

    /**
     * Creates a new instance of LoginManageBean
     */
    public LoginManageBean() {
    }
    
    private String username;
    private String password;
    private boolean worker;
    private String message;
    
    private List<WaitingQueue> queue = new ArrayList<>();

    public List<WaitingQueue> getQueue() {
        return queue;
    }

    public void setQueue(List<WaitingQueue> queue) {
        this.queue = queue;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isWorker() {
        return worker;
    }

    public void setWorker(boolean worker) {
        this.worker = worker;
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
        User u = new UserDao().fetchUserByUsername(username);
        
        if(u == null){
            message = "Korisnik sa datim korisnickim imenom ne postoji";
            return "index";            
        }
        
        if(!u.getPassword().equals(password)){
            message = "Pogresna lozinka";
            return "index";
        }
        if((u.getType().equals("radnik") && this.worker == false) || (!u.getType().equals("radnik") && this.worker == true)){
            message = "Pogresan tip korisnika";
            return "index";            
        }
        SessionUtils.getSession().setAttribute("user", u);
        
        if(u.getType().equals("radnik")){
            return "worker";
        } else{
            System.out.println("CUSTOMER: ");
            new WaitingQueueDao().addUser(u);
            queue = new WaitingQueueDao().fetchAllUsers();
            SessionUtils.getSession().removeAttribute("queue");
            SessionUtils.getSession().setAttribute("queue", queue);
            for(WaitingQueue w: queue){
                System.out.println(w.getUsername() + " + " + w.getBirthday());
            }
            return "customer";
        }
    }
    
    public String logout(){
        SessionUtils.getSession().removeAttribute("user");
        return "index";
    }
    
}
