package org.solent.com504.project.model.bid.dto;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.solent.com504.project.model.lot.dto.Lot;

@Entity
public class Bid {
    private Double value;

    private Date timeStamp;

    private Long id;

    private Long partyId;
    
    private Lot lot;
    
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    @ManyToOne
    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    @Override
    public String toString() {
        return "Bid{" + "value=" + value + ", timeStamp=" + timeStamp + ", id=" + id + ", partyId=" + partyId + ", lot=" + lot + '}';
    }






}
