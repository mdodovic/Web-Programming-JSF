/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entities.Game;
import entities.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import util.SessionUtils;
import util.dao.GameGiftsDao;
import util.dao.GameInfoDao;
import util.dao.UserDao;

/**
 *
 * @author Matija
 */
@Named(value = "adminManageBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class AdminManageBean {

    /**
     * Creates a new instance of AdminManageBean
     */
    public AdminManageBean() {
    }
    
    private List<User> allTeachers = new ArrayList<>();
    private User u = new User();

    
    
    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    public List<User> getAllTeachers() {
        return allTeachers;
    }

    public void setAllTeachers(List<User> allTeachers) {
        this.allTeachers = allTeachers;
    }
    
    @PostConstruct
    public void fillTableOfTeachersFromDB(){
        allTeachers = new UserDao().fetchAllTeachers();
        u = (User)(SessionUtils.getSession().getAttribute("user"));
    }
    
    public String confirmParticipants(){
        for(User us: allTeachers){
            System.out.println(us.getFirstName() + " + " + us.isPlayCurrentGame());
            if(us.isPlayCurrentGame()){
                new UserDao().addTeacherToTheGame(us.getUsername());
            }
        }
        //new UserDao().addTeacherToTheGame(username);
        System.out.println("Update!");
        return "administrator.xhtml?faces-redirect=true";
    }
    
    public void startGame(){
        
        GameInfoDao giDao = new GameInfoDao();
        
        giDao.cancelCurrentGame();
        Game newGame = giDao.addNewGame();
        //System.out.println(newGame.getIdGame());

        List<User> usersThatParticipate = new ArrayList<>();
        for(User us: allTeachers){
            if(us.isPlayCurrentGame()){
                usersThatParticipate.add(us);
            }
        }
        User[] secondColumn = new User[usersThatParticipate.size()];
        
//        Set<Integer> indexes = new HashSet<>();  
//        int j = 0;
        for(int i = 0; i < usersThatParticipate.size(); i++){

            /*
            User us = usersThatParticipate.get(i);
            
            System.out.println(us.getUsername());
            while(true){
                int indSecond = (int)(Math.random() * usersThatParticipate.size());
                System.out.println(usersThatParticipate.get(indSecond).getUsername());
                if(indSecond != i && !indexes.contains(indSecond)){
                    System.out.println("!!!  " + usersThatParticipate.get(indSecond).getUsername());
                    indexes.add(indSecond);
                    secondColumn[j] = usersThatParticipate.get(indSecond);
                    j++;
                    break;
                }
            }
            System.out.println("New");
            */
            secondColumn[i] = usersThatParticipate.get((i + 1) % usersThatParticipate.size());
        }
        
      
        for(int i = 0; i < usersThatParticipate.size(); i++){
            System.out.println(usersThatParticipate.get(i).getUsername() + " -> " + secondColumn[i].getUsername());
        }
        
        GameGiftsDao ggDao = new GameGiftsDao();

        ggDao.addParticipants(newGame, usersThatParticipate, secondColumn);
        
        SessionUtils.getSession().setAttribute("game", newGame);
        
    }
    
}
