
import conn.Boards;
import conn.BoardsJpaController;
import conn.TableDemo;
import conn.TableDemoJpaController;
import conn.exceptions.PreexistingEntityException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author YAIR.D
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PreexistingEntityException, Exception {
        Boards u1 = new Boards();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mines_Sweppers_PU");
        BoardsJpaController s = new BoardsJpaController(emf);
        u1.setBoardID(444);
        u1.setLength(444);
        u1.setWidth(444);
        s.create(u1);
    }

}
