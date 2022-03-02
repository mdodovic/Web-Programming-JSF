/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage_beans;

import entities.Orders;
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

/**
 *
 * @author Matija
 */
@Named(value = "workerManageBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class WorkerManageBean {

    /**
     * Creates a new instance of WorkerManageBean
     */
    public WorkerManageBean() {
    }
    
    private List<WaitingQueue> waitingQueue = new ArrayList<>();
    private List<Orders> orderQueue = new ArrayList<>();
    private User u;

    public List<WaitingQueue> getWaitingQueue() {
        return waitingQueue;
    }

    public void setWaitingQueue(List<WaitingQueue> waitingQueue) {
        this.waitingQueue = waitingQueue;
    }

    public List<Orders> getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(List<Orders> orderQueue) {
        this.orderQueue = orderQueue;
    }
    
    
    

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    @PostConstruct
    public void fillAll(){
        orderQueue = new OrdersDao().fetchAllOrders();
        waitingQueue = (List<WaitingQueue>) SessionUtils.getSession().getAttribute("queue");
        u = (User) SessionUtils.getSession().getAttribute("user");
        
    }
    
    public String accept(Orders o){
        new OrdersDao().accept(o);
        orderQueue = new OrdersDao().fetchAllOrders();
        return "worker.xhtml?faces_redirect=true";
    }
}
