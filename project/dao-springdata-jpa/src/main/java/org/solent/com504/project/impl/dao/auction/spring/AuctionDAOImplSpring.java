/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.auction.spring;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.solent.com504.project.model.auction.dto.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.solent.com504.project.model.auction.dao.AuctionDAO;
import org.solent.com504.project.impl.dao.auction.springdata.AuctionRepository;
import org.solent.com504.project.model.party.dto.Party;


/**
 *
 * @author Andre
 */
@Component
public class AuctionDAOImplSpring implements AuctionDAO {
    
    @Autowired
    private AuctionRepository auctionRepository = null;

    @Override
    public Auction findById(Long id) {
        return auctionRepository.getOne(id);
    }

    @Override
    public Auction save(Auction auction) {
        return auctionRepository.save(auction);
    }

    @Override
    public List<Auction> findAll() {
        return auctionRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        auctionRepository.deleteById(id);
    }

    @Override
    public void delete(Auction auction) {
        auctionRepository.delete(auction);
    }

    @Override
    public void deleteAll() {
        auctionRepository.deleteAll();
    }

    @Override
    public Auction findByAuctionuuid(String auctionuuid) {
        return auctionRepository.findByAuctionuuid(auctionuuid);
    }

    @Override
    public List<Auction> findActiveOrScheduledBefore(Date time) {
        return auctionRepository.findActiveOrScheduledBefore(time);
    }

    @Override
    public void updateAuctionPartys(String auctionuuid, Set<Party> partys) {
        auctionRepository.updateAuctionPartys(auctionuuid, partys);
    }

    
}
