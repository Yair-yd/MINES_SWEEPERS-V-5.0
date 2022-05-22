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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByBoardID", query = "SELECT u FROM Users u WHERE u.boardID = :boardID"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByMode", query = "SELECT u FROM Users u WHERE u.mode = :mode"),
    @NamedQuery(name = "Users.findByIPAdrees", query = "SELECT u FROM Users u WHERE u.iPAdrees = :iPAdrees")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Board_ID")
    private Integer boardID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Mode")
    private String mode;
    @Column(name = "IP_Adrees")
    private String iPAdrees;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
    private Boards boards;

    public Users() {
    }

    public Users(Integer boardID) {
        this.boardID = boardID;
    }

    public Users(Integer boardID, String name) {
        this.boardID = boardID;
        this.name = name;
    }

    public Integer getBoardID() {
        return boardID;
    }

    public void setBoardID(Integer boardID) {
        this.boardID = boardID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getIPAdrees() {
        return iPAdrees;
    }

    public void setIPAdrees(String iPAdrees) {
        this.iPAdrees = iPAdrees;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.boardID == null && other.boardID != null) || (this.boardID != null && !this.boardID.equals(other.boardID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conn.Users[ boardID=" + boardID + " ]";
    }
    
}
