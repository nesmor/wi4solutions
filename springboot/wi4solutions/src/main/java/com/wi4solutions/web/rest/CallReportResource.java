package com.wi4solutions.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.wi4solutions.domain.CallReport;
import com.wi4solutions.repository.CallReportRepositoryImp;
import com.wi4solutions.repository.CallReportSRepository;
import com.wi4solutions.web.rest.errors.BadRequestAlertException;
import com.wi4solutions.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing CallReport.
 */
@RestController
@RequestMapping("/api")
public class CallReportResource {

    private final Logger log = LoggerFactory.getLogger(CallReportResource.class);

    private static final String ENTITY_NAME = "callReport";

    private final CallReportSRepository callReportSRepository;
    
    private final CallReportRepositoryImp callReportRepository;

    public CallReportResource(CallReportSRepository callReportSRepository, CallReportRepositoryImp callReportRepository) {
        this.callReportSRepository = callReportSRepository;
        this.callReportRepository = callReportRepository;
    }

    /**
     * POST  /call-reports : Create a new callReport.
     *
     * @param callReport the callReport to create
     * @return the ResponseEntity with status 201 (Created) and with body the new callReport, or with status 400 (Bad Request) if the callReport has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PostMapping("/call-reports")
//    @Timed
//    public ResponseEntity<CallReport> createCallReport(@RequestBody CallReport callReport) throws URISyntaxException {
//        log.debug("REST request to save CallReport : {}", callReport);
//        if (callReport.getId() != null) {
//            throw new BadRequestAlertException("A new callReport cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        CallReport result = callReportSRepository.save(callReport);
//        return ResponseEntity.created(new URI("/api/call-reports/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /call-reports : Updates an existing callReport.
     *
     * @param callReport the callReport to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated callReport,
     * or with status 400 (Bad Request) if the callReport is not valid,
     * or with status 500 (Internal Server Error) if the callReport couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
//    @PutMapping("/call-reports")
//    @Timed
//    public ResponseEntity<CallReport> updateCallReport(@RequestBody CallReport callReport) throws URISyntaxException {
//        log.debug("REST request to update CallReport : {}", callReport);
//        if (callReport.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        CallReport result = callReportSRepository.save(callReport);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, callReport.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /call-reports : get all the callReports.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of callReports in body
     */
    @GetMapping("/call-reports/by-date")
    @Timed
    public List<CallReport> getAllCallReportsByDate(@RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate) {
        log.debug("REST request to get all CallReports");
        try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	    	fromDate = new Date(sdf.parse("31-03-2018").getTime());
	    	toDate = new Date(sdf.parse("1-4-2018").getTime());
        }catch (Exception e) {

        }
        return callReportRepository.findByDate(fromDate, toDate);       
    }

    
    @GetMapping("/call-reports/by-hour")
    @Timed
    public List<CallReport> getAllCallReportsByHour(@RequestParam("fromDate") Date fromDate) {
        log.debug("REST request to get all CallReports");
        try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	    	fromDate = new Date(sdf.parse("31-03-2018").getTime());
        }catch (Exception e) {

        }
        return callReportRepository.findByHour(fromDate);       
    }
    
    
    /**
     * GET  /call-reports/:id : get the "id" callReport.
     *
     * @param id the id of the callReport to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the callReport, or with status 404 (Not Found)
     */
//    @GetMapping("/call-reports/{id}")
//    @Timed
//    public ResponseEntity<CallReport> getCallReport(@PathVariable Long id) {
//        log.debug("REST request to get CallReport : {}", id);
//        Optional<CallReport> callReport = callReportSRepository.findById(id);
//        return ResponseUtil.wrapOrNotFound(callReport);
//    }

    /**
     * DELETE  /call-reports/:id : delete the "id" callReport.
     *
     * @param id the id of the callReport to delete
     * @return the ResponseEntity with status 200 (OK)
     */
//    @DeleteMapping("/call-reports/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteCallReport(@PathVariable Long id) {
//        log.debug("REST request to delete CallReport : {}", id);
//
//        callReportSRepository.deleteById(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }
}
