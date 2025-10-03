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
public class PlanAgenteJpaController implements Serializable {

    public PlanAgenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlanAgente planAgente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agente idAgente = planAgente.getIdAgente();
            if (idAgente != null) {
                idAgente = em.getReference(idAgente.getClass(), idAgente.getIdAgente());
                planAgente.setIdAgente(idAgente);
            }
            PlanServicio idPlan = planAgente.getIdPlan();
            if (idPlan != null) {
                idPlan = em.getReference(idPlan.getClass(), idPlan.getIdPlan());
                planAgente.setIdPlan(idPlan);
            }
            em.persist(planAgente);
            if (idAgente != null) {
                idAgente.getPlanAgenteList().add(planAgente);
                idAgente = em.merge(idAgente);
            }
            if (idPlan != null) {
                idPlan.getPlanAgenteList().add(planAgente);
                idPlan = em.merge(idPlan);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlanAgente planAgente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanAgente persistentPlanAgente = em.find(PlanAgente.class, planAgente.getIdPlanAgente());
            Agente idAgenteOld = persistentPlanAgente.getIdAgente();
            Agente idAgenteNew = planAgente.getIdAgente();
            PlanServicio idPlanOld = persistentPlanAgente.getIdPlan();
            PlanServicio idPlanNew = planAgente.getIdPlan();
            if (idAgenteNew != null) {
                idAgenteNew = em.getReference(idAgenteNew.getClass(), idAgenteNew.getIdAgente());
                planAgente.setIdAgente(idAgenteNew);
            }
            if (idPlanNew != null) {
                idPlanNew = em.getReference(idPlanNew.getClass(), idPlanNew.getIdPlan());
                planAgente.setIdPlan(idPlanNew);
            }
            planAgente = em.merge(planAgente);
            if (idAgenteOld != null && !idAgenteOld.equals(idAgenteNew)) {
                idAgenteOld.getPlanAgenteList().remove(planAgente);
                idAgenteOld = em.merge(idAgenteOld);
            }
            if (idAgenteNew != null && !idAgenteNew.equals(idAgenteOld)) {
                idAgenteNew.getPlanAgenteList().add(planAgente);
                idAgenteNew = em.merge(idAgenteNew);
            }
            if (idPlanOld != null && !idPlanOld.equals(idPlanNew)) {
                idPlanOld.getPlanAgenteList().remove(planAgente);
                idPlanOld = em.merge(idPlanOld);
            }
            if (idPlanNew != null && !idPlanNew.equals(idPlanOld)) {
                idPlanNew.getPlanAgenteList().add(planAgente);
                idPlanNew = em.merge(idPlanNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planAgente.getIdPlanAgente();
                if (findPlanAgente(id) == null) {
                    throw new NonexistentEntityException("The planAgente with id " + id + " no longer exists.");
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
            PlanAgente planAgente;
            try {
                planAgente = em.getReference(PlanAgente.class, id);
                planAgente.getIdPlanAgente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planAgente with id " + id + " no longer exists.", enfe);
            }
            Agente idAgente = planAgente.getIdAgente();
            if (idAgente != null) {
                idAgente.getPlanAgenteList().remove(planAgente);
                idAgente = em.merge(idAgente);
            }
            PlanServicio idPlan = planAgente.getIdPlan();
            if (idPlan != null) {
                idPlan.getPlanAgenteList().remove(planAgente);
                idPlan = em.merge(idPlan);
            }
            em.remove(planAgente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlanAgente> findPlanAgenteEntities() {
        return findPlanAgenteEntities(true, -1, -1);
    }

    public List<PlanAgente> findPlanAgenteEntities(int maxResults, int firstResult) {
        return findPlanAgenteEntities(false, maxResults, firstResult);
    }

    private List<PlanAgente> findPlanAgenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PlanAgente.class));
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

    public PlanAgente findPlanAgente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlanAgente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanAgenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlanAgente> rt = cq.from(PlanAgente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
