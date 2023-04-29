package com.twentythree.cashfree;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CalendarMeetings {

    @AllArgsConstructor
    @ToString
    static
    class Pair{
        int start;
        int end;
    }

    private List<Pair> mergeSimilarStartTime(List<Pair> slots){

        slots.sort(Comparator.comparingInt(o->o.start));

        int i=0;
        int size = slots.size();
        int maxEndTime = -1;

        List<Pair> mergedList = new ArrayList<>();

        while(i< size){

            while(i < size-1 && slots.get(i).start == slots.get(i+1).start){
                maxEndTime = Math.max(maxEndTime, slots.get(i).end);
                i++;
            }
            maxEndTime = Math.max(maxEndTime, slots.get(i).end);

            Pair mergedPair = new Pair(slots.get(i).start, maxEndTime);
            mergedList.add(mergedPair);
            maxEndTime = -1;
            i++;
        }
        return mergedList;
    }

    private List<Pair> merge(List<Pair> sortedList){

        int i=0;
        List<Pair> mergedList = new ArrayList<>();

        while(i < sortedList.size()){

            int maxEnd = -1;
            int minStart = 100;
            boolean wasMergeable = false;

            while (i < sortedList.size()-1 && sortedList.get(i).end >= sortedList.get(i+1).start){
                minStart = Math.min(minStart, Math.min(sortedList.get(i).start, sortedList.get(i+1).start));
                maxEnd = Math.max(maxEnd, Math.max(sortedList.get(i).end, sortedList.get(i+1).end));
                wasMergeable = true;
                i++;
            }

            if(!wasMergeable){
                minStart = sortedList.get(i).start;
                maxEnd = sortedList.get(i).end;
            }

            Pair mergedPair = new Pair(minStart, maxEnd);
            mergedList.add(mergedPair);
            i++;
        }
        return mergedList;
    }

    private int correctWay(List<Pair> slots){

        //slots.sort(Comparator.comparingInt(o->o.start));
        slots.sort((o1, o2) -> {
            if(o1.start==o2.start)return o1.end-o2.end;
            return o1.start-o2.start;
        });
        int total=0;
        int min = slots.get(0).start;
        int max = slots.get(0).end;
        for(Pair pair : slots){

            if(max>pair.start && max>pair.end){continue;}
            else if(max>pair.start&&max<pair.end){
                max=pair.end;
            }else if(max<pair.start){
                total+=(max-min)+1;
                min = pair.start;
                max=pair.end;
            }
        }
        total+=(max-min)+1;
        return total;

    }

    public List<Pair> getEmptySlots(List<Pair> slots){
        List<Pair> mergedList =  mergeSimilarStartTime(slots);
        System.out.println("After merging with similar start time" + mergedList);
        mergedList = merge(mergedList);
        return mergedList;
    }

    public static void main(String[] args) {

        CalendarMeetings calendarMeetings = new CalendarMeetings();
        Pair pair1 = new Pair(2,4);
        Pair pair2 = new Pair(4,8);
        Pair pair3 = new Pair(3,4);
        Pair pair4 = new Pair(2,6);
        Pair pair5 = new Pair(5,10);
        Pair pair6 = new Pair(12,13);

        List<Pair> slots = new ArrayList<>();

        slots.add(pair1);
        slots.add(pair2);
        slots.add(pair3);
        slots.add(pair4);
        slots.add(pair5);
        slots.add(pair6);

        System.out.println(calendarMeetings.getEmptySlots(slots));
        System.out.println(calendarMeetings.correctWay(slots));
    }
}
