/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.auction.springdao;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.auction.springdata.LotRepository;
import org.solent.com504.project.model.auction.dao.AuctionDAO;
import org.solent.com504.project.model.auction.dao.LotDAO;
import org.solent.com504.project.model.auction.dto.Auction;
import org.solent.com504.project.model.auction.dto.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component
public class LotSpringDAOImpl implements LotDAO {
    
    final static Logger LOG = LogManager.getLogger(LotSpringDAOImpl.class);

    private AuctionDAO auctionDAO;
    
    @Autowired
    private LotRepository lotRepository = null;
    
    public LotSpringDAOImpl(AuctionDAO auctionDAO) {
        this.auctionDAO = auctionDAO;
    }

    @Override
    public Lot findById(Long id) {
        return lotRepository.getOne(id);
    }

    @Override
    public Lot save(Lot lot, String auctionuuid) {
        Auction auction = auctionDAO.findByAuctionuuid(auctionuuid);
        List<Lot> lots; 
        if(auction != null) {
            lots = auction.getLots();
        } else {
           lots = new ArrayList<>(); 
        }
        lots.add(lot); 
        auction.setLots(lots);
        auctionDAO.updateAuction(auction);
        return lot;
    }

    @Override
    public List<Lot> findAll() {
        List<Lot> lotList= new ArrayList();
        for (Auction auction : auctionDAO.findAll()) {
            for (Lot lot : auction.getLots()) {
                lotList.add(lot);
            }
        }
        return lotList;
    }

    @Override
    public void deleteById(long id) {
        lotRepository.deleteById(id);
    }

    @Override
    public void delete(Lot lot) {
        lotRepository.delete(lot);
    }

    @Override
    public void deleteAll() {
        lotRepository.deleteAll();
    }

    @Override
    public Lot findByLotuuid(String lotuuid) {
        for (Auction auction : auctionDAO.findAll()) {
            for (Lot lot : auction.getLots()) {
                if (lot.getLotuuid().equals(lotuuid)) {
                    return lot;
                }
            }
        }
        return null;
    }

}
