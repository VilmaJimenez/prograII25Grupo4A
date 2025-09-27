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
public class PlanServicioJpaController implements Serializable {

    public PlanServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlanServicio planServicio) {
        if (planServicio.getPlanAgenteList() == null) {
            planServicio.setPlanAgenteList(new ArrayList<PlanAgente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = planServicio.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                planServicio.setIdCliente(idCliente);
            }
            Servicio idServicio = planServicio.getIdServicio();
            if (idServicio != null) {
                idServicio = em.getReference(idServicio.getClass(), idServicio.getIdServicio());
                planServicio.setIdServicio(idServicio);
            }
            List<PlanAgente> attachedPlanAgenteList = new ArrayList<PlanAgente>();
            for (PlanAgente planAgenteListPlanAgenteToAttach : planServicio.getPlanAgenteList()) {
                planAgenteListPlanAgenteToAttach = em.getReference(planAgenteListPlanAgenteToAttach.getClass(), planAgenteListPlanAgenteToAttach.getIdPlanAgente());
                attachedPlanAgenteList.add(planAgenteListPlanAgenteToAttach);
            }
            planServicio.setPlanAgenteList(attachedPlanAgenteList);
            em.persist(planServicio);
            if (idCliente != null) {
                idCliente.getPlanServicioList().add(planServicio);
                idCliente = em.merge(idCliente);
            }
            if (idServicio != null) {
                idServicio.getPlanServicioList().add(planServicio);
                idServicio = em.merge(idServicio);
            }
            for (PlanAgente planAgenteListPlanAgente : planServicio.getPlanAgenteList()) {
                PlanServicio oldIdPlanOfPlanAgenteListPlanAgente = planAgenteListPlanAgente.getIdPlan();
                planAgenteListPlanAgente.setIdPlan(planServicio);
                planAgenteListPlanAgente = em.merge(planAgenteListPlanAgente);
                if (oldIdPlanOfPlanAgenteListPlanAgente != null) {
                    oldIdPlanOfPlanAgenteListPlanAgente.getPlanAgenteList().remove(planAgenteListPlanAgente);
                    oldIdPlanOfPlanAgenteListPlanAgente = em.merge(oldIdPlanOfPlanAgenteListPlanAgente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlanServicio planServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanServicio persistentPlanServicio = em.find(PlanServicio.class, planServicio.getIdPlan());
            Cliente idClienteOld = persistentPlanServicio.getIdCliente();
            Cliente idClienteNew = planServicio.getIdCliente();
            Servicio idServicioOld = persistentPlanServicio.getIdServicio();
            Servicio idServicioNew = planServicio.getIdServicio();
            List<PlanAgente> planAgenteListOld = persistentPlanServicio.getPlanAgenteList();
            List<PlanAgente> planAgenteListNew = planServicio.getPlanAgenteList();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                planServicio.setIdCliente(idClienteNew);
            }
            if (idServicioNew != null) {
                idServicioNew = em.getReference(idServicioNew.getClass(), idServicioNew.getIdServicio());
                planServicio.setIdServicio(idServicioNew);
            }
            List<PlanAgente> attachedPlanAgenteListNew = new ArrayList<PlanAgente>();
            for (PlanAgente planAgenteListNewPlanAgenteToAttach : planAgenteListNew) {
                planAgenteListNewPlanAgenteToAttach = em.getReference(planAgenteListNewPlanAgenteToAttach.getClass(), planAgenteListNewPlanAgenteToAttach.getIdPlanAgente());
                attachedPlanAgenteListNew.add(planAgenteListNewPlanAgenteToAttach);
            }
            planAgenteListNew = attachedPlanAgenteListNew;
            planServicio.setPlanAgenteList(planAgenteListNew);
            planServicio = em.merge(planServicio);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getPlanServicioList().remove(planServicio);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getPlanServicioList().add(planServicio);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idServicioOld != null && !idServicioOld.equals(idServicioNew)) {
                idServicioOld.getPlanServicioList().remove(planServicio);
                idServicioOld = em.merge(idServicioOld);
            }
            if (idServicioNew != null && !idServicioNew.equals(idServicioOld)) {
                idServicioNew.getPlanServicioList().add(planServicio);
                idServicioNew = em.merge(idServicioNew);
            }
            for (PlanAgente planAgenteListOldPlanAgente : planAgenteListOld) {
                if (!planAgenteListNew.contains(planAgenteListOldPlanAgente)) {
                    planAgenteListOldPlanAgente.setIdPlan(null);
                    planAgenteListOldPlanAgente = em.merge(planAgenteListOldPlanAgente);
                }
            }
            for (PlanAgente planAgenteListNewPlanAgente : planAgenteListNew) {
                if (!planAgenteListOld.contains(planAgenteListNewPlanAgente)) {
                    PlanServicio oldIdPlanOfPlanAgenteListNewPlanAgente = planAgenteListNewPlanAgente.getIdPlan();
                    planAgenteListNewPlanAgente.setIdPlan(planServicio);
                    planAgenteListNewPlanAgente = em.merge(planAgenteListNewPlanAgente);
                    if (oldIdPlanOfPlanAgenteListNewPlanAgente != null && !oldIdPlanOfPlanAgenteListNewPlanAgente.equals(planServicio)) {
                        oldIdPlanOfPlanAgenteListNewPlanAgente.getPlanAgenteList().remove(planAgenteListNewPlanAgente);
                        oldIdPlanOfPlanAgenteListNewPlanAgente = em.merge(oldIdPlanOfPlanAgenteListNewPlanAgente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planServicio.getIdPlan();
                if (findPlanServicio(id) == null) {
                    throw new NonexistentEntityException("The planServicio with id " + id + " no longer exists.");
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
            PlanServicio planServicio;
            try {
                planServicio = em.getReference(PlanServicio.class, id);
                planServicio.getIdPlan();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planServicio with id " + id + " no longer exists.", enfe);
            }
            Cliente idCliente = planServicio.getIdCliente();
            if (idCliente != null) {
                idCliente.getPlanServicioList().remove(planServicio);
                idCliente = em.merge(idCliente);
            }
            Servicio idServicio = planServicio.getIdServicio();
            if (idServicio != null) {
                idServicio.getPlanServicioList().remove(planServicio);
                idServicio = em.merge(idServicio);
            }
            List<PlanAgente> planAgenteList = planServicio.getPlanAgenteList();
            for (PlanAgente planAgenteListPlanAgente : planAgenteList) {
                planAgenteListPlanAgente.setIdPlan(null);
                planAgenteListPlanAgente = em.merge(planAgenteListPlanAgente);
            }
            em.remove(planServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlanServicio> findPlanServicioEntities() {
        return findPlanServicioEntities(true, -1, -1);
    }

    public List<PlanServicio> findPlanServicioEntities(int maxResults, int firstResult) {
        return findPlanServicioEntities(false, maxResults, firstResult);
    }

    private List<PlanServicio> findPlanServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PlanServicio.class));
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

    public PlanServicio findPlanServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlanServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlanServicio> rt = cq.from(PlanServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
