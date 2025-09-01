package com.twentyfour.coupang.machinecoding.movieticket.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Movie {
    private int id;
    private String name;
    private Long runtime;

    private List<Audi> audisList;

    private Timestamp startTime;

    private Timestamp endTime;
}
