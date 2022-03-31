package com.meesho;

import com.meesho.entity.Buyer;
import com.meesho.entity.Order;

import java.util.HashMap;
import java.util.Map;

public interface IOrderManager {

    Map<Buyer, Order> orders = new HashMap<>();
    Order create(Buyer buyer, Order order);
    boolean isOrderValid(Buyer buyer, Order order);
}
