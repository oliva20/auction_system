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
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.solent.com504.project.impl.auction.springdao.AuctionSpringImplTest.LOG;
import org.solent.com504.project.impl.auction.springdata.LotRepository;
import org.solent.com504.project.model.auction.dto.AuctionType;
import org.solent.com504.project.model.auction.dto.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Andre
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class LotSpringImplTest {
    final static Logger LOG = LogManager.getLogger(AuctionRepositoryTest.class);

    @Autowired
    private LotSpringDAOImpl lotSpringRep = null; 
    
    @Autowired
    private LotRepository  lotRep = null;
    
    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(lotSpringRep);
        assertNotNull(lotRep);
        lotRep.deleteAll(); //erase any test data before testing
        LOG.debug("before test complete");
    }
    
    @Transactional
    @Test
    public void testFindById(){
        Lot expLot = new Lot();
        expLot.setAuctionType(AuctionType.NORMAL);
        
        lotRep.save(expLot);
        
        Lot returnedLot = lotSpringRep.findById(expLot.getId());
        
        assertEquals(expLot, returnedLot);
        
    }

    @Transactional
    @Test
    public void testFindByLotuuid() {
        Lot lot = lotSpringRep.findByLotuuid("fake-string-uuid");
        assertNull(lot);
    }
}
