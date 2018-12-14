package com.wi4solutions.web.rest;

import com.wi4solutions.Wi4SolutionsApp;

import com.wi4solutions.domain.CallReport;
//import com.wi4solutions.repository.CallReportRepository;
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
 * Test class for the CallReportResource REST controller.
 *
 * @see CallReportResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Wi4SolutionsApp.class)
public class CallReportResourceIntTest {

//    private static final String DEFAULT_FROM_DATE = "AAAAAAAAAA";
//    private static final String UPDATED_FROM_DATE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_TO_DATE = "AAAAAAAAAA";
//    private static final String UPDATED_TO_DATE = "BBBBBBBBBB";
//
//    private static final Long DEFAULT_FAILED_CALLS = 1L;
//    private static final Long UPDATED_FAILED_CALLS = 2L;
//
//    private static final Long DEFAULT_TOTAL_CALLS = 1L;
//    private static final Long UPDATED_TOTAL_CALLS = 2L;
//
//    private static final Long DEFAULT_TOTAL_DURATION = 1L;
//    private static final Long UPDATED_TOTAL_DURATION = 2L;
//
//    private static final Float DEFAULT_ASR = 1F;
//    private static final Float UPDATED_ASR = 2F;
//
//    private static final Long DEFAULT_ACD = 1L;
//    private static final Long UPDATED_ACD = 2L;
//
//    private static final Long DEFAULT_MINUTES = 1L;
//    private static final Long UPDATED_MINUTES = 2L;
//
//    private static final Long DEFAULT_CONNECTED_CALLS = 1L;
//    private static final Long UPDATED_CONNECTED_CALLS = 2L;
//
//    private static final String DEFAULT_REPORT_TYPE = "AAAAAAAAAA";
//    private static final String UPDATED_REPORT_TYPE = "BBBBBBBBBB";
//
//    private static final Integer DEFAULT_HOUR = 1;
//    private static final Integer UPDATED_HOUR = 2;
//
//    private static final String DEFAULT_DATE = "AAAAAAAAAA";
//    private static final String UPDATED_DATE = "BBBBBBBBBB";
//
//    @Autowired
//    private CallReportRepository callReportRepository;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Autowired
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Autowired
//    private ExceptionTranslator exceptionTranslator;
//
//    @Autowired
//    private EntityManager em;
//
//    private MockMvc restCallReportMockMvc;
//
//    private CallReportOld callReport;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final CallReportResource callReportResource = new CallReportResource(callReportRepository);
//        this.restCallReportMockMvc = MockMvcBuilders.standaloneSetup(callReportResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
//    }
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static CallReportOld createEntity(EntityManager em) {
//        CallReportOld callReport = new CallReportOld()
//            .fromDate(DEFAULT_FROM_DATE)
//            .toDate(DEFAULT_TO_DATE)
//            .failedCalls(DEFAULT_FAILED_CALLS)
//            .totalCalls(DEFAULT_TOTAL_CALLS)
//            .totalDuration(DEFAULT_TOTAL_DURATION)
//            .asr(DEFAULT_ASR)
//            .acd(DEFAULT_ACD)
//            .minutes(DEFAULT_MINUTES)
//            .connectedCalls(DEFAULT_CONNECTED_CALLS)
//            .reportType(DEFAULT_REPORT_TYPE)
//            .hour(DEFAULT_HOUR)
//            .date(DEFAULT_DATE);
//        return callReport;
//    }
//
//    @Before
//    public void initTest() {
//        callReport = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createCallReport() throws Exception {
//        int databaseSizeBeforeCreate = callReportRepository.findAll().size();
//
//        // Create the CallReport
//        restCallReportMockMvc.perform(post("/api/call-reports")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(callReport)))
//            .andExpect(status().isCreated());
//
//        // Validate the CallReport in the database
//        List<CallReportOld> callReportList = callReportRepository.findAll();
//        assertThat(callReportList).hasSize(databaseSizeBeforeCreate + 1);
//        CallReportOld testCallReport = callReportList.get(callReportList.size() - 1);
//        assertThat(testCallReport.getFromDate()).isEqualTo(DEFAULT_FROM_DATE);
//        assertThat(testCallReport.getToDate()).isEqualTo(DEFAULT_TO_DATE);
//        assertThat(testCallReport.getFailedCalls()).isEqualTo(DEFAULT_FAILED_CALLS);
//        assertThat(testCallReport.getTotalCalls()).isEqualTo(DEFAULT_TOTAL_CALLS);
//        assertThat(testCallReport.getTotalDuration()).isEqualTo(DEFAULT_TOTAL_DURATION);
//        assertThat(testCallReport.getAsr()).isEqualTo(DEFAULT_ASR);
//        assertThat(testCallReport.getAcd()).isEqualTo(DEFAULT_ACD);
//        assertThat(testCallReport.getMinutes()).isEqualTo(DEFAULT_MINUTES);
//        assertThat(testCallReport.getConnectedCalls()).isEqualTo(DEFAULT_CONNECTED_CALLS);
//        assertThat(testCallReport.getReportType()).isEqualTo(DEFAULT_REPORT_TYPE);
//        assertThat(testCallReport.getHour()).isEqualTo(DEFAULT_HOUR);
//        assertThat(testCallReport.getDate()).isEqualTo(DEFAULT_DATE);
//    }
//
//    @Test
//    @Transactional
//    public void createCallReportWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = callReportRepository.findAll().size();
//
//        // Create the CallReport with an existing ID
//        callReport.setId(1L);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restCallReportMockMvc.perform(post("/api/call-reports")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(callReport)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the CallReport in the database
//        List<CallReportOld> callReportList = callReportRepository.findAll();
//        assertThat(callReportList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void getAllCallReports() throws Exception {
//        // Initialize the database
//        callReportRepository.saveAndFlush(callReport);
//
//        // Get all the callReportList
//        restCallReportMockMvc.perform(get("/api/call-reports?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(callReport.getId().intValue())))
//            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
//            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
//            .andExpect(jsonPath("$.[*].failedCalls").value(hasItem(DEFAULT_FAILED_CALLS.intValue())))
//            .andExpect(jsonPath("$.[*].totalCalls").value(hasItem(DEFAULT_TOTAL_CALLS.intValue())))
//            .andExpect(jsonPath("$.[*].totalDuration").value(hasItem(DEFAULT_TOTAL_DURATION.intValue())))
//            .andExpect(jsonPath("$.[*].asr").value(hasItem(DEFAULT_ASR.doubleValue())))
//            .andExpect(jsonPath("$.[*].acd").value(hasItem(DEFAULT_ACD.intValue())))
//            .andExpect(jsonPath("$.[*].minutes").value(hasItem(DEFAULT_MINUTES.intValue())))
//            .andExpect(jsonPath("$.[*].connectedCalls").value(hasItem(DEFAULT_CONNECTED_CALLS.intValue())))
//            .andExpect(jsonPath("$.[*].reportType").value(hasItem(DEFAULT_REPORT_TYPE.toString())))
//            .andExpect(jsonPath("$.[*].hour").value(hasItem(DEFAULT_HOUR)))
//            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
//    }
//    
//    @Test
//    @Transactional
//    public void getCallReport() throws Exception {
//        // Initialize the database
//        callReportRepository.saveAndFlush(callReport);
//
//        // Get the callReport
//        restCallReportMockMvc.perform(get("/api/call-reports/{id}", callReport.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(callReport.getId().intValue()))
//            .andExpect(jsonPath("$.fromDate").value(DEFAULT_FROM_DATE.toString()))
//            .andExpect(jsonPath("$.toDate").value(DEFAULT_TO_DATE.toString()))
//            .andExpect(jsonPath("$.failedCalls").value(DEFAULT_FAILED_CALLS.intValue()))
//            .andExpect(jsonPath("$.totalCalls").value(DEFAULT_TOTAL_CALLS.intValue()))
//            .andExpect(jsonPath("$.totalDuration").value(DEFAULT_TOTAL_DURATION.intValue()))
//            .andExpect(jsonPath("$.asr").value(DEFAULT_ASR.doubleValue()))
//            .andExpect(jsonPath("$.acd").value(DEFAULT_ACD.intValue()))
//            .andExpect(jsonPath("$.minutes").value(DEFAULT_MINUTES.intValue()))
//            .andExpect(jsonPath("$.connectedCalls").value(DEFAULT_CONNECTED_CALLS.intValue()))
//            .andExpect(jsonPath("$.reportType").value(DEFAULT_REPORT_TYPE.toString()))
//            .andExpect(jsonPath("$.hour").value(DEFAULT_HOUR))
//            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingCallReport() throws Exception {
//        // Get the callReport
//        restCallReportMockMvc.perform(get("/api/call-reports/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateCallReport() throws Exception {
//        // Initialize the database
//        callReportRepository.saveAndFlush(callReport);
//
//        int databaseSizeBeforeUpdate = callReportRepository.findAll().size();
//
//        // Update the callReport
//        CallReportOld updatedCallReport = callReportRepository.findById(callReport.getId()).get();
//        // Disconnect from session so that the updates on updatedCallReport are not directly saved in db
//        em.detach(updatedCallReport);
//        updatedCallReport
//            .fromDate(UPDATED_FROM_DATE)
//            .toDate(UPDATED_TO_DATE)
//            .failedCalls(UPDATED_FAILED_CALLS)
//            .totalCalls(UPDATED_TOTAL_CALLS)
//            .totalDuration(UPDATED_TOTAL_DURATION)
//            .asr(UPDATED_ASR)
//            .acd(UPDATED_ACD)
//            .minutes(UPDATED_MINUTES)
//            .connectedCalls(UPDATED_CONNECTED_CALLS)
//            .reportType(UPDATED_REPORT_TYPE)
//            .hour(UPDATED_HOUR)
//            .date(UPDATED_DATE);
//
//        restCallReportMockMvc.perform(put("/api/call-reports")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(updatedCallReport)))
//            .andExpect(status().isOk());
//
//        // Validate the CallReport in the database
//        List<CallReportOld> callReportList = callReportRepository.findAll();
//        assertThat(callReportList).hasSize(databaseSizeBeforeUpdate);
//        CallReportOld testCallReport = callReportList.get(callReportList.size() - 1);
//        assertThat(testCallReport.getFromDate()).isEqualTo(UPDATED_FROM_DATE);
//        assertThat(testCallReport.getToDate()).isEqualTo(UPDATED_TO_DATE);
//        assertThat(testCallReport.getFailedCalls()).isEqualTo(UPDATED_FAILED_CALLS);
//        assertThat(testCallReport.getTotalCalls()).isEqualTo(UPDATED_TOTAL_CALLS);
//        assertThat(testCallReport.getTotalDuration()).isEqualTo(UPDATED_TOTAL_DURATION);
//        assertThat(testCallReport.getAsr()).isEqualTo(UPDATED_ASR);
//        assertThat(testCallReport.getAcd()).isEqualTo(UPDATED_ACD);
//        assertThat(testCallReport.getMinutes()).isEqualTo(UPDATED_MINUTES);
//        assertThat(testCallReport.getConnectedCalls()).isEqualTo(UPDATED_CONNECTED_CALLS);
//        assertThat(testCallReport.getReportType()).isEqualTo(UPDATED_REPORT_TYPE);
//        assertThat(testCallReport.getHour()).isEqualTo(UPDATED_HOUR);
//        assertThat(testCallReport.getDate()).isEqualTo(UPDATED_DATE);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingCallReport() throws Exception {
//        int databaseSizeBeforeUpdate = callReportRepository.findAll().size();
//
//        // Create the CallReport
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restCallReportMockMvc.perform(put("/api/call-reports")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(callReport)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the CallReport in the database
//        List<CallReportOld> callReportList = callReportRepository.findAll();
//        assertThat(callReportList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteCallReport() throws Exception {
//        // Initialize the database
//        callReportRepository.saveAndFlush(callReport);
//
//        int databaseSizeBeforeDelete = callReportRepository.findAll().size();
//
//        // Get the callReport
//        restCallReportMockMvc.perform(delete("/api/call-reports/{id}", callReport.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<CallReportOld> callReportList = callReportRepository.findAll();
//        assertThat(callReportList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(CallReportOld.class);
//        CallReportOld callReport1 = new CallReportOld();
//        callReport1.setId(1L);
//        CallReportOld callReport2 = new CallReportOld();
//        callReport2.setId(callReport1.getId());
//        assertThat(callReport1).isEqualTo(callReport2);
//        callReport2.setId(2L);
//        assertThat(callReport1).isNotEqualTo(callReport2);
//        callReport1.setId(null);
//        assertThat(callReport1).isNotEqualTo(callReport2);
//    }
}
