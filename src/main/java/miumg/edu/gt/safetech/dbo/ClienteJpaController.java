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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import miumg.edu.gt.safetech.controller.Cliente;
import miumg.edu.gt.safetech.controller.Factura;
import miumg.edu.gt.safetech.exceptions.NonexistentEntityException;

/**
 *
 * @author Julissa
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getPlanServicioList() == null) {
            cliente.setPlanServicioList(new ArrayList<PlanServicio>());
        }
        if (cliente.getFacturaList() == null) {
            cliente.setFacturaList(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PlanServicio> attachedPlanServicioList = new ArrayList<PlanServicio>();
            for (PlanServicio planServicioListPlanServicioToAttach : cliente.getPlanServicioList()) {
                planServicioListPlanServicioToAttach = em.getReference(planServicioListPlanServicioToAttach.getClass(), planServicioListPlanServicioToAttach.getIdPlan());
                attachedPlanServicioList.add(planServicioListPlanServicioToAttach);
            }
            cliente.setPlanServicioList(attachedPlanServicioList);
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : cliente.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdFactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            cliente.setFacturaList(attachedFacturaList);
            em.persist(cliente);
            for (PlanServicio planServicioListPlanServicio : cliente.getPlanServicioList()) {
                Cliente oldIdClienteOfPlanServicioListPlanServicio = planServicioListPlanServicio.getIdCliente();
                planServicioListPlanServicio.setIdCliente(cliente);
                planServicioListPlanServicio = em.merge(planServicioListPlanServicio);
                if (oldIdClienteOfPlanServicioListPlanServicio != null) {
                    oldIdClienteOfPlanServicioListPlanServicio.getPlanServicioList().remove(planServicioListPlanServicio);
                    oldIdClienteOfPlanServicioListPlanServicio = em.merge(oldIdClienteOfPlanServicioListPlanServicio);
                }
            }
            for (Factura facturaListFactura : cliente.getFacturaList()) {
                Cliente oldIdClienteOfFacturaListFactura = facturaListFactura.getIdCliente();
                facturaListFactura.setIdCliente(cliente);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldIdClienteOfFacturaListFactura != null) {
                    oldIdClienteOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldIdClienteOfFacturaListFactura = em.merge(oldIdClienteOfFacturaListFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            List<PlanServicio> planServicioListOld = persistentCliente.getPlanServicioList();
            List<PlanServicio> planServicioListNew = cliente.getPlanServicioList();
            List<Factura> facturaListOld = persistentCliente.getFacturaList();
            List<Factura> facturaListNew = cliente.getFacturaList();
            List<PlanServicio> attachedPlanServicioListNew = new ArrayList<PlanServicio>();
            for (PlanServicio planServicioListNewPlanServicioToAttach : planServicioListNew) {
                planServicioListNewPlanServicioToAttach = em.getReference(planServicioListNewPlanServicioToAttach.getClass(), planServicioListNewPlanServicioToAttach.getIdPlan());
                attachedPlanServicioListNew.add(planServicioListNewPlanServicioToAttach);
            }
            planServicioListNew = attachedPlanServicioListNew;
            cliente.setPlanServicioList(planServicioListNew);
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdFactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            cliente.setFacturaList(facturaListNew);
            cliente = em.merge(cliente);
            for (PlanServicio planServicioListOldPlanServicio : planServicioListOld) {
                if (!planServicioListNew.contains(planServicioListOldPlanServicio)) {
                    planServicioListOldPlanServicio.setIdCliente(null);
                    planServicioListOldPlanServicio = em.merge(planServicioListOldPlanServicio);
                }
            }
            for (PlanServicio planServicioListNewPlanServicio : planServicioListNew) {
                if (!planServicioListOld.contains(planServicioListNewPlanServicio)) {
                    Cliente oldIdClienteOfPlanServicioListNewPlanServicio = planServicioListNewPlanServicio.getIdCliente();
                    planServicioListNewPlanServicio.setIdCliente(cliente);
                    planServicioListNewPlanServicio = em.merge(planServicioListNewPlanServicio);
                    if (oldIdClienteOfPlanServicioListNewPlanServicio != null && !oldIdClienteOfPlanServicioListNewPlanServicio.equals(cliente)) {
                        oldIdClienteOfPlanServicioListNewPlanServicio.getPlanServicioList().remove(planServicioListNewPlanServicio);
                        oldIdClienteOfPlanServicioListNewPlanServicio = em.merge(oldIdClienteOfPlanServicioListNewPlanServicio);
                    }
                }
            }
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    facturaListOldFactura.setIdCliente(null);
                    facturaListOldFactura = em.merge(facturaListOldFactura);
                }
            }
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Cliente oldIdClienteOfFacturaListNewFactura = facturaListNewFactura.getIdCliente();
                    facturaListNewFactura.setIdCliente(cliente);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldIdClienteOfFacturaListNewFactura != null && !oldIdClienteOfFacturaListNewFactura.equals(cliente)) {
                        oldIdClienteOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldIdClienteOfFacturaListNewFactura = em.merge(oldIdClienteOfFacturaListNewFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<PlanServicio> planServicioList = cliente.getPlanServicioList();
            for (PlanServicio planServicioListPlanServicio : planServicioList) {
                planServicioListPlanServicio.setIdCliente(null);
                planServicioListPlanServicio = em.merge(planServicioListPlanServicio);
            }
            List<Factura> facturaList = cliente.getFacturaList();
            for (Factura facturaListFactura : facturaList) {
                facturaListFactura.setIdCliente(null);
                facturaListFactura = em.merge(facturaListFactura);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
