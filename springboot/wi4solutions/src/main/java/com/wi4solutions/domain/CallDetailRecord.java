package com.wi4solutions.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A CallDetailRecord.
 */
@Entity
@Table(name = "call_detail_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CallDetailRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calldate")
    private ZonedDateTime calldate;

    @Column(name = "clid")
    private String clid;

    @Column(name = "src")
    private String src;

    @Column(name = "dst")
    private String dst;

    @Column(name = "dcontext")
    private String dcontext;

    @Column(name = "channel")
    private String channel;

    @Column(name = "dstchannel")
    private String dstchannel;

    @Column(name = "lastapp")
    private String lastapp;

    @Column(name = "lastdata")
    private String lastdata;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "billsec")
    private Integer billsec;

    @Column(name = "disposition")
    private String disposition;

    @Column(name = "amaflags")
    private Integer amaflags;

    @Column(name = "accountcode")
    private String accountcode;

    @Column(name = "uniqueid")
    private String uniqueid;

    @Column(name = "userfield")
    private String userfield;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCalldate() {
        return calldate;
    }

    public CallDetailRecord calldate(ZonedDateTime calldate) {
        this.calldate = calldate;
        return this;
    }

    public void setCalldate(ZonedDateTime calldate) {
        this.calldate = calldate;
    }

    public String getClid() {
        return clid;
    }

    public CallDetailRecord clid(String clid) {
        this.clid = clid;
        return this;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    public String getSrc() {
        return src;
    }

    public CallDetailRecord src(String src) {
        this.src = src;
        return this;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public CallDetailRecord dst(String dst) {
        this.dst = dst;
        return this;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getDcontext() {
        return dcontext;
    }

    public CallDetailRecord dcontext(String dcontext) {
        this.dcontext = dcontext;
        return this;
    }

    public void setDcontext(String dcontext) {
        this.dcontext = dcontext;
    }

    public String getChannel() {
        return channel;
    }

    public CallDetailRecord channel(String channel) {
        this.channel = channel;
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDstchannel() {
        return dstchannel;
    }

    public CallDetailRecord dstchannel(String dstchannel) {
        this.dstchannel = dstchannel;
        return this;
    }

    public void setDstchannel(String dstchannel) {
        this.dstchannel = dstchannel;
    }

    public String getLastapp() {
        return lastapp;
    }

    public CallDetailRecord lastapp(String lastapp) {
        this.lastapp = lastapp;
        return this;
    }

    public void setLastapp(String lastapp) {
        this.lastapp = lastapp;
    }

    public String getLastdata() {
        return lastdata;
    }

    public CallDetailRecord lastdata(String lastdata) {
        this.lastdata = lastdata;
        return this;
    }

    public void setLastdata(String lastdata) {
        this.lastdata = lastdata;
    }

    public Integer getDuration() {
        return duration;
    }

    public CallDetailRecord duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getBillsec() {
        return billsec;
    }

    public CallDetailRecord billsec(Integer billsec) {
        this.billsec = billsec;
        return this;
    }

    public void setBillsec(Integer billsec) {
        this.billsec = billsec;
    }

    public String getDisposition() {
        return disposition;
    }

    public CallDetailRecord disposition(String disposition) {
        this.disposition = disposition;
        return this;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public Integer getAmaflags() {
        return amaflags;
    }

    public CallDetailRecord amaflags(Integer amaflags) {
        this.amaflags = amaflags;
        return this;
    }

    public void setAmaflags(Integer amaflags) {
        this.amaflags = amaflags;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public CallDetailRecord accountcode(String accountcode) {
        this.accountcode = accountcode;
        return this;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public CallDetailRecord uniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
        return this;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getUserfield() {
        return userfield;
    }

    public CallDetailRecord userfield(String userfield) {
        this.userfield = userfield;
        return this;
    }

    public void setUserfield(String userfield) {
        this.userfield = userfield;
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
        CallDetailRecord callDetailRecord = (CallDetailRecord) o;
        if (callDetailRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), callDetailRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CallDetailRecord{" +
            "id=" + getId() +
            ", calldate='" + getCalldate() + "'" +
            ", clid='" + getClid() + "'" +
            ", src='" + getSrc() + "'" +
            ", dst='" + getDst() + "'" +
            ", dcontext='" + getDcontext() + "'" +
            ", channel='" + getChannel() + "'" +
            ", dstchannel='" + getDstchannel() + "'" +
            ", lastapp='" + getLastapp() + "'" +
            ", lastdata='" + getLastdata() + "'" +
            ", duration=" + getDuration() +
            ", billsec=" + getBillsec() +
            ", disposition='" + getDisposition() + "'" +
            ", amaflags=" + getAmaflags() +
            ", accountcode='" + getAccountcode() + "'" +
            ", uniqueid='" + getUniqueid() + "'" +
            ", userfield='" + getUserfield() + "'" +
            "}";
    }
}
