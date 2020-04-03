/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.auction.springdata;

import java.util.Date;
import java.util.List;
import org.solent.com504.project.model.auction.dto.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long>{
      @Query("select a from Auction a where a.startTime = :startTime")
      public List<Auction> findByStartTime(@Param("startTime") Date StartTime);
}
