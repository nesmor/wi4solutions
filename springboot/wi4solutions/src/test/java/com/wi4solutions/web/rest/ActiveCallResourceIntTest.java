package com.wi4solutions.web.rest;

import com.wi4solutions.Wi4SolutionsApp;

import com.wi4solutions.domain.ActiveCall;
import com.wi4solutions.repository.ActiveCallRepository;
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
 * Test class for the ActiveCallResource REST controller.
 *
 * @see ActiveCallResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Wi4SolutionsApp.class)
public class ActiveCallResourceIntTest {

//    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
//    private static final String UPDATED_NUMBER = "BBBBBBBBBB";
//
//    private static final String DEFAULT_ORIGINATOR = "AAAAAAAAAA";
//    private static final String UPDATED_ORIGINATOR = "BBBBBBBBBB";
//
//    private static final String DEFAULT_ANI = "AAAAAAAAAA";
//    private static final String UPDATED_ANI = "BBBBBBBBBB";
//
//    private static final String DEFAULT_DNI = "AAAAAAAAAA";
//    private static final String UPDATED_DNI = "BBBBBBBBBB";
//
//    private static final String DEFAULT_GATEWAY = "AAAAAAAAAA";
//    private static final String UPDATED_GATEWAY = "BBBBBBBBBB";
//
//    private static final String DEFAULT_DURATION = "AAAAAAAAAA";
//    private static final String UPDATED_DURATION = "BBBBBBBBBB";
//
//    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
//    private static final String UPDATED_STATUS = "BBBBBBBBBB";
//
//    @Autowired
//    private ActiveCallRepository activeCallRepository;
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
//    private MockMvc restActiveCallMockMvc;
//
//    private ActiveCall activeCall;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final ActiveCallResource activeCallResource = new ActiveCallResource(activeCallRepository);
//        this.restActiveCallMockMvc = MockMvcBuilders.standaloneSetup(activeCallResource)
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
//    public static ActiveCall createEntity(EntityManager em) {
//        ActiveCall activeCall = new ActiveCall()
//            .number(DEFAULT_NUMBER)
//            .originator(DEFAULT_ORIGINATOR)
//            .ani(DEFAULT_ANI)
//            .dni(DEFAULT_DNI)
//            .gateway(DEFAULT_GATEWAY)
//            .duration(DEFAULT_DURATION)
//            .status(DEFAULT_STATUS);
//        return activeCall;
//    }
//
//    @Before
//    public void initTest() {
//        activeCall = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createActiveCall() throws Exception {
//        int databaseSizeBeforeCreate = activeCallRepository.findAll().size();
//
//        // Create the ActiveCall
//        restActiveCallMockMvc.perform(post("/api/active-calls")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(activeCall)))
//            .andExpect(status().isCreated());
//
//        // Validate the ActiveCall in the database
//        List<ActiveCall> activeCallList = activeCallRepository.findAll();
//        assertThat(activeCallList).hasSize(databaseSizeBeforeCreate + 1);
//        ActiveCall testActiveCall = activeCallList.get(activeCallList.size() - 1);
//        assertThat(testActiveCall.getNumber()).isEqualTo(DEFAULT_NUMBER);
//        assertThat(testActiveCall.getOriginator()).isEqualTo(DEFAULT_ORIGINATOR);
//        assertThat(testActiveCall.getAni()).isEqualTo(DEFAULT_ANI);
//        assertThat(testActiveCall.getDni()).isEqualTo(DEFAULT_DNI);
//        assertThat(testActiveCall.getGateway()).isEqualTo(DEFAULT_GATEWAY);
//        assertThat(testActiveCall.getDuration()).isEqualTo(DEFAULT_DURATION);
//        assertThat(testActiveCall.getStatus()).isEqualTo(DEFAULT_STATUS);
//    }
//
//    @Test
//    @Transactional
//    public void createActiveCallWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = activeCallRepository.findAll().size();
//
//        // Create the ActiveCall with an existing ID
//        activeCall.setId(1L);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restActiveCallMockMvc.perform(post("/api/active-calls")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(activeCall)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the ActiveCall in the database
//        List<ActiveCall> activeCallList = activeCallRepository.findAll();
//        assertThat(activeCallList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void getAllActiveCalls() throws Exception {
//        // Initialize the database
//        activeCallRepository.saveAndFlush(activeCall);
//
//        // Get all the activeCallList
//        restActiveCallMockMvc.perform(get("/api/active-calls?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(activeCall.getId().intValue())))
//            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER.toString())))
//            .andExpect(jsonPath("$.[*].originator").value(hasItem(DEFAULT_ORIGINATOR.toString())))
//            .andExpect(jsonPath("$.[*].ani").value(hasItem(DEFAULT_ANI.toString())))
//            .andExpect(jsonPath("$.[*].dni").value(hasItem(DEFAULT_DNI.toString())))
//            .andExpect(jsonPath("$.[*].gateway").value(hasItem(DEFAULT_GATEWAY.toString())))
//            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION.toString())))
//            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
//    }
//    
//    @Test
//    @Transactional
//    public void getActiveCall() throws Exception {
//        // Initialize the database
//        activeCallRepository.saveAndFlush(activeCall);
//
//        // Get the activeCall
//        restActiveCallMockMvc.perform(get("/api/active-calls/{id}", activeCall.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(activeCall.getId().intValue()))
//            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER.toString()))
//            .andExpect(jsonPath("$.originator").value(DEFAULT_ORIGINATOR.toString()))
//            .andExpect(jsonPath("$.ani").value(DEFAULT_ANI.toString()))
//            .andExpect(jsonPath("$.dni").value(DEFAULT_DNI.toString()))
//            .andExpect(jsonPath("$.gateway").value(DEFAULT_GATEWAY.toString()))
//            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.toString()))
//            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingActiveCall() throws Exception {
//        // Get the activeCall
//        restActiveCallMockMvc.perform(get("/api/active-calls/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateActiveCall() throws Exception {
//        // Initialize the database
//        activeCallRepository.saveAndFlush(activeCall);
//
//        int databaseSizeBeforeUpdate = activeCallRepository.findAll().size();
//
//        // Update the activeCall
//        ActiveCall updatedActiveCall = activeCallRepository.findById(activeCall.getId()).get();
//        // Disconnect from session so that the updates on updatedActiveCall are not directly saved in db
//        em.detach(updatedActiveCall);
//        updatedActiveCall
//            .number(UPDATED_NUMBER)
//            .originator(UPDATED_ORIGINATOR)
//            .ani(UPDATED_ANI)
//            .dni(UPDATED_DNI)
//            .gateway(UPDATED_GATEWAY)
//            .duration(UPDATED_DURATION)
//            .status(UPDATED_STATUS);
//
//        restActiveCallMockMvc.perform(put("/api/active-calls")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(updatedActiveCall)))
//            .andExpect(status().isOk());
//
//        // Validate the ActiveCall in the database
//        List<ActiveCall> activeCallList = activeCallRepository.findAll();
//        assertThat(activeCallList).hasSize(databaseSizeBeforeUpdate);
//        ActiveCall testActiveCall = activeCallList.get(activeCallList.size() - 1);
//        assertThat(testActiveCall.getNumber()).isEqualTo(UPDATED_NUMBER);
//        assertThat(testActiveCall.getOriginator()).isEqualTo(UPDATED_ORIGINATOR);
//        assertThat(testActiveCall.getAni()).isEqualTo(UPDATED_ANI);
//        assertThat(testActiveCall.getDni()).isEqualTo(UPDATED_DNI);
//        assertThat(testActiveCall.getGateway()).isEqualTo(UPDATED_GATEWAY);
//        assertThat(testActiveCall.getDuration()).isEqualTo(UPDATED_DURATION);
//        assertThat(testActiveCall.getStatus()).isEqualTo(UPDATED_STATUS);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingActiveCall() throws Exception {
//        int databaseSizeBeforeUpdate = activeCallRepository.findAll().size();
//
//        // Create the ActiveCall
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restActiveCallMockMvc.perform(put("/api/active-calls")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(activeCall)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the ActiveCall in the database
//        List<ActiveCall> activeCallList = activeCallRepository.findAll();
//        assertThat(activeCallList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteActiveCall() throws Exception {
//        // Initialize the database
//        activeCallRepository.saveAndFlush(activeCall);
//
//        int databaseSizeBeforeDelete = activeCallRepository.findAll().size();
//
//        // Get the activeCall
//        restActiveCallMockMvc.perform(delete("/api/active-calls/{id}", activeCall.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<ActiveCall> activeCallList = activeCallRepository.findAll();
//        assertThat(activeCallList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(ActiveCall.class);
//        ActiveCall activeCall1 = new ActiveCall();
//        activeCall1.setId(1L);
//        ActiveCall activeCall2 = new ActiveCall();
//        activeCall2.setId(activeCall1.getId());
//        assertThat(activeCall1).isEqualTo(activeCall2);
//        activeCall2.setId(2L);
//        assertThat(activeCall1).isNotEqualTo(activeCall2);
//        activeCall1.setId(null);
//        assertThat(activeCall1).isNotEqualTo(activeCall2);
//    }
}
