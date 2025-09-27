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
@Table(name = "Agente", catalog = "SafeTechProd", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Agente.findAll", query = "SELECT a FROM Agente a")})
public class Agente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAgente", nullable = false)
    private Integer idAgente;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;
    @Basic(optional = false)
    @Column(name = "edad", nullable = false)
    private int edad;
    @Basic(optional = false)
    @Column(name = "residencia", nullable = false, length = 100)
    private String residencia;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private boolean estado;
    @Basic(optional = false)
    @Column(name = "telefono", nullable = false)
    private int telefono;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idAgente", fetch = FetchType.LAZY)
    private List<PlanAgente> planAgenteList;

    public Agente() {
    }

    public Agente(Integer idAgente) {
        this.idAgente = idAgente;
    }

    public Agente(Integer idAgente, String nombre, String apellido, int edad, String residencia, boolean estado, int telefono) {
        this.idAgente = idAgente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.residencia = residencia;
        this.estado = estado;
        this.telefono = telefono;
    }

    public Integer getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(Integer idAgente) {
        this.idAgente = idAgente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        hash += (idAgente != null ? idAgente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agente)) {
            return false;
        }
        Agente other = (Agente) object;
        if ((this.idAgente == null && other.idAgente != null) || (this.idAgente != null && !this.idAgente.equals(other.idAgente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.miumg.bd.Agente[ idAgente=" + idAgente + " ]";
    }
    
}
