/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conn;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YAIR.D
 */
@Entity
@Table(name = "Users_Demo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersDemo.findAll", query = "SELECT u FROM UsersDemo u"),
    @NamedQuery(name = "UsersDemo.findById", query = "SELECT u FROM UsersDemo u WHERE u.id = :id"),
    @NamedQuery(name = "UsersDemo.findByDateOfBirth", query = "SELECT u FROM UsersDemo u WHERE u.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "UsersDemo.findByAge", query = "SELECT u FROM UsersDemo u WHERE u.age = :age")})
public class UsersDemo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "First_Name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "Date_Of_Birth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    @Basic(optional = false)
    @Column(name = "Age")
    private int age;

    public UsersDemo() {
    }

    public UsersDemo(Integer id) {
        this.id = id;
    }

    public UsersDemo(Integer id, String firstName, Date dateOfBirth, int age) {
        this.id = id;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        if (!(object instanceof UsersDemo)) {
            return false;
        }
        UsersDemo other = (UsersDemo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conn.UsersDemo[ id=" + id + " ]";
    }
    
}
