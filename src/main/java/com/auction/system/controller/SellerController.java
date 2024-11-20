package com.auction.system.controller;

import com.auction.system.services.SellerService;

public class SellerController {
    private SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public void addSeller(String name) {
        try {
            sellerService.addSeller(name);
            System.out.println("Seller added: " + name);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
