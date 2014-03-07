/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.alexdutescu.coduribare.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex Dutescu
 */
@Entity
@Table(name = "utilizatori")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilizator.findAll", query = "SELECT u FROM Utilizator u"),
    @NamedQuery(name = "Utilizator.findByIdUtilizator", query = "SELECT u FROM Utilizator u WHERE u.idUtilizator = :idUtilizator"),
    @NamedQuery(name = "Utilizator.findByNumeUtilizator", query = "SELECT u FROM Utilizator u WHERE u.numeUtilizator = :numeUtilizator"),
    @NamedQuery(name = "Utilizator.findByParolaUtilizator", query = "SELECT u FROM Utilizator u WHERE u.parolaUtilizator = :parolaUtilizator"),
    @NamedQuery(name = "Utilizator.findByLocatie", query = "SELECT u FROM Utilizator u WHERE u.locatie = :locatie")})
public class Utilizator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUtilizator")
    private Integer idUtilizator;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "numeUtilizator")
    private String numeUtilizator;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "parolaUtilizator")
    private String parolaUtilizator;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "locatie")
    private String locatie;
    @ManyToOne
    @JoinColumn(name = "rolId")
    private Rol rol;

    public Utilizator() {
    }

    public Utilizator(Integer idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public Utilizator(Integer idUtilizator, String numeUtilizator, String parolaUtilizator, String locatie, Rol rol) {
        this.idUtilizator = idUtilizator;
        this.numeUtilizator = numeUtilizator;
        this.parolaUtilizator = parolaUtilizator;
        this.locatie = locatie;
        this.rol = rol;
    }

    public Integer getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Integer idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getParolaUtilizator() {
        return parolaUtilizator;
    }

    public void setParolaUtilizator(String parolaUtilizator) {
        this.parolaUtilizator = parolaUtilizator;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }
    

    public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilizator != null ? idUtilizator.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Utilizator)) {
            return false;
        }
        Utilizator other = (Utilizator) object;
        if ((this.idUtilizator == null && other.idUtilizator != null) || (this.idUtilizator != null && !this.idUtilizator.equals(other.idUtilizator))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ro.alexdutescu.coduribare.model.Utilizator[ idUtilizator=" + idUtilizator + " ]";
    }
    
}
