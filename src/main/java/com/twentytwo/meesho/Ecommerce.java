package com.twentytwo.meesho;

import com.twentytwo.meesho.entity.Buyer;
import com.twentytwo.meesho.entity.Order;
import com.twentytwo.meesho.entity.Product;
import com.twentytwo.meesho.entity.enums.PaymentMode;
import com.twentytwo.meesho.impl.OrderManagerImpl;
import com.twentytwo.meesho.impl.ProductManagerImpl;
import com.twentytwo.meesho.impl.ServiceabilityManagerImpl;

public class Ecommerce {

    public static void main(String[] args) {

        Buyer buyer = new Buyer(1, "Ayush", 123456, "ayush@gmail.com", "9792922864");
        Buyer buyer1 = new Buyer(1, "Ayush", 1234567, "ayush@gmail.com", "9792922864");

        Product product = new Product(1, "Laptop", 100000d, 12345, 3);

        IProductManager productManager = ProductManagerImpl.getINSTANCE();

        productManager.create(product, product.getInventory());

        Order order = new Order(1, PaymentMode.COD, 2, product);
        Order order2 = new Order(1, PaymentMode.COD, 2, product);


        IServiceabilityManager serviceabilityManager = ServiceabilityManagerImpl.getINSTANCE();

        serviceabilityManager.create(12345, 123456, PaymentMode.COD);

        IOrderManager orderManager = OrderManagerImpl.getINSTANCE();

        Order responseOrder1 = orderManager.create(buyer, order);
        Order responseOrder2 = orderManager.create(buyer, order2);

        System.out.println(responseOrder1);
        System.out.println(responseOrder2);
    }
}
