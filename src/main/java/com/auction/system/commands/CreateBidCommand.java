package com.auction.system.commands;

import com.auction.system.controller.AuctionController;

public class CreateBidCommand implements Command{
    private final AuctionController auctionController;

    public CreateBidCommand(AuctionController auctionController) {
        this.auctionController = auctionController;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: CREATE_BID <buyerName> <auctionId> <amount>");
            return;
        }
        try {
            String buyerName = args[1];
            String auctionId = args[2];
            double amount = Double.parseDouble(args[3]);
            auctionController.placeBid(buyerName, auctionId, amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input for CREATE_BID.");
        }
    }
}
