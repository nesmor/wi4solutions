package com.wi4solutions.repository;

import com.wi4solutions.domain.SipPeer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SipPeer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SipPeerRepository extends JpaRepository<SipPeer, Long> {

}
