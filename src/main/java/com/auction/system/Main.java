package com.auction.system;

import com.auction.system.commands.Command;
import com.auction.system.commands.CommandFactory;
import com.auction.system.controller.AuctionController;
import com.auction.system.controller.BuyerController;
import com.auction.system.controller.SellerController;
import com.auction.system.repository.AuctionRepo;
import com.auction.system.repository.BuyerRepo;
import com.auction.system.repository.SellerRepo;
import com.auction.system.services.AuctionService;
import com.auction.system.services.BuyerService;
import com.auction.system.services.SellerService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        AuctionRepo auctionRepo = AuctionRepo.getInstance();
        BuyerRepo buyerRepo = BuyerRepo.getInstance();
        SellerRepo sellerRepo = SellerRepo.getInstance();

        SellerService sellerService = new SellerService(sellerRepo);
        BuyerService buyerService = new BuyerService(buyerRepo);
        AuctionService auctionService = new AuctionService(auctionRepo,sellerService,buyerService);
        BuyerController buyerController = new BuyerController(buyerService);
        SellerController sellerController = new SellerController(sellerService);
        AuctionController auctionController = new AuctionController(auctionService);

        CommandFactory commandFactory = new CommandFactory(buyerController, sellerController, auctionController);

        System.out.println("Welcome to the Online Auction System!");
        System.out.println("""
                Available Commands:
                1. ADD_BUYER <buyerName>
                2. ADD_SELLER <sellerName>
                3. CREATE_AUCTION <auctionId> <lowestBid> <highestBid> <participationCost> <sellerName>
                4. CREATE_BID <buyerName> <auctionId> <amount>
                5. CLOSE_AUCTION <auctionId>
                6. GET_PROFIT <sellerName> <auctionId>
                7. EXIT
                """);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            }

            String[] parts = input.split("\\s+");
            String commandName = parts[0];

            Command command = commandFactory.getCommand(commandName);
            if (command != null) {
                command.execute(parts);
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();

    }
}