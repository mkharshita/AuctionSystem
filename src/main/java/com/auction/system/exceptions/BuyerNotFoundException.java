package com.auction.system.exceptions;

public class BuyerNotFoundException extends RuntimeException {
    public BuyerNotFoundException(String message) {
        super(message);
    }
}
