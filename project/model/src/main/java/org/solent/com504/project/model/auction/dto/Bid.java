package org.solent.com504.project.model.auction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.solent.com504.project.model.party.dto.Party;

@Schema(description = "Flower Object which contains details of flowers")

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Bid {

    private Date time;
    
    private Long id;

    private Party bidder;
    
    private String bidderuuid;

    private Lot lot;

    private Double amount;
    
    // should be unique at time of creation but not best solution
    private String biduuid =UUID.randomUUID().toString(); 

    public Bid(){
        time = new Date();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public Party getBidder() {
        return bidder;
    }

    public void setBidder(Party bidder) {
        this.bidder = bidder;
    }

    @ManyToOne
    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getBiduuid() {
        return biduuid;
    }

    public void setBiduuid(String biduuid) {
        this.biduuid = biduuid;
    }

    public String getBidderuuid() {
        return bidderuuid;
    }

    public void setBidderuuid(String bidderuuid) {
        this.bidderuuid = bidderuuid;
    }

    @Override
    public String toString() {
        return "Bid{ time=" + time 
                + ", id=" + id 
                + ", bidderid=" +  ( (bidder==null) ? null : bidder.getId())
                + ", bidderuuid=" + bidderuuid 
                + ", lotuuid=" + ( (lot==null) ? null : lot.getLotuuid())
                + ", amount=" + amount 
                + ", biduuid=" + biduuid + '}';
    }




    
    
}
