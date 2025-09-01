package com.twentyfour.coupang.machinecoding.movieticket.controller;

import com.twentyfour.coupang.machinecoding.movieticket.exception.CustomException;
import com.twentyfour.coupang.machinecoding.movieticket.models.Audi;
import com.twentyfour.coupang.machinecoding.movieticket.models.Movie;
import com.twentyfour.coupang.machinecoding.movieticket.models.Theatre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {

    private static TheatreController INSTANCE = new TheatreController();

    private TheatreController() {}

    public static TheatreController getINSTANCE(){
        return INSTANCE;
    }

    private static int id = 1;
    Map<Integer, Theatre> theatreMap = new HashMap<>();

    public Theatre create(Theatre theatre) throws CustomException {
        if(theatreMap.containsKey(theatre.getId())){
            throw new CustomException("theatre already exists");
        }
        theatreMap.put(id++, theatre);
        return theatre;
    }

    public Theatre get(int id) throws CustomException {
        if(!theatreMap.containsKey(id)){
            throw new CustomException("theatre does not exists");
        }
        return theatreMap.get(id);
    }

    public List<Movie> getMovies(Long theatreId) throws CustomException {
        Theatre theatre = theatreMap.get(theatreId);
        List<Movie> currentMovies = new ArrayList<>();

        if(theatre == null){
            throw new CustomException("invalid theatre id");
        }

        Map<Long, Audi> audiMap = theatre.getAudis();

        for(Map.Entry<Long, Audi> entry : audiMap.entrySet()){
            currentMovies.add(entry.getValue().getMovie());
        }

        return currentMovies;
    }

    public Audi getAudisByMovie(int theatreId, int movieId){
        Theatre theatre = theatreMap.get(theatreId);
        return theatre.getAudis().values().stream().filter(m -> m.getMovie().getId() == movieId).findFirst().orElse(null);
    }
}
