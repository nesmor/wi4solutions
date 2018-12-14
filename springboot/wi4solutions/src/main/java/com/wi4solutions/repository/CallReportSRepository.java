package com.wi4solutions.repository;

import com.wi4solutions.domain.CallReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CallReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CallReportSRepository extends JpaRepository<CallReport, Long> {

}
