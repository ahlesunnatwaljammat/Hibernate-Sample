package edu.learn.backend.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Blob;

/**
 * Created by Noman Ali on 12/24/2016.
 */
@Entity
@Table(name = "CONFIGURATION")
public class Configuration implements Serializable {
    private String salt;
    private Blob configuration;
    private Firm firm;

    /**
     * For two way binding use the following:
     * @GenericGenerator(name = "generator", strategy = "foreign",
     * parameters = @org.hibernate.annotations.Parameter(name = "property", value = "firm"))
     * @Id
     * @GeneratedValue(generator = "generator")
     * @Column(name = "FIRM_ID", unique = true, nullable = false)
     * public Integer getFirmId() {
     *   return firmId;
     * }

     * public void setFirmId(Integer firmId) {
     *  this.firmId = firmId;
     * }*/

    @Column(name = "PWD_SALT")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Column(name = "CONFIGURATION")
    public Blob getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Blob configuration) {
        this.configuration = configuration;
    }

    /**
     * For two way binding use the following:
     * @OneToOne(fetch = FetchType.LAZY)
       @PrimaryKeyJoinColumn
    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }*/

    /**
     * For two way binding use the following:
     * @OneToOne(fetch = FetchType.LAZY, mappedBy = "firm", cascade = CascadeType.ALL)
     *
     * @return
     */
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="FIRM_ID")
    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Configuration)) return false;

        Configuration that = (Configuration) o;

        return getSalt().equals(that.getSalt());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(getSalt());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Configuration{");
        sb.append(", salt='").append(salt).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
