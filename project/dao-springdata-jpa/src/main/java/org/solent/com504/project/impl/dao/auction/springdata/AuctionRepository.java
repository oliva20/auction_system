/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.auction.springdata;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.solent.com504.project.model.auction.dto.Auction;
import org.solent.com504.project.model.party.dto.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andre
 */
@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
   
      
      @Query("select a from Auction a where a.startTime = :startTime")
      public List<Auction> findByStartTime(@Param("startTime") Date StartTime);
      
      @Query("select a from Auction a where a.auctionuuid = :uuid")
      public Auction findByAuctionuuid(@Param("uuid") String uuid);
      
      @Query("select a from Auction a where a.startTime = :startTime")
      public List<Auction> findActiveOrScheduledBefore(@Param("startTime") Date time);
      
      @Modifying
      @Query("update Auction a set a.registeredPartys = :updatedpartys where a.auctionuuid = :uuid")
      public void updateAuctionPartys(@Param("uuid") String auctionuuid, @Param("updatedpartys") Set<Party> party);
}
