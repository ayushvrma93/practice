package com.twentyfour.coupang.machinecoding.movieticket.models;

import lombok.Data;

import java.util.List;

@Data
public class Ticket {

    private int id;
    private Theatre theatre;
    private int movieId;

    private List<Integer> seatIds;

    public Ticket(Theatre theatre, int movieId){
        this.theatre = theatre;
        this.movieId = movieId;
    }
}
