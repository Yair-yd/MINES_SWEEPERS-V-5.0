/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conn;

import conn.exceptions.NonexistentEntityException;
import conn.exceptions.PreexistingEntityException;
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
 * @author YAIR.D
 */
public class Users1JpaController implements Serializable {

    public Users1JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users1 users1) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(users1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsers1(users1.getId()) != null) {
                throw new PreexistingEntityException("Users1 " + users1 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users1 users1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            users1 = em.merge(users1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = users1.getId();
                if (findUsers1(id) == null) {
                    throw new NonexistentEntityException("The users1 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users1 users1;
            try {
                users1 = em.getReference(Users1.class, id);
                users1.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users1 with id " + id + " no longer exists.", enfe);
            }
            em.remove(users1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users1> findUsers1Entities() {
        return findUsers1Entities(true, -1, -1);
    }

    public List<Users1> findUsers1Entities(int maxResults, int firstResult) {
        return findUsers1Entities(false, maxResults, firstResult);
    }

    private List<Users1> findUsers1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users1.class));
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

    public Users1 findUsers1(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users1.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsers1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users1> rt = cq.from(Users1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
