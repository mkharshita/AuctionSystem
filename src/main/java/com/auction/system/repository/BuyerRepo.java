package com.auction.system.repository;

import com.auction.system.models.Auction;
import com.auction.system.models.Buyer;

import java.util.HashMap;
import java.util.Map;

public class BuyerRepo {
    private static BuyerRepo instance;
    private static Map<String, Buyer> buyers;

    private BuyerRepo(){
        this.buyers = new HashMap<>();
    }

    public static synchronized BuyerRepo getInstance() {
        if (instance == null) {
            instance = new BuyerRepo();
        }
        return instance;
    }

    public Map<String, Buyer> getBuyers() {
        return buyers;
    }
}
