package com.twentyfour.coupang.machinecoding.movieticket.models;

import lombok.Data;

import java.util.List;

@Data
public class Audi {
    private Long id;
    private String name;
    private String TheatreId;
    private Movie movie;

    private List<Seat> seats;
}
