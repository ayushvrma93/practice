package com.twentyfour.coupang.coding;

import com.twentyfour.coupang.machinecoding.movieticket.exception.CustomException;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
class Movie{
    String name;
    List<Show> shows;

    public Movie(String name, List<Show> shows) {
        this.name = name;
        this.shows = shows;
    }
}


@Data
class Ticket{

    String movieName;
    Show show;

    List<Integer> seatNos;
}

@Data
class Show{
    String movieName;
    String startTime;
    String endTime;
    Integer totalSeats;
    Set<Integer> bookedSeats;

    public Show(String movieName, String startTime, String endTime, Integer totalSeats) {
        this.movieName = movieName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalSeats = totalSeats;
        this.bookedSeats = new HashSet<>();
    }

    public boolean isSeatValid(int id){
        return id >= 0 && id < totalSeats && !bookedSeats.contains(id);
    }
}

class MovieTicketService{

    List<Movie> movies;

    MovieTicketService(){
        this.movies = new ArrayList<>();
    }

    public void addMovies(Movie movie){
        this.movies.add(movie);
    }

    List<Movie> getMovies(){
        return this.movies;
    }

    public Movie find(Movie movie){
        return this.movies.stream().filter(m -> m.getName().equalsIgnoreCase(movie.getName())).findFirst().orElse(null);
    }

    public Ticket bookSeat(Show show, List<Integer> seats) throws CustomException {
        if(show.totalSeats - show.bookedSeats.size() < seats.size()){
            throw new CustomException("seats not available");
        }

        Ticket ticket = new Ticket();

        synchronized (show){
            for(Integer currSeat : seats){
                if(show.bookedSeats.contains(currSeat)){
                    throw new CustomException("seat already booked");
                }

                if(!show.isSeatValid(currSeat)){
                    throw new CustomException("invalid seat");
                }
            }

            for(Integer currSeat : seats){
                show.bookedSeats.add(currSeat);
            }
        }
        ticket.setShow(show);
        ticket.setSeatNos(seats);
        ticket.setMovieName(show.getMovieName());

        return ticket;
    }
}

public class MovieTicketBooking {


    public static void main(String[] args) {
        Show show1 = new Show("movie1", "", "", 5);
        Show show2 = new Show("movie1", "", "", 100);

        List<Show> shows = new ArrayList<>();
        shows.add(show1);
        shows.add(show2);

        Movie movie1 = new Movie("movie1", shows);
        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);

        MovieTicketService movieTicketService = new MovieTicketService();
        movieTicketService.addMovies(movie1);

        List<Integer> seatsToBook1 = new ArrayList<>();
        seatsToBook1.add(1);
        seatsToBook1.add(2);
        seatsToBook1.add(3);
        seatsToBook1.add(4);
        seatsToBook1.add(0);
//        seatsToBook1.add(6);

        List<Integer> seatsToBook2 = new ArrayList<>();
        seatsToBook2.add(99);
        seatsToBook2.add(12);

        try {
            System.out.println(movieTicketService.bookSeat(show1, seatsToBook1));
            System.out.println(movieTicketService.bookSeat(show2, seatsToBook2));
        } catch (CustomException e) {
            System.out.println("Could not book seats becaus of the following reason: " + e.getMessage());
        }


    }




}
