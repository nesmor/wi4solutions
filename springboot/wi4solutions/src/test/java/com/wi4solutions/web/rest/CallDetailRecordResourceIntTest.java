package com.wi4solutions.web.rest;

import com.wi4solutions.Wi4SolutionsApp;

import com.wi4solutions.domain.CallDetailRecord;
import com.wi4solutions.repository.CallDetailRecordRepository;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.wi4solutions.web.rest.TestUtil.sameInstant;
import static com.wi4solutions.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CallDetailRecordResource REST controller.
 *
 * @see CallDetailRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Wi4SolutionsApp.class)
public class CallDetailRecordResourceIntTest {

    private static final ZonedDateTime DEFAULT_CALLDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CALLDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CLID = "AAAAAAAAAA";
    private static final String UPDATED_CLID = "BBBBBBBBBB";

    private static final String DEFAULT_SRC = "AAAAAAAAAA";
    private static final String UPDATED_SRC = "BBBBBBBBBB";

    private static final String DEFAULT_DST = "AAAAAAAAAA";
    private static final String UPDATED_DST = "BBBBBBBBBB";

    private static final String DEFAULT_DCONTEXT = "AAAAAAAAAA";
    private static final String UPDATED_DCONTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_DSTCHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_DSTCHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_LASTAPP = "AAAAAAAAAA";
    private static final String UPDATED_LASTAPP = "BBBBBBBBBB";

    private static final String DEFAULT_LASTDATA = "AAAAAAAAAA";
    private static final String UPDATED_LASTDATA = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    private static final Integer DEFAULT_BILLSEC = 1;
    private static final Integer UPDATED_BILLSEC = 2;

    private static final String DEFAULT_DISPOSITION = "AAAAAAAAAA";
    private static final String UPDATED_DISPOSITION = "BBBBBBBBBB";

    private static final Integer DEFAULT_AMAFLAGS = 1;
    private static final Integer UPDATED_AMAFLAGS = 2;

    private static final String DEFAULT_ACCOUNTCODE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIQUEID = "AAAAAAAAAA";
    private static final String UPDATED_UNIQUEID = "BBBBBBBBBB";

    private static final String DEFAULT_USERFIELD = "AAAAAAAAAA";
    private static final String UPDATED_USERFIELD = "BBBBBBBBBB";

    @Autowired
    private CallDetailRecordRepository callDetailRecordRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCallDetailRecordMockMvc;

    private CallDetailRecord callDetailRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CallDetailRecordResource callDetailRecordResource = new CallDetailRecordResource(callDetailRecordRepository);
        this.restCallDetailRecordMockMvc = MockMvcBuilders.standaloneSetup(callDetailRecordResource)
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
    public static CallDetailRecord createEntity(EntityManager em) {
        CallDetailRecord callDetailRecord = new CallDetailRecord()
            .calldate(DEFAULT_CALLDATE)
            .clid(DEFAULT_CLID)
            .src(DEFAULT_SRC)
            .dst(DEFAULT_DST)
            .dcontext(DEFAULT_DCONTEXT)
            .channel(DEFAULT_CHANNEL)
            .dstchannel(DEFAULT_DSTCHANNEL)
            .lastapp(DEFAULT_LASTAPP)
            .lastdata(DEFAULT_LASTDATA)
            .duration(DEFAULT_DURATION)
            .billsec(DEFAULT_BILLSEC)
            .disposition(DEFAULT_DISPOSITION)
            .amaflags(DEFAULT_AMAFLAGS)
            .accountcode(DEFAULT_ACCOUNTCODE)
            .uniqueid(DEFAULT_UNIQUEID)
            .userfield(DEFAULT_USERFIELD);
        return callDetailRecord;
    }

    @Before
    public void initTest() {
        callDetailRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createCallDetailRecord() throws Exception {
        int databaseSizeBeforeCreate = callDetailRecordRepository.findAll().size();

        // Create the CallDetailRecord
        restCallDetailRecordMockMvc.perform(post("/api/call-detail-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(callDetailRecord)))
            .andExpect(status().isCreated());

        // Validate the CallDetailRecord in the database
        List<CallDetailRecord> callDetailRecordList = callDetailRecordRepository.findAll();
        assertThat(callDetailRecordList).hasSize(databaseSizeBeforeCreate + 1);
        CallDetailRecord testCallDetailRecord = callDetailRecordList.get(callDetailRecordList.size() - 1);
        assertThat(testCallDetailRecord.getCalldate()).isEqualTo(DEFAULT_CALLDATE);
        assertThat(testCallDetailRecord.getClid()).isEqualTo(DEFAULT_CLID);
        assertThat(testCallDetailRecord.getSrc()).isEqualTo(DEFAULT_SRC);
        assertThat(testCallDetailRecord.getDst()).isEqualTo(DEFAULT_DST);
        assertThat(testCallDetailRecord.getDcontext()).isEqualTo(DEFAULT_DCONTEXT);
        assertThat(testCallDetailRecord.getChannel()).isEqualTo(DEFAULT_CHANNEL);
        assertThat(testCallDetailRecord.getDstchannel()).isEqualTo(DEFAULT_DSTCHANNEL);
        assertThat(testCallDetailRecord.getLastapp()).isEqualTo(DEFAULT_LASTAPP);
        assertThat(testCallDetailRecord.getLastdata()).isEqualTo(DEFAULT_LASTDATA);
        assertThat(testCallDetailRecord.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testCallDetailRecord.getBillsec()).isEqualTo(DEFAULT_BILLSEC);
        assertThat(testCallDetailRecord.getDisposition()).isEqualTo(DEFAULT_DISPOSITION);
        assertThat(testCallDetailRecord.getAmaflags()).isEqualTo(DEFAULT_AMAFLAGS);
        assertThat(testCallDetailRecord.getAccountcode()).isEqualTo(DEFAULT_ACCOUNTCODE);
        assertThat(testCallDetailRecord.getUniqueid()).isEqualTo(DEFAULT_UNIQUEID);
        assertThat(testCallDetailRecord.getUserfield()).isEqualTo(DEFAULT_USERFIELD);
    }

    @Test
    @Transactional
    public void createCallDetailRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = callDetailRecordRepository.findAll().size();

        // Create the CallDetailRecord with an existing ID
        callDetailRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCallDetailRecordMockMvc.perform(post("/api/call-detail-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(callDetailRecord)))
            .andExpect(status().isBadRequest());

        // Validate the CallDetailRecord in the database
        List<CallDetailRecord> callDetailRecordList = callDetailRecordRepository.findAll();
        assertThat(callDetailRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCallDetailRecords() throws Exception {
        // Initialize the database
        callDetailRecordRepository.saveAndFlush(callDetailRecord);

        // Get all the callDetailRecordList
        restCallDetailRecordMockMvc.perform(get("/api/call-detail-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(callDetailRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].calldate").value(hasItem(sameInstant(DEFAULT_CALLDATE))))
            .andExpect(jsonPath("$.[*].clid").value(hasItem(DEFAULT_CLID.toString())))
            .andExpect(jsonPath("$.[*].src").value(hasItem(DEFAULT_SRC.toString())))
            .andExpect(jsonPath("$.[*].dst").value(hasItem(DEFAULT_DST.toString())))
            .andExpect(jsonPath("$.[*].dcontext").value(hasItem(DEFAULT_DCONTEXT.toString())))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL.toString())))
            .andExpect(jsonPath("$.[*].dstchannel").value(hasItem(DEFAULT_DSTCHANNEL.toString())))
            .andExpect(jsonPath("$.[*].lastapp").value(hasItem(DEFAULT_LASTAPP.toString())))
            .andExpect(jsonPath("$.[*].lastdata").value(hasItem(DEFAULT_LASTDATA.toString())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].billsec").value(hasItem(DEFAULT_BILLSEC)))
            .andExpect(jsonPath("$.[*].disposition").value(hasItem(DEFAULT_DISPOSITION.toString())))
            .andExpect(jsonPath("$.[*].amaflags").value(hasItem(DEFAULT_AMAFLAGS)))
            .andExpect(jsonPath("$.[*].accountcode").value(hasItem(DEFAULT_ACCOUNTCODE.toString())))
            .andExpect(jsonPath("$.[*].uniqueid").value(hasItem(DEFAULT_UNIQUEID.toString())))
            .andExpect(jsonPath("$.[*].userfield").value(hasItem(DEFAULT_USERFIELD.toString())));
    }
    
    @Test
    @Transactional
    public void getCallDetailRecord() throws Exception {
        // Initialize the database
        callDetailRecordRepository.saveAndFlush(callDetailRecord);

        // Get the callDetailRecord
        restCallDetailRecordMockMvc.perform(get("/api/call-detail-records/{id}", callDetailRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(callDetailRecord.getId().intValue()))
            .andExpect(jsonPath("$.calldate").value(sameInstant(DEFAULT_CALLDATE)))
            .andExpect(jsonPath("$.clid").value(DEFAULT_CLID.toString()))
            .andExpect(jsonPath("$.src").value(DEFAULT_SRC.toString()))
            .andExpect(jsonPath("$.dst").value(DEFAULT_DST.toString()))
            .andExpect(jsonPath("$.dcontext").value(DEFAULT_DCONTEXT.toString()))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL.toString()))
            .andExpect(jsonPath("$.dstchannel").value(DEFAULT_DSTCHANNEL.toString()))
            .andExpect(jsonPath("$.lastapp").value(DEFAULT_LASTAPP.toString()))
            .andExpect(jsonPath("$.lastdata").value(DEFAULT_LASTDATA.toString()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.billsec").value(DEFAULT_BILLSEC))
            .andExpect(jsonPath("$.disposition").value(DEFAULT_DISPOSITION.toString()))
            .andExpect(jsonPath("$.amaflags").value(DEFAULT_AMAFLAGS))
            .andExpect(jsonPath("$.accountcode").value(DEFAULT_ACCOUNTCODE.toString()))
            .andExpect(jsonPath("$.uniqueid").value(DEFAULT_UNIQUEID.toString()))
            .andExpect(jsonPath("$.userfield").value(DEFAULT_USERFIELD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCallDetailRecord() throws Exception {
        // Get the callDetailRecord
        restCallDetailRecordMockMvc.perform(get("/api/call-detail-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCallDetailRecord() throws Exception {
        // Initialize the database
        callDetailRecordRepository.saveAndFlush(callDetailRecord);

        int databaseSizeBeforeUpdate = callDetailRecordRepository.findAll().size();

        // Update the callDetailRecord
        CallDetailRecord updatedCallDetailRecord = callDetailRecordRepository.findById(callDetailRecord.getId()).get();
        // Disconnect from session so that the updates on updatedCallDetailRecord are not directly saved in db
        em.detach(updatedCallDetailRecord);
        updatedCallDetailRecord
            .calldate(UPDATED_CALLDATE)
            .clid(UPDATED_CLID)
            .src(UPDATED_SRC)
            .dst(UPDATED_DST)
            .dcontext(UPDATED_DCONTEXT)
            .channel(UPDATED_CHANNEL)
            .dstchannel(UPDATED_DSTCHANNEL)
            .lastapp(UPDATED_LASTAPP)
            .lastdata(UPDATED_LASTDATA)
            .duration(UPDATED_DURATION)
            .billsec(UPDATED_BILLSEC)
            .disposition(UPDATED_DISPOSITION)
            .amaflags(UPDATED_AMAFLAGS)
            .accountcode(UPDATED_ACCOUNTCODE)
            .uniqueid(UPDATED_UNIQUEID)
            .userfield(UPDATED_USERFIELD);

        restCallDetailRecordMockMvc.perform(put("/api/call-detail-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCallDetailRecord)))
            .andExpect(status().isOk());

        // Validate the CallDetailRecord in the database
        List<CallDetailRecord> callDetailRecordList = callDetailRecordRepository.findAll();
        assertThat(callDetailRecordList).hasSize(databaseSizeBeforeUpdate);
        CallDetailRecord testCallDetailRecord = callDetailRecordList.get(callDetailRecordList.size() - 1);
        assertThat(testCallDetailRecord.getCalldate()).isEqualTo(UPDATED_CALLDATE);
        assertThat(testCallDetailRecord.getClid()).isEqualTo(UPDATED_CLID);
        assertThat(testCallDetailRecord.getSrc()).isEqualTo(UPDATED_SRC);
        assertThat(testCallDetailRecord.getDst()).isEqualTo(UPDATED_DST);
        assertThat(testCallDetailRecord.getDcontext()).isEqualTo(UPDATED_DCONTEXT);
        assertThat(testCallDetailRecord.getChannel()).isEqualTo(UPDATED_CHANNEL);
        assertThat(testCallDetailRecord.getDstchannel()).isEqualTo(UPDATED_DSTCHANNEL);
        assertThat(testCallDetailRecord.getLastapp()).isEqualTo(UPDATED_LASTAPP);
        assertThat(testCallDetailRecord.getLastdata()).isEqualTo(UPDATED_LASTDATA);
        assertThat(testCallDetailRecord.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testCallDetailRecord.getBillsec()).isEqualTo(UPDATED_BILLSEC);
        assertThat(testCallDetailRecord.getDisposition()).isEqualTo(UPDATED_DISPOSITION);
        assertThat(testCallDetailRecord.getAmaflags()).isEqualTo(UPDATED_AMAFLAGS);
        assertThat(testCallDetailRecord.getAccountcode()).isEqualTo(UPDATED_ACCOUNTCODE);
        assertThat(testCallDetailRecord.getUniqueid()).isEqualTo(UPDATED_UNIQUEID);
        assertThat(testCallDetailRecord.getUserfield()).isEqualTo(UPDATED_USERFIELD);
    }

    @Test
    @Transactional
    public void updateNonExistingCallDetailRecord() throws Exception {
        int databaseSizeBeforeUpdate = callDetailRecordRepository.findAll().size();

        // Create the CallDetailRecord

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCallDetailRecordMockMvc.perform(put("/api/call-detail-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(callDetailRecord)))
            .andExpect(status().isBadRequest());

        // Validate the CallDetailRecord in the database
        List<CallDetailRecord> callDetailRecordList = callDetailRecordRepository.findAll();
        assertThat(callDetailRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCallDetailRecord() throws Exception {
        // Initialize the database
        callDetailRecordRepository.saveAndFlush(callDetailRecord);

        int databaseSizeBeforeDelete = callDetailRecordRepository.findAll().size();

        // Get the callDetailRecord
        restCallDetailRecordMockMvc.perform(delete("/api/call-detail-records/{id}", callDetailRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CallDetailRecord> callDetailRecordList = callDetailRecordRepository.findAll();
        assertThat(callDetailRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CallDetailRecord.class);
        CallDetailRecord callDetailRecord1 = new CallDetailRecord();
        callDetailRecord1.setId(1L);
        CallDetailRecord callDetailRecord2 = new CallDetailRecord();
        callDetailRecord2.setId(callDetailRecord1.getId());
        assertThat(callDetailRecord1).isEqualTo(callDetailRecord2);
        callDetailRecord2.setId(2L);
        assertThat(callDetailRecord1).isNotEqualTo(callDetailRecord2);
        callDetailRecord1.setId(null);
        assertThat(callDetailRecord1).isNotEqualTo(callDetailRecord2);
    }
}
