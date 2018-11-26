package com.wi4solutions.web.rest;

import com.wi4solutions.Wi4SolutionsApp;

import com.wi4solutions.domain.DialPlan;
import com.wi4solutions.repository.DialPlanRepository;
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
 * Test class for the DialPlanResource REST controller.
 *
 * @see DialPlanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Wi4SolutionsApp.class)
public class DialPlanResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_PREFIX = "BBBBBBBBBB";

    private static final String DEFAULT_DIGIT_CUT = "AAAAAAAAAA";
    private static final String UPDATED_DIGIT_CUT = "BBBBBBBBBB";

    private static final String DEFAULT_PRECEDING = "AAAAAAAAAA";
    private static final String UPDATED_PRECEDING = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    private static final Long DEFAULT_GATEWAY = 1L;
    private static final Long UPDATED_GATEWAY = 2L;

    private static final Integer DEFAULT_LIMIT = 1;
    private static final Integer UPDATED_LIMIT = 2;

    @Autowired
    private DialPlanRepository dialPlanRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDialPlanMockMvc;

    private DialPlan dialPlan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DialPlanResource dialPlanResource = new DialPlanResource(dialPlanRepository);
        this.restDialPlanMockMvc = MockMvcBuilders.standaloneSetup(dialPlanResource)
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
    public static DialPlan createEntity(EntityManager em) {
        DialPlan dialPlan = new DialPlan()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .prefix(DEFAULT_PREFIX)
            .digitCut(DEFAULT_DIGIT_CUT)
            .preceding(DEFAULT_PRECEDING)
            .priority(DEFAULT_PRIORITY)
            .gateway(DEFAULT_GATEWAY)
            .limit(DEFAULT_LIMIT);
        return dialPlan;
    }

    @Before
    public void initTest() {
        dialPlan = createEntity(em);
    }

    @Test
    @Transactional
    public void createDialPlan() throws Exception {
        int databaseSizeBeforeCreate = dialPlanRepository.findAll().size();

        // Create the DialPlan
        restDialPlanMockMvc.perform(post("/api/dial-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dialPlan)))
            .andExpect(status().isCreated());

        // Validate the DialPlan in the database
        List<DialPlan> dialPlanList = dialPlanRepository.findAll();
        assertThat(dialPlanList).hasSize(databaseSizeBeforeCreate + 1);
        DialPlan testDialPlan = dialPlanList.get(dialPlanList.size() - 1);
        assertThat(testDialPlan.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDialPlan.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDialPlan.getPrefix()).isEqualTo(DEFAULT_PREFIX);
        assertThat(testDialPlan.getDigitCut()).isEqualTo(DEFAULT_DIGIT_CUT);
        assertThat(testDialPlan.getPreceding()).isEqualTo(DEFAULT_PRECEDING);
        assertThat(testDialPlan.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testDialPlan.getGateway()).isEqualTo(DEFAULT_GATEWAY);
        assertThat(testDialPlan.getLimit()).isEqualTo(DEFAULT_LIMIT);
    }

    @Test
    @Transactional
    public void createDialPlanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dialPlanRepository.findAll().size();

        // Create the DialPlan with an existing ID
        dialPlan.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDialPlanMockMvc.perform(post("/api/dial-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dialPlan)))
            .andExpect(status().isBadRequest());

        // Validate the DialPlan in the database
        List<DialPlan> dialPlanList = dialPlanRepository.findAll();
        assertThat(dialPlanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDialPlans() throws Exception {
        // Initialize the database
        dialPlanRepository.saveAndFlush(dialPlan);

        // Get all the dialPlanList
        restDialPlanMockMvc.perform(get("/api/dial-plans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dialPlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].prefix").value(hasItem(DEFAULT_PREFIX.toString())))
            .andExpect(jsonPath("$.[*].digitCut").value(hasItem(DEFAULT_DIGIT_CUT.toString())))
            .andExpect(jsonPath("$.[*].preceding").value(hasItem(DEFAULT_PRECEDING.toString())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].gateway").value(hasItem(DEFAULT_GATEWAY.intValue())))
            .andExpect(jsonPath("$.[*].limit").value(hasItem(DEFAULT_LIMIT)));
    }
    
    @Test
    @Transactional
    public void getDialPlan() throws Exception {
        // Initialize the database
        dialPlanRepository.saveAndFlush(dialPlan);

        // Get the dialPlan
        restDialPlanMockMvc.perform(get("/api/dial-plans/{id}", dialPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dialPlan.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.prefix").value(DEFAULT_PREFIX.toString()))
            .andExpect(jsonPath("$.digitCut").value(DEFAULT_DIGIT_CUT.toString()))
            .andExpect(jsonPath("$.preceding").value(DEFAULT_PRECEDING.toString()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.gateway").value(DEFAULT_GATEWAY.intValue()))
            .andExpect(jsonPath("$.limit").value(DEFAULT_LIMIT));
    }

    @Test
    @Transactional
    public void getNonExistingDialPlan() throws Exception {
        // Get the dialPlan
        restDialPlanMockMvc.perform(get("/api/dial-plans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDialPlan() throws Exception {
        // Initialize the database
        dialPlanRepository.saveAndFlush(dialPlan);

        int databaseSizeBeforeUpdate = dialPlanRepository.findAll().size();

        // Update the dialPlan
        DialPlan updatedDialPlan = dialPlanRepository.findById(dialPlan.getId()).get();
        // Disconnect from session so that the updates on updatedDialPlan are not directly saved in db
        em.detach(updatedDialPlan);
        updatedDialPlan
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .prefix(UPDATED_PREFIX)
            .digitCut(UPDATED_DIGIT_CUT)
            .preceding(UPDATED_PRECEDING)
            .priority(UPDATED_PRIORITY)
            .gateway(UPDATED_GATEWAY)
            .limit(UPDATED_LIMIT);

        restDialPlanMockMvc.perform(put("/api/dial-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDialPlan)))
            .andExpect(status().isOk());

        // Validate the DialPlan in the database
        List<DialPlan> dialPlanList = dialPlanRepository.findAll();
        assertThat(dialPlanList).hasSize(databaseSizeBeforeUpdate);
        DialPlan testDialPlan = dialPlanList.get(dialPlanList.size() - 1);
        assertThat(testDialPlan.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDialPlan.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDialPlan.getPrefix()).isEqualTo(UPDATED_PREFIX);
        assertThat(testDialPlan.getDigitCut()).isEqualTo(UPDATED_DIGIT_CUT);
        assertThat(testDialPlan.getPreceding()).isEqualTo(UPDATED_PRECEDING);
        assertThat(testDialPlan.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testDialPlan.getGateway()).isEqualTo(UPDATED_GATEWAY);
        assertThat(testDialPlan.getLimit()).isEqualTo(UPDATED_LIMIT);
    }

    @Test
    @Transactional
    public void updateNonExistingDialPlan() throws Exception {
        int databaseSizeBeforeUpdate = dialPlanRepository.findAll().size();

        // Create the DialPlan

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDialPlanMockMvc.perform(put("/api/dial-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dialPlan)))
            .andExpect(status().isBadRequest());

        // Validate the DialPlan in the database
        List<DialPlan> dialPlanList = dialPlanRepository.findAll();
        assertThat(dialPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDialPlan() throws Exception {
        // Initialize the database
        dialPlanRepository.saveAndFlush(dialPlan);

        int databaseSizeBeforeDelete = dialPlanRepository.findAll().size();

        // Get the dialPlan
        restDialPlanMockMvc.perform(delete("/api/dial-plans/{id}", dialPlan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DialPlan> dialPlanList = dialPlanRepository.findAll();
        assertThat(dialPlanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DialPlan.class);
        DialPlan dialPlan1 = new DialPlan();
        dialPlan1.setId(1L);
        DialPlan dialPlan2 = new DialPlan();
        dialPlan2.setId(dialPlan1.getId());
        assertThat(dialPlan1).isEqualTo(dialPlan2);
        dialPlan2.setId(2L);
        assertThat(dialPlan1).isNotEqualTo(dialPlan2);
        dialPlan1.setId(null);
        assertThat(dialPlan1).isNotEqualTo(dialPlan2);
    }
}
