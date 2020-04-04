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
 * @author andre
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class BidSpringImplTest {
    final static Logger LOG = LogManager.getLogger(BidSpringImplTest.class);

    @Autowired
    private BidRepository bidRep = null;
    
    @Autowired
    private BidSpringDAOImpl bidSpringRep = null;
    
    @Before
    public void before(){
        LOG.debug("Starting bid test");
        assertNotNull(bidSpringRep);
        assertNotNull(bidRep);
        bidSpringRep.deleteAll();
    }

    @Transactional
    @Test
    public void testSave(){
        Bid expBid = new Bid();
        expBid.setAmount(2.2);
        LOG.debug("@@@ Exp Bid:" + expBid.toString());
        bidSpringRep.save(expBid);

        Bid gotBid = bidSpringRep.findById(expBid.getId());
        LOG.debug("@@@ Got Bid:" + gotBid.toString());

        assertEquals(expBid, gotBid);
        
    }
    
}
