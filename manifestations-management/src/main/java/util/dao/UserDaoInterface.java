/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.User;

/**
 *
 * @author Matija
 */
public interface UserDaoInterface {
    
    public abstract User login(String username, String password);
    
}
