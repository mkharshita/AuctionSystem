package com.auction.system.commands;

import com.auction.system.controller.SellerController;

public class AddSellerCommand implements Command{
    private final SellerController sellerController;

    public AddSellerCommand(SellerController sellerController) {
        this.sellerController = sellerController;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: ADD_SELLER <sellerName>");
            return;
        }
        sellerController.addSeller(args[1]);
    }
}
