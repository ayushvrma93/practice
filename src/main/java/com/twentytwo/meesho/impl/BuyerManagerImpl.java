package com.twentytwo.meesho.impl;

import com.twentytwo.meesho.IBuyerManager;
import com.twentytwo.meesho.entity.Buyer;

public class BuyerManagerImpl implements IBuyerManager {

    private static int buyerId = 1;

    @Override
    public Buyer create(Buyer buyer) {

        Integer currId = buyerId++;
        buyers.put(currId, buyer);
        return buyers.get(currId);
    }
}
