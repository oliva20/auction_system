/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.auction.springdao;

import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solent.com504.project.impl.auction.springdata.AuctionRepository;
import org.solent.com504.project.model.auction.dto.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Andre
 * This article explains junit very well -> https://www.javatpoint.com/junit-tutorial
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class AuctionRepositoryTest {
    final static Logger LOG = LogManager.getLogger(AuctionRepositoryTest.class);

    @Autowired
    private AuctionRepository auctionRep = null;
    
    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(auctionRep);
        LOG.debug("before test complete");
    }
    
    @Transactional
    @Test
    public void testFindAuction(){
       LOG.debug("@@@ Beginning test");
       
       Auction auction1 = new Auction();
       auctionRep.save(auction1);
       
       LOG.debug("@@@ Auction1 = " + auction1.toString());
       
       Long id = auction1.getId();
       Auction auction2 = auctionRep.getOne(id);
       
       assertNotNull(auction2);
       assertEquals(auction1, auction2);
       
    }
    
        
    @Transactional
    @Test
    public void testFindAllAuctions(){
        
    }
    
}