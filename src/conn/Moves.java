/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conn;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YAIR.D
 */
@Entity
@Table(name = "Moves")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moves.findAll", query = "SELECT m FROM Moves m"),
    @NamedQuery(name = "Moves.findByBoardID", query = "SELECT m FROM Moves m WHERE m.boardID = :boardID"),
    @NamedQuery(name = "Moves.findByLocalX", query = "SELECT m FROM Moves m WHERE m.localX = :localX"),
    @NamedQuery(name = "Moves.findByLocalY", query = "SELECT m FROM Moves m WHERE m.localY = :localY"),
    @NamedQuery(name = "Moves.findByDescreption", query = "SELECT m FROM Moves m WHERE m.descreption = :descreption")})
public class Moves implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Board_ID")
    private Integer boardID;
    @Basic(optional = false)
    @Column(name = "Local_X")
    private int localX;
    @Basic(optional = false)
    @Column(name = "Local_Y")
    private int localY;
    @Column(name = "Descreption")
    private String descreption;
    @JoinColumn(name = "Board_ID", referencedColumnName = "Board_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Boards boards;

    public Moves() {
    }

    public Moves(Integer boardID) {
        this.boardID = boardID;
    }

    public Moves(Integer boardID, int localX, int localY) {
        this.boardID = boardID;
        this.localX = localX;
        this.localY = localY;
    }

    public Integer getBoardID() {
        return boardID;
    }

    public void setBoardID(Integer boardID) {
        this.boardID = boardID;
    }

    public int getLocalX() {
        return localX;
    }

    public void setLocalX(int localX) {
        this.localX = localX;
    }

    public int getLocalY() {
        return localY;
    }

    public void setLocalY(int localY) {
        this.localY = localY;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public Boards getBoards() {
        return boards;
    }

    public void setBoards(Boards boards) {
        this.boards = boards;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boardID != null ? boardID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moves)) {
            return false;
        }
        Moves other = (Moves) object;
        if ((this.boardID == null && other.boardID != null) || (this.boardID != null && !this.boardID.equals(other.boardID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conn.Moves[ boardID=" + boardID + " ]";
    }
    
}
