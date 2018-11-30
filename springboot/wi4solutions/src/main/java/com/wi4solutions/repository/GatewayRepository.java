package com.wi4solutions.repository;

import com.wi4solutions.domain.Gateway;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Gateway entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long> {

}
