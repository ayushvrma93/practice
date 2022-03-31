package com.meesho;

import com.meesho.entity.Buyer;

import java.util.HashMap;
import java.util.Map;

public interface IBuyerManager {

    Map<Integer, Buyer> buyers = new HashMap<>();

    Buyer create(Buyer buyer);
}
