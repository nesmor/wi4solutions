package com.wi4solutions.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SipPeer.
 */
@Entity
@Table(name = "sip_peer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SipPeer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "host")
    private String host;

    @Column(name = "nat")
    private String nat;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "accountcode")
    private String accountcode;

    @Column(name = "amaflags")
    private String amaflags;

    @Column(name = "`call-limit`")
    private Integer calllimit;

    @Column(name = "callgroup")
    private String callgroup;

    @Column(name = "callerid")
    private String callerid;

    @Column(name = "cancallforward")
    private String cancallforward;

    @Column(name = "canreinvite")
    private String canreinvite;

    @Column(name = "context")
    private String context;

    @Column(name = "defaultip")
    private String defaultip;

    @Column(name = "dtmfmode")
    private String dtmfmode;

    @Column(name = "fromuser")
    private String fromuser;

    @Column(name = "fromdomain")
    private String fromdomain;

    @Column(name = "insecure")
    private String insecure;

    @Column(name = "language")
    private String language;

    @Column(name = "mailbox")
    private String mailbox;

    @Column(name = "md5secret")
    private String md5secret;

    @Column(name = "jhi_deny")
    private String deny;

    @Column(name = "permit")
    private String permit;

    @Column(name = "mask")
    private String mask;

    @Column(name = "musiconhold")
    private String musiconhold;

    @Column(name = "pickupgroup")
    private String pickupgroup;

    @Column(name = "qualify")
    private String qualify;

    @Column(name = "regexten")
    private String regexten;

    @Column(name = "restrictcid")
    private String restrictcid;

    @Column(name = "rtptimeout")
    private String rtptimeout;

    @Column(name = "rtpholdtimeout")
    private String rtpholdtimeout;

    @Column(name = "secret")
    private String secret;

    @Column(name = "setvar")
    private String setvar;

    @Column(name = "disallow")
    private String disallow;

    @Column(name = "allow")
    private String allow;

    @Column(name = "fullcontact")
    private String fullcontact;

    @Column(name = "ipaddr")
    private String ipaddr;

    @Column(name = "port")
    private Integer port;

    @Column(name = "regserver")
    private String regserver;

    @Column(name = "regseconds")
    private Integer regseconds;

    @Column(name = "lastms")
    private Integer lastms;

    @Column(name = "username")
    private String username;

    @Column(name = "defaultuser")
    private String defaultuser;

    @Column(name = "subscribecontext")
    private String subscribecontext;

    @Column(name = "useragent")
    private String useragent;

    @OneToOne(mappedBy = "sipPeer")
    @JsonIgnore
    private DialPlan dialPlan;

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

    public SipPeer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public SipPeer host(String host) {
        this.host = host;
        return this;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getNat() {
        return nat;
    }

    public SipPeer nat(String nat) {
        this.nat = nat;
        return this;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public String getType() {
        return type;
    }

    public SipPeer type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public SipPeer accountcode(String accountcode) {
        this.accountcode = accountcode;
        return this;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public String getAmaflags() {
        return amaflags;
    }

    public SipPeer amaflags(String amaflags) {
        this.amaflags = amaflags;
        return this;
    }

    public void setAmaflags(String amaflags) {
        this.amaflags = amaflags;
    }

    public Integer getCalllimit() {
        return calllimit;
    }

    public SipPeer calllimit(Integer calllimit) {
        this.calllimit = calllimit;
        return this;
    }

    public void setCalllimit(Integer calllimit) {
        this.calllimit = calllimit;
    }

    public String getCallgroup() {
        return callgroup;
    }

    public SipPeer callgroup(String callgroup) {
        this.callgroup = callgroup;
        return this;
    }

    public void setCallgroup(String callgroup) {
        this.callgroup = callgroup;
    }

    public String getCallerid() {
        return callerid;
    }

    public SipPeer callerid(String callerid) {
        this.callerid = callerid;
        return this;
    }

    public void setCallerid(String callerid) {
        this.callerid = callerid;
    }

    public String getCancallforward() {
        return cancallforward;
    }

    public SipPeer cancallforward(String cancallforward) {
        this.cancallforward = cancallforward;
        return this;
    }

    public void setCancallforward(String cancallforward) {
        this.cancallforward = cancallforward;
    }

    public String getCanreinvite() {
        return canreinvite;
    }

    public SipPeer canreinvite(String canreinvite) {
        this.canreinvite = canreinvite;
        return this;
    }

    public void setCanreinvite(String canreinvite) {
        this.canreinvite = canreinvite;
    }

    public String getContext() {
        return context;
    }

    public SipPeer context(String context) {
        this.context = context;
        return this;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getDefaultip() {
        return defaultip;
    }

    public SipPeer defaultip(String defaultip) {
        this.defaultip = defaultip;
        return this;
    }

    public void setDefaultip(String defaultip) {
        this.defaultip = defaultip;
    }

    public String getDtmfmode() {
        return dtmfmode;
    }

    public SipPeer dtmfmode(String dtmfmode) {
        this.dtmfmode = dtmfmode;
        return this;
    }

    public void setDtmfmode(String dtmfmode) {
        this.dtmfmode = dtmfmode;
    }

    public String getFromuser() {
        return fromuser;
    }

    public SipPeer fromuser(String fromuser) {
        this.fromuser = fromuser;
        return this;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getFromdomain() {
        return fromdomain;
    }

    public SipPeer fromdomain(String fromdomain) {
        this.fromdomain = fromdomain;
        return this;
    }

    public void setFromdomain(String fromdomain) {
        this.fromdomain = fromdomain;
    }

    public String getInsecure() {
        return insecure;
    }

    public SipPeer insecure(String insecure) {
        this.insecure = insecure;
        return this;
    }

    public void setInsecure(String insecure) {
        this.insecure = insecure;
    }

    public String getLanguage() {
        return language;
    }

    public SipPeer language(String language) {
        this.language = language;
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMailbox() {
        return mailbox;
    }

    public SipPeer mailbox(String mailbox) {
        this.mailbox = mailbox;
        return this;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getMd5secret() {
        return md5secret;
    }

    public SipPeer md5secret(String md5secret) {
        this.md5secret = md5secret;
        return this;
    }

    public void setMd5secret(String md5secret) {
        this.md5secret = md5secret;
    }

    public String getDeny() {
        return deny;
    }

    public SipPeer deny(String deny) {
        this.deny = deny;
        return this;
    }

    public void setDeny(String deny) {
        this.deny = deny;
    }

    public String getPermit() {
        return permit;
    }

    public SipPeer permit(String permit) {
        this.permit = permit;
        return this;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getMask() {
        return mask;
    }

    public SipPeer mask(String mask) {
        this.mask = mask;
        return this;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getMusiconhold() {
        return musiconhold;
    }

    public SipPeer musiconhold(String musiconhold) {
        this.musiconhold = musiconhold;
        return this;
    }

    public void setMusiconhold(String musiconhold) {
        this.musiconhold = musiconhold;
    }

    public String getPickupgroup() {
        return pickupgroup;
    }

    public SipPeer pickupgroup(String pickupgroup) {
        this.pickupgroup = pickupgroup;
        return this;
    }

    public void setPickupgroup(String pickupgroup) {
        this.pickupgroup = pickupgroup;
    }

    public String getQualify() {
        return qualify;
    }

    public SipPeer qualify(String qualify) {
        this.qualify = qualify;
        return this;
    }

    public void setQualify(String qualify) {
        this.qualify = qualify;
    }

    public String getRegexten() {
        return regexten;
    }

    public SipPeer regexten(String regexten) {
        this.regexten = regexten;
        return this;
    }

    public void setRegexten(String regexten) {
        this.regexten = regexten;
    }

    public String getRestrictcid() {
        return restrictcid;
    }

    public SipPeer restrictcid(String restrictcid) {
        this.restrictcid = restrictcid;
        return this;
    }

    public void setRestrictcid(String restrictcid) {
        this.restrictcid = restrictcid;
    }

    public String getRtptimeout() {
        return rtptimeout;
    }

    public SipPeer rtptimeout(String rtptimeout) {
        this.rtptimeout = rtptimeout;
        return this;
    }

    public void setRtptimeout(String rtptimeout) {
        this.rtptimeout = rtptimeout;
    }

    public String getRtpholdtimeout() {
        return rtpholdtimeout;
    }

    public SipPeer rtpholdtimeout(String rtpholdtimeout) {
        this.rtpholdtimeout = rtpholdtimeout;
        return this;
    }

    public void setRtpholdtimeout(String rtpholdtimeout) {
        this.rtpholdtimeout = rtpholdtimeout;
    }

    public String getSecret() {
        return secret;
    }

    public SipPeer secret(String secret) {
        this.secret = secret;
        return this;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSetvar() {
        return setvar;
    }

    public SipPeer setvar(String setvar) {
        this.setvar = setvar;
        return this;
    }

    public void setSetvar(String setvar) {
        this.setvar = setvar;
    }

    public String getDisallow() {
        return disallow;
    }

    public SipPeer disallow(String disallow) {
        this.disallow = disallow;
        return this;
    }

    public void setDisallow(String disallow) {
        this.disallow = disallow;
    }

    public String getAllow() {
        return allow;
    }

    public SipPeer allow(String allow) {
        this.allow = allow;
        return this;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    public String getFullcontact() {
        return fullcontact;
    }

    public SipPeer fullcontact(String fullcontact) {
        this.fullcontact = fullcontact;
        return this;
    }

    public void setFullcontact(String fullcontact) {
        this.fullcontact = fullcontact;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public SipPeer ipaddr(String ipaddr) {
        this.ipaddr = ipaddr;
        return this;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public Integer getPort() {
        return port;
    }

    public SipPeer port(Integer port) {
        this.port = port;
        return this;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getRegserver() {
        return regserver;
    }

    public SipPeer regserver(String regserver) {
        this.regserver = regserver;
        return this;
    }

    public void setRegserver(String regserver) {
        this.regserver = regserver;
    }

    public Integer getRegseconds() {
        return regseconds;
    }

    public SipPeer regseconds(Integer regseconds) {
        this.regseconds = regseconds;
        return this;
    }

    public void setRegseconds(Integer regseconds) {
        this.regseconds = regseconds;
    }

    public Integer getLastms() {
        return lastms;
    }

    public SipPeer lastms(Integer lastms) {
        this.lastms = lastms;
        return this;
    }

    public void setLastms(Integer lastms) {
        this.lastms = lastms;
    }

    public String getUsername() {
        return username;
    }

    public SipPeer username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDefaultuser() {
        return defaultuser;
    }

    public SipPeer defaultuser(String defaultuser) {
        this.defaultuser = defaultuser;
        return this;
    }

    public void setDefaultuser(String defaultuser) {
        this.defaultuser = defaultuser;
    }

    public String getSubscribecontext() {
        return subscribecontext;
    }

    public SipPeer subscribecontext(String subscribecontext) {
        this.subscribecontext = subscribecontext;
        return this;
    }

    public void setSubscribecontext(String subscribecontext) {
        this.subscribecontext = subscribecontext;
    }

    public String getUseragent() {
        return useragent;
    }

    public SipPeer useragent(String useragent) {
        this.useragent = useragent;
        return this;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public DialPlan getDialPlan() {
        return dialPlan;
    }

    public SipPeer dialPlan(DialPlan dialPlan) {
        this.dialPlan = dialPlan;
        return this;
    }

    public void setDialPlan(DialPlan dialPlan) {
        this.dialPlan = dialPlan;
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
        SipPeer sipPeer = (SipPeer) o;
        if (sipPeer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sipPeer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SipPeer{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", host='" + getHost() + "'" +
            ", nat='" + getNat() + "'" +
            ", type='" + getType() + "'" +
            ", accountcode='" + getAccountcode() + "'" +
            ", amaflags='" + getAmaflags() + "'" +
            ", calllimit=" + getCalllimit() +
            ", callgroup='" + getCallgroup() + "'" +
            ", callerid='" + getCallerid() + "'" +
            ", cancallforward='" + getCancallforward() + "'" +
            ", canreinvite='" + getCanreinvite() + "'" +
            ", context='" + getContext() + "'" +
            ", defaultip='" + getDefaultip() + "'" +
            ", dtmfmode='" + getDtmfmode() + "'" +
            ", fromuser='" + getFromuser() + "'" +
            ", fromdomain='" + getFromdomain() + "'" +
            ", insecure='" + getInsecure() + "'" +
            ", language='" + getLanguage() + "'" +
            ", mailbox='" + getMailbox() + "'" +
            ", md5secret='" + getMd5secret() + "'" +
            ", deny='" + getDeny() + "'" +
            ", permit='" + getPermit() + "'" +
            ", mask='" + getMask() + "'" +
            ", musiconhold='" + getMusiconhold() + "'" +
            ", pickupgroup='" + getPickupgroup() + "'" +
            ", qualify='" + getQualify() + "'" +
            ", regexten='" + getRegexten() + "'" +
            ", restrictcid='" + getRestrictcid() + "'" +
            ", rtptimeout='" + getRtptimeout() + "'" +
            ", rtpholdtimeout='" + getRtpholdtimeout() + "'" +
            ", secret='" + getSecret() + "'" +
            ", setvar='" + getSetvar() + "'" +
            ", disallow='" + getDisallow() + "'" +
            ", allow='" + getAllow() + "'" +
            ", fullcontact='" + getFullcontact() + "'" +
            ", ipaddr='" + getIpaddr() + "'" +
            ", port=" + getPort() +
            ", regserver='" + getRegserver() + "'" +
            ", regseconds=" + getRegseconds() +
            ", lastms=" + getLastms() +
            ", username='" + getUsername() + "'" +
            ", defaultuser='" + getDefaultuser() + "'" +
            ", subscribecontext='" + getSubscribecontext() + "'" +
            ", useragent='" + getUseragent() + "'" +
            "}";
    }
}
