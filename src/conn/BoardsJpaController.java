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
public class BoardsJpaController implements Serializable {

    public BoardsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Boards boards) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Users usersOrphanCheck = boards.getUsers();
        if (usersOrphanCheck != null) {
            Boards oldBoardsOfUsers = usersOrphanCheck.getBoards();
            if (oldBoardsOfUsers != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Users " + usersOrphanCheck + " already has an item of type Boards whose users column cannot be null. Please make another selection for the users field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moves moves = boards.getMoves();
            if (moves != null) {
                moves = em.getReference(moves.getClass(), moves.getBoardID());
                boards.setMoves(moves);
            }
            Users users = boards.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getBoardID());
                boards.setUsers(users);
            }
            em.persist(boards);
            if (moves != null) {
                Boards oldBoardsOfMoves = moves.getBoards();
                if (oldBoardsOfMoves != null) {
                    oldBoardsOfMoves.setMoves(null);
                    oldBoardsOfMoves = em.merge(oldBoardsOfMoves);
                }
                moves.setBoards(boards);
                moves = em.merge(moves);
            }
            if (users != null) {
                users.setBoards(boards);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBoards(boards.getBoardID()) != null) {
                throw new PreexistingEntityException("Boards " + boards + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Boards boards) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Boards persistentBoards = em.find(Boards.class, boards.getBoardID());
            Moves movesOld = persistentBoards.getMoves();
            Moves movesNew = boards.getMoves();
            Users usersOld = persistentBoards.getUsers();
            Users usersNew = boards.getUsers();
            List<String> illegalOrphanMessages = null;
            if (movesOld != null && !movesOld.equals(movesNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Moves " + movesOld + " since its boards field is not nullable.");
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                Boards oldBoardsOfUsers = usersNew.getBoards();
                if (oldBoardsOfUsers != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Users " + usersNew + " already has an item of type Boards whose users column cannot be null. Please make another selection for the users field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (movesNew != null) {
                movesNew = em.getReference(movesNew.getClass(), movesNew.getBoardID());
                boards.setMoves(movesNew);
            }
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getBoardID());
                boards.setUsers(usersNew);
            }
            boards = em.merge(boards);
            if (movesNew != null && !movesNew.equals(movesOld)) {
                Boards oldBoardsOfMoves = movesNew.getBoards();
                if (oldBoardsOfMoves != null) {
                    oldBoardsOfMoves.setMoves(null);
                    oldBoardsOfMoves = em.merge(oldBoardsOfMoves);
                }
                movesNew.setBoards(boards);
                movesNew = em.merge(movesNew);
            }
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.setBoards(null);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.setBoards(boards);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = boards.getBoardID();
                if (findBoards(id) == null) {
                    throw new NonexistentEntityException("The boards with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Boards boards;
            try {
                boards = em.getReference(Boards.class, id);
                boards.getBoardID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The boards with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Moves movesOrphanCheck = boards.getMoves();
            if (movesOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Boards (" + boards + ") cannot be destroyed since the Moves " + movesOrphanCheck + " in its moves field has a non-nullable boards field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Users users = boards.getUsers();
            if (users != null) {
                users.setBoards(null);
                users = em.merge(users);
            }
            em.remove(boards);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Boards> findBoardsEntities() {
        return findBoardsEntities(true, -1, -1);
    }

    public List<Boards> findBoardsEntities(int maxResults, int firstResult) {
        return findBoardsEntities(false, maxResults, firstResult);
    }

    private List<Boards> findBoardsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Boards.class));
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

    public Boards findBoards(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Boards.class, id);
        } finally {
            em.close();
        }
    }

    public int getBoardsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Boards> rt = cq.from(Boards.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
