package com.wi4solutions.repository;

import com.wi4solutions.domain.CallDetailRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CallDetailRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CallDetailRecordRepository extends JpaRepository<CallDetailRecord, Long> {

}
