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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YAIR.D
 */
@Entity
@Table(name = "USERS1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users1.findAll", query = "SELECT u FROM Users1 u"),
    @NamedQuery(name = "Users1.findById", query = "SELECT u FROM Users1 u WHERE u.id = :id"),
    @NamedQuery(name = "Users1.findByUserName", query = "SELECT u FROM Users1 u WHERE u.userName = :userName")})
public class Users1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @Column(name = "User_Name")
    private String userName;

    public Users1() {
    }

    public Users1(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users1)) {
            return false;
        }
        Users1 other = (Users1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conn.Users1[ id=" + id + " ]";
    }
    
}
