package com.wi4solutions.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wi4solutions.domain.SipPeer;
import com.wi4solutions.repository.SipPeerRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing SipPeer.
 */
@RestController
@RequestMapping("/api")
public class SipPeerResource {

    private final Logger log = LoggerFactory.getLogger(SipPeerResource.class);

    private static final String ENTITY_NAME = "sipPeer";

    private final SipPeerRepository sipPeerRepository;

    public SipPeerResource(SipPeerRepository sipPeerRepository) {
        this.sipPeerRepository = sipPeerRepository;
    }

    /**
     * POST  /sip-peers : Create a new sipPeer.
     *
     * @param sipPeer the sipPeer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sipPeer, or with status 400 (Bad Request) if the sipPeer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sip-peers")
    @Timed
    public ResponseEntity<SipPeer> createSipPeer(@RequestBody SipPeer sipPeer) throws URISyntaxException {
        log.debug("REST request to save SipPeer : {}", sipPeer);
        if (sipPeer.getId() != null) {
            throw new BadRequestAlertException("A new sipPeer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SipPeer result = sipPeerRepository.save(sipPeer);
        return ResponseEntity.created(new URI("/api/sip-peers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sip-peers : Updates an existing sipPeer.
     *
     * @param sipPeer the sipPeer to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sipPeer,
     * or with status 400 (Bad Request) if the sipPeer is not valid,
     * or with status 500 (Internal Server Error) if the sipPeer couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sip-peers")
    @Timed
    public ResponseEntity<SipPeer> updateSipPeer(@RequestBody SipPeer sipPeer) throws URISyntaxException {
        log.debug("REST request to update SipPeer : {}", sipPeer);
        if (sipPeer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SipPeer result = sipPeerRepository.save(sipPeer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sipPeer.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sip-peers : get all the sipPeers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sipPeers in body
     */
    @GetMapping("/sip-peers")
    @Timed
    public List<SipPeer> getAllSipPeers() {
        log.debug("REST request to get all SipPeers");
        return StreamSupport
                .stream(sipPeerRepository.findAll().spliterator(), false)
                .filter(sipPeer -> sipPeer.getPeerType().equals("CARRIER"))
                .collect(Collectors.toList());
    }

    /**
     * GET  /sip-peers/:id : get the "id" sipPeer.
     *
     * @param id the id of the sipPeer to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sipPeer, or with status 404 (Not Found)
     */
    @GetMapping("/sip-peers/{id}")
    @Timed
    public ResponseEntity<SipPeer> getSipPeer(@PathVariable Long id) {
        log.debug("REST request to get SipPeer : {}", id);
        Optional<SipPeer> sipPeer = sipPeerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sipPeer);
    }

    /**
     * DELETE  /sip-peers/:id : delete the "id" sipPeer.
     *
     * @param id the id of the sipPeer to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sip-peers/{id}")
    @Timed
    public ResponseEntity<Void> deleteSipPeer(@PathVariable Long id) {
        log.debug("REST request to delete SipPeer : {}", id);

        sipPeerRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
