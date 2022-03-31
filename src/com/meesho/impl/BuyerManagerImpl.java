package com.meesho.impl;

import com.meesho.IBuyerManager;
import com.meesho.entity.Buyer;

public class BuyerManagerImpl implements IBuyerManager {

    private static int buyerId = 1;

    @Override
    public Buyer create(Buyer buyer) {

        Integer currId = buyerId++;
        buyers.put(currId, buyer);
        return buyers.get(currId);
    }
}
