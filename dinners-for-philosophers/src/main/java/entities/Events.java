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
@Table(name = "events")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e"),
    @NamedQuery(name = "Events.findById", query = "SELECT e FROM Events e WHERE e.id = :id"),
    @NamedQuery(name = "Events.findByName", query = "SELECT e FROM Events e WHERE e.name = :name"),
    @NamedQuery(name = "Events.findByOption1", query = "SELECT e FROM Events e WHERE e.option1 = :option1"),
    @NamedQuery(name = "Events.findByOption2", query = "SELECT e FROM Events e WHERE e.option2 = :option2"),
    @NamedQuery(name = "Events.findByOption3", query = "SELECT e FROM Events e WHERE e.option3 = :option3"),
    @NamedQuery(name = "Events.findByOption4", query = "SELECT e FROM Events e WHERE e.option4 = :option4"),
    @NamedQuery(name = "Events.findByActive", query = "SELECT e FROM Events e WHERE e.active = :active"),
    @NamedQuery(name = "Events.findByMany", query = "SELECT e FROM Events e WHERE e.many = :many")})
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "option1")
    private String option1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "option2")
    private String option2;
    @Size(max = 50)
    @Column(name = "option3")
    private String option3;
    @Size(max = 50)
    @Column(name = "option4")
    private String option4;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private short active;
    @Basic(optional = false)
    @NotNull
    @Column(name = "many")
    private short many;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
    private Collection<Votes> votesCollection;

    public Events() {
    }

    public Events(Integer id) {
        this.id = id;
    }

    public Events(Integer id, String name, String option1, String option2, short active, short many) {
        this.id = id;
        this.name = name;
        this.option1 = option1;
        this.option2 = option2;
        this.active = active;
        this.many = many;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    public short getMany() {
        return many;
    }

    public void setMany(short many) {
        this.many = many;
    }

    @XmlTransient
    public Collection<Votes> getVotesCollection() {
        return votesCollection;
    }

    public void setVotesCollection(Collection<Votes> votesCollection) {
        this.votesCollection = votesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Events[ id=" + id + " ]";
    }

    
}
