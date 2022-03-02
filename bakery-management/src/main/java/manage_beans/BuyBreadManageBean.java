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
import util.dao.OrdersDao;
import util.dao.WaitingQueueDao;

/**
 *
 * @author Matija
 */
@Named(value = "buyBreadManageBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class BuyBreadManageBean {

    /**
     * Creates a new instance of BuyBreadManageBean
     */
    public BuyBreadManageBean() {
    }

    private int amount;
    private List<WaitingQueue> queue = new ArrayList<>();
    private User u;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<WaitingQueue> getQueue() {
        return queue;
    }

    public void setQueue(List<WaitingQueue> queue) {
        this.queue = queue;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    @PostConstruct
    public void fillAll(){
        queue = (List<WaitingQueue>) SessionUtils.getSession().getAttribute("queue");
        u = (User) SessionUtils.getSession().getAttribute("user");
        
    }
    
    public String accept(){
        new WaitingQueueDao().removeUser(u);
        new OrdersDao().addNewOrder(u, this.amount);
        return "index";
    }
}
