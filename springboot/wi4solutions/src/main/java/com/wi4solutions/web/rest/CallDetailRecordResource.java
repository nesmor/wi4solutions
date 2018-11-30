package com.wi4solutions.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wi4solutions.domain.CallDetailRecord;
import com.wi4solutions.repository.CallDetailRecordRepository;
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
 * REST controller for managing CallDetailRecord.
 */
@RestController
@RequestMapping("/api")
public class CallDetailRecordResource {

    private final Logger log = LoggerFactory.getLogger(CallDetailRecordResource.class);

    private static final String ENTITY_NAME = "callDetailRecord";

    private final CallDetailRecordRepository callDetailRecordRepository;

    public CallDetailRecordResource(CallDetailRecordRepository callDetailRecordRepository) {
        this.callDetailRecordRepository = callDetailRecordRepository;
    }

    /**
     * POST  /call-detail-records : Create a new callDetailRecord.
     *
     * @param callDetailRecord the callDetailRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new callDetailRecord, or with status 400 (Bad Request) if the callDetailRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/call-detail-records")
    @Timed
    public ResponseEntity<CallDetailRecord> createCallDetailRecord(@RequestBody CallDetailRecord callDetailRecord) throws URISyntaxException {
        log.debug("REST request to save CallDetailRecord : {}", callDetailRecord);
        if (callDetailRecord.getId() != null) {
            throw new BadRequestAlertException("A new callDetailRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CallDetailRecord result = callDetailRecordRepository.save(callDetailRecord);
        return ResponseEntity.created(new URI("/api/call-detail-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /call-detail-records : Updates an existing callDetailRecord.
     *
     * @param callDetailRecord the callDetailRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated callDetailRecord,
     * or with status 400 (Bad Request) if the callDetailRecord is not valid,
     * or with status 500 (Internal Server Error) if the callDetailRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/call-detail-records")
    @Timed
    public ResponseEntity<CallDetailRecord> updateCallDetailRecord(@RequestBody CallDetailRecord callDetailRecord) throws URISyntaxException {
        log.debug("REST request to update CallDetailRecord : {}", callDetailRecord);
        if (callDetailRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CallDetailRecord result = callDetailRecordRepository.save(callDetailRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, callDetailRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /call-detail-records : get all the callDetailRecords.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of callDetailRecords in body
     */
    @GetMapping("/call-detail-records")
    @Timed
    public List<CallDetailRecord> getAllCallDetailRecords() {
        log.debug("REST request to get all CallDetailRecords");
        return callDetailRecordRepository.findAll();
    }

    /**
     * GET  /call-detail-records/:id : get the "id" callDetailRecord.
     *
     * @param id the id of the callDetailRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the callDetailRecord, or with status 404 (Not Found)
     */
    @GetMapping("/call-detail-records/{id}")
    @Timed
    public ResponseEntity<CallDetailRecord> getCallDetailRecord(@PathVariable Long id) {
        log.debug("REST request to get CallDetailRecord : {}", id);
        Optional<CallDetailRecord> callDetailRecord = callDetailRecordRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(callDetailRecord);
    }

    /**
     * DELETE  /call-detail-records/:id : delete the "id" callDetailRecord.
     *
     * @param id the id of the callDetailRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/call-detail-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteCallDetailRecord(@PathVariable Long id) {
        log.debug("REST request to delete CallDetailRecord : {}", id);

        callDetailRecordRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
