package com.wi4solutions.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wi4solutions.asterisk.CommandFailedException;
import com.wi4solutions.domain.ActiveCall;
import com.wi4solutions.domain.Call;
import com.wi4solutions.repository.AsteriskRepository;
import com.wi4solutions.repository.AsteriskRepositoryImp;
import com.wi4solutions.service.dto.MessageDTO;
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
 * REST controller for managing ActiveCall.
 */
@RestController
@RequestMapping("/api")
public class ActiveCallResource {

    private final Logger log = LoggerFactory.getLogger(ActiveCallResource.class);

    private static final String ENTITY_NAME = "activeCall";

    private final AsteriskRepositoryImp activeCallRepository;

    public ActiveCallResource(AsteriskRepositoryImp activeCallRepository) {
        this.activeCallRepository = activeCallRepository;
    }

    /**
     * POST  /active-calls : Create a new activeCall.
     *
     * @param activeCall the activeCall to create
     * @return the ResponseEntity with status 201 (Created) and with body the new activeCall, or with status 400 (Bad Request) if the activeCall has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/send-calls")
    @Timed
    public MessageDTO createActiveCall(@RequestBody Call call) throws URISyntaxException {
      log.debug("REST request to save ActiveCall : {}", call.getPhoneNumber());
      try {
          log.debug("REST request to get all ActiveCalls");
          activeCallRepository.sendCall(call.getPhoneNumber());
      }catch(CommandFailedException e) {
        return new MessageDTO("1","wi4SolutionsApp.asterisk.message.call-error");
      }
      catch(Exception e) {
    		return new MessageDTO("1","wi4SolutionsApp.asterisk.message.call-error");
		  }
        return new MessageDTO("0","wi4SolutionsApp.asterisk.message.call-sended");
    }

    /**
     * PUT  /active-calls : Updates an existing activeCall.
     *
     * @param activeCall the activeCall to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated activeCall,
     * or with status 400 (Bad Request) if the activeCall is not valid,
     * or with status 500 (Internal Server Error) if the activeCall couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PutMapping("/active-calls")
//    @Timed
//    public ResponseEntity<ActiveCall> updateActiveCall(@RequestBody ActiveCall activeCall) throws URISyntaxException {
//        log.debug("REST request to update ActiveCall : {}", activeCall);
//        if (activeCall.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        ActiveCall result = activeCallRepository.save(activeCall);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, activeCall.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /active-calls : get all the activeCalls.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of activeCalls in body
     */
    @GetMapping("/active-calls")
    @Timed
    public String getAllActiveCalls() {
        log.debug("REST request to get all ActiveCalls");
        return activeCallRepository.findAll();
    }

    @GetMapping("/reload")
    @Timed
    public MessageDTO getReload() {
    	try {
    		log.debug("REST request to get all ActiveCalls");
    	    activeCallRepository.reloadServer();
    	}catch(CommandFailedException e) {
    		return new MessageDTO("1","wi4SolutionsApp.asterisk.message.error");
		  }
	    return new MessageDTO("0","wi4SolutionsApp.asterisk.message.success");
    }


    @GetMapping("/restart")
    @Timed
    public MessageDTO getRestart() {
	    try {
	        log.debug("REST request to get all ActiveCalls");
	        activeCallRepository.restartServer();
	    }catch(CommandFailedException e) {

			return new MessageDTO("1","wi4SolutionsApp.asterisk.message.error");
		}
	    return new MessageDTO("0","wi4SolutionsApp.asterisk.message.success");
	}

    /**
     * GET  /active-calls/:id : get the "id" activeCall.
     *
     * @param id the id of the activeCall to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the activeCall, or with status 404 (Not Found)
     */
//    @GetMapping("/active-calls/{id}")
//    @Timed
//    public ResponseEntity<ActiveCall> getActiveCall(@PathVariable Long id) {
//        log.debug("REST request to get ActiveCall : {}", id);
//        Optional<ActiveCall> activeCall = activeCallRepository.findById(id);
//        return ResponseUtil.wrapOrNotFound(activeCall);
//    }

    /**
     * DELETE  /active-calls/:id : delete the "id" activeCall.
     *
     * @param id the id of the activeCall to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/active-calls/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteActiveCall(@PathVariable Long id) {
//        log.debug("REST request to delete ActiveCall : {}", id);
//
//        activeCallRepository.deleteById(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
}
