package com.auction.system.services;

import com.auction.system.exceptions.AuctionClosedException;
import com.auction.system.exceptions.AuctionNotFoundException;
import com.auction.system.exceptions.AuctionSystemException;
import com.auction.system.exceptions.BidValidationException;
import com.auction.system.models.Auction;
import com.auction.system.models.Buyer;
import com.auction.system.models.Seller;
import com.auction.system.repository.AuctionRepo;
import com.auction.system.repository.BuyerRepo;
import com.auction.system.repository.SellerRepo;

import java.util.HashMap;
import java.util.Map;

public class AuctionService {
    private AuctionRepo auctionRepo;
    private SellerService sellerService;
    private BuyerService buyerService;

    public AuctionService(AuctionRepo auctionRepo, SellerService sellerService, BuyerService buyerService) {
        this.auctionRepo = auctionRepo;
        this.sellerService = sellerService;
        this.buyerService = buyerService;
    }

    public void createAuction(String id, double lowestBid, double highestBid, double participationCost, String sellerName) {
        if (auctionRepo.getAuctions().containsKey(id)) {
            throw new AuctionSystemException("Auction already exists.");
        }
        Seller seller = sellerService.getSeller(sellerName);
        auctionRepo.getAuctions().put(id, new Auction(id, lowestBid, highestBid, participationCost, seller));
    }

    public void placeBid(String buyerName, String auctionId, double amount) {
        Auction auction = getAuction(auctionId);
        if (!auction.isOpen()) {
            throw new AuctionClosedException("Auction is closed: " + auctionId);
        }
        if (amount < auction.getLowestBidLimit() || amount > auction.getHighestBidLimit()) {
            throw new BidValidationException("Bid amount out of range: " + amount);
        }
        Buyer buyer = buyerService.getBuyer(buyerName);
        auction.getBids().put(buyer, amount);
        buyerService.incrementParticipation(buyerName);
    }

    public Auction getAuction(String id) {
        Auction auction = auctionRepo.getAuctions().get(id);
        if (auction == null) {
            throw new AuctionNotFoundException("Auction not found: " + id);
        }
        return auction;
    }

    public void closeAuction(String auctionId) {
        Auction auction = getAuction(auctionId);
        auction.close();

        Map<Buyer, Double> bids = auction.getBids();
        Map<Double, Integer> bidCounts = new HashMap<>();

        for (double bid : bids.values()) {
            bidCounts.put(bid, bidCounts.getOrDefault(bid, 0) + 1);
        }

        double highestUniqueBid = -1;
        for (Map.Entry<Double, Integer> entry : bidCounts.entrySet()) {
            if (entry.getValue() == 1 && entry.getKey() > highestUniqueBid) {
                highestUniqueBid = entry.getKey();
            }
        }

        if (highestUniqueBid == -1) {
            System.out.println("No winner for auction " + auctionId);
            return;
        }

        for (Map.Entry<Buyer, Double> entry : bids.entrySet()) {
            if (entry.getValue() == highestUniqueBid) {
                System.out.println("Winner for auction " + auctionId + ": " + entry.getKey().getName());
                return;
            }
        }
    }

    public double getProfit(String sellerName, String auctionId) {
        Auction auction = getAuction(auctionId);

        if (!auction.getSeller().getName().equals(sellerName)) {
            throw new AuctionSystemException("Seller does not own this auction.");
        }

        Map<Buyer, Double> bids = auction.getBids();
        Map<Double, Integer> bidCounts = new HashMap<>();

        // Calculate bid counts
        for (double bid : bids.values()) {
            bidCounts.put(bid, bidCounts.getOrDefault(bid, 0) + 1);
        }

        // Find the highest unique bid
        double highestUniqueBid = -1;
        for (Map.Entry<Double, Integer> entry : bidCounts.entrySet()) {
            if (entry.getValue() == 1 && entry.getKey() > highestUniqueBid) {
                highestUniqueBid = entry.getKey();
            }
        }

        // Participation cost share for the seller
        double participationCostShare = bids.size() * auction.getParticipationCost() * 0.2;

        // Average of lowest and highest bid limits
        double avgBidLimit = (auction.getLowestBidLimit() + auction.getHighestBidLimit()) / 2;

        // Profit/Loss Calculation
        double profit;
        if (highestUniqueBid == -1) {
            // No winner
            profit = participationCostShare;
        } else {
            profit = highestUniqueBid + participationCostShare - avgBidLimit;
        }

        System.out.printf("Profit/Loss for seller %s in auction %s: %.2f%n", sellerName, auctionId, profit);
        return profit;
    }

}
