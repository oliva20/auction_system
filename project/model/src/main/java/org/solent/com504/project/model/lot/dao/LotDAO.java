package org.solent.com504.project.model.lot.dao;


import org.solent.com504.project.model.bid.dto.Bid;
import org.solent.com504.project.model.lot.dto.Lot;

public interface LotDAO {

    public Lot createLot(Lot lot);

    public Long deleteLotById(Long lotId);

    public Lot createBid(Bid bid, Lot lot);

    public Long getLotById(Long lotId);

    public Lot updateLot(Lot lot);
}
