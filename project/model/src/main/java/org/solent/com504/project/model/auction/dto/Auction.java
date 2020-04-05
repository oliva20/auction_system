package org.solent.com504.project.model.auction.dto;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.solent.com504.project.model.lot.dto.Lot;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Auction {
    
    public Long id;
    
    private Date startTime;
    
    private Set<Lot> lots;
    
    private AuctionType auctionType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    public AuctionType getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(AuctionType auctionType) {
        this.auctionType = auctionType;
    }
    


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public AuctionType getType() {
        return auctionType;
    }

    public void setType(AuctionType type) {
        this.auctionType = type;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Lot> getLots() {
        return lots;
    }

    public void setLots(Set<Lot> lots) {
        this.lots = lots;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
