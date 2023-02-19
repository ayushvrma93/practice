package com.twentytwo.meesho.impl;

import com.twentytwo.meesho.IOrderManager;
import com.twentytwo.meesho.IProductManager;
import com.twentytwo.meesho.IServiceabilityManager;
import com.twentytwo.meesho.entity.Buyer;
import com.twentytwo.meesho.entity.Order;
import com.twentytwo.meesho.entity.Product;
import com.twentytwo.meesho.entity.enums.PaymentMode;

import java.util.Map;
import java.util.Set;

public class OrderManagerImpl implements IOrderManager {

    private static final IOrderManager INSTANCE = new OrderManagerImpl();

    private static final IServiceabilityManager serviceabilityManager = ServiceabilityManagerImpl.getINSTANCE();
    private static final IProductManager productManager = ProductManagerImpl.getINSTANCE();

    public static IOrderManager getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Order create(Buyer buyer, Order order) {

        if(isOrderValid(buyer, order)) {
            orders.put(buyer, order);

            Product product = order.getProduct();

            Map<Product, Integer> productIntegerMap = productManager.getProducts();

            synchronized (this) {
                Integer quantity = productIntegerMap.get(product);

                quantity = quantity - order.getQuantity();

                productIntegerMap.put(product, quantity);
            }

            return orders.get(buyer);
        }


        return null;
    }

    @Override
    public boolean isOrderValid(Buyer buyer, Order order) {

        if(buyer == null || order == null){
            System.out.println("Buyer or Order can not be null");
            return false;
        }
        return isServiceable(buyer, order) && isInventoryAvailable(order);
    }


    private boolean isInventoryAvailable(Order order){

        Map<Product, Integer> products = productManager.getProducts();
        Product product = order.getProduct();

        synchronized (this){

            if(!products.containsKey(product)){
                System.out.println("Order failed because product is unavailable");
                return false;
            }

            if(products.get(product) < order.getQuantity()){
                System.out.println("Order failed because product is unavailable");
                return false;
            }
        }
        return true;
    }


    private boolean isServiceable(Buyer buyer, Order order) {
        Map<Integer, Map<Integer, Set<PaymentMode>>> pinVsMode = serviceabilityManager.getServiceabilityMap();

        Product product = order.getProduct();
        Integer pinCode = product.getPickupAddress();

        if(!pinVsMode.containsKey(pinCode)){
            System.out.println("Order failed because pincode is unserviceable");
            return false;
        }

        Map<Integer, Set<PaymentMode>> destMap = pinVsMode.get(pinCode);

        if(destMap == null){
            System.out.println("Order failed because pincode is unserviceable");
            return false;
        }

        if(!destMap.containsKey(buyer.getAddress())){
            System.out.println("Order failed because pincode is unserviceable");
            return false;
        }

        Set<PaymentMode> modes = destMap.get(buyer.getAddress());

        if(!modes.contains(order.getPaymentMode())){
            System.out.println("Order failed because pincode is unserviceable");
            return false;
        }
        return true;
    }
}
