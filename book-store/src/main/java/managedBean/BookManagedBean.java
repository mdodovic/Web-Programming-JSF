/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entities.Book;
import entities.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.annotation.FacesConfig;
import javax.faces.view.ViewScoped;
import util.SessionUtils;
import util.dao.BooksDao;

/**
 *
 * @author Matija
 */
@Named(value = "bookManagedBean")
@ViewScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class BookManagedBean implements Serializable{

    private boolean hasUser;

    /**
     * Creates a new instance of BookManagedBean
     */
    public BookManagedBean() {
    }
    
    private List<Book> allBooks = new ArrayList<>();
    private String bookName;
    private String authorName;
    private String errorMessage;
    private User u;

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
    }

    public boolean isHasUser() {
        return hasUser;
    }

    public void setHasUser(boolean hasUser) {
        this.hasUser = hasUser;
    }
    
    @PostConstruct
    public void fillAllBooks(){
        allBooks = new BooksDao().fetchAllBooksFromDB();
        u = (User) SessionUtils.getSession().getAttribute("user");
        if(u == null){
            this.hasUser = false;
        } else{
            this.hasUser = true;
        }
    }
    
    public String buyBook(Book b){
        
        if(u == null){
            this.errorMessage = "Niste prijavljeni!";
            return "";
        }
        SessionUtils.getSession().setAttribute("book", b);
        System.out.println(b.getNaslov());
        return "buyBook";
    }

    
    public void filterBooks(){
        this.errorMessage = "";
        List<Book> filteredBooks = new ArrayList<>();
        allBooks = new BooksDao().fetchAllBooksFromDB();
        
        for(int i = 0; i < allBooks.size(); i++){
            if(!this.authorName.equals("")){
                if(!allBooks.get(i).getAuthor().equals(this.authorName)){
                    filteredBooks.add(allBooks.get(i));
                }
            }
            if(!this.bookName.equals("")){
                if(!allBooks.get(i).getNaslov().equals(this.bookName)){
                    filteredBooks.add(allBooks.get(i));
                }
            }
        }
        for(Book b: filteredBooks){
            allBooks.remove(b);
        }
        
    }
    
    public void sortBooksByYear(){
        this.errorMessage = "";

        allBooks.sort((o1, o2) -> {
            int year1 = o1.getYear();
            int year2 = o2.getYear();
            if(year1 > year2)
                return -1;
            if(year2 > year1)
                return 1;            
            return 0; //To change body of generated lambdas, choose Tools | Templates.
        });
    }
    
    
}
