package com.twentythree.mistifly;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MeetingRooms{

    static class Pair{

        public int start;
        public int end;

        Pair(int start, int end){
            this.start = start;
            this.end = end;
        }

        public String toString(){
            return "start= " + this.start + ", " + "end= " + this.end;
        }
    }

    public int getNoOfMeetingRooms(List<Pair> meetings){

        meetings.sort(Comparator.comparingInt(o -> o.start));

        int roomsRequired = 1;

        for(int i=0; i<meetings.size()-1; i++){

            if(meetings.get(i).end >= meetings.get(i+1).start){
                roomsRequired++;
            }
        }
        return roomsRequired;
    }

    public static void main(String[] args) {

        MeetingRooms meetingRooms = new MeetingRooms();
        Pair meeting1 = new Pair(1, 10);
        Pair meeting2 = new Pair(8, 10);
        Pair meeting3 = new Pair(11, 30);

        List<Pair> pairs = new ArrayList<>();
        pairs.add(meeting1);
        pairs.add(meeting2);
        pairs.add(meeting3);

        System.out.print(meetingRooms.getNoOfMeetingRooms(pairs));
    }
}
