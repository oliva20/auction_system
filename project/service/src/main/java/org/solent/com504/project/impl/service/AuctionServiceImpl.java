/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.service;

import java.util.List;
import org.solent.com504.project.impl.dao.auction.springdata.AuctionRepository;
import org.solent.com504.project.model.auction.dao.AuctionDAO;
import org.solent.com504.project.model.auction.dto.Auction;
import org.solent.com504.project.model.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Josh
 */
@Component("auctionService")
public class AuctionServiceImpl implements AuctionService{

    @Autowired
    private AuctionDAO auctionDao = null;
    
    @Autowired
    private AuctionRepository auctionRepo;
    
    @Override
    public List<Auction> getAuctions() {
        return auctionRepo.findAll();
    }
    
}
