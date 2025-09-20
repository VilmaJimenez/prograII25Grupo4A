/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.safetech.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Julissa
 */
@Entity
@Table(name = "PlanServicio", catalog = "SafeTech", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "PlanServicio.findAll", query = "SELECT p FROM PlanServicio p"),
    @NamedQuery(name = "PlanServicio.findByIdPlan", query = "SELECT p FROM PlanServicio p WHERE p.idPlan = :idPlan"),
    @NamedQuery(name = "PlanServicio.findByNombrePlan", query = "SELECT p FROM PlanServicio p WHERE p.nombrePlan = :nombrePlan"),
    @NamedQuery(name = "PlanServicio.findByDescripcion", query = "SELECT p FROM PlanServicio p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PlanServicio.findByPrecio", query = "SELECT p FROM PlanServicio p WHERE p.precio = :precio")})
public class PlanServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlan", nullable = false)
    private Integer idPlan;
    @Basic(optional = false)
    @Column(name = "nombrePlan", nullable = false, length = 100)
    private String nombrePlan;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 2147483647)
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente idCliente;
    @OneToMany(mappedBy = "idPlan", fetch = FetchType.LAZY)
    private List<Servicio> servicioList;
    @OneToMany(mappedBy = "idPlan", fetch = FetchType.LAZY)
    private List<PlanAgente> planAgenteList;

    public PlanServicio() {
    }

    public PlanServicio(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public PlanServicio(Integer idPlan, String nombrePlan, String descripcion, BigDecimal precio) {
        this.idPlan = idPlan;
        this.nombrePlan = nombrePlan;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    public List<PlanAgente> getPlanAgenteList() {
        return planAgenteList;
    }

    public void setPlanAgenteList(List<PlanAgente> planAgenteList) {
        this.planAgenteList = planAgenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlan != null ? idPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanServicio)) {
            return false;
        }
        PlanServicio other = (PlanServicio) object;
        if ((this.idPlan == null && other.idPlan != null) || (this.idPlan != null && !this.idPlan.equals(other.idPlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miumg.edu.gt.safetech.controller.PlanServicio[ idPlan=" + idPlan + " ]";
    }
    
}
