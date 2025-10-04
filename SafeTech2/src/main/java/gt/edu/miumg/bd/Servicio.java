/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author whiteHat
 */
@Entity
@Table(name = "Servicio", catalog = "SafeTechProd", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s")})
public class Servicio implements Serializable {

    /*private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServicio", nullable = false)
    private Integer idServicio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;
    @OneToMany(mappedBy = "idServicio", fetch = FetchType.LAZY)
    private List<PlanServicio> planServicioList;
    @OneToMany(mappedBy = "idServicio", fetch = FetchType.LAZY)
    private List<Detalle> detalleList;

    public Servicio() {
    }

    public Servicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Servicio(Integer idServicio, BigDecimal precio, String descripcion) {
        this.idServicio = idServicio;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PlanServicio> getPlanServicioList() {
        return planServicioList;
    }

    public void setPlanServicioList(List<PlanServicio> planServicioList) {
        this.planServicioList = planServicioList;
    }

    public List<Detalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle> detalleList) {
        this.detalleList = detalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    /*@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }
    
      @Override
    public boolean equals(Object object) {
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        return (this.idServicio != null && this.idServicio.equals(other.idServicio));
    }

    @Override
    public String toString() {
        return "gt.edu.miumg.bd.Servicio[ idServicio=" + idServicio + " ]";
    }*/
    
        private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idServicio", nullable = false)
    private Integer idServicio;

    @Basic(optional = false)
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;

    @OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY)
    private List<PlanServicio> planServicioList;

    @OneToMany(mappedBy = "idServicio", fetch = FetchType.LAZY)
    private List<Detalle> detalleList;

    public Servicio() {
    }

    public Servicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Servicio(Integer idServicio, BigDecimal precio, String descripcion) {
        this.idServicio = idServicio;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PlanServicio> getPlanServicioList() {
        return planServicioList;
    }

    public void setPlanServicioList(List<PlanServicio> planServicioList) {
        this.planServicioList = planServicioList;
    }

    public List<Detalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle> detalleList) {
        this.detalleList = detalleList;
    }

    @Override
    public int hashCode() {
        return idServicio != null ? idServicio.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        return (this.idServicio != null && this.idServicio.equals(other.idServicio));
    }

    @Override
    public String toString() {
        return "gt.edu.miumg.bd.Servicio[ idServicio=" + idServicio + " ]";
    }
    
}
