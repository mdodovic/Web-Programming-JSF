/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entities.Book;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.BooksDao;
import util.dao.OrderDao;

/**
 *
 * @author Matija
 */
@Named(value = "buyBookManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class BuyBookManagedBean {

    private String message;

    /**
     * Creates a new instance of BuyBookManagedBean
     */
    public BuyBookManagedBean() {
    }
    
    private Book b = new Book();
    private List<Book> books = new ArrayList<>();
    private int amount;
    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Book getB() {
        return b;
    }

    public void setB(Book b) {
        this.b = b;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @PostConstruct
    public void fetchBook(){
        b = (Book)(SessionUtils.getSession().getAttribute("book"));
        books.add(b);
        System.out.println(b.getNaslov());

    }
    
    public void acceptBought(){
        if(this.amount > b.getAmount()){
            this.message = "Nema dovoljno knjiga na stanju";
            this.error = true;
            return;
        }
        this.error = false;
        this.message = "Morate platiti " + this.amount * b.getNumPages();
        new OrderDao().buyBook(this.b,(User)(SessionUtils.getSession().getAttribute("user")), this.amount);
        new BooksDao().buyBook(this.b, this.amount);
        books.get(0).setAmount(b.getAmount() - this.amount);
    }
    

}
