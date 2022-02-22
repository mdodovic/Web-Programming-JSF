/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import entities.Manifestations;
import entities.User;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Matija
 */
public interface ManifestationDaoInterface {
    public abstract List<Manifestations> getAllManifestations();
    public List<Manifestations> getAllManifestationsOfUser(User u);
    public void insertNewManifestation(Manifestations m);
    public void cancelManifestation(Manifestations m);
    public List<Manifestations> getAllNewManifestations();
    public void approveManifestation(Manifestations newMan);
    public void disapproveManifestation(Manifestations newMan);
    public List<Manifestations> fetchManifestations(String name, LocalDate dateFrom, LocalDate dateTo);
}
