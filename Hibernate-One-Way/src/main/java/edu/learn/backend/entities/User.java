package edu.learn.backend.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Noman Ali on 12/25/2016.
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {
    private String userId;
    private String password;
    private String email;
    private Firm firm;
    private Configuration configuration;

    @Id
    @Column(name = "USERNAME")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "USER_PWD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "USER_EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @ManyToOne(targetEntity = Configuration.class , fetch = FetchType.LAZY)
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Id
    @ManyToOne(targetEntity = Firm.class , fetch = FetchType.LAZY)
    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getUserId().equals(user.getUserId())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        return getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        int result = java.util.Objects.hash(getUserId());
        result = result + java.util.Objects.hash(getPassword());
        result = result + java.util.Objects.hash(getEmail());
        return result;
    }
}
