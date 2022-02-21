/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Events;
import entities.Users;
import entities.Votes;
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
import util.dao.VotesDao;

/**
 *
 * @author Matija
 */
@Named(value = "voteManagedBean")
@ViewScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class VoteManagedBean implements Serializable{

    /**
     * Creates a new instance of VoteManagedBean
     */
    public VoteManagedBean() {
    }
    
    private Events e = new Events();
    private Users u = new Users();
    private boolean voted = false;
    private List<Votes> allVotes = new ArrayList<>();

    public boolean isVoted() {
        return voted;
    }

    public Users getU() {
        return u;
    }

    public void setU(Users u) {
        this.u = u;
    }

    
    
    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public List<Votes> getAllVotes() {
        return allVotes;
    }

    public void setAllVotes(List<Votes> allVotes) {
        this.allVotes = allVotes;
    }

    public Events getE() {
        return e;
    }

    public void setE(Events e) {
        this.e = e;
    }

    private boolean manyOptions = false;

    public boolean isManyOptions() {
        return manyOptions;
    }

    public void setManyOptions(boolean manyOptions) {
        this.manyOptions = manyOptions;
    }
    
    private HashMap<String, Integer> possibleOptions = new HashMap<>();
    private int singleVotedFor = -1;
    private List<Integer> ListVotedFor = new ArrayList<>();

    public HashMap<String, Integer> getPossibleOptions() {
        return possibleOptions;
    }

    public void setPossibleOptions(HashMap<String, Integer> possibleOptions) {
        this.possibleOptions = possibleOptions;
    }

    public int getSingleVotedFor() {
        return singleVotedFor;
    }

    public void setSingleVotedFor(int singleVotedFor) {
        this.singleVotedFor = singleVotedFor;
    }

    public List<Integer> getListVotedFor() {
        return ListVotedFor;
    }

    public void setListVotedFor(List<Integer> ListVotedFor) {
        this.ListVotedFor = ListVotedFor;
    }

    
    
    @PostConstruct
    public void fetch(){
        e = (Events) (SessionUtils.getSession().getAttribute("event"));
        u = (Users) (SessionUtils.getSession().getAttribute("user"));
        allVotes = new VotesDao().fetchVotes(e);
        System.out.println(u.getUsername() + " choose vote for " + e.getName());
        System.out.println(allVotes.size());
        for(Votes vote :allVotes){
            System.out.println(vote.getUser());
            if(vote.getUser().equals(u.getUsername())){
                this.voted = true;
                return;
            }
        }
        this.voted = false;
        if(e.getMany() == 1){
            manyOptions = true;
        } else{
            manyOptions = false;
        }
        System.out.println(manyOptions);
        possibleOptions.put(e.getOption1(), 1);
        possibleOptions.put(e.getOption2(), 2);
        if(e.getOption3() != null)
            possibleOptions.put(e.getOption3(), 3);
        if(e.getOption4() != null)
            possibleOptions.put(e.getOption4(), 4);
        System.out.println(possibleOptions.size());

    }
    public String chooseOptions(){
        System.out.println("Hi " + singleVotedFor + " ; " + manyOptions);
        if(manyOptions == true){
            System.out.println("Multiple options");
            
            for(int x : this.ListVotedFor){
                new VotesDao().addVote(u, e, x);
            }
        } else{
            if(singleVotedFor == -1){
                return back();
            } 
            System.out.println("Only one option");
            System.out.println(this.singleVotedFor);
            new VotesDao().addVote(u, e, singleVotedFor);
        }
        
        return "vote.xhtml?faces-redirect=true"; 
    }
    public String back(){
        return "philosopher";
    }
    
}
