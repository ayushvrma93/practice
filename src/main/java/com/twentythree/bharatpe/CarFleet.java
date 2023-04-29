package com.twentythree.bharatpe;

import java.util.Arrays;
import java.util.Comparator;

public class CarFleet {

    class Pair{
        public int pos;
        public int speed;

        public Pair(int pos, int speed){
            this.pos = pos;
            this.speed = speed;
        }
    }

    public int carFleet(int target, int[] position, int[] speed) {

        int len = position.length;

        Pair[] pairs = new Pair[len];

        for(int i=0; i<position.length; i++){
            Pair newPair = new Pair(position[i], speed[i]);
            pairs[i] = newPair;
        }

        Arrays.sort(pairs, Comparator.comparingInt(o->o.pos));
        int fleetCount = len;

        for(int i=0; i<len-1; i++){

            int pos = pairs[i].pos + pairs[i].speed;
            int posNext = pairs[i+1].pos + pairs[i+1].speed;

            if(pos >= posNext && pos <= target && posNext <= target){
                fleetCount--;
                //i++;
            }
        }
        return fleetCount;
    }

    public static void main(String[] args) {

        CarFleet carFleet = new CarFleet();
        int[] position1 = {10,8,0,5,3};
        int[] speed1 = {10,8,0,5,3};
        int[] position2 = {0,2,4};
        int[] speed2 = {4,2,1};
        int[] position3 = {3};
        int[] speed3 = {3};
        System.out.println(carFleet.carFleet(12, position1, speed1));
        System.out.println(carFleet.carFleet(100, position2, speed2));
        System.out.println(carFleet.carFleet(10, position3, speed3));
    }
}
