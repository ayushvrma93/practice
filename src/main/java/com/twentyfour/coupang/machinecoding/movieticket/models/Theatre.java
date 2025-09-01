package com.twentyfour.coupang.machinecoding.movieticket.models;

import lombok.Data;

import java.util.Map;

@Data
public class Theatre {

    private int id;
    private String name;
    private String address;
    Map<Long, Audi> audis;
}
