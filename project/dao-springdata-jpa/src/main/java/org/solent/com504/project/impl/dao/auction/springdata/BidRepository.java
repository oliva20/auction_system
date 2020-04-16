/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.auction.springdata;

import org.solent.com504.project.model.auction.dto.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Josh
 */
@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
   
      @Query("select b from Bid b where b.biduuid = :uuid")
      public Bid findByBiduuid(@Param("uuid") String uuid);
}