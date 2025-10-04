/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import gt.edu.miumg.bd.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author whiteHat
 */
public class DetalleJpaController implements Serializable {

   
    
     public DetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalle detalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura idFactura = detalle.getIdFactura();
            if (idFactura != null) {
                idFactura = em.getReference(idFactura.getClass(), idFactura.getIdFactura());
                detalle.setIdFactura(idFactura);
            }
            Servicio idServicio = detalle.getIdServicio();
            if (idServicio != null) {
                idServicio = em.getReference(idServicio.getClass(), idServicio.getIdServicio());
                detalle.setIdServicio(idServicio);
            }
            em.persist(detalle);
            if (idFactura != null) {
                idFactura.getDetalleList().add(detalle);
                idFactura = em.merge(idFactura);
            }
            if (idServicio != null) {
                idServicio.getDetalleList().add(detalle);
                idServicio = em.merge(idServicio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalle detalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalle persistentDetalle = em.find(Detalle.class, detalle.getIdDetalle());
            Factura idFacturaOld = persistentDetalle.getIdFactura();
            Factura idFacturaNew = detalle.getIdFactura();
            Servicio idServicioOld = persistentDetalle.getIdServicio();
            Servicio idServicioNew = detalle.getIdServicio();
            if (idFacturaNew != null) {
                idFacturaNew = em.getReference(idFacturaNew.getClass(), idFacturaNew.getIdFactura());
                detalle.setIdFactura(idFacturaNew);
            }
            if (idServicioNew != null) {
                idServicioNew = em.getReference(idServicioNew.getClass(), idServicioNew.getIdServicio());
                detalle.setIdServicio(idServicioNew);
            }
            detalle = em.merge(detalle);
            if (idFacturaOld != null && !idFacturaOld.equals(idFacturaNew)) {
                idFacturaOld.getDetalleList().remove(detalle);
                idFacturaOld = em.merge(idFacturaOld);
            }
            if (idFacturaNew != null && !idFacturaNew.equals(idFacturaOld)) {
                idFacturaNew.getDetalleList().add(detalle);
                idFacturaNew = em.merge(idFacturaNew);
            }
            if (idServicioOld != null && !idServicioOld.equals(idServicioNew)) {
                idServicioOld.getDetalleList().remove(detalle);
                idServicioOld = em.merge(idServicioOld);
            }
            if (idServicioNew != null && !idServicioNew.equals(idServicioOld)) {
                idServicioNew.getDetalleList().add(detalle);
                idServicioNew = em.merge(idServicioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalle.getIdDetalle();
                if (findDetalle(id) == null) {
                    throw new NonexistentEntityException("The detalle with id " + id + " no longer exists.");
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
            Detalle detalle;
            try {
                detalle = em.getReference(Detalle.class, id);
                detalle.getIdDetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalle with id " + id + " no longer exists.", enfe);
            }
            Factura idFactura = detalle.getIdFactura();
            if (idFactura != null) {
                idFactura.getDetalleList().remove(detalle);
                idFactura = em.merge(idFactura);
            }
            Servicio idServicio = detalle.getIdServicio();
            if (idServicio != null) {
                idServicio.getDetalleList().remove(detalle);
                idServicio = em.merge(idServicio);
            }
            em.remove(detalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalle> findDetalleEntities() {
        return findDetalleEntities(true, -1, -1);
    }

    public List<Detalle> findDetalleEntities(int maxResults, int firstResult) {
        return findDetalleEntities(false, maxResults, firstResult);
    }

    private List<Detalle> findDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalle.class));
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

    public Detalle findDetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalle> rt = cq.from(Detalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Detalle> findDetalleByFactura(Factura factura) {
        return getEntityManager()
                .createQuery("SELECT d FROM Detalle d WHERE d.idFactura.idFactura = :facturaId", Detalle.class)
                .setParameter("facturaId", factura.getIdFactura())
                .getResultList();
    }

    public List<Detalle> findDetalleByFactura(Integer idFactura) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Detalle d WHERE d.idFactura.idFactura = :id", Detalle.class)
                    .setParameter("id", idFactura)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
}
