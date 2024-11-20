package com.auction.system.services;

import com.auction.system.exceptions.AuctionSystemException;
import com.auction.system.exceptions.BuyerNotFoundException;
import com.auction.system.models.Buyer;
import com.auction.system.repository.BuyerRepo;

public class BuyerService {
    private BuyerRepo buyerRepo;

    public BuyerService(BuyerRepo buyerRepo) {
        this.buyerRepo = buyerRepo;
    }

    public void addBuyer(String name) {
        if (buyerRepo.getBuyers().containsKey(name)) {
            throw new AuctionSystemException("Buyer already exists.");
        }
        buyerRepo.getBuyers().put(name, new Buyer(name));
    }

    public Buyer getBuyer(String name) {
        Buyer buyer = buyerRepo.getBuyers().get(name);
        if (buyer == null) {
            throw new BuyerNotFoundException("Buyer not found: " + name);
        }
        return buyer;
    }

    public void incrementParticipation(String buyerName) {
        Buyer buyer = getBuyer(buyerName);
        buyer.incrementParticipation();
    }
}
