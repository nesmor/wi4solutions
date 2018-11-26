package com.wi4solutions.repository;

import com.wi4solutions.domain.DialPlan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DialPlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DialPlanRepository extends JpaRepository<DialPlan, Long> {

}
