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
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;

/**
 *
 * @author Matija
 */
@Named(value = "customerManageBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class CustomerManageBean {

    /**
     * Creates a new instance of CustomerManageBean
     */
    public CustomerManageBean() {
    }
    
    private List<WaitingQueue> queue = new ArrayList<>();
    private boolean first = false;
    private User u;

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }
    
    public List<WaitingQueue> getQueue() {
        return queue;
    }

    public void setQueue(List<WaitingQueue> queue) {
        this.queue = queue;
    }

    @PostConstruct
    public void fillAll(){
        queue = (List<WaitingQueue>) SessionUtils.getSession().getAttribute("queue");
        u = (User) SessionUtils.getSession().getAttribute("user");
        if(queue.get(0).getUsername().equals(u.getUsername())){
            first = true;
        }
    }

    public String orderBread(){
        return "buyBread";
    }
}
