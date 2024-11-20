package com.auction.system.models;

import java.util.*;

public class Auction {
    private String id;
    private double lowestBidLimit;
    private double highestBidLimit;
    private double participationCost;
    private Seller seller;
    private Map<Buyer, Double> bids;
    private boolean isOpen;

    public Auction(String id, double lowestBidLimit, double highestBidLimit, double participationCost, Seller seller) {
        this.id = id;
        this.lowestBidLimit = lowestBidLimit;
        this.highestBidLimit = highestBidLimit;
        this.participationCost = participationCost;
        this.seller = seller;
        this.bids = new HashMap<>();
        this.isOpen = true;
    }

    public String getId() {
        return id;
    }

    public double getParticipationCost() {
        return participationCost;
    }

    public Seller getSeller() {
        return seller;
    }

    public Map<Buyer, Double> getBids() {
        return bids;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void close() {
        this.isOpen = false;
    }

    public double getLowestBidLimit() {
        return lowestBidLimit;
    }

    public double getHighestBidLimit() {
        return highestBidLimit;
    }
}
