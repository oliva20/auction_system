/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.auction.springdao;

import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solent.com504.project.impl.auction.springdata.BidRepository;
import org.solent.com504.project.model.auction.dto.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author aang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class BidRepositoryTest {
    
    final static Logger LOG = LogManager.getLogger(BidRepositoryTest.class);

    @Autowired
    private BidRepository bidRep = null;
    
    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(bidRep);
        bidRep.deleteAll();
        LOG.debug("before test complete");
    }
    
    @Transactional //this needs to be added
    @Test
    public void testSaveAndGetBid(){
        Bid expBid = new Bid(); 
        expBid.setAmount(2.2);
        LOG.debug("@@@ Exp bid -> " + expBid.toString());
        bidRep.save(expBid);
        Bid gotBid = bidRep.getOne(expBid.getId());
        LOG.debug("@@@ Got bid -> " + gotBid.toString());
        assertEquals(expBid, gotBid);
    }
    
}
