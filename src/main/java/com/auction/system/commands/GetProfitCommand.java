package com.auction.system.commands;

import com.auction.system.controller.AuctionController;

public class GetProfitCommand implements Command {
    private final AuctionController auctionController;

    public GetProfitCommand(AuctionController auctionController) {
        this.auctionController = auctionController;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: GET_PROFIT <sellerName> <auctionId>");
            return;
        }
        auctionController.getProfit(args[1], args[2]);
    }
}
