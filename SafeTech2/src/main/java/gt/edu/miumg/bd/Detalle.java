/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import static gt.edu.miumg.bd.PlanAgente_.idPlan;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;

/**
 *
 * @author whiteHat
 */
@Entity
@Table(name = "Detalle", catalog = "SafeTechProd", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d")})
public class Detalle implements Serializable {


    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalle", nullable = false)
    private Integer idDetalle;

    @Basic(optional = false)
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Basic(optional = false)
    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @JoinColumn(name = "idFactura", referencedColumnName = "idFactura")
    @ManyToOne(fetch = FetchType.LAZY)  // Cambiar a LAZY para mejor performance
    private Factura idFactura;

    @JoinColumn(name = "idServicio", referencedColumnName = "idServicio")
    @ManyToOne(fetch = FetchType.EAGER)  // Mantener EAGER para servicio
    private Servicio idServicio;

    @JoinColumn(name = "idPlan", referencedColumnName = "idPlan")
    @ManyToOne(fetch = FetchType.EAGER)  // Mantener EAGER para plan
    private PlanServicio idPlan;

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    public Servicio getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Servicio idServicio) {
        this.idServicio = idServicio;
    }

    public PlanServicio getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(PlanServicio idPlan) {
        this.idPlan = idPlan;
    }

    @Override
    public int hashCode() {
        return idDetalle != null ? idDetalle.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        return (this.idDetalle != null && this.idDetalle.equals(other.idDetalle));
    }

    @Override
    public String toString() {
        return "gt.edu.miumg.bd.Detalle[ idDetalle=" + idDetalle + " ]";
    }
    
}
