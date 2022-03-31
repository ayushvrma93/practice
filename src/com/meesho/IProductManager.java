package com.meesho;

import com.meesho.entity.Product;

import java.util.HashMap;
import java.util.Map;

public interface IProductManager {

    Map<Product, Integer> products = new HashMap<>();

    Product create(Product product, Integer quantity);

    Map<Product, Integer> getProducts();
}
