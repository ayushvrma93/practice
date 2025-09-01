package com.twentyfour.coupang.machinecoding.movieticket.controller;

import com.twentyfour.coupang.machinecoding.movieticket.enums.SeatStatus;
import com.twentyfour.coupang.machinecoding.movieticket.exception.CustomException;
import com.twentyfour.coupang.machinecoding.movieticket.models.Audi;
import com.twentyfour.coupang.machinecoding.movieticket.models.Seat;
import com.twentyfour.coupang.machinecoding.movieticket.models.Theatre;
import com.twentyfour.coupang.machinecoding.movieticket.models.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookingController {

    TheatreController theatreController = TheatreController.getINSTANCE();
    private static int ticketId = 1;


    public Ticket book(int theatreId, int audiId, int movieId, String userPhone, List<Seat> seatsToBeBooked) throws CustomException {

        Theatre theatre = theatreController.get(theatreId);

        Map<Long, Audi> theatreAudis = theatre.getAudis();

        Audi audiByMovie = theatreController.getAudisByMovie(theatreId, movieId);

        Ticket ticket = new Ticket(theatre, movieId);
        List<Integer> seatIds = new ArrayList<>();

        synchronized (this){
            for(Seat currSeat : seatsToBeBooked){
                Seat currentAudiSeat = audiByMovie.getSeats().get(currSeat.getId());
                if(currentAudiSeat.getStatus().equals(SeatStatus.OCCUPIED)){
                    throw new CustomException("seat already occupied");
                }
            }

            for(Seat currSeat : seatsToBeBooked){
                Seat currentAudiSeat = audiByMovie.getSeats().get(currSeat.getId());
                currentAudiSeat.setStatus(SeatStatus.OCCUPIED);
                seatIds.add(currentAudiSeat.getId());

            }
            ticket.setSeatIds(seatIds);
            ticket.setId(ticketId++);
        }
        return ticket;
    }
}
