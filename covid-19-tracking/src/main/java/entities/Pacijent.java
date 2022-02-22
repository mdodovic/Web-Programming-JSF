/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matija
 */
@Entity
@Table(name = "pacijent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pacijent.findAll", query = "SELECT p FROM Pacijent p"),
    @NamedQuery(name = "Pacijent.findByUsername", query = "SELECT p FROM Pacijent p WHERE p.username = :username"),
    @NamedQuery(name = "Pacijent.findByHospitalizovan", query = "SELECT p FROM Pacijent p WHERE p.hospitalizovan = :hospitalizovan"),
    @NamedQuery(name = "Pacijent.findByStatus", query = "SELECT p FROM Pacijent p WHERE p.status = :status"),
    @NamedQuery(name = "Pacijent.findByDatumTest", query = "SELECT p FROM Pacijent p WHERE p.datumTest = :datumTest"),
    @NamedQuery(name = "Pacijent.findByVrstaTest", query = "SELECT p FROM Pacijent p WHERE p.vrstaTest = :vrstaTest"),
    @NamedQuery(name = "Pacijent.findByRezultatTestPositiv", query = "SELECT p FROM Pacijent p WHERE p.rezultatTestPositiv = :rezultatTestPositiv"),
    @NamedQuery(name = "Pacijent.findByDatumHosp", query = "SELECT p FROM Pacijent p WHERE p.datumHosp = :datumHosp"),
    @NamedQuery(name = "Pacijent.findByDatumOtpust", query = "SELECT p FROM Pacijent p WHERE p.datumOtpust = :datumOtpust")})
public class Pacijent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hospitalizovan")
    private boolean hospitalizovan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;
    @Column(name = "datum_test")
    @Temporal(TemporalType.DATE)
    private Date datumTest;
    @Size(max = 3)
    @Column(name = "vrsta_test")
    private String vrstaTest;
    @Column(name = "rezultat_test_positiv")
    private Boolean rezultatTestPositiv;
    @Column(name = "datum_hosp")
    @Temporal(TemporalType.DATE)
    private Date datumHosp;
    @Column(name = "datum_otpust")
    @Temporal(TemporalType.DATE)
    private Date datumOtpust;
    @JoinColumn(name = "idBolnice", referencedColumnName = "bID")
    @ManyToOne(optional = false)
    private int idBolnice;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Korisnik korisnik;

    public Pacijent() {
    }

    public Pacijent(String username) {
        this.username = username;
    }

    public Pacijent(String username, boolean hospitalizovan, String status) {
        this.username = username;
        this.hospitalizovan = hospitalizovan;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getHospitalizovan() {
        return hospitalizovan;
    }

    public void setHospitalizovan(boolean hospitalizovan) {
        this.hospitalizovan = hospitalizovan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatumTest() {
        return datumTest;
    }

    public void setDatumTest(Date datumTest) {
        this.datumTest = datumTest;
    }

    public String getVrstaTest() {
        return vrstaTest;
    }

    public void setVrstaTest(String vrstaTest) {
        this.vrstaTest = vrstaTest;
    }

    public Boolean getRezultatTestPositiv() {
        return rezultatTestPositiv;
    }

    public void setRezultatTestPositiv(Boolean rezultatTestPositiv) {
        this.rezultatTestPositiv = rezultatTestPositiv;
    }

    public Date getDatumHosp() {
        return datumHosp;
    }

    public void setDatumHosp(Date datumHosp) {
        this.datumHosp = datumHosp;
    }

    public Date getDatumOtpust() {
        return datumOtpust;
    }

    public void setDatumOtpust(Date datumOtpust) {
        this.datumOtpust = datumOtpust;
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
        if (!(object instanceof Pacijent)) {
            return false;
        }
        Pacijent other = (Pacijent) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pacijent[ username=" + username + " ]";
    }
    
}
