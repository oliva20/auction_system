/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.auction.springdao;

import java.util.ArrayList;
import java.util.List;
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.model.MultipleFailureException.assertEmpty;
import org.solent.com504.project.model.auction.dto.AuctionOrLotStatus;

/**
 *
 * @author Andre
 * This article explains junit very well -> https://www.javatpoint.com/junit-tutorial
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class AuctionSpringImplTest {
    
    final static Logger LOG = LogManager.getLogger(AuctionRepositoryTest.class);

    @Autowired
    private AuctionSpringDAOImpl auctionSpringRep = null;
    
    @Autowired
    private AuctionRepository auctionRep = null;
    
    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(auctionSpringRep);
        assertNotNull(auctionRep);
        auctionRep.deleteAll(); //erase any test data before testing
        LOG.debug("before test complete");
    }
    
    @Transactional
    @Test
    public void testFindAuction(){
       LOG.debug("@@@ Beginning test");
       
       Auction auction1 = new Auction();
       auctionSpringRep.save(auction1); //saving to the map 
       auctionSpringRep.saveAuctionMap(); //saving the map to the database
       
       LOG.debug("@@@ Auction1 = " + auction1.toString());
       
       Long id = auction1.getId();
       Auction auction2 = auctionRep.getOne(id); //get the auction after being save by the first repository
       
       LOG.debug("@@@ Auction2 = " + auction2.toString());

       assertNotNull(auction2);
       assertEquals(auction1, auction2);
    }
    
        
    @Transactional
    @Test
    public void testFindAndDeleteAllAuctions(){
        //generate dummy auctions
        LOG.debug("@@@ Started saving auctions");
        List<Auction> auctions = storeDummyAuctions(10);
        List<Auction> expAuctions = auctionRep.findAll();
        
        assertNotNull(auctions);
        assertEquals(auctions, expAuctions);
        
        auctionRep.deleteAll();
        expAuctions = auctionRep.findAll();
        
        assertTrue(expAuctions.isEmpty());
    }
 
    public void testFindByStartTime(){ 
    
    }
    
    
    public List<Auction> storeDummyAuctions(int num) {
        List<Auction> auctions = new ArrayList<Auction>(); 

        for(int i=0; i < num; i++) {
            Auction auction = new Auction(); 
            auction.setAuctionStatus(AuctionOrLotStatus.ACTIVE);
            auction.setDescription("Test Auction no. " + i);
            auctions.add(auction);
            auctionSpringRep.save(auction);
        }
        
        auctionSpringRep.saveAuctionMap();
        return auctions;
    }
}