package com.twentyfour.coupang.machinecoding.movieticket.models;

import com.twentyfour.coupang.machinecoding.movieticket.enums.SeatStatus;
import lombok.Data;

@Data
public class Seat {
    private int id;
    private int row;
    private int col;
    private SeatStatus status;
}
