package com.auction.system.services;

import com.auction.system.exceptions.AuctionSystemException;
import com.auction.system.exceptions.SellerNotFoundException;
import com.auction.system.models.Seller;
import com.auction.system.repository.AuctionRepo;
import com.auction.system.repository.SellerRepo;

public class SellerService {
    private SellerRepo sellerRepo;

    public SellerService(SellerRepo sellerRepo) {
        this.sellerRepo = sellerRepo;
    }

    public void addSeller(String name) {
        if (sellerRepo.getSellers().containsKey(name)) {
            throw new AuctionSystemException("Seller already exists.");
        }
        sellerRepo.getSellers().put(name, new Seller(name));
    }

    public Seller getSeller(String name) {
        Seller seller = sellerRepo.getSellers().get(name);
        if (seller == null) {
            throw new SellerNotFoundException("Seller not found: " + name);
        }
        return seller;
    }
}
