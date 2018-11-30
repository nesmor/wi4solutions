package com.wi4solutions.web.rest;

import com.wi4solutions.Wi4SolutionsApp;

import com.wi4solutions.domain.SipPeer;
import com.wi4solutions.repository.SipPeerRepository;
import com.wi4solutions.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.wi4solutions.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SipPeerResource REST controller.
 *
 * @see SipPeerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Wi4SolutionsApp.class)
public class SipPeerResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HOST = "AAAAAAAAAA";
    private static final String UPDATED_HOST = "BBBBBBBBBB";

    private static final String DEFAULT_NAT = "AAAAAAAAAA";
    private static final String UPDATED_NAT = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNTCODE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_AMAFLAGS = "AAAAAAAAAA";
    private static final String UPDATED_AMAFLAGS = "BBBBBBBBBB";

    private static final Integer DEFAULT_CALLLIMIT = 1;
    private static final Integer UPDATED_CALLLIMIT = 2;

    private static final String DEFAULT_CALLGROUP = "AAAAAAAAAA";
    private static final String UPDATED_CALLGROUP = "BBBBBBBBBB";

    private static final String DEFAULT_CALLERID = "AAAAAAAAAA";
    private static final String UPDATED_CALLERID = "BBBBBBBBBB";

    private static final String DEFAULT_CANCALLFORWARD = "AAAAAAAAAA";
    private static final String UPDATED_CANCALLFORWARD = "BBBBBBBBBB";

    private static final String DEFAULT_CANREINVITE = "AAAAAAAAAA";
    private static final String UPDATED_CANREINVITE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTEXT = "AAAAAAAAAA";
    private static final String UPDATED_CONTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULTIP = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULTIP = "BBBBBBBBBB";

    private static final String DEFAULT_DTMFMODE = "AAAAAAAAAA";
    private static final String UPDATED_DTMFMODE = "BBBBBBBBBB";

    private static final String DEFAULT_FROMUSER = "AAAAAAAAAA";
    private static final String UPDATED_FROMUSER = "BBBBBBBBBB";

    private static final String DEFAULT_FROMDOMAIN = "AAAAAAAAAA";
    private static final String UPDATED_FROMDOMAIN = "BBBBBBBBBB";

    private static final String DEFAULT_INSECURE = "AAAAAAAAAA";
    private static final String UPDATED_INSECURE = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_MAILBOX = "AAAAAAAAAA";
    private static final String UPDATED_MAILBOX = "BBBBBBBBBB";

    private static final String DEFAULT_MD_5_SECRET = "AAAAAAAAAA";
    private static final String UPDATED_MD_5_SECRET = "BBBBBBBBBB";

    private static final String DEFAULT_DENY = "AAAAAAAAAA";
    private static final String UPDATED_DENY = "BBBBBBBBBB";

    private static final String DEFAULT_PERMIT = "AAAAAAAAAA";
    private static final String UPDATED_PERMIT = "BBBBBBBBBB";

    private static final String DEFAULT_MASK = "AAAAAAAAAA";
    private static final String UPDATED_MASK = "BBBBBBBBBB";

    private static final String DEFAULT_MUSICONHOLD = "AAAAAAAAAA";
    private static final String UPDATED_MUSICONHOLD = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUPGROUP = "AAAAAAAAAA";
    private static final String UPDATED_PICKUPGROUP = "BBBBBBBBBB";

    private static final String DEFAULT_QUALIFY = "AAAAAAAAAA";
    private static final String UPDATED_QUALIFY = "BBBBBBBBBB";

    private static final String DEFAULT_REGEXTEN = "AAAAAAAAAA";
    private static final String UPDATED_REGEXTEN = "BBBBBBBBBB";

    private static final String DEFAULT_RESTRICTCID = "AAAAAAAAAA";
    private static final String UPDATED_RESTRICTCID = "BBBBBBBBBB";

    private static final String DEFAULT_RTPTIMEOUT = "AAAAAAAAAA";
    private static final String UPDATED_RTPTIMEOUT = "BBBBBBBBBB";

    private static final String DEFAULT_RTPHOLDTIMEOUT = "AAAAAAAAAA";
    private static final String UPDATED_RTPHOLDTIMEOUT = "BBBBBBBBBB";

    private static final String DEFAULT_SECRET = "AAAAAAAAAA";
    private static final String UPDATED_SECRET = "BBBBBBBBBB";

    private static final String DEFAULT_SETVAR = "AAAAAAAAAA";
    private static final String UPDATED_SETVAR = "BBBBBBBBBB";

    private static final String DEFAULT_DISALLOW = "AAAAAAAAAA";
    private static final String UPDATED_DISALLOW = "BBBBBBBBBB";

    private static final String DEFAULT_ALLOW = "AAAAAAAAAA";
    private static final String UPDATED_ALLOW = "BBBBBBBBBB";

    private static final String DEFAULT_FULLCONTACT = "AAAAAAAAAA";
    private static final String UPDATED_FULLCONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_IPADDR = "AAAAAAAAAA";
    private static final String UPDATED_IPADDR = "BBBBBBBBBB";

    private static final Integer DEFAULT_PORT = 1;
    private static final Integer UPDATED_PORT = 2;

    private static final String DEFAULT_REGSERVER = "AAAAAAAAAA";
    private static final String UPDATED_REGSERVER = "BBBBBBBBBB";

    private static final Integer DEFAULT_REGSECONDS = 1;
    private static final Integer UPDATED_REGSECONDS = 2;

    private static final Integer DEFAULT_LASTMS = 1;
    private static final Integer UPDATED_LASTMS = 2;

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULTUSER = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULTUSER = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIBECONTEXT = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIBECONTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_USERAGENT = "AAAAAAAAAA";
    private static final String UPDATED_USERAGENT = "BBBBBBBBBB";

    private static final boolean DEFAULT_STATUS = true;
    private static final boolean UPDATED_STATUS = false;

    @Autowired
    private SipPeerRepository sipPeerRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSipPeerMockMvc;

    private SipPeer sipPeer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SipPeerResource sipPeerResource = new SipPeerResource(sipPeerRepository);
        this.restSipPeerMockMvc = MockMvcBuilders.standaloneSetup(sipPeerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SipPeer createEntity(EntityManager em) {
        SipPeer sipPeer = new SipPeer()
            .name(DEFAULT_NAME)
            .host(DEFAULT_HOST)
            .nat(DEFAULT_NAT)
            .type(DEFAULT_TYPE)
            .accountcode(DEFAULT_ACCOUNTCODE)
            .amaflags(DEFAULT_AMAFLAGS)
            .calllimit(DEFAULT_CALLLIMIT)
            .callgroup(DEFAULT_CALLGROUP)
            .callerid(DEFAULT_CALLERID)
            .cancallforward(DEFAULT_CANCALLFORWARD)
            .canreinvite(DEFAULT_CANREINVITE)
            .context(DEFAULT_CONTEXT)
            .defaultip(DEFAULT_DEFAULTIP)
            .dtmfmode(DEFAULT_DTMFMODE)
            .fromuser(DEFAULT_FROMUSER)
            .fromdomain(DEFAULT_FROMDOMAIN)
            .insecure(DEFAULT_INSECURE)
            .language(DEFAULT_LANGUAGE)
            .mailbox(DEFAULT_MAILBOX)
            .md5secret(DEFAULT_MD_5_SECRET)
            .deny(DEFAULT_DENY)
            .permit(DEFAULT_PERMIT)
            .mask(DEFAULT_MASK)
            .musiconhold(DEFAULT_MUSICONHOLD)
            .pickupgroup(DEFAULT_PICKUPGROUP)
            .qualify(DEFAULT_QUALIFY)
            .regexten(DEFAULT_REGEXTEN)
            .restrictcid(DEFAULT_RESTRICTCID)
            .rtptimeout(DEFAULT_RTPTIMEOUT)
            .rtpholdtimeout(DEFAULT_RTPHOLDTIMEOUT)
            .secret(DEFAULT_SECRET)
            .setvar(DEFAULT_SETVAR)
            .disallow(DEFAULT_DISALLOW)
            .allow(DEFAULT_ALLOW)
            .fullcontact(DEFAULT_FULLCONTACT)
            .ipaddr(DEFAULT_IPADDR)
            .port(DEFAULT_PORT)
            .regserver(DEFAULT_REGSERVER)
            .regseconds(DEFAULT_REGSECONDS)
            .lastms(DEFAULT_LASTMS)
            .username(DEFAULT_USERNAME)
            .defaultuser(DEFAULT_DEFAULTUSER)
            .subscribecontext(DEFAULT_SUBSCRIBECONTEXT)
            .useragent(DEFAULT_USERAGENT)
            .status(DEFAULT_STATUS);
        return sipPeer;
    }

    @Before
    public void initTest() {
        sipPeer = createEntity(em);
    }

    @Test
    @Transactional
    public void createSipPeer() throws Exception {
        int databaseSizeBeforeCreate = sipPeerRepository.findAll().size();

        // Create the SipPeer
        restSipPeerMockMvc.perform(post("/api/sip-peers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sipPeer)))
            .andExpect(status().isCreated());

        // Validate the SipPeer in the database
        List<SipPeer> sipPeerList = sipPeerRepository.findAll();
        assertThat(sipPeerList).hasSize(databaseSizeBeforeCreate + 1);
        SipPeer testSipPeer = sipPeerList.get(sipPeerList.size() - 1);
        assertThat(testSipPeer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSipPeer.getHost()).isEqualTo(DEFAULT_HOST);
        assertThat(testSipPeer.getNat()).isEqualTo(DEFAULT_NAT);
        assertThat(testSipPeer.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSipPeer.getAccountcode()).isEqualTo(DEFAULT_ACCOUNTCODE);
        assertThat(testSipPeer.getAmaflags()).isEqualTo(DEFAULT_AMAFLAGS);
        assertThat(testSipPeer.getCalllimit()).isEqualTo(DEFAULT_CALLLIMIT);
        assertThat(testSipPeer.getCallgroup()).isEqualTo(DEFAULT_CALLGROUP);
        assertThat(testSipPeer.getCallerid()).isEqualTo(DEFAULT_CALLERID);
        assertThat(testSipPeer.getCancallforward()).isEqualTo(DEFAULT_CANCALLFORWARD);
        assertThat(testSipPeer.getCanreinvite()).isEqualTo(DEFAULT_CANREINVITE);
        assertThat(testSipPeer.getContext()).isEqualTo(DEFAULT_CONTEXT);
        assertThat(testSipPeer.getDefaultip()).isEqualTo(DEFAULT_DEFAULTIP);
        assertThat(testSipPeer.getDtmfmode()).isEqualTo(DEFAULT_DTMFMODE);
        assertThat(testSipPeer.getFromuser()).isEqualTo(DEFAULT_FROMUSER);
        assertThat(testSipPeer.getFromdomain()).isEqualTo(DEFAULT_FROMDOMAIN);
        assertThat(testSipPeer.getInsecure()).isEqualTo(DEFAULT_INSECURE);
        assertThat(testSipPeer.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testSipPeer.getMailbox()).isEqualTo(DEFAULT_MAILBOX);
        assertThat(testSipPeer.getMd5secret()).isEqualTo(DEFAULT_MD_5_SECRET);
        assertThat(testSipPeer.getDeny()).isEqualTo(DEFAULT_DENY);
        assertThat(testSipPeer.getPermit()).isEqualTo(DEFAULT_PERMIT);
        assertThat(testSipPeer.getMask()).isEqualTo(DEFAULT_MASK);
        assertThat(testSipPeer.getMusiconhold()).isEqualTo(DEFAULT_MUSICONHOLD);
        assertThat(testSipPeer.getPickupgroup()).isEqualTo(DEFAULT_PICKUPGROUP);
        assertThat(testSipPeer.getQualify()).isEqualTo(DEFAULT_QUALIFY);
        assertThat(testSipPeer.getRegexten()).isEqualTo(DEFAULT_REGEXTEN);
        assertThat(testSipPeer.getRestrictcid()).isEqualTo(DEFAULT_RESTRICTCID);
        assertThat(testSipPeer.getRtptimeout()).isEqualTo(DEFAULT_RTPTIMEOUT);
        assertThat(testSipPeer.getRtpholdtimeout()).isEqualTo(DEFAULT_RTPHOLDTIMEOUT);
        assertThat(testSipPeer.getSecret()).isEqualTo(DEFAULT_SECRET);
        assertThat(testSipPeer.getSetvar()).isEqualTo(DEFAULT_SETVAR);
        assertThat(testSipPeer.getDisallow()).isEqualTo(DEFAULT_DISALLOW);
        assertThat(testSipPeer.getAllow()).isEqualTo(DEFAULT_ALLOW);
        assertThat(testSipPeer.getFullcontact()).isEqualTo(DEFAULT_FULLCONTACT);
        assertThat(testSipPeer.getIpaddr()).isEqualTo(DEFAULT_IPADDR);
        assertThat(testSipPeer.getPort()).isEqualTo(DEFAULT_PORT);
        assertThat(testSipPeer.getRegserver()).isEqualTo(DEFAULT_REGSERVER);
        assertThat(testSipPeer.getRegseconds()).isEqualTo(DEFAULT_REGSECONDS);
        assertThat(testSipPeer.getLastms()).isEqualTo(DEFAULT_LASTMS);
        assertThat(testSipPeer.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testSipPeer.getDefaultuser()).isEqualTo(DEFAULT_DEFAULTUSER);
        assertThat(testSipPeer.getSubscribecontext()).isEqualTo(DEFAULT_SUBSCRIBECONTEXT);
        assertThat(testSipPeer.getUseragent()).isEqualTo(DEFAULT_USERAGENT);
        assertThat(testSipPeer.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createSipPeerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sipPeerRepository.findAll().size();

        // Create the SipPeer with an existing ID
        sipPeer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSipPeerMockMvc.perform(post("/api/sip-peers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sipPeer)))
            .andExpect(status().isBadRequest());

        // Validate the SipPeer in the database
        List<SipPeer> sipPeerList = sipPeerRepository.findAll();
        assertThat(sipPeerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSipPeers() throws Exception {
        // Initialize the database
        sipPeerRepository.saveAndFlush(sipPeer);

        // Get all the sipPeerList
        restSipPeerMockMvc.perform(get("/api/sip-peers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sipPeer.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].host").value(hasItem(DEFAULT_HOST.toString())))
            .andExpect(jsonPath("$.[*].nat").value(hasItem(DEFAULT_NAT.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].accountcode").value(hasItem(DEFAULT_ACCOUNTCODE.toString())))
            .andExpect(jsonPath("$.[*].amaflags").value(hasItem(DEFAULT_AMAFLAGS.toString())))
            .andExpect(jsonPath("$.[*].calllimit").value(hasItem(DEFAULT_CALLLIMIT)))
            .andExpect(jsonPath("$.[*].callgroup").value(hasItem(DEFAULT_CALLGROUP.toString())))
            .andExpect(jsonPath("$.[*].callerid").value(hasItem(DEFAULT_CALLERID.toString())))
            .andExpect(jsonPath("$.[*].cancallforward").value(hasItem(DEFAULT_CANCALLFORWARD.toString())))
            .andExpect(jsonPath("$.[*].canreinvite").value(hasItem(DEFAULT_CANREINVITE.toString())))
            .andExpect(jsonPath("$.[*].context").value(hasItem(DEFAULT_CONTEXT.toString())))
            .andExpect(jsonPath("$.[*].defaultip").value(hasItem(DEFAULT_DEFAULTIP.toString())))
            .andExpect(jsonPath("$.[*].dtmfmode").value(hasItem(DEFAULT_DTMFMODE.toString())))
            .andExpect(jsonPath("$.[*].fromuser").value(hasItem(DEFAULT_FROMUSER.toString())))
            .andExpect(jsonPath("$.[*].fromdomain").value(hasItem(DEFAULT_FROMDOMAIN.toString())))
            .andExpect(jsonPath("$.[*].insecure").value(hasItem(DEFAULT_INSECURE.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
            .andExpect(jsonPath("$.[*].mailbox").value(hasItem(DEFAULT_MAILBOX.toString())))
            .andExpect(jsonPath("$.[*].md5secret").value(hasItem(DEFAULT_MD_5_SECRET.toString())))
            .andExpect(jsonPath("$.[*].deny").value(hasItem(DEFAULT_DENY.toString())))
            .andExpect(jsonPath("$.[*].permit").value(hasItem(DEFAULT_PERMIT.toString())))
            .andExpect(jsonPath("$.[*].mask").value(hasItem(DEFAULT_MASK.toString())))
            .andExpect(jsonPath("$.[*].musiconhold").value(hasItem(DEFAULT_MUSICONHOLD.toString())))
            .andExpect(jsonPath("$.[*].pickupgroup").value(hasItem(DEFAULT_PICKUPGROUP.toString())))
            .andExpect(jsonPath("$.[*].qualify").value(hasItem(DEFAULT_QUALIFY.toString())))
            .andExpect(jsonPath("$.[*].regexten").value(hasItem(DEFAULT_REGEXTEN.toString())))
            .andExpect(jsonPath("$.[*].restrictcid").value(hasItem(DEFAULT_RESTRICTCID.toString())))
            .andExpect(jsonPath("$.[*].rtptimeout").value(hasItem(DEFAULT_RTPTIMEOUT.toString())))
            .andExpect(jsonPath("$.[*].rtpholdtimeout").value(hasItem(DEFAULT_RTPHOLDTIMEOUT.toString())))
            .andExpect(jsonPath("$.[*].secret").value(hasItem(DEFAULT_SECRET.toString())))
            .andExpect(jsonPath("$.[*].setvar").value(hasItem(DEFAULT_SETVAR.toString())))
            .andExpect(jsonPath("$.[*].disallow").value(hasItem(DEFAULT_DISALLOW.toString())))
            .andExpect(jsonPath("$.[*].allow").value(hasItem(DEFAULT_ALLOW.toString())))
            .andExpect(jsonPath("$.[*].fullcontact").value(hasItem(DEFAULT_FULLCONTACT.toString())))
            .andExpect(jsonPath("$.[*].ipaddr").value(hasItem(DEFAULT_IPADDR.toString())))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT)))
            .andExpect(jsonPath("$.[*].regserver").value(hasItem(DEFAULT_REGSERVER.toString())))
            .andExpect(jsonPath("$.[*].regseconds").value(hasItem(DEFAULT_REGSECONDS)))
            .andExpect(jsonPath("$.[*].lastms").value(hasItem(DEFAULT_LASTMS)))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].defaultuser").value(hasItem(DEFAULT_DEFAULTUSER.toString())))
            .andExpect(jsonPath("$.[*].subscribecontext").value(hasItem(DEFAULT_SUBSCRIBECONTEXT.toString())))
            .andExpect(jsonPath("$.[*].useragent").value(hasItem(DEFAULT_USERAGENT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getSipPeer() throws Exception {
        // Initialize the database
        sipPeerRepository.saveAndFlush(sipPeer);

        // Get the sipPeer
        restSipPeerMockMvc.perform(get("/api/sip-peers/{id}", sipPeer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sipPeer.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.host").value(DEFAULT_HOST.toString()))
            .andExpect(jsonPath("$.nat").value(DEFAULT_NAT.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.accountcode").value(DEFAULT_ACCOUNTCODE.toString()))
            .andExpect(jsonPath("$.amaflags").value(DEFAULT_AMAFLAGS.toString()))
            .andExpect(jsonPath("$.calllimit").value(DEFAULT_CALLLIMIT))
            .andExpect(jsonPath("$.callgroup").value(DEFAULT_CALLGROUP.toString()))
            .andExpect(jsonPath("$.callerid").value(DEFAULT_CALLERID.toString()))
            .andExpect(jsonPath("$.cancallforward").value(DEFAULT_CANCALLFORWARD.toString()))
            .andExpect(jsonPath("$.canreinvite").value(DEFAULT_CANREINVITE.toString()))
            .andExpect(jsonPath("$.context").value(DEFAULT_CONTEXT.toString()))
            .andExpect(jsonPath("$.defaultip").value(DEFAULT_DEFAULTIP.toString()))
            .andExpect(jsonPath("$.dtmfmode").value(DEFAULT_DTMFMODE.toString()))
            .andExpect(jsonPath("$.fromuser").value(DEFAULT_FROMUSER.toString()))
            .andExpect(jsonPath("$.fromdomain").value(DEFAULT_FROMDOMAIN.toString()))
            .andExpect(jsonPath("$.insecure").value(DEFAULT_INSECURE.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()))
            .andExpect(jsonPath("$.mailbox").value(DEFAULT_MAILBOX.toString()))
            .andExpect(jsonPath("$.md5secret").value(DEFAULT_MD_5_SECRET.toString()))
            .andExpect(jsonPath("$.deny").value(DEFAULT_DENY.toString()))
            .andExpect(jsonPath("$.permit").value(DEFAULT_PERMIT.toString()))
            .andExpect(jsonPath("$.mask").value(DEFAULT_MASK.toString()))
            .andExpect(jsonPath("$.musiconhold").value(DEFAULT_MUSICONHOLD.toString()))
            .andExpect(jsonPath("$.pickupgroup").value(DEFAULT_PICKUPGROUP.toString()))
            .andExpect(jsonPath("$.qualify").value(DEFAULT_QUALIFY.toString()))
            .andExpect(jsonPath("$.regexten").value(DEFAULT_REGEXTEN.toString()))
            .andExpect(jsonPath("$.restrictcid").value(DEFAULT_RESTRICTCID.toString()))
            .andExpect(jsonPath("$.rtptimeout").value(DEFAULT_RTPTIMEOUT.toString()))
            .andExpect(jsonPath("$.rtpholdtimeout").value(DEFAULT_RTPHOLDTIMEOUT.toString()))
            .andExpect(jsonPath("$.secret").value(DEFAULT_SECRET.toString()))
            .andExpect(jsonPath("$.setvar").value(DEFAULT_SETVAR.toString()))
            .andExpect(jsonPath("$.disallow").value(DEFAULT_DISALLOW.toString()))
            .andExpect(jsonPath("$.allow").value(DEFAULT_ALLOW.toString()))
            .andExpect(jsonPath("$.fullcontact").value(DEFAULT_FULLCONTACT.toString()))
            .andExpect(jsonPath("$.ipaddr").value(DEFAULT_IPADDR.toString()))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT))
            .andExpect(jsonPath("$.regserver").value(DEFAULT_REGSERVER.toString()))
            .andExpect(jsonPath("$.regseconds").value(DEFAULT_REGSECONDS))
            .andExpect(jsonPath("$.lastms").value(DEFAULT_LASTMS))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.defaultuser").value(DEFAULT_DEFAULTUSER.toString()))
            .andExpect(jsonPath("$.subscribecontext").value(DEFAULT_SUBSCRIBECONTEXT.toString()))
            .andExpect(jsonPath("$.useragent").value(DEFAULT_USERAGENT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingSipPeer() throws Exception {
        // Get the sipPeer
        restSipPeerMockMvc.perform(get("/api/sip-peers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSipPeer() throws Exception {
        // Initialize the database
        sipPeerRepository.saveAndFlush(sipPeer);

        int databaseSizeBeforeUpdate = sipPeerRepository.findAll().size();

        // Update the sipPeer
        SipPeer updatedSipPeer = sipPeerRepository.findById(sipPeer.getId()).get();
        // Disconnect from session so that the updates on updatedSipPeer are not directly saved in db
        em.detach(updatedSipPeer);
        updatedSipPeer
            .name(UPDATED_NAME)
            .host(UPDATED_HOST)
            .nat(UPDATED_NAT)
            .type(UPDATED_TYPE)
            .accountcode(UPDATED_ACCOUNTCODE)
            .amaflags(UPDATED_AMAFLAGS)
            .calllimit(UPDATED_CALLLIMIT)
            .callgroup(UPDATED_CALLGROUP)
            .callerid(UPDATED_CALLERID)
            .cancallforward(UPDATED_CANCALLFORWARD)
            .canreinvite(UPDATED_CANREINVITE)
            .context(UPDATED_CONTEXT)
            .defaultip(UPDATED_DEFAULTIP)
            .dtmfmode(UPDATED_DTMFMODE)
            .fromuser(UPDATED_FROMUSER)
            .fromdomain(UPDATED_FROMDOMAIN)
            .insecure(UPDATED_INSECURE)
            .language(UPDATED_LANGUAGE)
            .mailbox(UPDATED_MAILBOX)
            .md5secret(UPDATED_MD_5_SECRET)
            .deny(UPDATED_DENY)
            .permit(UPDATED_PERMIT)
            .mask(UPDATED_MASK)
            .musiconhold(UPDATED_MUSICONHOLD)
            .pickupgroup(UPDATED_PICKUPGROUP)
            .qualify(UPDATED_QUALIFY)
            .regexten(UPDATED_REGEXTEN)
            .restrictcid(UPDATED_RESTRICTCID)
            .rtptimeout(UPDATED_RTPTIMEOUT)
            .rtpholdtimeout(UPDATED_RTPHOLDTIMEOUT)
            .secret(UPDATED_SECRET)
            .setvar(UPDATED_SETVAR)
            .disallow(UPDATED_DISALLOW)
            .allow(UPDATED_ALLOW)
            .fullcontact(UPDATED_FULLCONTACT)
            .ipaddr(UPDATED_IPADDR)
            .port(UPDATED_PORT)
            .regserver(UPDATED_REGSERVER)
            .regseconds(UPDATED_REGSECONDS)
            .lastms(UPDATED_LASTMS)
            .username(UPDATED_USERNAME)
            .defaultuser(UPDATED_DEFAULTUSER)
            .subscribecontext(UPDATED_SUBSCRIBECONTEXT)
            .useragent(UPDATED_USERAGENT)
            .status(UPDATED_STATUS);

        restSipPeerMockMvc.perform(put("/api/sip-peers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSipPeer)))
            .andExpect(status().isOk());

        // Validate the SipPeer in the database
        List<SipPeer> sipPeerList = sipPeerRepository.findAll();
        assertThat(sipPeerList).hasSize(databaseSizeBeforeUpdate);
        SipPeer testSipPeer = sipPeerList.get(sipPeerList.size() - 1);
        assertThat(testSipPeer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSipPeer.getHost()).isEqualTo(UPDATED_HOST);
        assertThat(testSipPeer.getNat()).isEqualTo(UPDATED_NAT);
        assertThat(testSipPeer.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSipPeer.getAccountcode()).isEqualTo(UPDATED_ACCOUNTCODE);
        assertThat(testSipPeer.getAmaflags()).isEqualTo(UPDATED_AMAFLAGS);
        assertThat(testSipPeer.getCalllimit()).isEqualTo(UPDATED_CALLLIMIT);
        assertThat(testSipPeer.getCallgroup()).isEqualTo(UPDATED_CALLGROUP);
        assertThat(testSipPeer.getCallerid()).isEqualTo(UPDATED_CALLERID);
        assertThat(testSipPeer.getCancallforward()).isEqualTo(UPDATED_CANCALLFORWARD);
        assertThat(testSipPeer.getCanreinvite()).isEqualTo(UPDATED_CANREINVITE);
        assertThat(testSipPeer.getContext()).isEqualTo(UPDATED_CONTEXT);
        assertThat(testSipPeer.getDefaultip()).isEqualTo(UPDATED_DEFAULTIP);
        assertThat(testSipPeer.getDtmfmode()).isEqualTo(UPDATED_DTMFMODE);
        assertThat(testSipPeer.getFromuser()).isEqualTo(UPDATED_FROMUSER);
        assertThat(testSipPeer.getFromdomain()).isEqualTo(UPDATED_FROMDOMAIN);
        assertThat(testSipPeer.getInsecure()).isEqualTo(UPDATED_INSECURE);
        assertThat(testSipPeer.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testSipPeer.getMailbox()).isEqualTo(UPDATED_MAILBOX);
        assertThat(testSipPeer.getMd5secret()).isEqualTo(UPDATED_MD_5_SECRET);
        assertThat(testSipPeer.getDeny()).isEqualTo(UPDATED_DENY);
        assertThat(testSipPeer.getPermit()).isEqualTo(UPDATED_PERMIT);
        assertThat(testSipPeer.getMask()).isEqualTo(UPDATED_MASK);
        assertThat(testSipPeer.getMusiconhold()).isEqualTo(UPDATED_MUSICONHOLD);
        assertThat(testSipPeer.getPickupgroup()).isEqualTo(UPDATED_PICKUPGROUP);
        assertThat(testSipPeer.getQualify()).isEqualTo(UPDATED_QUALIFY);
        assertThat(testSipPeer.getRegexten()).isEqualTo(UPDATED_REGEXTEN);
        assertThat(testSipPeer.getRestrictcid()).isEqualTo(UPDATED_RESTRICTCID);
        assertThat(testSipPeer.getRtptimeout()).isEqualTo(UPDATED_RTPTIMEOUT);
        assertThat(testSipPeer.getRtpholdtimeout()).isEqualTo(UPDATED_RTPHOLDTIMEOUT);
        assertThat(testSipPeer.getSecret()).isEqualTo(UPDATED_SECRET);
        assertThat(testSipPeer.getSetvar()).isEqualTo(UPDATED_SETVAR);
        assertThat(testSipPeer.getDisallow()).isEqualTo(UPDATED_DISALLOW);
        assertThat(testSipPeer.getAllow()).isEqualTo(UPDATED_ALLOW);
        assertThat(testSipPeer.getFullcontact()).isEqualTo(UPDATED_FULLCONTACT);
        assertThat(testSipPeer.getIpaddr()).isEqualTo(UPDATED_IPADDR);
        assertThat(testSipPeer.getPort()).isEqualTo(UPDATED_PORT);
        assertThat(testSipPeer.getRegserver()).isEqualTo(UPDATED_REGSERVER);
        assertThat(testSipPeer.getRegseconds()).isEqualTo(UPDATED_REGSECONDS);
        assertThat(testSipPeer.getLastms()).isEqualTo(UPDATED_LASTMS);
        assertThat(testSipPeer.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testSipPeer.getDefaultuser()).isEqualTo(UPDATED_DEFAULTUSER);
        assertThat(testSipPeer.getSubscribecontext()).isEqualTo(UPDATED_SUBSCRIBECONTEXT);
        assertThat(testSipPeer.getUseragent()).isEqualTo(UPDATED_USERAGENT);
        assertThat(testSipPeer.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingSipPeer() throws Exception {
        int databaseSizeBeforeUpdate = sipPeerRepository.findAll().size();

        // Create the SipPeer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSipPeerMockMvc.perform(put("/api/sip-peers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sipPeer)))
            .andExpect(status().isBadRequest());

        // Validate the SipPeer in the database
        List<SipPeer> sipPeerList = sipPeerRepository.findAll();
        assertThat(sipPeerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSipPeer() throws Exception {
        // Initialize the database
        sipPeerRepository.saveAndFlush(sipPeer);

        int databaseSizeBeforeDelete = sipPeerRepository.findAll().size();

        // Get the sipPeer
        restSipPeerMockMvc.perform(delete("/api/sip-peers/{id}", sipPeer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SipPeer> sipPeerList = sipPeerRepository.findAll();
        assertThat(sipPeerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SipPeer.class);
        SipPeer sipPeer1 = new SipPeer();
        sipPeer1.setId(1L);
        SipPeer sipPeer2 = new SipPeer();
        sipPeer2.setId(sipPeer1.getId());
        assertThat(sipPeer1).isEqualTo(sipPeer2);
        sipPeer2.setId(2L);
        assertThat(sipPeer1).isNotEqualTo(sipPeer2);
        sipPeer1.setId(null);
        assertThat(sipPeer1).isNotEqualTo(sipPeer2);
    }
}
