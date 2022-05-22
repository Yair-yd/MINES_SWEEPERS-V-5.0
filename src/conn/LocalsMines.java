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
@Table(name = "Local's_Mines")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocalsMines.findAll", query = "SELECT l FROM LocalsMines l"),
    @NamedQuery(name = "LocalsMines.findByLocalX", query = "SELECT l FROM LocalsMines l WHERE l.localX = :localX"),
    @NamedQuery(name = "LocalsMines.findByLocalY", query = "SELECT l FROM LocalsMines l WHERE l.localY = :localY")})
public class LocalsMines implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Local_X")
    private Integer localX;
    @Column(name = "Local_Y")
    private Integer localY;

    public LocalsMines() {
    }

    public LocalsMines(Integer localX) {
        this.localX = localX;
    }

    public Integer getLocalX() {
        return localX;
    }

    public void setLocalX(Integer localX) {
        this.localX = localX;
    }

    public Integer getLocalY() {
        return localY;
    }

    public void setLocalY(Integer localY) {
        this.localY = localY;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localX != null ? localX.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalsMines)) {
            return false;
        }
        LocalsMines other = (LocalsMines) object;
        if ((this.localX == null && other.localX != null) || (this.localX != null && !this.localX.equals(other.localX))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conn.LocalsMines[ localX=" + localX + " ]";
    }
    
}
