/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import gt.edu.miumg.bd.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author whiteHat
 */
public class FacturaJpaController implements Serializable {

   /* public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        if (factura.getDetalleList() == null) {
            factura.setDetalleList(new ArrayList<Detalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = factura.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                factura.setIdCliente(idCliente);
            }
            List<Detalle> attachedDetalleList = new ArrayList<Detalle>();
            for (Detalle detalleListDetalleToAttach : factura.getDetalleList()) {
                detalleListDetalleToAttach = em.getReference(detalleListDetalleToAttach.getClass(), detalleListDetalleToAttach.getIdDetalle());
                attachedDetalleList.add(detalleListDetalleToAttach);
            }
            factura.setDetalleList(attachedDetalleList);
            em.persist(factura);
            if (idCliente != null) {
                idCliente.getFacturaList().add(factura);
                idCliente = em.merge(idCliente);
            }
            for (Detalle detalleListDetalle : factura.getDetalleList()) {
                Factura oldIdFacturaOfDetalleListDetalle = detalleListDetalle.getIdFactura();
                detalleListDetalle.setIdFactura(factura);
                detalleListDetalle = em.merge(detalleListDetalle);
                if (oldIdFacturaOfDetalleListDetalle != null) {
                    oldIdFacturaOfDetalleListDetalle.getDetalleList().remove(detalleListDetalle);
                    oldIdFacturaOfDetalleListDetalle = em.merge(oldIdFacturaOfDetalleListDetalle);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdFactura());
            Cliente idClienteOld = persistentFactura.getIdCliente();
            Cliente idClienteNew = factura.getIdCliente();
            List<Detalle> detalleListOld = persistentFactura.getDetalleList();
            List<Detalle> detalleListNew = factura.getDetalleList();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                factura.setIdCliente(idClienteNew);
            }
            List<Detalle> attachedDetalleListNew = new ArrayList<Detalle>();
            for (Detalle detalleListNewDetalleToAttach : detalleListNew) {
                detalleListNewDetalleToAttach = em.getReference(detalleListNewDetalleToAttach.getClass(), detalleListNewDetalleToAttach.getIdDetalle());
                attachedDetalleListNew.add(detalleListNewDetalleToAttach);
            }
            detalleListNew = attachedDetalleListNew;
            factura.setDetalleList(detalleListNew);
            factura = em.merge(factura);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getFacturaList().remove(factura);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getFacturaList().add(factura);
                idClienteNew = em.merge(idClienteNew);
            }
            for (Detalle detalleListOldDetalle : detalleListOld) {
                if (!detalleListNew.contains(detalleListOldDetalle)) {
                    detalleListOldDetalle.setIdFactura(null);
                    detalleListOldDetalle = em.merge(detalleListOldDetalle);
                }
            }
            for (Detalle detalleListNewDetalle : detalleListNew) {
                if (!detalleListOld.contains(detalleListNewDetalle)) {
                    Factura oldIdFacturaOfDetalleListNewDetalle = detalleListNewDetalle.getIdFactura();
                    detalleListNewDetalle.setIdFactura(factura);
                    detalleListNewDetalle = em.merge(detalleListNewDetalle);
                    if (oldIdFacturaOfDetalleListNewDetalle != null && !oldIdFacturaOfDetalleListNewDetalle.equals(factura)) {
                        oldIdFacturaOfDetalleListNewDetalle.getDetalleList().remove(detalleListNewDetalle);
                        oldIdFacturaOfDetalleListNewDetalle = em.merge(oldIdFacturaOfDetalleListNewDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdFactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            Cliente idCliente = factura.getIdCliente();
            if (idCliente != null) {
                idCliente.getFacturaList().remove(factura);
                idCliente = em.merge(idCliente);
            }
            List<Detalle> detalleList = factura.getDetalleList();
            for (Detalle detalleListDetalle : detalleList) {
                detalleListDetalle.setIdFactura(null);
                detalleListDetalle = em.merge(detalleListDetalle);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }*/
    
    //cambios de benito
    
    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        if (factura.getDetalleList() == null) {
            factura.setDetalleList(new ArrayList<Detalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = factura.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                factura.setIdCliente(idCliente);
            }
            List<Detalle> attachedDetalleList = new ArrayList<Detalle>();
            for (Detalle detalleListDetalleToAttach : factura.getDetalleList()) {
                detalleListDetalleToAttach = em.getReference(detalleListDetalleToAttach.getClass(), detalleListDetalleToAttach.getIdDetalle());
                attachedDetalleList.add(detalleListDetalleToAttach);
            }
            factura.setDetalleList(attachedDetalleList);
            em.persist(factura);
            if (idCliente != null) {
                idCliente.getFacturaList().add(factura);
                idCliente = em.merge(idCliente);
            }
            for (Detalle detalleListDetalle : factura.getDetalleList()) {
                Factura oldIdFacturaOfDetalleListDetalle = detalleListDetalle.getIdFactura();
                detalleListDetalle.setIdFactura(factura);
                detalleListDetalle = em.merge(detalleListDetalle);
                if (oldIdFacturaOfDetalleListDetalle != null) {
                    oldIdFacturaOfDetalleListDetalle.getDetalleList().remove(detalleListDetalle);
                    oldIdFacturaOfDetalleListDetalle = em.merge(oldIdFacturaOfDetalleListDetalle);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdFactura());
            Cliente idClienteOld = persistentFactura.getIdCliente();
            Cliente idClienteNew = factura.getIdCliente();
            List<Detalle> detalleListOld = persistentFactura.getDetalleList();
            List<Detalle> detalleListNew = factura.getDetalleList();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                factura.setIdCliente(idClienteNew);
            }
            List<Detalle> attachedDetalleListNew = new ArrayList<Detalle>();
            for (Detalle detalleListNewDetalleToAttach : detalleListNew) {
                detalleListNewDetalleToAttach = em.getReference(detalleListNewDetalleToAttach.getClass(), detalleListNewDetalleToAttach.getIdDetalle());
                attachedDetalleListNew.add(detalleListNewDetalleToAttach);
            }
            detalleListNew = attachedDetalleListNew;
            factura.setDetalleList(detalleListNew);
            factura = em.merge(factura);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getFacturaList().remove(factura);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getFacturaList().add(factura);
                idClienteNew = em.merge(idClienteNew);
            }
            for (Detalle detalleListOldDetalle : detalleListOld) {
                if (!detalleListNew.contains(detalleListOldDetalle)) {
                    detalleListOldDetalle.setIdFactura(null);
                    detalleListOldDetalle = em.merge(detalleListOldDetalle);
                }
            }
            for (Detalle detalleListNewDetalle : detalleListNew) {
                if (!detalleListOld.contains(detalleListNewDetalle)) {
                    Factura oldIdFacturaOfDetalleListNewDetalle = detalleListNewDetalle.getIdFactura();
                    detalleListNewDetalle.setIdFactura(factura);
                    detalleListNewDetalle = em.merge(detalleListNewDetalle);
                    if (oldIdFacturaOfDetalleListNewDetalle != null && !oldIdFacturaOfDetalleListNewDetalle.equals(factura)) {
                        oldIdFacturaOfDetalleListNewDetalle.getDetalleList().remove(detalleListNewDetalle);
                        oldIdFacturaOfDetalleListNewDetalle = em.merge(oldIdFacturaOfDetalleListNewDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdFactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            Cliente idCliente = factura.getIdCliente();
            if (idCliente != null) {
                idCliente.getFacturaList().remove(factura);
                idCliente = em.merge(idCliente);
            }
            List<Detalle> detalleList = factura.getDetalleList();
            for (Detalle detalleListDetalle : detalleList) {
                detalleListDetalle.setIdFactura(null);
                detalleListDetalle = em.merge(detalleListDetalle);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Factura> query = em.createQuery(
                    "SELECT DISTINCT f FROM Factura f LEFT JOIN FETCH f.detalleList d LEFT JOIN FETCH d.idServicio LEFT JOIN FETCH d.idPlan",
                    Factura.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
