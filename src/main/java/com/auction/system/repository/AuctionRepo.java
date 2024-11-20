package com.auction.system.repository;

import com.auction.system.models.Auction;
import com.auction.system.models.Seller;

import java.util.HashMap;
import java.util.Map;

public class AuctionRepo {

    private static AuctionRepo instance;
    private static Map<String, Auction> auctions;

    private AuctionRepo(){
        this.auctions = new HashMap<>();
    }

    public static synchronized AuctionRepo getInstance() {
        if (instance == null) {
            instance = new AuctionRepo();
        }
        return instance;
    }

    public Map<String, Auction> getAuctions() {
        return auctions;
    }
}
