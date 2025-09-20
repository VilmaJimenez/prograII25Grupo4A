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
import miumg.edu.gt.safetech.controller.PlanServicio;
import miumg.edu.gt.safetech.controller.Detalle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import miumg.edu.gt.safetech.controller.Servicio;
import miumg.edu.gt.safetech.exceptions.NonexistentEntityException;

/**
 *
 * @author Julissa
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getDetalleList() == null) {
            servicio.setDetalleList(new ArrayList<Detalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanServicio idPlan = servicio.getIdPlan();
            if (idPlan != null) {
                idPlan = em.getReference(idPlan.getClass(), idPlan.getIdPlan());
                servicio.setIdPlan(idPlan);
            }
            List<Detalle> attachedDetalleList = new ArrayList<Detalle>();
            for (Detalle detalleListDetalleToAttach : servicio.getDetalleList()) {
                detalleListDetalleToAttach = em.getReference(detalleListDetalleToAttach.getClass(), detalleListDetalleToAttach.getIdDetalle());
                attachedDetalleList.add(detalleListDetalleToAttach);
            }
            servicio.setDetalleList(attachedDetalleList);
            em.persist(servicio);
            if (idPlan != null) {
                idPlan.getServicioList().add(servicio);
                idPlan = em.merge(idPlan);
            }
            for (Detalle detalleListDetalle : servicio.getDetalleList()) {
                Servicio oldIdServicioOfDetalleListDetalle = detalleListDetalle.getIdServicio();
                detalleListDetalle.setIdServicio(servicio);
                detalleListDetalle = em.merge(detalleListDetalle);
                if (oldIdServicioOfDetalleListDetalle != null) {
                    oldIdServicioOfDetalleListDetalle.getDetalleList().remove(detalleListDetalle);
                    oldIdServicioOfDetalleListDetalle = em.merge(oldIdServicioOfDetalleListDetalle);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getIdServicio());
            PlanServicio idPlanOld = persistentServicio.getIdPlan();
            PlanServicio idPlanNew = servicio.getIdPlan();
            List<Detalle> detalleListOld = persistentServicio.getDetalleList();
            List<Detalle> detalleListNew = servicio.getDetalleList();
            if (idPlanNew != null) {
                idPlanNew = em.getReference(idPlanNew.getClass(), idPlanNew.getIdPlan());
                servicio.setIdPlan(idPlanNew);
            }
            List<Detalle> attachedDetalleListNew = new ArrayList<Detalle>();
            for (Detalle detalleListNewDetalleToAttach : detalleListNew) {
                detalleListNewDetalleToAttach = em.getReference(detalleListNewDetalleToAttach.getClass(), detalleListNewDetalleToAttach.getIdDetalle());
                attachedDetalleListNew.add(detalleListNewDetalleToAttach);
            }
            detalleListNew = attachedDetalleListNew;
            servicio.setDetalleList(detalleListNew);
            servicio = em.merge(servicio);
            if (idPlanOld != null && !idPlanOld.equals(idPlanNew)) {
                idPlanOld.getServicioList().remove(servicio);
                idPlanOld = em.merge(idPlanOld);
            }
            if (idPlanNew != null && !idPlanNew.equals(idPlanOld)) {
                idPlanNew.getServicioList().add(servicio);
                idPlanNew = em.merge(idPlanNew);
            }
            for (Detalle detalleListOldDetalle : detalleListOld) {
                if (!detalleListNew.contains(detalleListOldDetalle)) {
                    detalleListOldDetalle.setIdServicio(null);
                    detalleListOldDetalle = em.merge(detalleListOldDetalle);
                }
            }
            for (Detalle detalleListNewDetalle : detalleListNew) {
                if (!detalleListOld.contains(detalleListNewDetalle)) {
                    Servicio oldIdServicioOfDetalleListNewDetalle = detalleListNewDetalle.getIdServicio();
                    detalleListNewDetalle.setIdServicio(servicio);
                    detalleListNewDetalle = em.merge(detalleListNewDetalle);
                    if (oldIdServicioOfDetalleListNewDetalle != null && !oldIdServicioOfDetalleListNewDetalle.equals(servicio)) {
                        oldIdServicioOfDetalleListNewDetalle.getDetalleList().remove(detalleListNewDetalle);
                        oldIdServicioOfDetalleListNewDetalle = em.merge(oldIdServicioOfDetalleListNewDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicio.getIdServicio();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getIdServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            PlanServicio idPlan = servicio.getIdPlan();
            if (idPlan != null) {
                idPlan.getServicioList().remove(servicio);
                idPlan = em.merge(idPlan);
            }
            List<Detalle> detalleList = servicio.getDetalleList();
            for (Detalle detalleListDetalle : detalleList) {
                detalleListDetalle.setIdServicio(null);
                detalleListDetalle = em.merge(detalleListDetalle);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
