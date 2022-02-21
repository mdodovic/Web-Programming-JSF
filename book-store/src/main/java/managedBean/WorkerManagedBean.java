/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entities.BuyCollection;
import entities.Order;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.dao.BooksDao;
import util.dao.OrderDao;

/**
 *
 * @author Matija
 */
@Named(value = "workerManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class WorkerManagedBean {

    /**
     * Creates a new instance of WorkerManagedBean
     */
    public WorkerManagedBean() {
    }
    
    List<BuyCollection> allOrders = new ArrayList<>();

    public List<BuyCollection> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(List<BuyCollection> allOrders) {
        this.allOrders = allOrders;
    }
    
    

    @PostConstruct
    public void fetchAllOrders(){
        allOrders = new OrderDao().fetchAllBuy();
    }
    
    public String approve(BuyCollection b){
        new OrderDao().approve(b);
        return "worker.xhtml?faces-redirect=true";
    }
    
    
}
