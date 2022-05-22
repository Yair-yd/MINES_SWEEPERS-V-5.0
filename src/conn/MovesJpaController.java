/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conn;

import conn.exceptions.IllegalOrphanException;
import conn.exceptions.NonexistentEntityException;
import conn.exceptions.PreexistingEntityException;
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
 * @author YAIR.D
 */
public class MovesJpaController implements Serializable {

    public MovesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Moves moves) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Boards boardsOrphanCheck = moves.getBoards();
        if (boardsOrphanCheck != null) {
            Moves oldMovesOfBoards = boardsOrphanCheck.getMoves();
            if (oldMovesOfBoards != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Boards " + boardsOrphanCheck + " already has an item of type Moves whose boards column cannot be null. Please make another selection for the boards field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Boards boards = moves.getBoards();
            if (boards != null) {
                boards = em.getReference(boards.getClass(), boards.getBoardID());
                moves.setBoards(boards);
            }
            em.persist(moves);
            if (boards != null) {
                boards.setMoves(moves);
                boards = em.merge(boards);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMoves(moves.getBoardID()) != null) {
                throw new PreexistingEntityException("Moves " + moves + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Moves moves) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moves persistentMoves = em.find(Moves.class, moves.getBoardID());
            Boards boardsOld = persistentMoves.getBoards();
            Boards boardsNew = moves.getBoards();
            List<String> illegalOrphanMessages = null;
            if (boardsNew != null && !boardsNew.equals(boardsOld)) {
                Moves oldMovesOfBoards = boardsNew.getMoves();
                if (oldMovesOfBoards != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Boards " + boardsNew + " already has an item of type Moves whose boards column cannot be null. Please make another selection for the boards field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (boardsNew != null) {
                boardsNew = em.getReference(boardsNew.getClass(), boardsNew.getBoardID());
                moves.setBoards(boardsNew);
            }
            moves = em.merge(moves);
            if (boardsOld != null && !boardsOld.equals(boardsNew)) {
                boardsOld.setMoves(null);
                boardsOld = em.merge(boardsOld);
            }
            if (boardsNew != null && !boardsNew.equals(boardsOld)) {
                boardsNew.setMoves(moves);
                boardsNew = em.merge(boardsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = moves.getBoardID();
                if (findMoves(id) == null) {
                    throw new NonexistentEntityException("The moves with id " + id + " no longer exists.");
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
            Moves moves;
            try {
                moves = em.getReference(Moves.class, id);
                moves.getBoardID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The moves with id " + id + " no longer exists.", enfe);
            }
            Boards boards = moves.getBoards();
            if (boards != null) {
                boards.setMoves(null);
                boards = em.merge(boards);
            }
            em.remove(moves);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Moves> findMovesEntities() {
        return findMovesEntities(true, -1, -1);
    }

    public List<Moves> findMovesEntities(int maxResults, int firstResult) {
        return findMovesEntities(false, maxResults, firstResult);
    }

    private List<Moves> findMovesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Moves.class));
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

    public Moves findMoves(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Moves.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Moves> rt = cq.from(Moves.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
