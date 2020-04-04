/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.auction.springdao;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.auction.springdata.BidRepository;

import org.solent.com504.project.model.auction.dao.BidDAO;
import org.solent.com504.project.model.auction.dto.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component
public class BidSpringDAOImpl implements BidDAO {
    
    final static Logger LOG = LogManager.getLogger(BidSpringDAOImpl.class);
    
    @Autowired
    private BidRepository bidRepository = null; //@@@ autowired not working it is null;
    
    @Override
    public Bid findById(Long id) {
        return bidRepository.getOne(id);
    }

    @Override
    public Bid save(Bid bid) {
       LOG.debug("logging bid: "+bid);
       if(bidRepository != null) {
        bidRepository.save(bid); 
       }
       return bid;
    }

    @Override
    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        bidRepository.deleteById(id);
    }

    @Override
    public void delete(Bid bid) {
        bidRepository.delete(bid);
    }

    @Override
    public void deleteAll() {
        bidRepository.deleteAll();
    }

    @Override
    public Bid findByBiduuid(String biduuid) {
        return bidRepository.getBidByUuid(biduuid);
    }

}
