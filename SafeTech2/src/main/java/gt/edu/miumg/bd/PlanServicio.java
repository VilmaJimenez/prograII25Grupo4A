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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author whiteHat
 */
@Entity
@Table(name = "PlanServicio", catalog = "SafeTechProd", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "PlanServicio.findAll", query = "SELECT p FROM PlanServicio p")})
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
    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente idCliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idServicio", referencedColumnName = "idServicio")
    private Servicio servicio;

    @OneToMany(mappedBy = "idPlan", fetch = FetchType.EAGER)
    private List<PlanAgente> planAgenteList;

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

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public List<PlanAgente> getPlanAgenteList() {
        return planAgenteList;
    }

    public void setPlanAgenteList(List<PlanAgente> planAgenteList) {
        this.planAgenteList = planAgenteList;
    }

    @Override
    public int hashCode() {
        return idPlan != null ? idPlan.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PlanServicio)) {
            return false;
        }
        PlanServicio other = (PlanServicio) object;
        return (this.idPlan != null && this.idPlan.equals(other.idPlan));
    }

    @Override
    public String toString() {
        return "gt.edu.miumg.bd.PlanServicio[ idPlan=" + idPlan + " ]";
    }
    
}
