/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conn;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "Boards")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boards.findAll", query = "SELECT b FROM Boards b"),
    @NamedQuery(name = "Boards.findByBoardID", query = "SELECT b FROM Boards b WHERE b.boardID = :boardID"),
    @NamedQuery(name = "Boards.findByLength", query = "SELECT b FROM Boards b WHERE b.length = :length"),
    @NamedQuery(name = "Boards.findByWidth", query = "SELECT b FROM Boards b WHERE b.width = :width")})
public class Boards implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Board_ID")
    private Integer boardID;
    @Column(name = "Length")
    private Integer length;
    @Column(name = "Width")
    private Integer width;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "boards")
    private Moves moves;
    @JoinColumn(name = "Board_ID", referencedColumnName = "Board_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;

    public Boards() {
    }

    public Boards(Integer boardID) {
        this.boardID = boardID;
    }

    public Integer getBoardID() {
        return boardID;
    }

    public void setBoardID(Integer boardID) {
        this.boardID = boardID;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Moves getMoves() {
        return moves;
    }

    public void setMoves(Moves moves) {
        this.moves = moves;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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
        if (!(object instanceof Boards)) {
            return false;
        }
        Boards other = (Boards) object;
        if ((this.boardID == null && other.boardID != null) || (this.boardID != null && !this.boardID.equals(other.boardID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conn.Boards[ boardID=" + boardID + " ]";
    }
    
}
