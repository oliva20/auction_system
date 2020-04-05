package org.solent.com504.project.model.lot.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.solent.com504.project.model.bid.dto.Bid;

@Entity
public class Lot {

    private Long id;

    private Double reservedPrice = 0.0;

    private Double highestBidPrice = 0.0;

    private Integer duration;

    private Date pickdate;

    private String grade;

    private Integer life_days;

    private Integer quantity;

    private Set<Bid> bids = new HashSet();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getReservedPrice() {
        return reservedPrice;
    }

    public void setReservedPrice(Double reservedPrice) {
        this.reservedPrice = reservedPrice;
    }

    public Double getHighestBidPrice() {
        return highestBidPrice;
    }

    private void setHighestBidPrice() {
        Double x = 0.0;
        for(Bid bid:bids){
            if(bid.getValue() > x){x=bid.getValue();}
        }
        this.highestBidPrice = x;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getPickdate() {
        return pickdate;
    }

    public void setPickdate(Date pickdate) {
        this.pickdate = pickdate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getLife_days() {
        return life_days;
    }

    public void setLife_days(Integer life_days) {
        this.life_days = life_days;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    //before adding a bid to a lot, the bid must have a higher value that the bid that was added before
    public Boolean addBid(Bid bid) {
        //before adding a bid to a lot, the bid must have a higher value that the bid that was added before
        if (bid.getValue() > highestBidPrice && bid.getValue() > reservedPrice) {
            bids.add(bid);
            //set the new highest bid price
            setHighestBidPrice();
            return true;
        }

        return false;
    }

    public void setHighestBidPrice(Double highestBidPrice) {
        this.highestBidPrice = highestBidPrice;
    }
    
    

    @Override
    public String toString() {
        return "Lot{" + "id=" + id + ", reservedPrice=" + reservedPrice + ", highestBidPrice=" 
                + highestBidPrice + ", duration=" + duration + ", pickdate=" 
                + pickdate + ", grade=" + grade + ", life_days=" + life_days + ", quantity=" 
                + quantity + ", bids=" + bids + '}';
    }

}
