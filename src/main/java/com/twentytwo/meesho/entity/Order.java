package com.twentytwo.meesho.entity;

import com.twentytwo.meesho.entity.enums.PaymentMode;

public class Order {

    private int id;
    private PaymentMode paymentMode;
    private int quantity;
    private Product product;


    public Order(int id, PaymentMode paymentMode, int quantity, Product product) {
        this.id = id;
        this.paymentMode = paymentMode;
        this.quantity = quantity;
        this.product = product;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", paymentMode=" + paymentMode +
                ", quantity=" + quantity +
                ", products=" + product +
                '}';
    }
}
