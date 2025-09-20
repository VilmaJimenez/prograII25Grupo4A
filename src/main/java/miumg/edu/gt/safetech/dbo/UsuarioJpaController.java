/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miumg.edu.gt.safetech.dbo;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import miumg.edu.gt.safetech.controller.Rol;
import miumg.edu.gt.safetech.controller.Agente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import miumg.edu.gt.safetech.controller.Usuario;
import miumg.edu.gt.safetech.exceptions.NonexistentEntityException;

/**
 *
 * @author Julissa
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getAgenteList() == null) {
            usuario.setAgenteList(new ArrayList<Agente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol idRol = usuario.getIdRol();
            if (idRol != null) {
                idRol = em.getReference(idRol.getClass(), idRol.getIdRol());
                usuario.setIdRol(idRol);
            }
            List<Agente> attachedAgenteList = new ArrayList<Agente>();
            for (Agente agenteListAgenteToAttach : usuario.getAgenteList()) {
                agenteListAgenteToAttach = em.getReference(agenteListAgenteToAttach.getClass(), agenteListAgenteToAttach.getIdAgente());
                attachedAgenteList.add(agenteListAgenteToAttach);
            }
            usuario.setAgenteList(attachedAgenteList);
            em.persist(usuario);
            if (idRol != null) {
                idRol.getUsuarioList().add(usuario);
                idRol = em.merge(idRol);
            }
            for (Agente agenteListAgente : usuario.getAgenteList()) {
                Usuario oldIdUsuarioOfAgenteListAgente = agenteListAgente.getIdUsuario();
                agenteListAgente.setIdUsuario(usuario);
                agenteListAgente = em.merge(agenteListAgente);
                if (oldIdUsuarioOfAgenteListAgente != null) {
                    oldIdUsuarioOfAgenteListAgente.getAgenteList().remove(agenteListAgente);
                    oldIdUsuarioOfAgenteListAgente = em.merge(oldIdUsuarioOfAgenteListAgente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Rol idRolOld = persistentUsuario.getIdRol();
            Rol idRolNew = usuario.getIdRol();
            List<Agente> agenteListOld = persistentUsuario.getAgenteList();
            List<Agente> agenteListNew = usuario.getAgenteList();
            if (idRolNew != null) {
                idRolNew = em.getReference(idRolNew.getClass(), idRolNew.getIdRol());
                usuario.setIdRol(idRolNew);
            }
            List<Agente> attachedAgenteListNew = new ArrayList<Agente>();
            for (Agente agenteListNewAgenteToAttach : agenteListNew) {
                agenteListNewAgenteToAttach = em.getReference(agenteListNewAgenteToAttach.getClass(), agenteListNewAgenteToAttach.getIdAgente());
                attachedAgenteListNew.add(agenteListNewAgenteToAttach);
            }
            agenteListNew = attachedAgenteListNew;
            usuario.setAgenteList(agenteListNew);
            usuario = em.merge(usuario);
            if (idRolOld != null && !idRolOld.equals(idRolNew)) {
                idRolOld.getUsuarioList().remove(usuario);
                idRolOld = em.merge(idRolOld);
            }
            if (idRolNew != null && !idRolNew.equals(idRolOld)) {
                idRolNew.getUsuarioList().add(usuario);
                idRolNew = em.merge(idRolNew);
            }
            for (Agente agenteListOldAgente : agenteListOld) {
                if (!agenteListNew.contains(agenteListOldAgente)) {
                    agenteListOldAgente.setIdUsuario(null);
                    agenteListOldAgente = em.merge(agenteListOldAgente);
                }
            }
            for (Agente agenteListNewAgente : agenteListNew) {
                if (!agenteListOld.contains(agenteListNewAgente)) {
                    Usuario oldIdUsuarioOfAgenteListNewAgente = agenteListNewAgente.getIdUsuario();
                    agenteListNewAgente.setIdUsuario(usuario);
                    agenteListNewAgente = em.merge(agenteListNewAgente);
                    if (oldIdUsuarioOfAgenteListNewAgente != null && !oldIdUsuarioOfAgenteListNewAgente.equals(usuario)) {
                        oldIdUsuarioOfAgenteListNewAgente.getAgenteList().remove(agenteListNewAgente);
                        oldIdUsuarioOfAgenteListNewAgente = em.merge(oldIdUsuarioOfAgenteListNewAgente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Rol idRol = usuario.getIdRol();
            if (idRol != null) {
                idRol.getUsuarioList().remove(usuario);
                idRol = em.merge(idRol);
            }
            List<Agente> agenteList = usuario.getAgenteList();
            for (Agente agenteListAgente : agenteList) {
                agenteListAgente.setIdUsuario(null);
                agenteListAgente = em.merge(agenteListAgente);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
