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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
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
        Calendar calendar = Calendar.getInstance();	
		toDate = fromDate;
        return callReportRepository.findByDate(fromDate, toDate);       
    } 
    
    
    /**
     * GET  /call-reports : get all the callReports.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of callReports in body
     */
    @GetMapping("/call-reports/by-period/{period}")
    @Timed
    public List<CallReport> getAllCallReportsByPeriod(@PathVariable String period) {
    	List<CallReport> reports = null;
    	Date fromDate = null;
    	Date toDate = null;
    	log.debug("REST request to get all CallReports");
        Calendar calendar = Calendar.getInstance();	
        java.util.Date today = new java.util.Date();
        calendar.setTime(today);
      //  calendar.set(Calendar.MONTH, 3);
        
        switch (period) {
			case "today":
				fromDate = new java.sql.Date(calendar.getTime().getTime());
				toDate = fromDate;
				reports = callReportRepository.findByDate(fromDate, toDate);
				break;
			case "yesterday":
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				fromDate = new java.sql.Date(calendar.getTime().getTime());
				toDate = fromDate;
				reports = callReportRepository.findByDate(fromDate, toDate);
				break;
			case "month":
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				fromDate = new java.sql.Date(calendar.getTime().getTime());
				toDate = new java.sql.Date(today.getTime());
				reports = callReportRepository.getResume(fromDate, toDate);
			break;
			case "last-month":
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				if(calendar.get(Calendar.MONTH) == 0) {
					calendar.set(Calendar.MONTH, 11);
					calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
				}else {
					calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
				}
				fromDate = new java.sql.Date(calendar.getTime().getTime());
				int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				calendar.set(Calendar.DAY_OF_MONTH, days);
				toDate = new java.sql.Date(calendar.getTime().getTime());
				reports = callReportRepository.getResume(fromDate, toDate);
			break;
			case "always":
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.set(Calendar.MONTH, 0);
				calendar.set(Calendar.YEAR, 2018);
				fromDate = new java.sql.Date(calendar.getTime().getTime());
				toDate = new java.sql.Date(today.getTime());
				reports = callReportRepository.getResume(fromDate, toDate);
			break;
		}
        return reports;
       
    }

   
    
    @GetMapping("/call-reports/by-hour")
    @Timed
    public List<CallReport> getAllCallReportsByHour(@RequestParam("fromDate") Date fromDate) {
        log.debug("REST request to get all CallReports");
        try {
//	        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
//	    	fromDate = new Date(sdf.parse("31-03-2018").getTime());
        }catch (Exception e) {

        }
        return callReportRepository.findByHour(fromDate);       
    }
    
    /**
     * GET  /call-reports : get all the callReports.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of callReports in body
     */
    @GetMapping("/call-reports/by-type/{type}")
    @Timed
    public List<CallReport> getAllCallReportsByType(@RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate, @PathVariable String type) {
        log.debug("REST request to get all CallReports");
        return callReportRepository.findAll(fromDate, toDate,type);       
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
