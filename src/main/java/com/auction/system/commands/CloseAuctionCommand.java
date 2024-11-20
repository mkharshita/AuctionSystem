package com.auction.system.commands;

import com.auction.system.controller.AuctionController;

public class CloseAuctionCommand implements Command{
    private final AuctionController auctionController;

    public CloseAuctionCommand(AuctionController auctionController) {
        this.auctionController = auctionController;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: CLOSE_AUCTION <auctionId>");
            return;
        }
        auctionController.closeAuction(args[1]);
    }
}
