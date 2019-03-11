package edu.learn.backend.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Noman Ali on 12/24/2016.
 */
@Entity
@Table(name = "FIRM")
public class Firm implements Serializable {
    private Integer firmId;
    private String firmName;
    /*private Set<User> users = new HashSet<>();*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FIRM_ID")
    public Integer getFirmId() {
        return firmId;
    }

    public void setFirmId(Integer firmId) {
        this.firmId = firmId;
    }

    @Column(name = "FIRM_NAME")
    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "userId", cascade = CascadeType.ALL)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }*/

    @Override
    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }
        if (obj instanceof Firm) {
            Firm other = (Firm) obj;
            return Objects.equals(firmId, other.firmId) && Objects.equals(firmName, other.firmName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = java.util.Objects.hash(getFirmId());
        result = result + java.util.Objects.hash(getFirmName());
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Firm{");
        sb.append("firmId=").append(firmId);
        sb.append(", firmName='").append(firmName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
