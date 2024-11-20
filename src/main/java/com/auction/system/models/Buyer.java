package com.auction.system.models;

public class Buyer {
    private final String name;
    private boolean isPreferred;
    private int auctionParticipationCount;

    public Buyer(String name) {
        this.name = name;
        this.isPreferred = false;
        this.auctionParticipationCount = 0;
    }

    public String getName() {
        return name;
    }

    public boolean isPreferred() {
        return isPreferred;
    }

    public void incrementParticipation() {
        auctionParticipationCount++;
        if (auctionParticipationCount > 2) {
            isPreferred = true;
        }
    }
}
