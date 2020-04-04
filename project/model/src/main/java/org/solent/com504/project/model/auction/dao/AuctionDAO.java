package org.solent.com504.project.model.auction.dao;

import java.util.List;
import org.solent.com504.project.model.auction.dto.Auction;
import org.solent.com504.project.model.lot.dto.Lot;

public interface AuctionDAO {

    public Long findAuctionById(Long id);

    public List<Auction> findAll();

    public boolean deleteAutcion(Auction auction);

//    public List<Lot> getAuctionLotsById(Long auctionId);

    public Auction updateAuction(Auction auction);
}
