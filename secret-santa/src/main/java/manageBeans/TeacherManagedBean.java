/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entities.Game;
import entities.GameGift;
import entities.Gift;
import entities.User;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.ManagedProperty;
import util.SessionUtils;
import util.dao.ArticleDao;
import util.dao.GameGiftsDao;
import util.dao.GameInfoDao;
import util.dao.UserDao;

/**
 *
 * @author Matija
 */
@Named(value = "teacherManagedBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class TeacherManagedBean {

    /**
     * Creates a new instance of TeacherManagedBean
     */
    public TeacherManagedBean() {

    }
    
    User u = new User();

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
        private String giftIGot;
    private String shopName;
    private String shopAddress;

    public String getGiftIGot() {
        return giftIGot;
    }

    public void setGiftIGot(String giftIGot) {
        this.giftIGot = giftIGot;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
    
    private boolean playCurrentGame;
    
    private Game g = new Game();
    
    private boolean IPlayThisGame;

    private boolean IGotPresent;

    public boolean isIGotPresent() {
        return IGotPresent;
    }

    public void setIGotPresent(boolean IGotPresent) {
        this.IGotPresent = IGotPresent;
    }
    
    
    
    
    public Game getG() {
        return g;
    }

    public void setG(Game g) {
        this.g = g;
    }

    public boolean isIPlayThisGame() {
        return IPlayThisGame;
    }

    public void setIPlayThisGame(boolean IPlayThisGame) {
        this.IPlayThisGame = IPlayThisGame;
    }
    
    private String buyGiftTo;

    public String getBuyGiftTo() {
        return buyGiftTo;
    }

    public void setBuyGiftTo(String buyGiftTo) {
        this.buyGiftTo = buyGiftTo;
    }
   
    @PostConstruct
    public void fillPublicConstruct(){
        
        u = (User)(SessionUtils.getSession().getAttribute("user"));
        g = (Game)(SessionUtils.getSession().getAttribute("game"));
        playCurrentGame = new UserDao().fetchAndTestUserByUsername(u.getUsername()).isPlayCurrentGame();
        System.out.println(g.getIdGame());
        System.out.println(u.getUsername());
        GameGiftsDao ggDao = new GameGiftsDao();
        GameGift gg = ggDao.getReceiver(g.getIdGame(), u.getUsername());
        if(gg == null){
            IPlayThisGame = false;
            
            return;
        }
        
        IPlayThisGame = true;
        
        
        buyGiftTo = new UserDao().fetchAndTestUserByUsername(gg.getUsernameReceiver()).getFirstName();
        System.out.println(buyGiftTo);
        
        
        int idOfGift = ggDao.getMyGiftReceiver(g.getIdGame(), u.getUsername());
        System.out.println(idOfGift);
        if(idOfGift != 0) {
            
            Gift giftToMe = new ArticleDao().fetchDataForThisArticle(idOfGift);
            this.shopName = giftToMe.getShopName();
            this.shopAddress = giftToMe.getShopAddress();
            this.giftIGot = giftToMe.getGiftName();
            this.IGotPresent = true;
        } else {
          this.IGotPresent = false;  
        }
        
    }

    public boolean isPlayCurrentGame() {
        return playCurrentGame;
    }

    public void setPlayCurrentGame(boolean playCurrentGame) {
        this.playCurrentGame = playCurrentGame;
    }

    public void submitParticipation(){
        new UserDao().addTeacherToTheGame(u.getUsername(), playCurrentGame);
    }
    
    public String buyPresent(){
        
        return "presents";
    }
    

    
    
    
}
