package com.auction.system.commands;

import com.auction.system.controller.BuyerController;

public class AddBuyerCommand implements Command {
    private final BuyerController buyerController;

    public AddBuyerCommand(BuyerController buyerController) {
        this.buyerController = buyerController;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: ADD_BUYER <buyerName>");
            return;
        }
        buyerController.addBuyer(args[1]);
    }
}
