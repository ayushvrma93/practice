package com.twentytwo.meesho.impl;

import com.twentytwo.meesho.IProductManager;
import com.twentytwo.meesho.entity.Product;

import java.util.Map;

public class ProductManagerImpl implements IProductManager {

    private static final IProductManager INSTANCE = new ProductManagerImpl();

    private ProductManagerImpl(){}

    public static IProductManager getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Product create(Product product, Integer quantity) {

        products.put(product, quantity);
        return product;
    }

    @Override
    public Map<Product, Integer> getProducts() {
        return products;
    }
}
