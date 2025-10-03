/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author whiteHat
 */
@Entity
@Table(name = "Cliente", catalog = "SafeTechProd", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nitCliente"})})
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;
    @Basic(optional = false)
    @Column(name = "nitCliente", nullable = false, length = 50)
    private String nitCliente;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;
    @Basic(optional = false)
    @Column(name = "telefono", nullable = false)
    private int telefono;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Column(name = "observacion", length = 300)
    private String observacion;
    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<PlanServicio> planServicioList;
    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<Factura> facturaList;

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(Integer idCliente, String nitCliente, String nombre, String direccion, int telefono, String email) {
        this.idCliente = idCliente;
        this.nitCliente = nitCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<PlanServicio> getPlanServicioList() {
        return planServicioList;
    }

    public void setPlanServicioList(List<PlanServicio> planServicioList) {
        this.planServicioList = planServicioList;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.miumg.bd.Cliente[ idCliente=" + idCliente + " ]";
    }
    
}
