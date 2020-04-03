/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.auction.springdao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.auction.springdata.AuctionRepository;
import org.solent.com504.project.model.auction.dao.AuctionDAO;
import org.solent.com504.project.model.auction.dto.Auction;
import org.solent.com504.project.model.auction.dto.AuctionOrLotStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component
public class AuctionSpringDAOImpl implements AuctionDAO {

    final static Logger LOG = LogManager.getLogger(AuctionDAO.class);
    
    @Autowired
    AuctionRepository auctionRep = null;
    
    // hashmap of key auctionuuid, Auction - would replace with dao
    private LinkedHashMap<String, Auction> auctionMap = new LinkedHashMap();
    
    @Override
    public Auction findById(Long id) {
        if(auctionRep != null){
            return auctionRep.getOne(id);
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Auction save(Auction auction) {
        LOG.debug("save(auction)=" + auction);
        auctionMap.put(auction.getAuctionuuid(), auction);
        return auction;
    }

    @Override
    public List<Auction> findAll() {
        List<Auction> auctionList = new ArrayList();
        for (String key : auctionMap.keySet()) {
            Auction a = auctionMap.get(key);
            auctionList.add(a);
        }
        return auctionList;
    }

    @Override
    public void deleteById(long id) {
        auctionRep.deleteById(id);
    }

    @Override
    public void delete(Auction auction) {
        auctionRep.delete(auction);
    }

    @Override
    public void deleteAll() {
        auctionRep.deleteAll();
    }

    @Override
    public Auction findByAuctionuuid(String auctionuuid) {
        return auctionMap.get(auctionuuid);
    }

    @Override
    public List<Auction> findActiveOrScheduledBefore(Date time) {
        List<Auction> auctionList = new ArrayList();
        for (String key : auctionMap.keySet()) {
            Auction a = auctionMap.get(key);
            
            if(a.getStartTime().before(time) 
                    && ( AuctionOrLotStatus.SCHEDULED == a.getAuctionStatus()
                    || AuctionOrLotStatus.ACTIVE == a.getAuctionStatus() )
                    ){
                 auctionList.add(a);
            }
        }
        return auctionList;
    }
    
    /*
    * Persist the auction map to the database with this method
    *
    */
    public void saveAuctionMap(){
        if(auctionRep != null){
        if(!(auctionMap.isEmpty())) {
               List<Auction> auctions = findAll();
               for(Auction auction : auctions){
                   LOG.debug("Saving map: (auction)=" + auction);
                   auctionRep.save(auction);
               }
        }
        } else {
            LOG.debug("AuctionRep = " + auctionRep);
            throw new NullPointerException();
        }
    }

}
