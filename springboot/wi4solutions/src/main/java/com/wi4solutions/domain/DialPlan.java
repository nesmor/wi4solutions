package com.wi4solutions.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DialPlan.
 */
@Entity
@Table(name = "dial_plan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DialPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "digit_cut")
    private String digitCut;

    @Column(name = "preceding")
    private String preceding;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "jhi_limit")
    private Integer limit;
    
    @Column(name = "status")
    private boolean status = false;

    @ManyToOne    @JoinColumn(name="gateway_id")
    private Gateway gateway;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DialPlan name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public DialPlan description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrefix() {
        return prefix;
    }

    public DialPlan prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDigitCut() {
        return digitCut;
    }

    public DialPlan digitCut(String digitCut) {
        this.digitCut = digitCut;
        return this;
    }

    public void setDigitCut(String digitCut) {
        this.digitCut = digitCut;
    }

    public String getPreceding() {
        return preceding;
    }

    public DialPlan preceding(String preceding) {
        this.preceding = preceding;
        return this;
    }

    public void setPreceding(String preceding) {
        this.preceding = preceding;
    }

    public Integer getPriority() {
        return priority;
    }

    public DialPlan priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getLimit() {
        return limit;
    }

    public DialPlan limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    
    public boolean getStatus() {
        return status;
    }

    public DialPlan status(boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public DialPlan gateway(Gateway gateway) {
        this.gateway = gateway;
        return this;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
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
        DialPlan dialPlan = (DialPlan) o;
        if (dialPlan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dialPlan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DialPlan{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", prefix='" + getPrefix() + "'" +
            ", digitCut='" + getDigitCut() + "'" +
            ", preceding='" + getPreceding() + "'" +
            ", priority=" + getPriority() +
            ", limit=" + getLimit() +
            "}";
    }
}
