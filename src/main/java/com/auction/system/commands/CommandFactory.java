package com.auction.system.commands;

import com.auction.system.controller.AuctionController;
import com.auction.system.controller.BuyerController;
import com.auction.system.controller.SellerController;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandFactory(BuyerController buyerController, SellerController sellerController, AuctionController auctionController) {
        commands.put("ADD_BUYER", new AddBuyerCommand(buyerController));
        commands.put("ADD_SELLER", new AddSellerCommand(sellerController));
        commands.put("CREATE_AUCTION", new CreateAuctionCommand(auctionController));
        commands.put("CREATE_BID", new CreateBidCommand(auctionController));
        commands.put("CLOSE_AUCTION", new CloseAuctionCommand(auctionController));
        commands.put("GET_PROFIT", new GetProfitCommand(auctionController));
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName.toUpperCase());
    }
}
