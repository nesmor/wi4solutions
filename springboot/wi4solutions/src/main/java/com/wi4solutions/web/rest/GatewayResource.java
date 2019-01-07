package com.wi4solutions.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wi4solutions.domain.Gateway;
import com.wi4solutions.repository.AsteriskRepositoryImp;
import com.wi4solutions.repository.GatewayRepository;
import com.wi4solutions.service.util.RandomUtil;
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
 * REST controller for managing Gateway.
 */
@RestController
@RequestMapping("/api")
public class GatewayResource {

    private final Logger log = LoggerFactory.getLogger(GatewayResource.class);

    private static final String ENTITY_NAME = "gateway";

    private final GatewayRepository gatewayRepository;

    private final AsteriskRepositoryImp asteriskRepository;

    public GatewayResource(GatewayRepository gatewayRepository, AsteriskRepositoryImp asteriskRepository) {
        this.gatewayRepository = gatewayRepository;
        this.asteriskRepository = asteriskRepository;
    }

    /**
     * POST  /gateways : Create a new gateway.
     *
     * @param gateway the gateway to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gateway, or with status 400 (Bad Request) if the gateway has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gateways")
    @Timed
    public ResponseEntity<Gateway> createGateway(@RequestBody Gateway gateway) throws URISyntaxException {
        log.debug("REST request to save Gateway : {}", gateway);
        if (gateway.getId() != null) {
            throw new BadRequestAlertException("A new gateway cannot already have an ID", ENTITY_NAME, "idexists");
        }
        gateway.setUsername(gateway.getName());
        gateway.setType("friend");
        gateway.setHost("dynamic");
        gateway.setSecret(RandomUtil.generatePassword());
        Gateway result = gatewayRepository.save(gateway);
        asteriskRepository.reloadServer();
        return ResponseEntity.created(new URI("/api/gateways/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gateways : Updates an existing gateway.
     *
     * @param gateway the gateway to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gateway,
     * or with status 400 (Bad Request) if the gateway is not valid,
     * or with status 500 (Internal Server Error) if the gateway couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gateways")
    @Timed
    public ResponseEntity<Gateway> updateGateway(@RequestBody Gateway gateway) throws URISyntaxException {
        log.debug("REST request to update Gateway : {}", gateway);
        if (gateway.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        gateway.setUsername(gateway.getName());
        gateway.setType("friend");
        gateway.setHost("dynamic");
        Gateway result = gatewayRepository.save(gateway);
        asteriskRepository.reloadServer();
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gateway.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gateways : get all the gateways.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of gateways in body
     */
    @GetMapping("/gateways")
    @Timed
    public List<Gateway> getAllGateways(@RequestParam(required = false) String filter) {
        if ("dialplan-is-null".equals(filter)) {
            log.debug("REST request to get all Gateways where dialPlan is null");
            return StreamSupport
                .stream(gatewayRepository.findAll().spliterator(), false)
                .filter(gateway -> gateway.getDialPlan() == null && gateway.getPeerType().equals("GATEWAY"))
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Gateways");
        return StreamSupport
        .stream(gatewayRepository.findAll().spliterator(), false)
        .filter(gateway -> gateway.getPeerType().equals("GATEWAY"))
        .collect(Collectors.toList());
    }

    /**
     * GET  /gateways/:id : get the "id" gateway.
     *
     * @param id the id of the gateway to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gateway, or with status 404 (Not Found)
     */
    @GetMapping("/gateways/{id}")
    @Timed
    public ResponseEntity<Gateway> getGateway(@PathVariable Long id) {
        log.debug("REST request to get Gateway : {}", id);
        Optional<Gateway> gateway = gatewayRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(gateway);
    }

    /**
     * DELETE  /gateways/:id : delete the "id" gateway.
     *
     * @param id the id of the gateway to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gateways/{id}")
    @Timed
    public ResponseEntity<Void> deleteGateway(@PathVariable Long id) {
        log.debug("REST request to delete Gateway : {}", id);

        gatewayRepository.deleteById(id);
        asteriskRepository.reloadServer();
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
