/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Matija
 */
@Entity
@Table(name = "bolnica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bolnica.findAll", query = "SELECT b FROM Bolnica b"),
    @NamedQuery(name = "Bolnica.findByBID", query = "SELECT b FROM Bolnica b WHERE b.bID = :bID"),
    @NamedQuery(name = "Bolnica.findByNaziv", query = "SELECT b FROM Bolnica b WHERE b.naziv = :naziv"),
    @NamedQuery(name = "Bolnica.findByLokacija", query = "SELECT b FROM Bolnica b WHERE b.lokacija = :lokacija"),
    @NamedQuery(name = "Bolnica.findByUkupnoKreveta", query = "SELECT b FROM Bolnica b WHERE b.ukupnoKreveta = :ukupnoKreveta"),
    @NamedQuery(name = "Bolnica.findByBrojSlobodnihKreveta", query = "SELECT b FROM Bolnica b WHERE b.brojSlobodnihKreveta = :brojSlobodnihKreveta"),
    @NamedQuery(name = "Bolnica.findByBrojtestPCR", query = "SELECT b FROM Bolnica b WHERE b.brojtestPCR = :brojtestPCR"),
    @NamedQuery(name = "Bolnica.findByBrojTestAntitela", query = "SELECT b FROM Bolnica b WHERE b.brojTestAntitela = :brojTestAntitela")})
public class Bolnica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bID")
    private Integer bID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "lokacija")
    private String lokacija;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ukupno_kreveta")
    private int ukupnoKreveta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "broj_slobodnih_kreveta")
    private int brojSlobodnihKreveta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "broj_test_PCR")
    private int brojtestPCR;
    @Basic(optional = false)
    @NotNull
    @Column(name = "broj_test_antitela")
    private int brojTestAntitela;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBolnice")
    private Collection<Pacijent> pacijentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBolnice")
    private Collection<Lekar> lekarCollection;

    public Bolnica() {
    }

    public Bolnica(Integer bID) {
        this.bID = bID;
    }

    public Bolnica(Integer bID, String naziv, String lokacija, int ukupnoKreveta, int brojSlobodnihKreveta, int brojtestPCR, int brojTestAntitela) {
        this.bID = bID;
        this.naziv = naziv;
        this.lokacija = lokacija;
        this.ukupnoKreveta = ukupnoKreveta;
        this.brojSlobodnihKreveta = brojSlobodnihKreveta;
        this.brojtestPCR = brojtestPCR;
        this.brojTestAntitela = brojTestAntitela;
    }

    public Integer getBID() {
        return bID;
    }

    public void setBID(Integer bID) {
        this.bID = bID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public int getUkupnoKreveta() {
        return ukupnoKreveta;
    }

    public void setUkupnoKreveta(int ukupnoKreveta) {
        this.ukupnoKreveta = ukupnoKreveta;
    }

    public int getBrojSlobodnihKreveta() {
        return brojSlobodnihKreveta;
    }

    public void setBrojSlobodnihKreveta(int brojSlobodnihKreveta) {
        this.brojSlobodnihKreveta = brojSlobodnihKreveta;
    }

    public int getBrojtestPCR() {
        return brojtestPCR;
    }

    public void setBrojtestPCR(int brojtestPCR) {
        this.brojtestPCR = brojtestPCR;
    }

    public int getBrojTestAntitela() {
        return brojTestAntitela;
    }

    public void setBrojTestAntitela(int brojTestAntitela) {
        this.brojTestAntitela = brojTestAntitela;
    }

    @XmlTransient
    public Collection<Pacijent> getPacijentCollection() {
        return pacijentCollection;
    }

    public void setPacijentCollection(Collection<Pacijent> pacijentCollection) {
        this.pacijentCollection = pacijentCollection;
    }

    @XmlTransient
    public Collection<Lekar> getLekarCollection() {
        return lekarCollection;
    }

    public void setLekarCollection(Collection<Lekar> lekarCollection) {
        this.lekarCollection = lekarCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bID != null ? bID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bolnica)) {
            return false;
        }
        Bolnica other = (Bolnica) object;
        if ((this.bID == null && other.bID != null) || (this.bID != null && !this.bID.equals(other.bID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Bolnica[ bID=" + bID + " ]";
    }
    
}
