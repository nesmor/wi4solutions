package com.wi4solutions.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wi4solutions.domain.DialPlan;
import com.wi4solutions.repository.DialPlanRepository;
import com.wi4solutions.web.rest.errors.BadRequestAlertException;
import com.wi4solutions.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DialPlan.
 */
@RestController
@RequestMapping("/api")
public class DialPlanResource {

    private final Logger log = LoggerFactory.getLogger(DialPlanResource.class);

    private static final String ENTITY_NAME = "dialPlan";

    private final DialPlanRepository dialPlanRepository;

    public DialPlanResource(DialPlanRepository dialPlanRepository) {
        this.dialPlanRepository = dialPlanRepository;
    }

    /**
     * POST  /dial-plans : Create a new dialPlan.
     *
     * @param dialPlan the dialPlan to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dialPlan, or with status 400 (Bad Request) if the dialPlan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dial-plans")
    @Timed
    public ResponseEntity<DialPlan> createDialPlan(@RequestBody DialPlan dialPlan) throws URISyntaxException {
        log.debug("REST request to save DialPlan : {}", dialPlan);
        if (dialPlan.getId() != null) {
            throw new BadRequestAlertException("A new dialPlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DialPlan result = dialPlanRepository.save(dialPlan);
        return ResponseEntity.created(new URI("/api/dial-plans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dial-plans : Updates an existing dialPlan.
     *
     * @param dialPlan the dialPlan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dialPlan,
     * or with status 400 (Bad Request) if the dialPlan is not valid,
     * or with status 500 (Internal Server Error) if the dialPlan couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dial-plans")
    @Timed
    public ResponseEntity<DialPlan> updateDialPlan(@RequestBody DialPlan dialPlan) throws URISyntaxException {
        log.debug("REST request to update DialPlan : {}", dialPlan);
        if (dialPlan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DialPlan result = dialPlanRepository.save(dialPlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dialPlan.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dial-plans : get all the dialPlans.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of dialPlans in body
     */
    @GetMapping("/dial-plans")
    @Timed
    public List<DialPlan> getAllDialPlans() {
        log.debug("REST request to get all DialPlans");
        return dialPlanRepository.findAll();
    }

    /**
     * GET  /dial-plans/:id : get the "id" dialPlan.
     *
     * @param id the id of the dialPlan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dialPlan, or with status 404 (Not Found)
     */
    @GetMapping("/dial-plans/{id}")
    @Timed
    public ResponseEntity<DialPlan> getDialPlan(@PathVariable Long id) {
        log.debug("REST request to get DialPlan : {}", id);
        Optional<DialPlan> dialPlan = dialPlanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dialPlan);
    }

    /**
     * DELETE  /dial-plans/:id : delete the "id" dialPlan.
     *
     * @param id the id of the dialPlan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dial-plans/{id}")
    @Timed
    public ResponseEntity<Void> deleteDialPlan(@PathVariable Long id) {
        log.debug("REST request to delete DialPlan : {}", id);

        dialPlanRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
