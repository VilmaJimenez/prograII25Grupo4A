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
public class AgenteJpaController implements Serializable {

    public AgenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Agente agente) {
        if (agente.getPlanAgenteList() == null) {
            agente.setPlanAgenteList(new ArrayList<PlanAgente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idUsuario = agente.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                agente.setIdUsuario(idUsuario);
            }
            List<PlanAgente> attachedPlanAgenteList = new ArrayList<PlanAgente>();
            for (PlanAgente planAgenteListPlanAgenteToAttach : agente.getPlanAgenteList()) {
                planAgenteListPlanAgenteToAttach = em.getReference(planAgenteListPlanAgenteToAttach.getClass(), planAgenteListPlanAgenteToAttach.getIdPlanAgente());
                attachedPlanAgenteList.add(planAgenteListPlanAgenteToAttach);
            }
            agente.setPlanAgenteList(attachedPlanAgenteList);
            em.persist(agente);
            if (idUsuario != null) {
                idUsuario.getAgenteList().add(agente);
                idUsuario = em.merge(idUsuario);
            }
            for (PlanAgente planAgenteListPlanAgente : agente.getPlanAgenteList()) {
                Agente oldIdAgenteOfPlanAgenteListPlanAgente = planAgenteListPlanAgente.getIdAgente();
                planAgenteListPlanAgente.setIdAgente(agente);
                planAgenteListPlanAgente = em.merge(planAgenteListPlanAgente);
                if (oldIdAgenteOfPlanAgenteListPlanAgente != null) {
                    oldIdAgenteOfPlanAgenteListPlanAgente.getPlanAgenteList().remove(planAgenteListPlanAgente);
                    oldIdAgenteOfPlanAgenteListPlanAgente = em.merge(oldIdAgenteOfPlanAgenteListPlanAgente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Agente agente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agente persistentAgente = em.find(Agente.class, agente.getIdAgente());
            Usuario idUsuarioOld = persistentAgente.getIdUsuario();
            Usuario idUsuarioNew = agente.getIdUsuario();
            List<PlanAgente> planAgenteListOld = persistentAgente.getPlanAgenteList();
            List<PlanAgente> planAgenteListNew = agente.getPlanAgenteList();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                agente.setIdUsuario(idUsuarioNew);
            }
            List<PlanAgente> attachedPlanAgenteListNew = new ArrayList<PlanAgente>();
            for (PlanAgente planAgenteListNewPlanAgenteToAttach : planAgenteListNew) {
                planAgenteListNewPlanAgenteToAttach = em.getReference(planAgenteListNewPlanAgenteToAttach.getClass(), planAgenteListNewPlanAgenteToAttach.getIdPlanAgente());
                attachedPlanAgenteListNew.add(planAgenteListNewPlanAgenteToAttach);
            }
            planAgenteListNew = attachedPlanAgenteListNew;
            agente.setPlanAgenteList(planAgenteListNew);
            agente = em.merge(agente);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getAgenteList().remove(agente);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getAgenteList().add(agente);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (PlanAgente planAgenteListOldPlanAgente : planAgenteListOld) {
                if (!planAgenteListNew.contains(planAgenteListOldPlanAgente)) {
                    planAgenteListOldPlanAgente.setIdAgente(null);
                    planAgenteListOldPlanAgente = em.merge(planAgenteListOldPlanAgente);
                }
            }
            for (PlanAgente planAgenteListNewPlanAgente : planAgenteListNew) {
                if (!planAgenteListOld.contains(planAgenteListNewPlanAgente)) {
                    Agente oldIdAgenteOfPlanAgenteListNewPlanAgente = planAgenteListNewPlanAgente.getIdAgente();
                    planAgenteListNewPlanAgente.setIdAgente(agente);
                    planAgenteListNewPlanAgente = em.merge(planAgenteListNewPlanAgente);
                    if (oldIdAgenteOfPlanAgenteListNewPlanAgente != null && !oldIdAgenteOfPlanAgenteListNewPlanAgente.equals(agente)) {
                        oldIdAgenteOfPlanAgenteListNewPlanAgente.getPlanAgenteList().remove(planAgenteListNewPlanAgente);
                        oldIdAgenteOfPlanAgenteListNewPlanAgente = em.merge(oldIdAgenteOfPlanAgenteListNewPlanAgente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = agente.getIdAgente();
                if (findAgente(id) == null) {
                    throw new NonexistentEntityException("The agente with id " + id + " no longer exists.");
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
            Agente agente;
            try {
                agente = em.getReference(Agente.class, id);
                agente.getIdAgente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agente with id " + id + " no longer exists.", enfe);
            }
            Usuario idUsuario = agente.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getAgenteList().remove(agente);
                idUsuario = em.merge(idUsuario);
            }
            List<PlanAgente> planAgenteList = agente.getPlanAgenteList();
            for (PlanAgente planAgenteListPlanAgente : planAgenteList) {
                planAgenteListPlanAgente.setIdAgente(null);
                planAgenteListPlanAgente = em.merge(planAgenteListPlanAgente);
            }
            em.remove(agente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Agente> findAgenteEntities() {
        return findAgenteEntities(true, -1, -1);
    }

    public List<Agente> findAgenteEntities(int maxResults, int firstResult) {
        return findAgenteEntities(false, maxResults, firstResult);
    }

    private List<Agente> findAgenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Agente.class));
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

    public Agente findAgente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Agente.class, id);
        } finally {
            em.close();
        }
    }

    public int getAgenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Agente> rt = cq.from(Agente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
