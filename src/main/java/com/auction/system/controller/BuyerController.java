package com.auction.system.controller;

import com.auction.system.services.BuyerService;

public class BuyerController {
    private BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    public void addBuyer(String name) {
        try {
            buyerService.addBuyer(name);
            System.out.println("Buyer added: " + name);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
