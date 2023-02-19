package com.twentytwo.meesho;

import com.twentytwo.meesho.entity.Buyer;
import com.twentytwo.meesho.entity.Order;

import java.util.HashMap;
import java.util.Map;

public interface IOrderManager {

    Map<Buyer, Order> orders = new HashMap<>();
    Order create(Buyer buyer, Order order);
    boolean isOrderValid(Buyer buyer, Order order);
}
