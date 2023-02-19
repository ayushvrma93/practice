package com.twentytwo.meesho;

import com.twentytwo.meesho.entity.Buyer;

import java.util.HashMap;
import java.util.Map;

public interface IBuyerManager {

    Map<Integer, Buyer> buyers = new HashMap<>();

    Buyer create(Buyer buyer);
}
