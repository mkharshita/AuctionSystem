package com.auction.system.controller;

import com.auction.system.exceptions.AuctionSystemException;
import com.auction.system.services.AuctionService;

public class AuctionController {
    private AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    public void createAuction(String id, double lowestBid, double highestBid, double participationCost, String sellerName) {
        try {
            auctionService.createAuction(id, lowestBid, highestBid, participationCost, sellerName);
            System.out.println("Auction created: " + id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void placeBid(String buyerName, String auctionId, double amount) {
        try {
            auctionService.placeBid(buyerName, auctionId, amount);
            System.out.println("Bid placed: " + buyerName + " in " + auctionId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void closeAuction(String auctionId) {
        try {
            auctionService.closeAuction(auctionId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getProfit(String sellerName, String auctionId) {
        try {
            double profit = auctionService.getProfit(sellerName, auctionId);
            System.out.printf("Profit/Loss for seller %s in auction %s: %.2f%n", sellerName, auctionId, profit);
        } catch (AuctionSystemException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
