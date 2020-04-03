/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.auction.springdao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.solent.com504.project.impl.auction.springdao.AuctionRepositoryTest.LOG;
import org.solent.com504.project.impl.auction.springdata.AuctionRepository;
import org.solent.com504.project.impl.auction.springdata.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author aang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring.xml"})
public class LotRepositoryTest {
    
    final static Logger LOG = LogManager.getLogger(AuctionRepositoryTest.class);

    @Autowired
    private LotRepository lotRep = null;
    
    @Before
    public void before() {
        LOG.debug("before test running");
        assertNotNull(lotRep);
        LOG.debug("before test complete");
    }
    
    @Test
    public void test(){
        LOG.debug("");
    }
    
}
