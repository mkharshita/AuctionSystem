package com.auction.system.commands;

import com.auction.system.controller.AuctionController;

public class CreateAuctionCommand implements Command {
    private final AuctionController auctionController;

    public CreateAuctionCommand(AuctionController auctionController) {
        this.auctionController = auctionController;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 6) {
            System.out.println("Usage: CREATE_AUCTION <auctionId> <lowestBid> <highestBid> <participationCost> <sellerName>");
            return;
        }
        try {
            String auctionId = args[1];
            double lowestBid = Double.parseDouble(args[2]);
            double highestBid = Double.parseDouble(args[3]);
            double participationCost = Double.parseDouble(args[4]);
            String sellerName = args[5];
            auctionController.createAuction(auctionId, lowestBid, highestBid, participationCost, sellerName);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input for CREATE_AUCTION.");
        }
    }
}
