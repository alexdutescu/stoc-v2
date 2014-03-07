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
@Table(name = "stoc_total")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StocTotal.findAll", query = "SELECT s FROM StocTotal s ORDER BY s.nume"),
    @NamedQuery(name = "StocTotal.findByNume", query = "SELECT s FROM StocTotal s WHERE s.nume = :nume"),
    @NamedQuery(name = "StocTotal.findByCantFlacon", query = "SELECT s FROM StocTotal s WHERE s.cantFlacon = :cantFlacon"),
    @NamedQuery(name = "StocTotal.findByProducator", query = "SELECT s FROM StocTotal s WHERE s.producator = :producator"),
    @NamedQuery(name = "StocTotal.findByLocatie", query = "SELECT s FROM StocTotal s WHERE s.locatie = :locatie"),
    @NamedQuery(name = "StocTotal.findByCod", query = "SELECT s FROM StocTotal s WHERE s.cod = :cod"),
    @NamedQuery(name = "StocTotal.findByCodBare", query = "SELECT s FROM StocTotal s WHERE s.codBare = :codBare")})
public class StocTotal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nume")
    private String nume;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantFlacon")
    private int cantFlacon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "producator")
    private String producator;
    @Size(max = 255)
    @Column(name = "locatie")
    private String locatie;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod")
    private Integer cod;
    @Size(max = 255)
    @Column(name = "codBare")
    private String codBare;

    public StocTotal() {
    }

    public StocTotal(Integer cod) {
        this.cod = cod;
    }

    public StocTotal(Integer cod, String nume, int cantFlacon, String producator) {
        this.cod = cod;
        this.nume = nume;
        this.cantFlacon = cantFlacon;
        this.producator = producator;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCantFlacon() {
        return cantFlacon;
    }

    public void setCantFlacon(int cantFlacon) {
        this.cantFlacon = cantFlacon;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getCodBare() {
        return codBare;
    }

    public void setCodBare(String codBare) {
        this.codBare = codBare;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod != null ? cod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StocTotal)) {
            return false;
        }
        StocTotal other = (StocTotal) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ro.alexdutescu.coduribare.model.StocTotal[ cod=" + cod + " ]";
    }
    
}
