package com.auction.system.repository;

import com.auction.system.models.Seller;

import java.util.HashMap;
import java.util.Map;

public class SellerRepo {
    private static SellerRepo instance;
    private static Map<String, Seller> sellers;

    private SellerRepo(){
        this.sellers = new HashMap<>();
    }

    public static synchronized SellerRepo getInstance() {
        if (instance == null) {
            instance = new SellerRepo();
        }
        return instance;
    }

    public Map<String, Seller> getSellers() {
        return sellers;
    }

}
