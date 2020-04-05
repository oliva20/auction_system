
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.model.dto.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

import org.solent.com504.project.model.auction.dto.Auction;
import org.solent.com504.project.model.bid.dto.Bid;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.Address;
import org.solent.com504.project.model.dto.ReplyMessage;
import org.solent.com504.project.model.lot.dto.Lot;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.solent.com504.project.model.party.dto.PartyStatus;
import org.solent.com504.project.model.user.dto.Role;
import org.solent.com504.project.model.user.dto.User;
import org.solent.com504.project.model.user.dto.UserRoles;

public class ModelJaxbTest {

    final static Logger LOG = LogManager.getLogger(ModelJaxbTest.class);
    
    public JAXBContext jaxbContext;
    
    @Before
    public void setup() {
        // this contains a list of Jaxb annotated classes for the context to parse, seperated by :
        // NOTE you must also have a jaxb.index or jaxb ObjectFactory in the same classpath
        
        //TODO: Problem here and it isnt allowing the test to start
        try {
             jaxbContext = JAXBContext.newInstance(
                    "org.solent.com504.project.model.dto"
                    + ":org.solent.com504.project.model.user.dto"
                    + ":org.solent.com504.project.model.party.dto"
                    + ":org.solent.com504.project.model.auction.dto"
                    + ":org.solent.com504.project.model.bid.dto"
                    + ":org.solent.com504.project.model.lot.dto");
             
        } catch (JAXBException e) {
            throw new RuntimeException("problem creating jaxb context", e);
        }
    }

    @Test
    public void testModelJaxb1() {

        try {

            // test file we will write and read. 
            // Note in target folder so that will be deleted on each run and will not be saved to git
            File file = new File("target/testTransactionData.xml");
            LOG.debug("writing test file to " + file.getAbsolutePath());

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ReplyMessage replyMessage = new ReplyMessage();

            List<Party> partyList = new ArrayList<Party>();
            replyMessage.setPartyList(partyList);
            Party party = new Party();
            party.setPartyStatus(PartyStatus.ACTIVE);
            party.setPartyRole(PartyRole.UNDEFINED);
            Address address = new Address();
            address.setAddressLine1("home for me");
            party.setAddress(address);
            partyList.add(party);
            
            //################### auction 1 ###################
            //auction
            Auction a1 = new Auction();
            //Llots 
            Set<Lot> lots = new HashSet<>();
            Lot lot1 = new Lot();

            //Bids
            //bid objects needs to be iplemented with getters and setters
            Bid bid1 = new Bid();
            Bid bid2 = new Bid();
            Set<Bid> bids = new HashSet();
            
            //bids and lots to lists
            bids.add(bid1);
            bids.add(bid2);
            
            
            //add bids to lot
            lot1.setBids(bids);
            
            //create a lot
            lot1.setGrade("Grade 1");
            //highest bid price should be a method implemented in the actual object
            lot1.setDuration(2);
            lots.add(lot1);
            a1.setLots(lots);
            //################### end ##########################
            
            
            //################### auction 2 ####################
            Auction a2 = new Auction(); 
            //Llots 
            Set<Lot> lots2 = new HashSet<>();
            Lot lot2 = new Lot();

            //Bids
            //bid objects needs to be iplemented with getters and setters
            Bid bid3 = new Bid();
            Bid bid4 = new Bid();
            Set<Bid> bids2 = new HashSet();
            
            //bids and lots to lists
            bids.add(bid3);
            bids.add(bid4);
            
            
            //add bids to lot
            lot1.setBids(bids2);
            
            //create a lot
            lot2.setDuration(2);
            lots2.add(lot1);
            a2.setLots(lots2);
            
            //################### end ##########################

            //auction list conainting auctions
            List<Auction> auctionList = new ArrayList(); 
            //add auctions to the auction list
            auctionList.add(a1);
            auctionList.add(a2);

            //set reply message auctio  list
            replyMessage.setAuctionList(auctionList);
            
            // create XML from the object
            // marshal the object lists to system out, a file and a stringWriter
            jaxbMarshaller.marshal(replyMessage, System.out);
            jaxbMarshaller.marshal(replyMessage, file);

            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller.marshal(replyMessage, sw1);

            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            ReplyMessage receivedTransactionResult = (ReplyMessage) jaxbUnMarshaller.unmarshal(file);

            StringWriter sw2 = new StringWriter();
            jaxbMarshaller.marshal(receivedTransactionResult, sw2);

            // check transmitted and recieved messages are the same
            assertEquals(sw1.toString(), sw2.toString());

        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }

    @Test
    public void testUserModelJaxb() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // create objects to marshal
            User user = new User();
            Role role1 = new Role(UserRoles.ROLE_USER);
            Role role2 = new Role(UserRoles.ROLE_GLOBAL_ADMIN);
            user.setFirstName("firstName");
            user.setSecondName("secondName");
            user.setPassword("password");
            user.setPasswordConfirm("password");
            Set<Role> roles = new HashSet(Arrays.asList(role1, role2));
            user.setRoles(roles);
            
            
            // string writer is used to compare received object
            StringWriter sw1 = new StringWriter();
            StringWriter sw2 = new StringWriter();

            jaxbMarshaller.marshal(user, sw1);

            LOG.debug("marshaled code" + sw1);
          
            // having written the file we now read in the file for test
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            InputStream stream = new ByteArrayInputStream(sw1.toString().getBytes(StandardCharsets.UTF_8));
            InputStream stream2 = new ByteArrayInputStream(sw2.toString().getBytes(StandardCharsets.UTF_8));

            User receiveduser = (User) jaxbUnMarshaller.unmarshal(stream);

            LOG.debug("receiveduser=" + receiveduser);


        } catch (JAXBException e) {
            throw new RuntimeException("problem testing jaxb marshalling", e);
        }
    }
    
    @Test
    public void testLotDto(){
        Boolean res = true;
        Lot lot1 = new Lot(); 
        Bid bid = new Bid();
        bid.setValue(5.0);
        lot1.setReservedPrice(10.0);
        res = lot1.addBid(bid);
        
        LOG.debug("@@@ BID VALUE 5.0 / RESERVED PRICE 10.0 @@@ lot highest bid=" + lot1.getHighestBidPrice());
        
        //Method should retrieve false since it is a lower bide value than the reserved price
        assertFalse(res);

        bid.setValue(15.0);
        res = lot1.addBid(bid);
        assertTrue(res);

        
        LOG.debug("@@@ BID VALUE 15.0 / RESERVED PRICE 10.0 @@@ lot highest bid=" + lot1.getHighestBidPrice());
        
        bid.setValue(12.0);
        res = lot1.addBid(bid);
        assertFalse(res);

    }
}
