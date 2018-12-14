package com.wi4solutions.repository;

import com.google.common.base.Optional;
import com.wi4solutions.domain.CallDetailRecord;
import com.wi4solutions.domain.CallReport;
import com.wi4solutions.domain.DialPlan;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CallDetailRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CallDetailRecordRepository  extends JpaRepository<CallDetailRecord, Long>{

}
