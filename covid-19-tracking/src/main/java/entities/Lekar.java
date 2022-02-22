/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matija
 */
@Entity
@Table(name = "lekar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lekar.findAll", query = "SELECT l FROM Lekar l"),
    @NamedQuery(name = "Lekar.findByUsername", query = "SELECT l FROM Lekar l WHERE l.username = :username"),
    @NamedQuery(name = "Lekar.findBySpecijalizacija", query = "SELECT l FROM Lekar l WHERE l.specijalizacija = :specijalizacija")})
public class Lekar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "specijalizacija")
    private String specijalizacija;
    @JoinColumn(name = "idBolnice", referencedColumnName = "bID")
    @ManyToOne(optional = false)
    private int idBolnice;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Korisnik korisnik;

    public Lekar() {
    }

    public Lekar(String username) {
        this.username = username;
    }

    public Lekar(String username, String specijalizacija) {
        this.username = username;
        this.specijalizacija = specijalizacija;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSpecijalizacija() {
        return specijalizacija;
    }

    public void setSpecijalizacija(String specijalizacija) {
        this.specijalizacija = specijalizacija;
    }

    public int getIdBolnice() {
        return idBolnice;
    }

    public void setIdBolnice(int idBolnice) {
        this.idBolnice = idBolnice;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lekar)) {
            return false;
        }
        Lekar other = (Lekar) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Lekar[ username=" + username + " ]";
    }
    
}
