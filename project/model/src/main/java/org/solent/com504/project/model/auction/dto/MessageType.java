package org.solent.com504.project.model.auction.dto;

public enum MessageType {

    START_OF_AUCTION,
    START_OF_LOT,
    END_OF_AUCTION,
    LOT_WITHDRAWN,
    NEW_HIGHEST_BID,
    LOT_SOLD,
    NEW_PARTICIPANT,
    ERROR,
    NOT_REGISTERED,
    LOT_OR_AUCTION_CLOSED,
    BID,
    PARTICIPANT_LEFT,
    TEST

}
