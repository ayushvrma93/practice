package com.twentytwo.meesho.entity;

public class Product {

    private int id;
    private String name;
    private Double price;
    private int inventory;
    private Integer pickupAddress;


    public Product(int id, String name, Double price, Integer pickupAddress, int inventory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pickupAddress = pickupAddress;
        this.inventory = inventory;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(Integer pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", pickupAddress='" + pickupAddress + '\'' +
                '}';
    }
}
