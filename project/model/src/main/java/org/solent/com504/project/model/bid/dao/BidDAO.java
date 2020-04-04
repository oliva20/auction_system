package org.solent.com504.project.model.bid.dao;


import org.solent.com504.project.model.bid.dto.Bid;

public interface BidDAO {

    public Bid createBid(Bid bid);

    public Boolean deleteBidById(Long bidId);

    public Bid updateBid(Bid bid);

    public void untitledMethod();
}
