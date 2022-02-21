/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entities.Article;
import entities.Game;
import entities.GameGift;
import entities.Shop;
import entities.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.view.ViewScoped;
import util.SessionUtils;
import util.dao.ArticleDao;
import util.dao.GameGiftsDao;

/**
 *
 * @author Matija
 */
@Named(value = "presentManageBean")
@ViewScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class PresentManageBean implements Serializable{

    /**
     * Creates a new instance of PresentManageBean
     */
    public PresentManageBean() {
    }


    private String articleName;
    private int selectedShop;
    HashMap<String, Integer> allShopNames = new HashMap<>();
    List<Article> requestedPresents = new ArrayList<>();

    @PostConstruct
    public void fillMap(){
        
        allShopNames = new ArticleDao().returnAllShops();
        /*Article x = new Article();
        x.setAmount(13);
        x.setName("ss");     
        x.setPrice(3.2);
        requestedPresents.add(x);
        */
    }
    
    
    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public int getSelectedShop() {
        return selectedShop;
    }

    public void setSelectedShop(int selectedShop) {
        this.selectedShop = selectedShop;
    }
  
    public HashMap<String, Integer> getAllShopNames() {
        return allShopNames;
    }

    public void setAllShopNames(HashMap<String, Integer> allShopNames) {
        this.allShopNames = allShopNames;
    }

    public List<Article> getRequestedPresents() {
        return requestedPresents;
    }

    public void setRequestedPresents(List<Article> requestedPresents) {
        this.requestedPresents = requestedPresents;
    }

    public /*String*/ void search(){
        
        System.out.println("Find appropriate");
        System.out.println(selectedShop);
        System.out.println(this.articleName);
        
        requestedPresents = new ArticleDao().fetchAppropriateArticle(this.articleName, this.selectedShop);
        //return "presents.xhtml?faces-redirect=true";
    }
    
    public String buyGiftForTeacher(Article p){
        System.out.println("GIFT TO");
        
        User u = (User)(SessionUtils.getSession().getAttribute("user"));
        Game g = (Game)(SessionUtils.getSession().getAttribute("game"));
        new GameGiftsDao().setGiftFromInGame(p.getIdArt(), u.getUsername(), g.getIdGame());
        new ArticleDao().reduceAmountOfArticle(p.getIdArt());
        return "teacher";
    }
/*    
    public String order(Article a) {
        
        User u = (User)(SessionUtils.getSession().getAttribute("user"));
        Game g = (Game)(SessionUtils.getSession().getAttribute("game"));
        
        GameGiftsDao ggDao = new GameGiftsDao();
        GameGift gg = ggDao.getReceiver(g.getIdGame(), u.getUsername());
        
        System.out.println(u.getUsername() + " -> " + gg.getUsernameReceiver());
        System.out.println(a.getName() + " ; " + a.getIdShop());
        return "teacher";
    }
*/
}
