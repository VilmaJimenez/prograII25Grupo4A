/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import java.io.Serializable;
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
@Table(name = "PlanAgente", catalog = "SafeTechProd", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "PlanAgente.findAll", query = "SELECT p FROM PlanAgente p")})
public class PlanAgente implements Serializable {

    /*private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlanAgente", nullable = false)
    private Integer idPlanAgente;
    @JoinColumn(name = "idAgente", referencedColumnName = "idAgente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Agente idAgente;
    @JoinColumn(name = "idPlan", referencedColumnName = "idPlan")
    @ManyToOne(fetch = FetchType.LAZY)
    private PlanServicio idPlan;

    public PlanAgente() {
    }

    public PlanAgente(Integer idPlanAgente) {
        this.idPlanAgente = idPlanAgente;
    }

    public Integer getIdPlanAgente() {
        return idPlanAgente;
    }

    public void setIdPlanAgente(Integer idPlanAgente) {
        this.idPlanAgente = idPlanAgente;
    }

    public Agente getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(Agente idAgente) {
        this.idAgente = idAgente;
    }

    public PlanServicio getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(PlanServicio idPlan) {
        this.idPlan = idPlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanAgente != null ? idPlanAgente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanAgente)) {
            return false;
        }
        PlanAgente other = (PlanAgente) object;
        if ((this.idPlanAgente == null && other.idPlanAgente != null) || (this.idPlanAgente != null && !this.idPlanAgente.equals(other.idPlanAgente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.miumg.bd.PlanAgente[ idPlanAgente=" + idPlanAgente + " ]";
    }*/
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlanAgente", nullable = false)
    private Integer idPlanAgente;
    @JoinColumn(name = "idAgente", referencedColumnName = "idAgente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Agente idAgente;
    @JoinColumn(name = "idPlan", referencedColumnName = "idPlan")
    @ManyToOne(fetch = FetchType.LAZY)
    private PlanServicio idPlan;

    public PlanAgente() {
    }

    public PlanAgente(Integer idPlanAgente) {
        this.idPlanAgente = idPlanAgente;
    }

    public Integer getIdPlanAgente() {
        return idPlanAgente;
    }

    public void setIdPlanAgente(Integer idPlanAgente) {
        this.idPlanAgente = idPlanAgente;
    }

    public Agente getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(Agente idAgente) {
        this.idAgente = idAgente;
    }

    public PlanServicio getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(PlanServicio idPlan) {
        this.idPlan = idPlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanAgente != null ? idPlanAgente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PlanAgente)) {
            return false;
        }
        PlanAgente other = (PlanAgente) object;
        if ((this.idPlanAgente == null && other.idPlanAgente != null) || (this.idPlanAgente != null && !this.idPlanAgente.equals(other.idPlanAgente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.miumg.bd.PlanAgente[ idPlanAgente=" + idPlanAgente + " ]";
    }
    
}
