/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.auction.spring;

import java.util.List;
import org.solent.com504.project.model.auction.dto.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.solent.com504.project.model.auction.dao.AuctionDAO;
import org.solent.com504.project.impl.dao.auction.springdata.AuctionRepository;
import org.solent.com504.project.model.lot.dto.Lot;


/**
 *
 * @author Andre
 */
@Component
public class AuctionDAOImplSpring implements AuctionDAO {
    
    @Autowired
    private AuctionRepository auctionRepository = null;

    @Override
    public Long findAuctionById(Long id){
        return id;
    }
            
    @Override
    public List<Auction> findAll(){
        return auctionRepository.findAll();
    }

    @Override
    public boolean deleteAutcion(Auction auction){
        auctionRepository.deleteAll();
        return true;
    }
//
//    @Override
//    public List<Lot> getAuctionLotsById(Long auctionId){
//        List<Lot> lots = auctionRepository.getAuctionLotsById(auctionId);
//        return lots;
//    }

    @Override
    public Auction updateAuction(Auction auction){
        auctionRepository.save(auction);
        return auction;
    }
    
}
