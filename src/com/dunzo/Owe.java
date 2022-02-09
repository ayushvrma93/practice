package com.dunzo;

class Owe{

    private String source;
    private String destination;
    private int amount;

    public Owe(String source, String destination, int amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Owe{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", amount=" + amount +
                '}';
    }
}
