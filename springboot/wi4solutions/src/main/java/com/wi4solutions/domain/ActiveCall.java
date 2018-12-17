package com.wi4solutions.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ActiveCall.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ActiveCall implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(name = "jhi_number")
    private String number;

    @Column(name = "originator")
    private String originator;

    @Column(name = "ani")
    private String ani;

    @Column(name = "dni")
    private String dni;

    @Column(name = "gateway")
    private String gateway;

    @Column(name = "duration")
    private String duration;

    @Column(name = "status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public ActiveCall number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOriginator() {
        return originator;
    }

    public ActiveCall originator(String originator) {
        this.originator = originator;
        return this;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getAni() {
        return ani;
    }

    public ActiveCall ani(String ani) {
        this.ani = ani;
        return this;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public String getDni() {
        return dni;
    }

    public ActiveCall dni(String dni) {
        this.dni = dni;
        return this;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGateway() {
        return gateway;
    }

    public ActiveCall gateway(String gateway) {
        this.gateway = gateway;
        return this;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getDuration() {
        return duration;
    }

    public ActiveCall duration(String duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public ActiveCall status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActiveCall activeCall = (ActiveCall) o;
        if (activeCall.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), activeCall.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ActiveCall{" +
            "id=" + getId() +
            ", number='" + getNumber() + "'" +
            ", originator='" + getOriginator() + "'" +
            ", ani='" + getAni() + "'" +
            ", dni='" + getDni() + "'" +
            ", gateway='" + getGateway() + "'" +
            ", duration='" + getDuration() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
