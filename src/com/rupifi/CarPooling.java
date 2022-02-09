package com.rupifi;

import java.util.Comparator;
import java.util.PriorityQueue;

public class CarPooling {

    class PassengerAndArrival{

        int passengers;
        int arrival;

        PassengerAndArrival(int passengers, int arrival){
            this.passengers = passengers;
            this.arrival = arrival;
        }

    }


    public boolean carPooling(int[][] trips, int capacity) {

        PriorityQueue<PassengerAndArrival> arrivalPq = new PriorityQueue<>(Comparator.comparingInt(o -> o.arrival));

        PriorityQueue<PassengerAndArrival> deptPq = new PriorityQueue<>(Comparator.comparingInt(o -> o.arrival));

        int length = trips.length;
        int currPassenger = 0;

        for(int i=0; i<length; i++){

            PassengerAndArrival arrivals = new PassengerAndArrival(trips[i][0], trips[i][1]);
            PassengerAndArrival depts = new PassengerAndArrival(trips[i][0], trips[i][2]);
            arrivalPq.add(arrivals);
            deptPq.add(depts);
        }

        while(!arrivalPq.isEmpty() && !deptPq.isEmpty()){

            int arrivalTime = arrivalPq.peek().arrival;
            int deptTime = deptPq.peek().arrival;

            if(arrivalTime < deptTime){
                currPassenger += arrivalPq.poll().passengers;
            }

            else {
                currPassenger -= deptPq.poll().passengers;
            }

            if(currPassenger > capacity) return false;
        }

        while(!arrivalPq.isEmpty()){
            currPassenger += arrivalPq.poll().passengers;
            if(currPassenger > capacity) return false;
        }
        return true;
    }
}
