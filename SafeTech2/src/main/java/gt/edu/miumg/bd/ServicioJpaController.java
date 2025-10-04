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

/**
 *
 * @author whiteHat
 */
public class ServicioJpaController implements Serializable {

    /*public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getPlanServicioList() == null) {
            servicio.setPlanServicioList(new ArrayList<PlanServicio>());
        }
        if (servicio.getDetalleList() == null) {
            servicio.setDetalleList(new ArrayList<Detalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PlanServicio> attachedPlanServicioList = new ArrayList<PlanServicio>();
            for (PlanServicio planServicioListPlanServicioToAttach : servicio.getPlanServicioList()) {
                planServicioListPlanServicioToAttach = em.getReference(planServicioListPlanServicioToAttach.getClass(), planServicioListPlanServicioToAttach.getIdPlan());
                attachedPlanServicioList.add(planServicioListPlanServicioToAttach);
            }
            servicio.setPlanServicioList(attachedPlanServicioList);
            List<Detalle> attachedDetalleList = new ArrayList<Detalle>();
            for (Detalle detalleListDetalleToAttach : servicio.getDetalleList()) {
                detalleListDetalleToAttach = em.getReference(detalleListDetalleToAttach.getClass(), detalleListDetalleToAttach.getIdDetalle());
                attachedDetalleList.add(detalleListDetalleToAttach);
            }
            servicio.setDetalleList(attachedDetalleList);
            em.persist(servicio);
            for (PlanServicio planServicioListPlanServicio : servicio.getPlanServicioList()) {
                Servicio oldIdServicioOfPlanServicioListPlanServicio = planServicioListPlanServicio.getIdServicio();
                planServicioListPlanServicio.setIdServicio(servicio);
                planServicioListPlanServicio = em.merge(planServicioListPlanServicio);
                if (oldIdServicioOfPlanServicioListPlanServicio != null) {
                    oldIdServicioOfPlanServicioListPlanServicio.getPlanServicioList().remove(planServicioListPlanServicio);
                    oldIdServicioOfPlanServicioListPlanServicio = em.merge(oldIdServicioOfPlanServicioListPlanServicio);
                }
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
            List<PlanServicio> planServicioListOld = persistentServicio.getPlanServicioList();
            List<PlanServicio> planServicioListNew = servicio.getPlanServicioList();
            List<Detalle> detalleListOld = persistentServicio.getDetalleList();
            List<Detalle> detalleListNew = servicio.getDetalleList();
            List<PlanServicio> attachedPlanServicioListNew = new ArrayList<PlanServicio>();
            for (PlanServicio planServicioListNewPlanServicioToAttach : planServicioListNew) {
                planServicioListNewPlanServicioToAttach = em.getReference(planServicioListNewPlanServicioToAttach.getClass(), planServicioListNewPlanServicioToAttach.getIdPlan());
                attachedPlanServicioListNew.add(planServicioListNewPlanServicioToAttach);
            }
            planServicioListNew = attachedPlanServicioListNew;
            servicio.setPlanServicioList(planServicioListNew);
            List<Detalle> attachedDetalleListNew = new ArrayList<Detalle>();
            for (Detalle detalleListNewDetalleToAttach : detalleListNew) {
                detalleListNewDetalleToAttach = em.getReference(detalleListNewDetalleToAttach.getClass(), detalleListNewDetalleToAttach.getIdDetalle());
                attachedDetalleListNew.add(detalleListNewDetalleToAttach);
            }
            detalleListNew = attachedDetalleListNew;
            servicio.setDetalleList(detalleListNew);
            servicio = em.merge(servicio);
            for (PlanServicio planServicioListOldPlanServicio : planServicioListOld) {
                if (!planServicioListNew.contains(planServicioListOldPlanServicio)) {
                    planServicioListOldPlanServicio.setIdServicio(null);
                    planServicioListOldPlanServicio = em.merge(planServicioListOldPlanServicio);
                }
            }
            for (PlanServicio planServicioListNewPlanServicio : planServicioListNew) {
                if (!planServicioListOld.contains(planServicioListNewPlanServicio)) {
                    Servicio oldIdServicioOfPlanServicioListNewPlanServicio = planServicioListNewPlanServicio.getIdServicio();
                    planServicioListNewPlanServicio.setIdServicio(servicio);
                    planServicioListNewPlanServicio = em.merge(planServicioListNewPlanServicio);
                    if (oldIdServicioOfPlanServicioListNewPlanServicio != null && !oldIdServicioOfPlanServicioListNewPlanServicio.equals(servicio)) {
                        oldIdServicioOfPlanServicioListNewPlanServicio.getPlanServicioList().remove(planServicioListNewPlanServicio);
                        oldIdServicioOfPlanServicioListNewPlanServicio = em.merge(oldIdServicioOfPlanServicioListNewPlanServicio);
                    }
                }
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
            List<PlanServicio> planServicioList = servicio.getPlanServicioList();
            for (PlanServicio planServicioListPlanServicio : planServicioList) {
                planServicioListPlanServicio.setIdServicio(null);
                planServicioListPlanServicio = em.merge(planServicioListPlanServicio);
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
    }*/
    
    //CAMBIOS DE BENITO
    
    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getPlanServicioList() == null) {
            servicio.setPlanServicioList(new ArrayList<PlanServicio>());
        }
        if (servicio.getDetalleList() == null) {
            servicio.setDetalleList(new ArrayList<Detalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PlanServicio> attachedPlanServicioList = new ArrayList<PlanServicio>();
            for (PlanServicio planServicioListPlanServicioToAttach : servicio.getPlanServicioList()) {
                planServicioListPlanServicioToAttach = em.getReference(planServicioListPlanServicioToAttach.getClass(), planServicioListPlanServicioToAttach.getIdPlan());
                attachedPlanServicioList.add(planServicioListPlanServicioToAttach);
            }
            servicio.setPlanServicioList(attachedPlanServicioList);
            List<Detalle> attachedDetalleList = new ArrayList<Detalle>();
            for (Detalle detalleListDetalleToAttach : servicio.getDetalleList()) {
                detalleListDetalleToAttach = em.getReference(detalleListDetalleToAttach.getClass(), detalleListDetalleToAttach.getIdDetalle());
                attachedDetalleList.add(detalleListDetalleToAttach);
            }
            servicio.setDetalleList(attachedDetalleList);
            em.persist(servicio);
            for (PlanServicio planServicioListPlanServicio : servicio.getPlanServicioList()) {
                Servicio oldIdServicioOfPlanServicioListPlanServicio = planServicioListPlanServicio.getServicio();
                planServicioListPlanServicio.setServicio(servicio);
                planServicioListPlanServicio = em.merge(planServicioListPlanServicio);
                if (oldIdServicioOfPlanServicioListPlanServicio != null) {
                    oldIdServicioOfPlanServicioListPlanServicio.getPlanServicioList().remove(planServicioListPlanServicio);
                    oldIdServicioOfPlanServicioListPlanServicio = em.merge(oldIdServicioOfPlanServicioListPlanServicio);
                }
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
            List<PlanServicio> planServicioListOld = persistentServicio.getPlanServicioList();
            List<PlanServicio> planServicioListNew = servicio.getPlanServicioList();
            List<Detalle> detalleListOld = persistentServicio.getDetalleList();
            List<Detalle> detalleListNew = servicio.getDetalleList();
            List<PlanServicio> attachedPlanServicioListNew = new ArrayList<PlanServicio>();
            for (PlanServicio planServicioListNewPlanServicioToAttach : planServicioListNew) {
                planServicioListNewPlanServicioToAttach = em.getReference(planServicioListNewPlanServicioToAttach.getClass(), planServicioListNewPlanServicioToAttach.getIdPlan());
                attachedPlanServicioListNew.add(planServicioListNewPlanServicioToAttach);
            }
            planServicioListNew = attachedPlanServicioListNew;
            servicio.setPlanServicioList(planServicioListNew);
            List<Detalle> attachedDetalleListNew = new ArrayList<Detalle>();
            for (Detalle detalleListNewDetalleToAttach : detalleListNew) {
                detalleListNewDetalleToAttach = em.getReference(detalleListNewDetalleToAttach.getClass(), detalleListNewDetalleToAttach.getIdDetalle());
                attachedDetalleListNew.add(detalleListNewDetalleToAttach);
            }
            detalleListNew = attachedDetalleListNew;
            servicio.setDetalleList(detalleListNew);
            servicio = em.merge(servicio);
            for (PlanServicio planServicioListOldPlanServicio : planServicioListOld) {
                if (!planServicioListNew.contains(planServicioListOldPlanServicio)) {
                    planServicioListOldPlanServicio.setServicio(null);
                    planServicioListOldPlanServicio = em.merge(planServicioListOldPlanServicio);
                }
            }
            for (PlanServicio planServicioListNewPlanServicio : planServicioListNew) {
                if (!planServicioListOld.contains(planServicioListNewPlanServicio)) {
                    Servicio oldIdServicioOfPlanServicioListNewPlanServicio = planServicioListNewPlanServicio.getServicio();
                    planServicioListNewPlanServicio.setServicio(servicio);
                    planServicioListNewPlanServicio = em.merge(planServicioListNewPlanServicio);
                    if (oldIdServicioOfPlanServicioListNewPlanServicio != null && !oldIdServicioOfPlanServicioListNewPlanServicio.equals(servicio)) {
                        oldIdServicioOfPlanServicioListNewPlanServicio.getPlanServicioList().remove(planServicioListNewPlanServicio);
                        oldIdServicioOfPlanServicioListNewPlanServicio = em.merge(oldIdServicioOfPlanServicioListNewPlanServicio);
                    }
                }
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
            List<PlanServicio> planServicioList = servicio.getPlanServicioList();
            for (PlanServicio planServicioListPlanServicio : planServicioList) {
                planServicioListPlanServicio.setServicio(null);
                planServicioListPlanServicio = em.merge(planServicioListPlanServicio);
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
